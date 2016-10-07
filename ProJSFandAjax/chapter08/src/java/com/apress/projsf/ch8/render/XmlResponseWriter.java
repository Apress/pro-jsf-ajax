package com.apress.projsf.ch8.render;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

public class XmlResponseWriter extends ResponseWriter
{
  public XmlResponseWriter(
    Writer writer,
    String encoding)
  {
    this(writer, encoding, "application/xml");
  }

  public XmlResponseWriter(
    Writer writer,
    String encoding,
    String contentType)
  {
    this(writer, encoding, contentType, null);
  }

  public XmlResponseWriter(
    Writer writer,
    String encoding,
    String contentType,
    String documentType)
  {
    this(contentType, documentType, writer, encoding, (documentType != null));
  }
  
  private XmlResponseWriter(
    String  contentType,
    String  documentType,
    Writer  writer,
    String  encoding,
    boolean verboseEmptyElements)
  {
    _out = new PrintWriter(writer);
    _contentType = contentType;
    _documentType = documentType;
    _encoding = encoding;
    _verboseEmptyElements = verboseEmptyElements;
  }

  public String getCharacterEncoding()
  {
    return _encoding;
  }

  public String getContentType()
  {
    return _contentType;
  }
  
  public void setVerboseEmptyElements(
    boolean verboseEmptyElements)
  {
    _verboseEmptyElements = verboseEmptyElements;
  }
  
  public void startDocument() throws IOException
  {
    if (_documentType != null)
    {
      _out.write("<!DOCTYPE "); 
      _out.write(_documentType);
      _out.write(" >\n");
    }
    else
    {
      String charset = this.getCharacterEncoding();
      if (charset != null)
      {
        _out.write("<?xml version=\"1.0\" ");
        _out.write("encoding=\"");
        _out.write(charset);
        _out.write("\" ?>\n");
      }
      else
      {
        _out.write("<?xml version=\"1.0\" ?>\n");
      }
    }
  }

  public void endDocument() throws IOException
  {
  }

  public void startElement(
    String      name,
    UIComponent component) throws IOException
  {
    closeStartIfNecessary();

    PrintWriter out = _out;
    out.write('<');
    out.write(name);
    _closeStart = true;
  }

  public void writeAttribute(
    String name,
    Object value,
    String attrName) throws IOException
  {
    if (value == null)
      return;

    PrintWriter out = _out;
    // write the attribute value
    out.write(' ');
    out.write(name);
    out.write("=\"");
    writeAttributeText(value, attrName);
    out.write("\"");
  }

  public void writeComment(
    Object comment) throws IOException
  {
    if (comment != null)
    {
      closeStartIfNecessary();

      PrintWriter out = _out;
      out.write("<!-- ");
      out.write(comment.toString());
      out.write(" -->");
    }
  }

  public void writeText(
    char[] buffer,
    int    offset,
    int    length) throws IOException
  {
    if (buffer != null)
    {
      closeStartIfNecessary();
      _writeBodyText(buffer, offset, length);
    }
  }

  public void writeText(
    Object text,
    String attrName) throws IOException
  {
    if (text != null)
    {
      closeStartIfNecessary();
      writeBodyText(text, attrName);
    }
  }

  public void writeURIAttribute(
    String name,
    Object value,
    String attrName) throws IOException
  {
    writeAttribute(name, value, attrName);
  }

  public void endElement(
    String name) throws IOException
  {
    PrintWriter out = _out;

    if (_verboseEmptyElements)
      closeStartIfNecessary();

    if (_closeStart)
    {
      out.write("/>");
      _closeStart = false;
    }
    else
    {
      out.write("</");
      out.write(name);
      out.write(">");
    }
  }

  public ResponseWriter cloneWithWriter(
    Writer writer)
  {
    return new XmlResponseWriter(_contentType, _documentType, 
                                 writer, _encoding, _verboseEmptyElements);
  }

  public void write(
    char[] cbuf,
    int    off,
    int    len) throws IOException
  {
    closeStartIfNecessary();
    _out.write(cbuf, off, len);
  }

  public void write(char[] cbuf) throws IOException
  {
    closeStartIfNecessary();
    _out.write(cbuf);
  }

  public void write(
    int c) throws IOException
  {
    closeStartIfNecessary();
    _out.write(c);
  }

  public void write(
    String str) throws IOException
  {
    closeStartIfNecessary();
    _out.write(str);
  }

  public void write(
    String str, 
    int    off, 
    int    len) throws IOException
  {
    closeStartIfNecessary();
    _out.write(str, off, len);
  }

  public void close() throws IOException
  {
    _out.close();
  }

  public void flush() throws IOException
  {
    _out.flush();
  }
  
  protected void writeAttributeText(
    Object text,
    String attrName) throws IOException
  {
    char[] buffer = text.toString().toCharArray();
    _writeAttributeText(buffer, 0, buffer.length);
  }

  protected void writeBodyText(
    Object text,
    String attrName) throws IOException
  {
    char[] buffer = text.toString().toCharArray();
    _writeBodyText(buffer, 0, buffer.length);
  }

  protected void closeStartIfNecessary() throws IOException
  {
    if (_closeStart)
    {
      PrintWriter out = _out;
      out.write('>');
      _closeStart = false;
    }
  }

  private void _writeAttributeText(
    char[]  text,
    int     start,
    int     length) throws IOException
  {
    Writer out = _out;
    int end = start + length;
    for (int i = start; i < end; i++)
    {
      char ch = text[i];

      if (ch <= 0x7f)
      {
        switch (ch)
        {
          case '>':
            out.write("&gt;");
            break;
          case '<':
            out.write("&lt;");
            break;
          case '"':
            out.write("&quot;");
            break;
          case '&':
            out.write("&amp;");
            break;
          default:
            out.write(ch);
            break;
        }
      }
      else
      {
        _writeHexRef(ch);
      }
    }
  }

  private void _writeBodyText(
    char[]  text,
    int     start,
    int     length) throws IOException
  {
    Writer out = _out;
    int end = start + length;
    for (int i = start; i < end; i++)
    {
      char ch = text[i];

      if (ch <= 0x7f)
      {
        switch (ch)
        {
          case '>':
            out.write("&gt;");
            break;
          case '<':
            out.write("&lt;");
            break;
          case '&':
            out.write("&amp;");
            break;
          default:
            out.write(ch);
            break;
        }
      }
      else
      {
        _writeHexRef(ch);
      }
    }
  }

  private void _writeHexRef(
    char ch) throws IOException
  {
    Writer out = _out;
    out.write("&#x");
    out.write(Integer.toHexString(ch));
    out.write(';');
  }
  
  private final PrintWriter _out;
  private final String      _contentType;
  private final String      _documentType;
  private final String      _encoding;
  private       boolean     _closeStart;
  private       boolean     _verboseEmptyElements;
}