package com.apress.projsf.ch3.component;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;

/**
 * The UIShowItem component.
 */
public class UIShowItem extends UIComponentBase
{
  /**
   * The component type for this component.
   */
  public static final String COMPONENT_TYPE = "com.apress.projsf.ShowItem";

  /**
   * The component family for this component.
   */
  public static final String COMPONENT_FAMILY = "com.apress.projsf.ShowItem";

  /**
   * Creates a new UIShowItem.
   */
  public UIShowItem()
  {
  }

  /**
   * Returns the component family for this component.
   * 
   * @return  the component family
   */
  public String getFamily()
  {
    return COMPONENT_FAMILY;
  }

  /**
   * Returns the header facet.
   * 
   * @return the header facet
   */
  public UIComponent getHeader() 
  {
    return getFacet("header");
  }

  /**
   * Sets a new header facet.
   * 
   * @param header  the new header facet
   */
  public void setHeader(
    UIComponent header) 
  {
    getFacets().put("header", header);
  }
}
