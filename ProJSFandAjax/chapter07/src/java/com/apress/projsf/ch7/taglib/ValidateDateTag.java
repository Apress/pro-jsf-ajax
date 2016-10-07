package com.apress.projsf.ch7.taglib;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.validator.Validator;
import javax.faces.webapp.ValidatorTag;

import javax.servlet.jsp.JspException;

import com.apress.projsf.ch3.event.ShowListener;
import com.apress.projsf.ch7.validate.DateValidator;

/**
 * ValidateDateTag listener tag handler.
 */
public class ValidateDateTag extends ValidatorTag
{
  /**
   * Sets the availability method binding with signature (int, int)
   * returns boolean[], indicating availablilty for each day in
   * range (inclusive) since Jan 1, 1970.
   *
   * @param availability  the availability method binding
   */
  public void setAvailability(
    String availability)
  {
    this.availability = availability;
  }

  /**
   * Create and return a new {@link ShowListener} to be registered
   * on the surrounding {@link UIComponent}.
   *
   * @throws JspException  if a new listener instance cannot be created
   */
  protected Validator createValidator() throws JspException
  {
    DateValidator validator = new DateValidator();

    if (availability != null)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      Application application = context.getApplication();
      MethodBinding binding = application.createMethodBinding(availability,
                                                              new Class[]
                                                              {
                                                                int.class,
                                                                int.class
                                                              });
      validator.setAvailability(binding);
    }

    return validator;
  }

  private String availability;
}
