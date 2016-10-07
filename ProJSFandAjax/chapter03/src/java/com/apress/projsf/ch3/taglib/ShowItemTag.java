package com.apress.projsf.ch3.taglib;

import com.apress.projsf.ch2.taglib.UIComponentTagSupport;

import com.apress.projsf.ch3.component.UIShowItem;

/**
 * ShowItemTag is the UIShowItem component tag handler.
 */
public class ShowItemTag extends UIComponentTagSupport
{
  /**
   * Returns the component type.
   * 
   * @return  the component type
   */
  public String getComponentType()
  {
    return UIShowItem.COMPONENT_TYPE;
  }

  /**
   * Returns the renderer type.
   * 
   * @return  the renderer type
   */
  public String getRendererType()
  {
    return null;
  }
}
