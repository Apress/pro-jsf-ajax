package com.apress.projsf.ch7.render.html.ajax;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.el.MethodBinding;
import javax.faces.validator.Validator;

import com.apress.projsf.ch2.render.html.basic.HtmlInputDateRenderer;
import com.apress.projsf.ch7.validate.DateValidator;

/**
 * HtmlAjaxInputDateRenderer extends the HtmlInputDateRenderer
 * to add a popup calendar and AJAX-based data fetch of available days.
 */
public class HtmlAjaxInputDateRenderer extends HtmlInputDateRenderer
{
  /**
   * Encodes the content of this component, including a button to
   * trigger the popup calendar.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @throws IOException  if an I/O exception occurs during rendering
   */
  public void encodeEnd(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    String valueString = getValueAsString(context, component);
    String clientId = component.getClientId(context);
    String pattern = _determineDatePattern(context, component);
    String targetURL = _determineTargetURL(context, component);

    Map attrs = component.getAttributes();
    String title = (String)attrs.get(TITLE_ATTR);
    String onchange = (String)attrs.get(ONCHANGE_ATTR);

    ResponseWriter out = context.getResponseWriter();
    out.startElement("div", component);

    if (title != null)
      out.writeAttribute("title", title, TITLE_ATTR);

    // <input id="[clientId]" name="[clientId]"
    //        value="[converted-value]" onchange="[onchange]" />
    out.startElement("input", component);
    out.writeAttribute("style", "vertical-align:bottom;", null);
    out.writeAttribute("id", clientId, null);
    out.writeAttribute("name", clientId, null);
    if (valueString != null)
      out.writeAttribute("value", valueString, null);
    if (onchange != null)
      out.writeAttribute("onchange", onchange, ONCHANGE_ATTR);
    out.endElement("input");
    
    // <button type="button" >
    //   <img src="weblet://com.apress.projsf.ch7/inputDateButton.gif" >
    // </button>
    ViewHandler handler = context.getApplication().getViewHandler();
    String overlayURL = handler.getResourceURL(context, 
                          "weblet://com.apress.projsf.ch7/inputDateButton.gif");
    out.startElement("button", null);
    out.writeAttribute("type", "button", null);
    out.writeAttribute("class", "ProInputDateButton", null);
    out.writeAttribute("onclick",
                       "new HtmlInputDate(" + _toJavaScript(clientId) + "," +
                                          _toJavaScript(pattern) + "," +
                                          _toJavaScript(targetURL) + ").showPopup()",
                       null);
    out.startElement("img", null);
    out.writeAttribute("style", "vertical-align:middle;", null);
    out.writeAttribute("src", overlayURL, null);
    out.endElement("img");
    out.endElement("button");
    out.endElement("div");
  }

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
    writeScriptResource(context, "weblet://net.java.dev.mabon/mabon.js");
    writeScriptResource(context, "weblet://com.apress.projsf.ch7/inputDate.js");

    writeStyleResource(context, "weblet://com.apress.projsf.ch7/inputDate.css");
  }

  private String _determineDatePattern(
    FacesContext context,
    UIComponent  component)
  {
    UIInput input = (UIInput)component;
    Converter converter = getConverter(context, input);

    if (converter instanceof DateTimeConverter)
    {
      DateTimeConverter dateTime = (DateTimeConverter)converter;
      return dateTime.getPattern();
    }
    else
    {
      SimpleDateFormat dateFormat = (SimpleDateFormat)
                                    DateFormat.getDateInstance(DateFormat.SHORT);
      return dateFormat.toPattern();
    }
  }

  private String _determineTargetURL(
    FacesContext context,
    UIComponent  component)
  {
    UIInput input = (UIInput)component;
    Validator[] validators = input.getValidators();

    for (int i=0; i < validators.length; i++)
    {
      if (validators[i] instanceof DateValidator)
      {
        DateValidator validateDate = (DateValidator)validators[i];
        MethodBinding binding = validateDate.getAvailability();
        if (binding != null)
        {
          String expression = binding.getExpressionString();
          // #{backingBean.methodName} -> backingBean.methodName
          String bindingRef = expression.substring(2, expression.length() - 1);

          Application application = context.getApplication();
          ViewHandler handler = application.getViewHandler();
          return handler.getResourceURL(context, "mabon:/" + bindingRef);
        }
      }
    }

    return null;
  }

  private String _toJavaScript(
    String s)
  {
    if (s == null)
      return "null";

    return "'" + s + "'";
  }
}