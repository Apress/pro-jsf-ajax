package com.apress.projsf.ch7.validate;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * DateValidator checks to see if a Date value is available, according
 * to a managed bean method binding.
 */
public class DateValidator implements Validator
{
  /**
   * Validates the object value to make sure it is a Date and available.
   *
   * @param context     the Faces context
   * @param component   the Faces component
   * @param object      the object to validate
   */
  public void validate(
    FacesContext context,
    UIComponent  component,
    Object       object)
  {
    if (_availability != null)
    {

      //Conversion has succeeded to date.
      Date date = (Date)object;
      long millis = date.getTime();
      long millisPerDay = 1000 * 60 * 60 * 24;
      Integer days = new Integer((int)(millis / millisPerDay));
      Object[] args = new Object[] {days, days};
      //Call the backing bean. args need to be in the same order as in the tag.
      //part of a contract.
      boolean[] result = (boolean[])_availability.invoke(context, args);
      if (!result[0])
      {
        FacesMessage message = new FacesMessage("Date is unavailable");
        throw new ValidatorException(message);
      }
    }
  }

  /**
   * Sets the available days method binding with signature (int, int)
   * returns boolean[], indicating availablilty for each day in
   * range (inclusive) since Jan 1, 1970.
   *
   * @param availability  the available days method binding
   */
  public void setAvailability(
    MethodBinding availability)
  {
    _availability = availability;
  }

  /**
   * Returns the available days method binding with signature (int, int)
   * returns boolean[], indicating availablilty for each day in
   * range (inclusive) since Jan 1, 1970.
   *
   * @return the availability method binding
   */
  public MethodBinding getAvailability()
  {
    return _availability;
  }

  private MethodBinding _availability;
}
