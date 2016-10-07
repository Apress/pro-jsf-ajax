package com.apress.projsf.ch9.render.htc.ajax;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import com.apress.projsf.ch5.render.html.basic.HtmlInputDateRenderer;
import com.apress.projsf.ch7.validate.DateValidator;

public class HtcAjaxInputDateRenderer extends HtmlInputDateRenderer
{
  public void encodeEnd(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    String pattern = _determineDatePattern(context, component);
    String targetURL = _determineTargetURL(context, component);

    UIInput input = (UIInput)component;
    String valueString = getValueAsString(context, component);
    String clientId = input.getClientId(context);

    ViewHandler handler = context.getApplication().getViewHandler();
    String behaviorURL = handler.getResourceURL(context, "weblet://com.apress.projsf.ch9/inputDate.htc");

    ResponseWriter out = context.getResponseWriter();
    writeScriptInline(context, "document.namespaces.add('pro', 'http://projsf.apress.com/tags');");
    out.write("<?import namespace=\"pro\" implementation=\"" + behaviorURL + "\" ?>");

    out.startElement("pro:inputDate", component);
    out.writeAttribute("id", clientId, null);
    if (valueString != null)
      out.writeAttribute("value", valueString, null);
    if (pattern != null)
      out.writeAttribute("pattern", pattern, null);
    if (targetURL != null)
      out.writeAttribute("targetURL", targetURL, null);
    out.endElement("pro:inputDate");
  }

  protected void encodeResources(FacesContext context,
                                 UIComponent component) throws IOException
  {
    super.encodeResources(context, component);

    writeScriptResource(context, "weblet://org.dojotoolkit.browserio/dojo.js");
    writeScriptResource(context, "weblet://net.java.dev.mabon/mabon.js");
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
}
