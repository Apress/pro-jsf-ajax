package com.apress.projsf.ch6.external.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * DeferredContentTypeResponse manages setting the Faces-major content type.
 */
public class DeferredContentTypeResponse extends HttpServletResponseWrapper
{
  /**
   * Creates a new DeferredContentTypeResponse.
   * 
   * @param delegate  the HttpServletResponse delegate
   */
  public DeferredContentTypeResponse(
    HttpServletResponse delegate)
  {
    super(delegate);
  }

  /**
   * Attempt to set the content type as deferred.
   * 
   * @param contentTypeAndCharset  the content type and character set 
   *                               for this response
   */
  public void setContentType(
    String contentTypeAndCharset)
  {
    Matcher matcher = _CONTENT_TYPE_PATTERN.matcher(contentTypeAndCharset);
    if (matcher.matches())
    {
      String contentType = matcher.group(1);
      String charset = (matcher.groupCount() > 1) ? matcher.group(2) : null; 

      // remember _isFacesMajor for later, during onCommit, 
      // after Faces ResponseWriter has been created
      _isFacesMajor = _isFacesMajorContentType(contentType);

      if (_isFacesMajor)
      {
        // although we'll set the content type on onCommit,
        // we need to set the charset now
        // <f:view> will need charset when creating the ResponseWriter
        super.setCharacterEncoding(charset);
      }
      else
      {
        // content type will not be set on onCommit,
        // so set both content type and charset now
        super.setContentType(contentTypeAndCharset);
      }
    }
  }

  /**
   * Returns the servlet output stream for this response.
   * 
   * @return  the byte-based servlet output stream
   * 
   * @throws IOException  if an I/O error occurs
   */
  public ServletOutputStream getOutputStream() throws IOException
  {
    if (_out == null)
    {
      ServletOutputStream out = super.getOutputStream();
      _out = new DeferredServletOutputStream(out, this);
    }
    return _out;
  }

  /**
   * Returns the print writer for this response.
   * 
   * @return  the char-based print writer
   * 
   * @throws IOException  if an I/O error occurs
   */
  public PrintWriter getWriter() throws IOException
  {
    if (_writer == null)
    {
      PrintWriter writer = super.getWriter();
      _writer = new DeferredPrintWriter(writer, this);
    }
    return _writer;
  }

  /**
   * Callback method to be invoked when first bytes are written by
   * the servlet output stream, or when first chars are written by
   * the print writer.
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void onCommit() throws IOException
  {
    if (_isFacesMajor)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      ResponseWriter out = context.getResponseWriter();
      String contentType = out.getContentType();
      
      // set real content type via super.setContentType
      super.setContentType(contentType);
    }
  }

  /**
   * Returns true if the specified content type 
   * matches "application/x-javaserver-faces".
   * 
   * @param contentType  the response content type
   * 
   * @return true if the content type is "application/x-javaserver-faces"
   */
  private boolean _isFacesMajorContentType(
    String contentType)
  {
    return ("application/x-javaserver-faces".equals(contentType));
  }

  private ServletOutputStream _out;
  private PrintWriter         _writer;
  private boolean             _isFacesMajor;

  static private final Pattern _CONTENT_TYPE_PATTERN = 
                                  Pattern.compile("([^;]+)(?:;charset=(.*))?");
}
