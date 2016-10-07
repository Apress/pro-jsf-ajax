package com.apress.projsf.ch9.render.html.basic;

import java.io.IOException;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.apress.projsf.ch2.render.html.HtmlRenderer;

public class HtmlDocumentRenderer extends HtmlRenderer
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
    String styleClass = getStyleClass(attrs);

    // TODO: lang, dir
    out.startElement("html", component);
    out.startElement("head", null);
    encodeHead(context, out, attrs);
    out.endElement("head");
    out.startElement("body", null);
    if (styleClass != null)
      out.writeAttribute("class", styleClass, STYLE_CLASS_ATTR);
  }

  public void encodeEnd(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    ResponseWriter out = context.getResponseWriter();
    out.endElement("body");
    out.endElement("html");
  }

  protected void encodeHead(
    FacesContext   context,
    ResponseWriter out,
    Map            attrs) throws IOException
  {
    String title = getTitle(attrs);
    String stylesheetURI = getStylesheetURI(context, attrs);

    if (title != null)
    {
      out.startElement("title", null);
      out.writeText(title, TITLE_ATTR);
      out.endElement("title");
    }
    if (stylesheetURI != null)
    {
      out.startElement("link", null);
      out.writeAttribute("rel", "stylesheet", null);
      out.writeAttribute("href", stylesheetURI, STYLESHEET_URI_ATTR);
      out.endElement("link");
    }
  }

  protected String getTitle(
    Map attrs)
  {
    return (String)attrs.get(TITLE_ATTR);
  }

  protected String getStylesheetURI(
    FacesContext context,
    Map          attrs)
  {
    String stylesheetURI = (String)attrs.get(STYLESHEET_URI_ATTR);

    if (stylesheetURI != null)
    {
      Application application = context.getApplication();
      ViewHandler handler = application.getViewHandler();
      stylesheetURI = handler.getResourceURL(context, stylesheetURI);
    }

    return stylesheetURI;
  }

  protected String getStyleClass(
    Map attrs)
  {
    return (String)attrs.get(STYLE_CLASS_ATTR);
  }

}