package com.apress.projsf.ch7.application;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

  public boolean[] getAvailability(
    int startDays, 
    int endDays)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("GMT"));

    // calculate the total number of days in the range (inclusive)
    int totalDays = endDays - startDays + 1;
    long millisPerDay = 1000 * 60 * 60 * 24;
    long startMillis = startDays * millisPerDay;
    long endMillis = endDays * millisPerDay;

    cal.setTimeInMillis(startMillis);
    System.out.println(cal.getTime());
    cal.setTimeInMillis(endMillis);
    System.out.println(cal.getTime());


    // by default, every day is available    
    boolean[] availability = new boolean[totalDays];
    Arrays.fill(availability, true);
  
    // make Saturdays and Sundays unavailable
    cal.setTimeInMillis(startMillis);
    for (int i=0; i < totalDays; i++)
    {
      // Saturdays and Sundays are unavailable
      int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek == Calendar.SATURDAY || 
          dayOfWeek == Calendar.SUNDAY)
      {
        availability[i] = false;
      }

      cal.add(Calendar.DAY_OF_YEAR, 1);
    }

    // make holidays unavailable too!
    int daysProcessed = 0;
    cal.setTimeInMillis(startMillis);
    while (cal.getTimeInMillis() <= endMillis)
    {
      int month = cal.get(Calendar.MONTH);
      int day = cal.get(Calendar.DAY_OF_MONTH);
      int holidays[] = _HOLIDAYS[month];
      for (int i=0; i < holidays.length; i++)
      {
        int holiday = holidays[i];
        int offset = daysProcessed + holiday - day;
        if (offset >= 0 && offset < totalDays)
          availability[offset] = false;
      }
      
      daysProcessed += (cal.getActualMaximum(Calendar.DAY_OF_MONTH) - day + 1);

      cal.add(Calendar.MONTH, 1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
    }
    
    return availability;
  }
  
  // initialize to today's date
  private Date _date = new Date();

  static private int[][] _HOLIDAYS = 
    new int[][]
    {
      new int[] {1},  // Jan
      new int[] {},   // Feb
      new int[] {},   // Mar
      new int[] {},   // Apr
      new int[] {},   // May
      new int[] {},   // Jun
      new int[] {4},  // Jul
      new int[] {},   // Aug
      new int[] {},   // Sep
      new int[] {},   // Oct
      new int[] {27}, // Nov
      new int[] {25,26}  // Dec
    };
}