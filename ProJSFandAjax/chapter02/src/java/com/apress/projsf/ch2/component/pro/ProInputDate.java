package com.apress.projsf.ch2.component.pro;

import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

/**
 * The ProInputDate component.
 */
public class ProInputDate extends UIInput
{
  /**
   * The component type for this component.
   */
  public static final String COMPONENT_TYPE = "com.apress.projsf.ProInputDate";

  /**
   * The renderer type for this component.
   */
  public static final String RENDERER_TYPE = "com.apress.projsf.Date";

  /**
   * Creates a new ProInputDate.
   */
  public ProInputDate()
  {
    setRendererType(RENDERER_TYPE);
  }

  /**
   * Sets the onchange attribute value.
   *
   * @param onchange  the new onchange attribute value
   */
  public void setOnchange(
    String onchange)
  {
    _onchange = onchange;
  }

  /**
   * Returns the onchange attribute value.
   *
   * @return  the onchange attribute value
   */
  public String getOnchange()
  {
    if (_onchange != null)
      return _onchange;

    ValueBinding binding = getValueBinding("onchange");
    if (binding != null)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      return (String)binding.getValue(context);
    }

    return null;
  }

  /**
   * Sets the title attribute value.
   *
   * @param title  the new title attribute value
   */
  public void setTitle(
    String title)
  {
    _title = title;
  }

  /**
   * Returns the title attribute value.
   *
   * @return  the title attribute value
   */
  public String getTitle()
  {
    if (_title != null)
      return _title;

    ValueBinding binding = getValueBinding("title");
    if (binding != null)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      return (String)binding.getValue(context);
    }

    return null;
  }

  /**
   * Returns the saved state for this component.
   *
   * @param context the Faces context
   */
  public Object saveState(
    FacesContext context)
  {
    Object values[] = new Object[3];
    values[0] = super.saveState(context);
    values[1] = _onchange;
    values[2] = _title;
    return values;
  }

  /**
   * Restores the state of this component.
   *
   * @param context the Faces context
   * @param state   the saved state
   */
  public void restoreState(
    FacesContext context,
    Object       state)
  {
    Object values[] = (Object[])state;
    super.restoreState(context, values[0]);
    _onchange = (String)values[1];
    _title = (String)values[2];
  }

  /**
   * The onchange attribute value.
   */
  private String _onchange;

  /**
   * The title attribute value.
   */
  private String _title;
}
