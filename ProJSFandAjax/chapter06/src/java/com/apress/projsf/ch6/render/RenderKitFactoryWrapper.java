package com.apress.projsf.ch6.render;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

/**
 * RenderKitFactoryWrapper implements the decorator pattern 
 * for RenderKitFactory.
 */
public class RenderKitFactoryWrapper extends RenderKitFactory
{
  /**
   * Creates a new RenderKitFactoryWrapper.
   * 
   * @param delegate  the RenderKitFactory delegate
   */
  public RenderKitFactoryWrapper(
    RenderKitFactory delegate)
  {
    _delegate = delegate;
  }

  /**
   * Adds a new RenderKit to this RenderKitFactory.
   * 
   * @param renderKitId  the RenderKit identifier
   * @param renderKit    the RenderKit implementation
   */
  public void addRenderKit(
    String    renderKitId,
    RenderKit renderKit)
  {
    _delegate.addRenderKit(renderKitId, renderKit);
  }

  /**
   * Returns the RenderKit by identifier.
   * 
   * @param context      the Faces context
   * @param renderKitId  the RenderKit identifier
   * 
   * @return  the RenderKit implementation
   */
  public RenderKit getRenderKit(
    FacesContext context,
    String       renderKitId)
  {
    return _delegate.getRenderKit(context, renderKitId);
  }

  /**
   * Returns an Iterator of all the registered RenderKit identifiers.
   * 
   * @return the RenderKit identifier Iterator
   */
  public Iterator getRenderKitIds()
  {
    return _delegate.getRenderKitIds();
  }

  private final RenderKitFactory _delegate;
}