package com.apress.projsf.ch6.external.servlet;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

/**
 * DeferredServletOutputStream provides a callback when the first bytes
 * are written to the output stream.
 */
public class DeferredServletOutputStream extends ServletOutputStream
{
    /**
     * Creates a new DeferredServletOutputStream.
     * 
     * @param delegate  the ServletOutputStream delegate
     * @param response  the callback target
     */
  public DeferredServletOutputStream(
    ServletOutputStream         delegate,
    DeferredContentTypeResponse response)
  {
    _delegate = delegate;
    _response = response;
  }
  
  /**
   * Ensures that the callback has completed before calling flush() on
   * the ServletOutputStream delegate.
   */
  public void flush() throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.flush();
  }

  /**
   * Ensures that the callback has completed before calling close() on
   * the ServletOutputStream delegate.
   */
  public void close() throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.close();
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the ServletOutputStream delegate.
   */
  public void write(
    byte[] b) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.write(b);
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the ServletOutputStream delegate.
   */
  public void write(
    byte[] b, 
    int    off, 
    int    len) throws IOException
  {
    // Note: this method is called first in my test environment
    if (!_committed)
      _handleCommit();
    _delegate.write(b, off, len);
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the ServletOutputStream delegate.
   */
  public void write(
    int b) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.write(b);
  }
  
  /**
   * Ensures that the callback has completed before calling write() on
   * the ServletOutputStream delegate.
   */
  public void print(
    boolean value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the ServletOutputStream delegate.
   */
  public void print(
    char value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the ServletOutputStream delegate.
   */
  public void print(
    double value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the ServletOutputStream delegate.
   */
  public void print(
    float value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the ServletOutputStream delegate.
   */
  public void print(
    int value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the ServletOutputStream delegate.
   */
  public void print(
    long value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the ServletOutputStream delegate.
   */
  public void print(
    String value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.print(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println() throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println();
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    boolean value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    char value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    double value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    float value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    int value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    long value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the ServletOutputStream delegate.
   */
  public void println(
    String value) throws IOException
  {
    if (!_committed)
      _handleCommit();
    _delegate.println(value);
  }
  
  /**
   * The _handleCommit() method is called only once, when 
   * the first write(), print(), println(), flush(), or close() call 
   * is made to this ServletOutputStream.
   */
  private void _handleCommit() throws IOException
  {
    _committed = true;
    _response.onCommit();
  }

  private final ServletOutputStream         _delegate;
  private final DeferredContentTypeResponse _response;
  private       boolean                     _committed;
}