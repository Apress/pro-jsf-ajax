package com.apress.projsf.ch2.render.html;

import java.io.IOException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * HtmlRenderer is a base class for all Renderers that output HTML markup.
 */
public class HtmlRenderer extends Renderer
{
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
    // write out resources
    encodeResources(context, component);
  }
  
  /**
   * Override hook for subclasses to write out their resources.
   * 
   * @param context    the Faces context
   * @param component  the Faces component
   */
  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    // empty hook for subclasses to override as needed
  }

  /**
   * Writes a script library resource at-most-once within a single
   * RenderResponse phase.
   * 
   * @param context       the Faces context
   * @param resourcePath  the script library resource path
   * 
   * @throws IOException  if an error occurs during rendering
   */
  protected void writeScriptResource(
    FacesContext context,
    String       resourcePath) throws IOException
  {
    Set scriptResources = _getScriptResourcesAlreadyWritten(context);

    // Set.add() returns true only if item was added to the set
    // and returns false if item was already present in the set
    if (scriptResources.add(resourcePath))
    {
      ViewHandler handler = context.getApplication().getViewHandler();
      String resourceURL = handler.getResourceURL(context, resourcePath);
      ResponseWriter out = context.getResponseWriter();
      out.startElement("script", null);
      out.writeAttribute("type", "text/javascript", null);
      out.writeAttribute("src", resourceURL, null);
      out.endElement("script");
    }
  }
  
  /**
   * Writes an inline script at-most-once within a single
   * RenderResponse phase.
   * 
   * @param context       the Faces context
   * @param inlineScript  the inline script code
   * 
   * @throws IOException  if an error occurs during rendering
   */
  protected void writeScriptInline(
    FacesContext context,
    String       inlineScript) throws IOException
  {
    Set scriptResources = _getScriptResourcesAlreadyWritten(context);

    // Set.add() returns true only if item was added to the set
    // and returns false if item was already present in the set
    if (scriptResources.add(inlineScript))
    {
      ResponseWriter out = context.getResponseWriter();
      out.startElement("script", null);
      out.writeAttribute("type", "text/javascript", null);
      out.writeText(inlineScript, null);
      out.endElement("script");
    }
  }
  
  /**
   * Writes a stylesheet resource at-most-once within a single
   * RenderResponse phase.
   * 
   * @param context       the Faces context
   * @param resourcePath  the stylesheet resource path
   * 
   * @throws IOException  if an error occurs during rendering
   */
  protected void writeStyleResource(
    FacesContext context,
    String       resourcePath) throws IOException
  {
    Set styleResources = _getStyleResourcesAlreadyWritten(context);

    // Set.add() returns true only if item was added to the set
    // and returns false if item was already present in the set
    if (styleResources.add(resourcePath))
    {
      ViewHandler handler = context.getApplication().getViewHandler();
      String resourceURL = handler.getResourceURL(context, resourcePath);
      ResponseWriter out = context.getResponseWriter();
      out.startElement("style", null);
      out.writeAttribute("type", "text/css", null);
      out.writeText("@import url(" + resourceURL + ");", null);
      out.endElement("style");
    }
  }
  
  /**
   * Writes an inline style at-most-once within a single
   * RenderResponse phase.
   * 
   * @param context      the Faces context
   * @param inlineStyle  the inline style classes
   * 
   * @throws IOException  if an error occurs during rendering
   */
  protected void writeStyleInline(
    FacesContext context,
    String       inlineStyle) throws IOException
  {
    Set styleResources = _getStyleResourcesAlreadyWritten(context);

    // Set.add() returns true only if item was added to the set
    // and returns false if item was already present in the set
    if (styleResources.add(inlineStyle))
    {
      ResponseWriter out = context.getResponseWriter();
      out.startElement("style", null);
      out.writeAttribute("type", "text/css", null);
      out.writeText(inlineStyle, null);
      out.endElement("style");
    }
  }
  
  // Implements at-most-once semantics for each script resource on
  // the currently rendering page
  private Set _getScriptResourcesAlreadyWritten(
    FacesContext context)
  {
    ExternalContext external = context.getExternalContext();
    Map requestScope = external.getRequestMap();
    Set written = (Set)requestScope.get(_SCRIPT_RESOURCES_KEY);
    
    if (written == null)
    {
      written = new HashSet();
      requestScope.put(_SCRIPT_RESOURCES_KEY, written);
    }
    
    return written;
  }
  
  // Implements at-most-once semantics for each style resource on
  // the currently rendering page
  private Set _getStyleResourcesAlreadyWritten(
    FacesContext context)
  {
    ExternalContext external = context.getExternalContext();
    Map requestScope = external.getRequestMap();
    Set written = (Set)requestScope.get(_STYLE_RESOURCES_KEY);
    
    if (written == null)
    {
      written = new HashSet();
      requestScope.put(_STYLE_RESOURCES_KEY, written);
    }
    
    return written;
  }
  
  static private final String _STYLE_RESOURCES_KEY = 
                         HtmlRenderer.class.getName() + ".STYLES_WRITTEN";
  static private final String _SCRIPT_RESOURCES_KEY = 
                         HtmlRenderer.class.getName() + ".SCRIPTS_WRITTEN";
}