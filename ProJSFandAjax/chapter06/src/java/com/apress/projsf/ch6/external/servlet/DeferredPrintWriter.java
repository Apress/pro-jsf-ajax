package com.apress.projsf.ch6.external.servlet;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * DeferredPrintWriter provides a callback when the first characters
 * are written to the writer.
 */
public class DeferredPrintWriter extends PrintWriter
{
  /**
   * Creates a new DeferredPrintWriter.
   * 
   * @param delegate  the PrintWriter delegate
   * @param response  the callback target
   */
  public DeferredPrintWriter(
    PrintWriter                 delegate,
    DeferredContentTypeResponse response)
  {
    super(delegate);
    _response = response;
  }
  
  /**
   * Ensures that the callback has completed before calling flush() on
   * the PrintWriter delegate.
   */
  public void flush()
  {
    if (!_committed)
      _handleCommit();
    super.flush();
  }

  /**
   * Ensures that the callback has completed before calling close() on
   * the PrintWriter delegate.
   */
  public void close()
  {
    if (!_committed)
      _handleCommit();
    super.close();
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the PrintWriter delegate.
   */
  public void write(
    char[] buf)
  {
    if (!_committed)
      _handleCommit();
    super.write(buf);
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the PrintWriter delegate.
   */
  public void write(
    char[] buf, 
    int    off, 
    int    len)
  {
    if (!_committed)
      _handleCommit();
    super.write(buf, off, len);
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the PrintWriter delegate.
   */
  public void write(
    int c)
  {
    if (!_committed)
      _handleCommit();
    super.write(c);
  }
  
  /**
   * Ensures that the callback has completed before calling write() on
   * the PrintWriter delegate.
   */
  public void write(
    String buf, 
    int    off, 
    int    len)
  {
    if (!_committed)
      _handleCommit();
    super.write(buf, off, len);
  }

  /**
   * Ensures that the callback has completed before calling write() on
   * the PrintWriter delegate.
   */
  public void write(
    String buf)
  {
    if (!_committed)
      _handleCommit();
    super.write(buf);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    boolean value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    char value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    double value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    float value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    int value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    long value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    char[] buf)
  {
    if (!_committed)
      _handleCommit();
    super.print(buf);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    String value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling print() on
   * the PrintWriter delegate.
   */
  public void print(
    Object value)
  {
    if (!_committed)
      _handleCommit();
    super.print(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println()
  {
    if (!_committed)
      _handleCommit();
    super.println();
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    boolean value)
  {
    if (!_committed)
      _handleCommit();
    super.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    char value)
  {
    if (!_committed)
      _handleCommit();
    super.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    double value)
  {
    if (!_committed)
      _handleCommit();
    super.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    float value)
  {
    if (!_committed)
      _handleCommit();
    super.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    int value)
  {
    if (!_committed)
      _handleCommit();
    super.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    long value)
  {
    if (!_committed)
      _handleCommit();
    super.println(value);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    char[] buf)
  {
    if (!_committed)
      _handleCommit();
    super.println(buf);
  }

  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    String s)
  {
    if (!_committed)
      _handleCommit();
    super.println(s);
  }
  
  /**
   * Ensures that the callback has completed before calling println() on
   * the PrintWriter delegate.
   */
  public void println(
    Object o)
  {
    if (!_committed)
      _handleCommit();
    super.println(o);
  }

  /**
   * The _handleCommit() method is called only once, when 
   * the first write(), print(), println(), flush(), or close() call 
   * is made to this PrintWriter.
   */
  private void _handleCommit()
  {
    try
    {
      _committed = true;
      _response.onCommit();
    }
    catch (IOException e)
    {
      setError();
    }
  }

  private final DeferredContentTypeResponse _response;
  private       boolean                     _committed;
}