package com.apress.projsf.ch8.render.xul.basic;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.apress.projsf.ch8.component.UIDocument;
import com.apress.projsf.ch8.render.xul.XulRenderer;

public class XulDocumentRenderer extends XulRenderer
{
  /**
   * The title attribute.
   */
  public static String TITLE_ATTR = "title";

  /**
   * The styleClass attribute.
   */
  public static String STYLE_CLASS_ATTR = "styleClass";
  
  /**
   * The stylesheetURI attribute.
   */
  public static String STYLESHEET_URI_ATTR = "stylesheetURI";

  public void encodeBegin(
    FacesContext context, 
    UIComponent  component) throws IOException
  {
    ResponseWriter out = context.getResponseWriter();
    Map attrs = component.getAttributes();

    ViewHandler handler = context.getApplication().getViewHandler();

    UIDocument document = (UIDocument)component;
    String title = getTitle(attrs);
    String styleClass = getStyleClass(attrs);
    String stylesheetURI = getStylesheetURI(context, attrs);

    out.write("<?xml-stylesheet href=\"");
    out.writeText(handler.getResourceURL(context, "weblet://com.apress.projsf.ch8/document.css"), null);
    out.write("\" type=\"text/css\"?>\n");

    out.write("<?xml-stylesheet href=\"");
    out.writeText(handler.getResourceURL(context, "weblet://com.apress.projsf.ch8/pro.css"), null);
    out.write("\" type=\"text/css\"?>\n");

    if (stylesheetURI != null)
    {
      out.write("<?xml-stylesheet href=\"");
      out.writeText(handler.getResourceURL(context, stylesheetURI), 
                    STYLESHEET_URI_ATTR);
      out.write("\" type=\"text/css\"?>\n");
    }
    out.startElement("xul:window", document);
    out.writeAttribute("xmlns", 
                       "http://www.w3.org/1999/xhtml",
                       null);
    out.writeAttribute("xmlns:xul", 
                       "http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul",
                       null);
    out.writeAttribute("xmlns:pro", 
                       "http://projsf.apress.com/tags",
                       null);
    out.writeAttribute("orient", "horizontal", null);
    out.writeAttribute("align", "start", null);

    if (title != null)
      out.writeAttribute("title", title, TITLE_ATTR);

    super.encodeBegin(context, component);

    out.startElement("xul:hbox", null);
    if (styleClass != null)
      out.writeAttribute("class", styleClass, STYLE_CLASS_ATTR);
    out.writeAttribute("align", "center", null);
    out.writeAttribute("flex", "1", null);
  }

  public void encodeEnd(
    FacesContext context, 
    UIComponent  component) throws IOException
  {
    ResponseWriter out = context.getResponseWriter();
    out.endElement("xul:hbox");
    out.endElement("xul:window");
  }
  
  protected String getTitle(
    Map attrs)
  {
    return (String)attrs.get(TITLE_ATTR);
  }

  protected String getStyleClass(
    Map attrs)
  {
    return (String)attrs.get(STYLE_CLASS_ATTR);
  }

  protected String getStylesheetURI(
    FacesContext context,
    Map          attrs)
  {
    return (String)attrs.get(STYLESHEET_URI_ATTR);
  }
}