package com.apress.projsf.ch8.taglib.pro;

import javax.faces.component.UIComponent;

import com.apress.projsf.ch8.component.pro.ProDocument;
import com.apress.projsf.ch2.taglib.UIComponentTagSupport;

/**
 * ProDocumentTag component tag handler.
 */
public class ProDocumentTag extends UIComponentTagSupport
{
  /**
   * Returns the component type.
   * 
   * @return  the component type
   */
  public String getComponentType()
  {
    return ProDocument.COMPONENT_TYPE;
  }

  /**
   * Returns the renderer type.
   * 
   * @return  the renderer type
   */
  public String getRendererType()
  {
    return ProDocument.RENDERER_TYPE;
  }
  
  public void setStylesheetURI(String stylesheetURI)
  {
    _stylesheetURI = stylesheetURI;
  }
  
  public void setTitle(String title)
  {
    _title = title;
  }
  
  public void setStyleClass(String styleClass)
  {
    _styleClass = styleClass;
  }
  
  public void release()
  {
    _title = null;
    _styleClass = null;
    _stylesheetURI = null;
  }
  
  /**
   * Transfers the property values from this tag to the component.
   * 
   * @param component  the target component
   */
  protected void setProperties(
    UIComponent component)
  {
    super.setProperties(component);

    // Behavioral properties
    // (none)

    // Renderer-specific attributes
    setStringProperty(component, "title", _title);
    setStringProperty(component, "styleClass", _styleClass);
    setStringProperty(component, "stylesheetURI", _stylesheetURI);
  }

  private String _title;
  private String _styleClass;
  private String _stylesheetURI;
}