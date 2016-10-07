package com.apress.projsf.ch8.render.xul.ajax;

import java.io.Writer;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.apress.projsf.ch6.render.ExtendedRenderKit;
import com.apress.projsf.ch6.render.FixedContentTypeResponseWriter;
import com.apress.projsf.ch8.render.XmlResponseWriter;

public class XulAjaxRenderKit extends ExtendedRenderKit
{
  public static final String CONTENT_TYPE = "application/vnd.mozilla.xul+xml";

  public ResponseWriter createResponseWriter(
    Writer writer,
    String contentTypeList,
    String charset)
  {
    ResponseWriter out =  new XmlResponseWriter(writer, charset, CONTENT_TYPE);
    
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext external = context.getExternalContext();
    Map requestHeaders = external.getRequestHeaderMap();

    // Detect D2 request
    String d2ContentType = (String)requestHeaders.get("X-D2-Content-Type");
    if (d2ContentType != null)
    {
      out = new FixedContentTypeResponseWriter(out, "application/xml");
    }

    return out;
  }
}