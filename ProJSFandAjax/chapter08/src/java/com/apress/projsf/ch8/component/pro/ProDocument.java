package com.apress.projsf.ch8.component.pro;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.apress.projsf.ch8.component.UIDocument;

/**
 * The ProDocument component.
 */
public class ProDocument extends UIDocument
{
  /**
   * The component type for this component.
   */
  public static final String COMPONENT_TYPE = "com.apress.projsf.ProDocument";

  /**
   * The renderer type for this component.
   */
  public static final String RENDERER_TYPE = "com.apress.projsf.Document";

  /**
   * Creates a new ProDocument.
   */
  public ProDocument()
  {
    setRendererType(RENDERER_TYPE);
  }

  /**
   * Sets the CSS style class.
   *
   * @param styleClass  the new style class
   */
  public void setStyleClass(
    String styleClass)
  {
    _styleClass = styleClass;
  }

  /**
   * Returns the CSS style class.
   *
   * @return  the style class
   */
  public String getStyleClass()
  {
    if (_styleClass != null)
      return _styleClass;

    ValueBinding binding = getValueBinding("styleClass");
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
   * @param title  the title attribute value
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
   * Sets the stylesheetURI attribute value.
   * 
   * @param stylesheetURI  the stylesheetURI attribute value
   */
  public void setStylesheetURI(
    String stylesheetURI)
  {
    _stylesheetURI = stylesheetURI;
  }

  /**
   * Returns the title attribute value.
   * 
   * @return  the title attribute value
   */
  public String getStylesheetURI()
  {
    if (_stylesheetURI != null)
      return _stylesheetURI;
      
    ValueBinding binding = getValueBinding("stylesheetURI");
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
    Object values[] = new Object[4];
    values[0] = super.saveState(context);
    values[1] = _title;
    values[2] = _styleClass;
    values[3] = _stylesheetURI;
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
    _title = (String)values[1];
    _styleClass = (String)values[2];
    _stylesheetURI = (String)values[3];
  }
  
  /**
   * The stylesheetURI attribute.
   */
  private String _stylesheetURI;

  /**
   * The styleClass attribute value.
   */
  private String _styleClass;

  /**
   * The title attribute.
   */
  private String _title;
}
