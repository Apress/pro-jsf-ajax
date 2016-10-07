package com.apress.projsf.ch3.component.pro;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.apress.projsf.ch3.component.UIShowOne;

/**
 * The ProShowOneDeck renderer-specific component.
 */
public class ProShowOneDeck extends UIShowOne
{
  /**
   * The component type for this component.
   */
  public static final String COMPONENT_TYPE = "com.apress.projsf.ProShowOneDeck";

  /**
   * The renderer type for this component.
   */
  public static final String RENDERER_TYPE = "com.apress.projsf.Deck";

  /**
   * Creates a new ProShowOneDeck.
   */
  public ProShowOneDeck()
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
   * Sets the CSS item style class.
   *
   * @param itemStyleClass  the new item style class
   */
  public void setItemStyleClass(
    String itemStyleClass)
  {
    _itemStyleClass = itemStyleClass;
  }
  
  /**
   * Returns the CSS item style class.
   * 
   * @return  the item style class
   */
  public String getItemStyleClass()
  {
    if (_itemStyleClass != null)
      return _itemStyleClass;
  
    ValueBinding binding = getValueBinding("itemStyleClass");
    if (binding != null)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      return (String)binding.getValue(context);
    }

    return null;
  }
  
  /**
   * Sets the CSS style class for the item header facet.
   * 
   * @param itemHeaderStyleClass  the new style class for the item header facet
   */
  public void setItemHeaderStyleClass(
    String itemHeaderStyleClass)
  {
    _itemHeaderStyleClass = itemHeaderStyleClass;
  }
  
  /**
   * Returns the CSS style class for the header facet.
   * 
   * @return  the style class for the header facet
   */
  public String getItemHeaderStyleClass()
  {
    if (_itemHeaderStyleClass != null)
      return _itemHeaderStyleClass;

    ValueBinding binding = getValueBinding("itemHeaderStyleClass");
    if (binding != null)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      return (String)binding.getValue(context);
    }

    return null;
  }
  
  /**
   * Sets the CSS style class for the item content.
   * 
   * @param itemContentStyleClass  the new style class for the item content
   */
  public void setItemContentStyleClass(
    String itemContentStyleClass)
  {
    _itemContentStyleClass = itemContentStyleClass;
  }
  
  /**
   * Returns the CSS style class for the item content.
   * 
   * @return  the style class for the item content
   */
  public String getItemContentStyleClass()
  {
    if (_itemContentStyleClass != null)
      return _itemContentStyleClass;

    ValueBinding binding = getValueBinding("contentStyleClass");
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
    Object values[] = new Object[5];
    values[0] = super.saveState(context);
    values[1] = _styleClass;
    values[2] = _itemStyleClass;
    values[3] = _itemHeaderStyleClass;
    values[4] = _itemContentStyleClass;
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
    _styleClass = (String)values[1];
    _itemStyleClass = (String)values[2];
    _itemHeaderStyleClass = (String)values[3];
    _itemContentStyleClass = (String)values[4];
  }

  /**
   * The styleClass attribute value.
   */
  private String _styleClass;

  /**
   * The itemStyleClass attribute value.
   */
  private String _itemStyleClass;

  /**
   * The itemHeaderStyleClass attribute value.
   */
  private String _itemHeaderStyleClass;

  /**
   * The itemContentStyleClass attribute value.
   */
  private String _itemContentStyleClass;
}
