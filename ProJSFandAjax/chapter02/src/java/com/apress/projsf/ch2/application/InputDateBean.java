package com.apress.projsf.ch2.application;

import java.util.Date;

/**
 * InputDateBean is a backing bean for inputDate.jspx document.
 */
public class InputDateBean
{
  /**
   * Returns the previously stored Date, defaulting to today.
   *
   * @return  the date property value
   */
  public Date getDate()
  {
    return _date;
  }

  /**
   * Sets the new date property value.
   *
   * @param date  the new date property value
   */
  public void setDate(
    Date date)
  {
    _date = date;
  }

  // initialize to today's date
  private Date _date = new Date();
}