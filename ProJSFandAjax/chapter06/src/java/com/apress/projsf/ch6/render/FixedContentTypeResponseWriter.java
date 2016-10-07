package com.apress.projsf.ch6.render;

import javax.faces.context.ResponseWriter;

/**
 * FixedContentTypeResponseWriter is used to override the content type
 * when delivering the response.
 */
public class FixedContentTypeResponseWriter extends ResponseWriterWrapper
{
  /**
   * Creates a new FixedContentTypeResponseWriter.
   * 
   * @param delegate     the ResponseWriter delegate
   * @param contentType  the fixed content type to be used
   */
  public FixedContentTypeResponseWriter(
    ResponseWriter delegate,
    String         contentType)
  {
    super(delegate);
    _contentType = contentType;
  }

  /**
   * Returns the fixed content type for this ResponseWriter.
   * 
   * @return  the fixed content type
   */
  public String getContentType()
  {
    return _contentType;
  }

  private final String _contentType;
}
