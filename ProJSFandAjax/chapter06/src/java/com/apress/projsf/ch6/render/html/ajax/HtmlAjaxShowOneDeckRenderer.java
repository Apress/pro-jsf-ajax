package com.apress.projsf.ch6.render.html.ajax;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.apress.projsf.ch3.render.html.basic.HtmlShowOneDeckRenderer;

/**
 * HtmlAjaxShowOneDeckRenderer extends the HtmlShowOneDeckRenderer
 * to add AJAX-based postback.
 */
public class HtmlAjaxShowOneDeckRenderer extends HtmlShowOneDeckRenderer
{
  /**
   * Encodes the AJAX resoures.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @throws IOException  if an I/O error occurs
   */
  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    writeScriptResource(context, "weblet://org.dojotoolkit.browserio/dojo.js");
    writeScriptResource(context, "weblet://net.java.dev.d2/d2.js");
    writeScriptResource(context, "weblet://com.apress.projsf.ch6/showOneDeck.js");
    writeStyleResource(context, "weblet://com.apress.projsf.ch6/showOneDeck.css");
  }
}