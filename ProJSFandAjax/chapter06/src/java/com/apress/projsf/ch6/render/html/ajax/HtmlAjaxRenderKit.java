package com.apress.projsf.ch6.render.html.ajax;

import java.io.Writer;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.apress.projsf.ch6.render.ExtendedRenderKit;
import com.apress.projsf.ch6.render.FixedContentTypeResponseWriter;

/**
 * HtmlAjaxRenderKit is an extended RenderKit, using HTML_BASIC as the
 * base RenderKit.
 */
public class HtmlAjaxRenderKit extends ExtendedRenderKit
{
  /**
   * Creates the ResponseWriter, fixing the content type 
   * to "text/plain" for d2 AJAX requests.
   * 
   * @param writer           the writer
   * @param contentTypeList  the acceptable content types (q-values)
   * @param charset          the character encoding of the writer
   * 
   * @return  the newly created ResponseWriter
   */
  public ResponseWriter createResponseWriter(
    Writer writer,
    String contentTypeList,
    String charset)
  {
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext external = context.getExternalContext();
    Map requestHeaders = external.getRequestHeaderMap();

    if (contentTypeList == null)
    {
      contentTypeList = (String)requestHeaders.get("Accept");
      // IE sends a vague Accept header of "*/*"
      contentTypeList = contentTypeList.replaceFirst("(\\*/\\*)", "text/html");
    }

    ResponseWriter out = 
      super.createResponseWriter(writer, contentTypeList, charset);

    // Detect D2 request
    String d2ContentType = (String)requestHeaders.get("X-D2-Content-Type");

    // TODO: handle xml-based types as well
    if ("text/html".equals(d2ContentType))
    {
      out = new FixedContentTypeResponseWriter(out, "text/plain");
    }

    return out;
  }
}