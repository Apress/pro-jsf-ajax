package com.apress.projsf.ch6.render;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;

/**
 * ExtendedRenderKit supports dynamic extension of another RenderKit
 * without needing to re-register all the renderers from the base
 * RenderKit.
 */
public class ExtendedRenderKit extends RenderKit
{
  /**
   * Adds a Renderer to this RenderKit.
   * 
   * @param componentFamily  the component family
   * @param rendererType     the renderer type
   * @param renderer         the renderer implementation
   */
  public void addRenderer(
    String   componentFamily,
    String   rendererType,
    Renderer renderer)
  {
    Map map = _getRendererTypeMap(componentFamily, true);
    map.put(rendererType, renderer);
  }

  /**
   * Returns a Renderer for the specified component family and renderer type.
   * If a Renderer was registered directly on this ExtendedRenderKit, then
   * it is returned, otherwise the Renderer lookup is delegated to the base
   * RenderKit.
   * 
   * @param componentFamily  the component family
   * @param rendererType     the renderer type
   * 
   * @return the previously registered renderer implementation
   */
  public Renderer getRenderer(
    String componentFamily,
    String rendererType)
  {
    Map map = _getRendererTypeMap(componentFamily, false);
    Renderer renderer = (map != null) ? (Renderer)map.get(rendererType) : null;

    // fallback on base RenderKit
    if (renderer == null)
      renderer = _base.getRenderer(componentFamily, rendererType);

    return renderer;
  }

  /**
   * Creates a ResponseStream for the specified OutputStream.
   * 
   * @param out  the output stream
   * 
   * @return the newly created ResponseStream
   */
  public ResponseStream createResponseStream(
    OutputStream out)
  {
    return _base.createResponseStream(out);
  }

  /**
   * Creates a ResponseWriter for the specified Writer.
   * 
   * @param writer           the underlying writer
   * @param contentTypeList  the list of acceptable content types (q-values)
   * @param charset          the character encoding of the underlying writer
   * 
   * @return the newly created ResponseWriter
   */
  public ResponseWriter createResponseWriter(
    Writer writer,
    String contentTypeList,
    String charset)
  {
    return _base.createResponseWriter(writer, contentTypeList, charset);
  }

  /**
   * Returns the ResponseStateManager for this RenderKit.
   * 
   * @return the ResponseStateManager for this RenderKit
   */
  public ResponseStateManager getResponseStateManager()
  {
    return _base.getResponseStateManager();
  }

  /**
   * Sets the base RenderKit, since it is not available
   * when this instance is constructed.
   * 
   * @param base  the base RenderKit
   */
  void __setRenderKit(
    RenderKit base)
  {
    _base = base;
  }

  private Map _getRendererTypeMap(
    String  componentFamily,
    boolean createIfNull)
  {
    Map componentFamilyMap = (Map)_renderers.get(componentFamily);
    if (componentFamilyMap == null && createIfNull)
    {
      componentFamilyMap = new TreeMap();
      _renderers.put(componentFamily, componentFamilyMap);
    }
    return componentFamilyMap;
  }

  private       RenderKit _base;
  private final Map       _renderers = new TreeMap();
}