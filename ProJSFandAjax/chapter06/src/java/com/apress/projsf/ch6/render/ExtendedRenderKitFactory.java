package com.apress.projsf.ch6.render;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

/**
 * The ExtendedRenderKitFactory supports supports dynamic extension of 
 * RenderKits without needing to re-register all the renderers from the base
 * RenderKit.
 * 
 * The following syntax must be used to register the extended RenderKit.
 * 
 * <render-kit-id>extended-render-kit-id[base-render-kit-id]</render-kit-id>
 *
 * and the RenderKit implementation class must be of type ExtendedRenderKit.
 */
public class ExtendedRenderKitFactory extends RenderKitFactoryWrapper
{
  /**
   * Creates a new ExtendedRenderKitFactory.
   * 
   * @param delegate  the RenderKitFactory delegate
   */
  public ExtendedRenderKitFactory(
    RenderKitFactory delegate)
  {
    super(delegate);
  }

  /**
   * Adds a new RenderKit to this RenderKitFactory.
   * 
   * If the renderKitId syntax is of the form 
   * extended-render-kit-id[base-render-kit-id], and the RenderKit is
   * and instance of ExtendedRenderKit, then the extended-render-kit-id
   * is used to register the RenderKit, and the base-render-kit-id is used
   * as the base RenderKit for the ExtendedRenderKit.
   * 
   * @param renderKitId  the RenderKit identifier
   * @param renderKit    the RenderKit implementation
   */
  public void addRenderKit(
    String    renderKitId,
    RenderKit renderKit)
  {
    Matcher matcher = _EXTENDED_RENDERKIT_ID.matcher(renderKitId);
    if (matcher.matches() &&
        renderKit instanceof ExtendedRenderKit)
    {
      renderKitId = matcher.group(1);
      String baseRenderKitId = matcher.group(2);

      ExtendedRenderKit extension = (ExtendedRenderKit)renderKit;
      RenderKit base = getRenderKit(null, baseRenderKitId);
      extension.__setRenderKit(base);
    }

    super.addRenderKit(renderKitId, renderKit);
  }

  static final private Pattern _EXTENDED_RENDERKIT_ID =
                          Pattern.compile("([^\\[]+)\\[([^\\]]+)\\]");
}