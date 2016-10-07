package com.apress.projsf.ch8.component;

import javax.faces.component.UIComponentBase;

/**
 * The UIDocument component.
 */
public class UIDocument extends UIComponentBase
{
  /**
   * The component type for this component.
   */
  public static final String COMPONENT_TYPE = "com.apress.projsf.Document";

  /**
   * The component family for this component.
   */
  public static final String COMPONENT_FAMILY = "com.apress.projsf.Document";

  /**
   * Creates a new UIDocument.
   */
  public UIDocument()
  {
  }
  
  public String getFamily()
  {
    return COMPONENT_FAMILY;
  }
}
