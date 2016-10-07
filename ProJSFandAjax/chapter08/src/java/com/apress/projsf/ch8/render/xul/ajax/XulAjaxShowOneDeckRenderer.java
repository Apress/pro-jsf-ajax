package com.apress.projsf.ch8.render.xul.ajax;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.apress.projsf.ch3.component.UIShowItem;
import com.apress.projsf.ch3.component.UIShowOne;
import com.apress.projsf.ch3.event.ShowEvent;
import com.apress.projsf.ch8.render.xul.XulRenderer;

public class XulAjaxShowOneDeckRenderer extends XulRenderer
{
  /**
   * The styleClass attribute.
   */
  public static String STYLE_CLASS_ATTR = "styleClass";

  /**
   * The itemStyleClass attribute.
   */
  public static String ITEM_STYLE_CLASS_ATTR = "itemStyleClass";

  /**
   * The itemHeaderStyleClass attribute.
   */
  public static String ITEM_HEADER_STYLE_CLASS_ATTR = "itemHeaderStyleClass";

  /**
   * The itemContentStyleClass attribute.
   */
  public static String ITEM_CONTENT_STYLE_CLASS_ATTR = "itemContentStyleClass";

  public void encodeBegin(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    UIShowOne showOne = (UIShowOne)component;
    String clientId = showOne.getClientId(context);
    String showItemId = showOne.getShowItemId();

    ResponseWriter out = context.getResponseWriter();
    out.startElement("pro:showOneDeck", component);
    out.writeAttribute("id", clientId, null);
    out.writeAttribute("showItemId", showItemId, "showItemId");

    Map attrs = component.getAttributes();
    String styleClass = (String)attrs.get(STYLE_CLASS_ATTR);
    if (styleClass != null)
      out.writeAttribute("class", styleClass, STYLE_CLASS_ATTR);

    // encode resources
    super.encodeBegin(context, component);
  }
  
  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    writeScriptResource(context, "weblet://org.dojotoolkit.browserio/dojo.js");
    writeScriptResource(context, "weblet://net.java.dev.d2/d2.js");
  }

  public void encodeChildren(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    if (component.getChildCount() > 0)
    {
      UIShowOne showOne = (UIShowOne)component;
      String showItemId = showOne.getShowItemId();

      Map attrs = showOne.getAttributes();
      String styleClass = getItemStyleClass(attrs);
      String headerStyleClass = getItemHeaderStyleClass(attrs);
      String contentStyleClass = getItemContentStyleClass(attrs);

      List children = component.getChildren();
      for (Iterator iter = children.iterator(); iter.hasNext();)
      {
        UIComponent child = (UIComponent) iter.next();
        if (child instanceof UIShowItem)
        {
          UIShowItem showItem = (UIShowItem)child;
          String id = showItem.getId();
          boolean active = (id.equals(showItemId));

          ResponseWriter out = context.getResponseWriter();
          out.startElement("pro:showItem", showItem);
          out.writeAttribute("itemId", id, null);
          if (styleClass != null)
            out.writeAttribute("styleClass", styleClass, ITEM_STYLE_CLASS_ATTR);
          if (headerStyleClass != null)
            out.writeAttribute("headerStyleClass", headerStyleClass, ITEM_HEADER_STYLE_CLASS_ATTR);
          if (contentStyleClass != null)
            out.writeAttribute("contentStyleClass", contentStyleClass, ITEM_CONTENT_STYLE_CLASS_ATTR);
          if (active)
            out.writeAttribute("active", Boolean.toString(active), null);

          // the header facet
          UIComponent header = showItem.getHeader();
          if (header != null)
          {
            out.startElement("pro:showItemHeader", null);
            _encodeAll(context, header);
            out.endElement("pro:showItemHeader");
          }

          // the expanded item contents
          if (active)
          {
            List kids = showItem.getChildren();
            Iterator it = kids.iterator();
            while (it.hasNext())
            {
              UIComponent kid = (UIComponent)it.next();
              _encodeAll(context, kid);
            }
          }

          out.endElement("pro:showItem");
        }
      }
    }
  }

  public void encodeEnd(
    FacesContext context, 
    UIComponent  component) throws IOException
  {
    ResponseWriter out = context.getResponseWriter();
    out.endElement("pro:showOneDeck");
  }

  public boolean getRendersChildren()
  {
    return true;
  }

  private void _encodeAll(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    component.encodeBegin(context);
    if (component.getRendersChildren())
    {
      component.encodeChildren(context);
    }
    else
    {
      List kids = component.getChildren();
      Iterator it = kids.iterator();
      while (it.hasNext())
      {
        UIComponent kid = (UIComponent)it.next();
        _encodeAll(context, kid);
      }
    }
    component.encodeEnd(context);
  }

  public void decode(
    FacesContext context,
    UIComponent  component)
  {
    ExternalContext external = context.getExternalContext();
    Map requestParams = external.getRequestParameterMap();
    String clientId = component.getClientId(context);
    String newShowItemId = (String)requestParams.get(clientId);
    if (newShowItemId != null && newShowItemId.length() > 0)
    {
      UIShowOne showOne = (UIShowOne)component;
      String oldShowItemId = showOne.getShowItemId();
      if (!newShowItemId.equals(oldShowItemId))
      {
        showOne.setShowItemId(newShowItemId);
        ShowEvent event = new ShowEvent(showOne, oldShowItemId, newShowItemId);
        event.queue();
      }
    }
  }

  protected String getStyleClass(
    Map attrs)
  {
    return (String)attrs.get(STYLE_CLASS_ATTR);
  }
  
  protected String getItemStyleClass(
    Map attrs)
  {
    return (String)attrs.get(ITEM_STYLE_CLASS_ATTR);
  }
  
  protected String getItemHeaderStyleClass(
    Map attrs)
  {
    return (String)attrs.get(ITEM_HEADER_STYLE_CLASS_ATTR);
  }
  
  protected String getItemContentStyleClass(
    Map attrs)
  {
    return (String)attrs.get(ITEM_CONTENT_STYLE_CLASS_ATTR);
  }
}