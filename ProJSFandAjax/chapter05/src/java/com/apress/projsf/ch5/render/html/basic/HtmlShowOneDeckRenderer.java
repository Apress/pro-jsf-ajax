package com.apress.projsf.ch5.render.html.basic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.util.Iterator;

import com.apress.projsf.ch2.render.html.HtmlRenderer;
import com.apress.projsf.ch3.component.UIShowItem;
import com.apress.projsf.ch3.component.UIShowOne;
import com.apress.projsf.ch3.event.ShowEvent;

/**
 * Renders the UIShowOne component as a deck.
 */
public class HtmlShowOneDeckRenderer extends HtmlRenderer
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

  /**
   * Begins the encoded output for this component.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @throws IOException  if an I/O error occurs during rendering
   */
  public void encodeBegin(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    // first write out resources
    encodeResources(context, component);

    ResponseWriter out = context.getResponseWriter();
    out.startElement("div", component);
    Map attrs = component.getAttributes();
    String styleClass = (String)attrs.get(STYLE_CLASS_ATTR);
    if (styleClass != null)
      out.writeAttribute("class", styleClass, STYLE_CLASS_ATTR);
  }

  /**
   * Encodes the UIShowItem children of this UIShowOne component.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @throws IOException  if an I/O error occurs during rendering
   */
  public void encodeChildren(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    if (component.getChildCount() > 0)
    {
      UIShowOne showOne = (UIShowOne)component;
      String showItemId = showOne.getShowItemId();

      // the renderer-specific attributes
      Map attrs = showOne.getAttributes();
      String itemStyleClass = (String)attrs.get(ITEM_STYLE_CLASS_ATTR);
      if (itemStyleClass == null)
        itemStyleClass = "ProShowItem";
      String itemHeaderStyleClass = (String)
                                      attrs.get(ITEM_HEADER_STYLE_CLASS_ATTR);
      if (itemHeaderStyleClass == null)
        itemHeaderStyleClass = "ProShowItemHeader";
      String itemContentStyleClass = (String)
                                      attrs.get(ITEM_CONTENT_STYLE_CLASS_ATTR);
      if (itemContentStyleClass == null)
        itemContentStyleClass = "ProShowItemContent";

      String formClientId = _findFormClientId(context, component);
      String showOneClientId = component.getClientId(context);
      List children = component.getChildren();
      for (Iterator iter = children.iterator(); iter.hasNext();)
      {
        UIComponent child = (UIComponent) iter.next();
        if (child instanceof UIShowItem)
        {
          UIShowItem showItem = (UIShowItem)child;
          String id = showItem.getId();

          boolean isActive = id.equals(showItemId);
          ResponseWriter out = context.getResponseWriter();
          out.startElement("div", showItem);
          out.writeAttribute("class", itemStyleClass, ITEM_STYLE_CLASS_ATTR);

          out.startElement("div", showItem);
          out.writeAttribute("class", itemHeaderStyleClass, 
                             ITEM_HEADER_STYLE_CLASS_ATTR);

          if (formClientId != null)
          {
            out.writeAttribute("onclick",
                               "_showOneDeck_click('" + formClientId + "'," +
                                                  "'" + showOneClientId + "'," +
                                                  "'" + id + "')",
                               null);
          }

          // Default the header text
          UIComponent header = showItem.getHeader();
          if (header != null)
          {
            _encodeAll(context, header);
          }
          else
          {
            out.writeText("Header", null);
          }

          out.endElement("div");

          if (isActive)
          {
            out.startElement("div", null);
            out.writeAttribute("class", itemContentStyleClass, 
                               ITEM_CONTENT_STYLE_CLASS_ATTR);
            List kids = showItem.getChildren();
            Iterator it = kids.iterator();
            while (it.hasNext())
            {
              UIComponent kid = (UIComponent)it.next();
              _encodeAll(context, kid);
            }
            out.endElement("div");
          }

          out.endElement("div");
        }
      }
    }
  }

  /**
   * Completes the encoded output for this component.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @throws IOException  if an I/O error occurs during rendering
   */
  public void encodeEnd(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    ResponseWriter out = context.getResponseWriter();
    out.endElement("div");
  }

  /**
   * Indicates that this Renderer is responsible for rendering any
   * child components.
   */
  public boolean getRendersChildren()
  {
    return true;
  }

  /**
   * Write out the HtmlShowOneDeck resources.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   */
  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    writeScriptResource(context,
                        "weblet://com.apress.projsf.ch5/showOneDeck.js");
    writeStyleResource(context,
                       "weblet://com.apress.projsf.ch5/showOneDeck.css");
  }

  /**
   * Decodes the incoming request to detect the ShowEvent.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   */
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

  /**
   * Encodes a component and all of it's children.
   *
   * Note: a new method, UIComponent._encodeAll(FacesContext) has been added
   * to JSF 1.2 and implements equivalent functionality to this method.
   *
   * @param context the Faces context
   * @param component the Faces component
   *
   * @throws IOException if an I/O error occurs during rendering
   */
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

  /**
   * Finds the parent UIForm component client identifier.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @return  the parent UIForm client identifier, if present, otherwise null
   */
  private String _findFormClientId(
    FacesContext context,
    UIComponent  component)
  {
    while (component != null &&
           !(component instanceof UIForm))
    {
      component = component.getParent();
    }

    return (component != null) ? component.getClientId(context) : null;
  }
}