package com.apress.projsf.ch6.render;

import java.io.IOException;
import java.io.Writer;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

/**
 * ResponseWriterWrapper implements the decorator pattern
 * for ResponseWriter.
 */
public class ResponseWriterWrapper extends ResponseWriter
{
  /**
   * Creates a new ResponseWriterWrapper.
   * 
   * @param delegate  the ResponseWriter delegate
   */
  public ResponseWriterWrapper(
    ResponseWriter delegate)
  {
    _delegate = delegate;
  }

  /**
   * Returns the character encoding for this ResponseWriter.
   * 
   * @return  the character encoding for this ResponseWriter
   */
  public String getCharacterEncoding()
  {
    return _delegate.getCharacterEncoding();
  }

  /**
   * Returns the content type for this ResponseWriter.
   * 
   * @return  the content type for this ResponseWriter
   */
  public String getContentType()
  {
    return _delegate.getContentType();
  }

  /**
   * Starts the response document.
   */
  public void startDocument() throws IOException
  {
    _delegate.startDocument();
  }

  /**
   * Ends the response document.
   */
  public void endDocument() throws IOException
  {
    _delegate.endDocument();
  }

  /**
   * Starts an element in the response.
   * 
   * @param name       the element name
   * @param component  the Faces component owner
   */
  public void startElement(
    String      name,
    UIComponent component) throws IOException
  {
    _delegate.startElement(name, component);
  }

  /**
   * Writes an attribute to the response.
   * 
   * @param name      the attribute name
   * @param value     the attribute value
   * @param attrName  the Faces component attribute name
   */
  public void writeAttribute(
    String name,
    Object value,
    String attrName) throws IOException
  {
    _delegate.writeAttribute(name, value, attrName);
  }

  /**
   * Writes a comment to the response.
   * 
   * @param comment   the comment value
   */
  public void writeComment(
    Object comment) throws IOException
  {
    _delegate.writeComment(comment);
  }

  /**
   * Writes some text to the response.
   * 
   * @param buffer  the text buffer
   * @param offset  the text buffer offset
   * @param length  the number of chars to write
   */
  public void writeText(
    char[] buffer,
    int    offset,
    int    length) throws IOException
  {
    _delegate.writeText(buffer, offset, length);
  }

  /**
   * Writes some text to the response.
   * 
   * @param text      the text to write
   * @param attrName  the Faces component attribute name
   */
  public void writeText(
    Object text,
    String attrName) throws IOException
  {
    _delegate.writeText(text, attrName);
  }

  /**
   * Writes a URI attribute to the response.
   * 
   * @param name      the attribute name
   * @param value     the attribute value
   * @param attrName  the Faces component attribute name
   */
  public void writeURIAttribute(
    String name,
    Object value,
    String attrName) throws IOException
  {
    _delegate.writeURIAttribute(name, value, attrName);
  }

  /**
   * Ends an element in the response.
   * 
   * @param name       the element name
   */
  public void endElement(
    String name) throws IOException
  {
    _delegate.endElement(name);
  }

  /**
   * Clones this ResponseWriter.
   * 
   * @param writer  the new writer
   * 
   * @return  the newly cloned ResponseWriter
   */
  final public ResponseWriter cloneWithWriter(
    Writer writer)
  {
    return cloneWithWriter(_delegate, writer);
  }

  /**
   * Writes the character buffer, starting at offset, for length characters.
   * 
   * @param cbuf  the character buffer
   * @param off   the buffer offset
   * @param len   the number of characters to write
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void write(
    char[] cbuf,
    int    off,
    int    len) throws IOException
  {
    _delegate.write(cbuf, off, len);
  }

  /**
   * Writes the character buffer.
   * 
   * @param cbuf  the character buffer
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void write(
    char[] cbuf) throws IOException
  {
    _delegate.write(cbuf);
  }

  /**
   * Writes the character.
   * 
   * @param c  the character
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void write(
    int c) throws IOException
  {
    _delegate.write(c);
  }

  /**
   * Writes the string.
   * 
   * @param str  the string
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void write(
    String str) throws IOException
  {
    _delegate.write(str);
  }

  /**
   * Writes the string, starting at offset, for length characters.
   * 
   * @param str  the string
   * @param off  the string offset
   * @param len  the number of characters to write
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void write(
    String str,
    int    off,
    int    len) throws IOException
  {
    _delegate.write(str, off, len);
  }

  /**
   * Closes the writer.
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void close() throws IOException
  {
    _delegate.close();
  }

  /**
   * Flushes the writer.
   * 
   * @throws IOException  if an I/O error occurs
   */
  public void flush() throws IOException
  {
    _delegate.flush();
  }

  /**
   * Clones this ResponseWriter using the specified delegate ResponseWriter
   * and new writer.
   * 
   * @param delegate  the ResponseWriter delegate
   * @param writer    the new writer
   * 
   * @return  the newly cloned ResponseWriter
   */
  protected ResponseWriter cloneWithWriter(
    ResponseWriter delegate,
    Writer         writer)
  {
    return new ResponseWriterWrapper(delegate.cloneWithWriter(writer));
  }

  private final ResponseWriter _delegate;
}