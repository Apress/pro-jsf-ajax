package com.apress.projsf.ch8.render.xul;

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

import net.java.dev.weblets.WebletContainer;

public class XulRenderer extends Renderer
{
  public void encodeBegin(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    encodeResources(context, component);
  }
  
  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    // by-default, no resources
  }
  
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

      // configure Dojo Toolkit for XUL
      // in future, this should no longer be necessary
      if ("weblet://org.dojotoolkit.browserio/dojo.js".equals(resourcePath))
      {
        WebletContainer container = WebletContainer.getInstance();
        String libraryURL = container.getWebletURL("org.dojotoolkit.browserio", "/dojo.js");
        out.startElement("xul:script", null);
        out.writeAttribute("type", "application/x-javascript", null);
        out.writeText("var djConfig={preventBackButtonFix: true, " +
                      "libraryScriptUri:'" + libraryURL + "'};", null);
        out.endElement("xul:script");
      }

      out.startElement("xul:script", null);
      out.writeAttribute("type", "application/x-javascript", null);
      out.writeAttribute("src", resourceURL, null);
      out.endElement("xul:script");
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
  
  static private final String _SCRIPT_RESOURCES_KEY = 
                            XulRenderer.class.getName() + ".SCRIPTS_WRITTEN";
}