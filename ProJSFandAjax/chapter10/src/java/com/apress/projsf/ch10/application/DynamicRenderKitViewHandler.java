package com.apress.projsf.ch10.application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;


/**
 * DynamicRenderKitViewHandler uses the default RenderKit identifier
 * as a base to locate agent-specific RenderKits for the incoming request.
 */
public class DynamicRenderKitViewHandler extends ViewHandlerWrapper
{
  /**
   * Creates a new DynamicRenderKitViewHandler.
   *
   * @param handler  the delegate ViewHandler
   */
  public DynamicRenderKitViewHandler(
    ViewHandler handler)
  {
    super(handler);
  }

  /**
   * Calculates the RenderKit identifier to use for this request.
   *
   * @param context  the Faces context
   *
   * @return  the calculated RenderKit identifier
   */
  public String calculateRenderKitId(
    FacesContext context)
  {
    // calculate the RenderKit id
    String renderKitId = super.calculateRenderKitId(context);

    // detect an expression to evaluate the dynamic RenderKit identifier
    Matcher matcher = _DYNAMIC_RENDER_KIT_ID.matcher(renderKitId);
    if (matcher.matches())
    {
      String expression = matcher.group(1);
      Application application = context.getApplication();
      ValueBinding binding = application.createValueBinding(expression);
      if (binding.getType(context) == String.class)
        renderKitId = (String)binding.getValue(context);
    }

    // return either the calculated or dynamic RenderKit id
    return renderKitId;
  }

  // Matches RenderKit identifier of the form "#{...}"
  static private final Pattern _DYNAMIC_RENDER_KIT_ID =
                                  Pattern.compile("(\\Q#{\\E[^\\}]+\\Q}\\E)");
}
