package com.apress.projsf.ch3.taglib.pro;

import javax.faces.component.UIComponent;
import javax.faces.el.MethodBinding;

import com.apress.projsf.ch2.taglib.UIComponentTagSupport;
import com.apress.projsf.ch3.component.UIShowOne;
import com.apress.projsf.ch3.component.pro.ProShowOneDeck;
import com.apress.projsf.ch3.event.ShowAdapter;

/**
 * ProShowOneDeckTag component tag handler.
 */
public class ProShowOneDeckTag extends UIComponentTagSupport
{
  /**
   * Returns the component type.
   *
   * @return  the component type
   */
  public String getComponentType()
  {
    return ProShowOneDeck.COMPONENT_TYPE;
  }

  /**
   * Returns the renderer type.
   *
   * @return  the renderer type
   */
  public String getRendererType()
  {
    return ProShowOneDeck.RENDERER_TYPE;
  }

  /**
   * Sets the showItemId attribute value.
   *
   * @param showItemId  the currently showing item identifer
   */
  public void setShowItemId(
    String showItemId)
  {
    _showItemId = showItemId;
  }

  /**
   * Sets the showListener attribute value.
   *
   * @param showListener  the showListener attribute value
   */
  public void setShowListener(
    String showListener)
  {
    _showListener = showListener;
  }

  /**
   * Sets the CSS style class.
   *
   * @param styleClass  the new style class
   */
  public void setStyleClass(String styleClass)
  {
    _styleClass = styleClass;
  }

  /**
   * Sets the item CSS style class.
   * 
   * @param itemStyleClass  the new item style class
   */
  public void setItemStyleClass(
    String itemStyleClass)
  {
    _itemStyleClass = itemStyleClass;
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
   * Releases the internal state used by the tag.
   */
  public void release()
  {
    _showItemId = null;
    _showListener = null;
    _styleClass = null;
    _itemStyleClass = null;
    _itemHeaderStyleClass = null;
    _itemContentStyleClass = null;
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
    setStringProperty(component, "showItemId", _showItemId);

    // Behavioral listeners
    if (_showListener != null)
    {
      UIShowOne showOne = (UIShowOne) component;
      MethodBinding showMethod = createMethodBinding(_showListener, 
                                                     ShowAdapter.SIGNATURE);
      showOne.addShowListener(new ShowAdapter(showMethod));
    }

    // Renderer-specific attributes
    setStringProperty(component, "styleClass", _styleClass);
    setStringProperty(component, "itemStyleClass", _itemStyleClass);
    setStringProperty(component, "itemHeaderStyleClass", _itemHeaderStyleClass);
    setStringProperty(component, "itemContentStyleClass", _itemContentStyleClass);

  }

  private String _showItemId;
  private String _showListener;
  private String _styleClass;
  private String _itemStyleClass;
  private String _itemHeaderStyleClass;
  private String _itemContentStyleClass;
}
