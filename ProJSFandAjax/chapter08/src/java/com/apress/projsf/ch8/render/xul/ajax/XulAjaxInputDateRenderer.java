package com.apress.projsf.ch8.render.xul.ajax;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.el.MethodBinding;
import javax.faces.validator.Validator;

import com.apress.projsf.ch8.render.xul.XulRenderer;
import com.apress.projsf.ch7.validate.DateValidator;

public class XulAjaxInputDateRenderer extends XulRenderer
{
  public void decode(
    FacesContext context, 
    UIComponent  component)
  {
    UIInput input = (UIInput)component;
    String clientId = input.getClientId(context);
    
    ExternalContext external = context.getExternalContext();
    Map requestParams = external.getRequestParameterMap();
    String submittedValue = (String)requestParams.get(clientId);

    input.setSubmittedValue(submittedValue);
  }

  public Object getConvertedValue(
    FacesContext context, 
    UIComponent  component,
    Object       submittedValue) throws ConverterException
  {
    UIInput input = (UIInput)component;
    Converter converter = getConverter(context, input);
    String valueString = (String)submittedValue;
    return converter.getAsObject(context, component, valueString);
  }

  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    writeScriptResource(context, "weblet://org.dojotoolkit.browserio/dojo.js");
    writeScriptResource(context, "weblet://net.java.dev.mabon/mabon.js");
  }
  
  public void encodeEnd(
    FacesContext context, 
    UIComponent  component) throws IOException
  {
    String pattern = determineDatePattern(context, component);
    String targetURL = determineTargetURL(context, component);

    UIInput input = (UIInput)component;
    String valueString = (String)input.getSubmittedValue();

    if (valueString == null)
    {
      Object value = input.getValue();
      if (value != null)
      {
        Converter converter = getConverter(context, input);
        valueString = converter.getAsString(context, component, value);
      }
    }

    String clientId = input.getClientId(context);

    ResponseWriter out = context.getResponseWriter();
    out.startElement("pro:inputDate", component);
    out.writeAttribute("id", clientId, null);
    out.writeAttribute("value", valueString, null);
    out.writeAttribute("pattern", pattern, null);
    out.writeAttribute("targetURL", targetURL, null);
    out.endElement("pro:inputDate");
  }
  
  private final Converter getConverter(
    FacesContext context,
    UIInput      input)
  {
    Converter converter = input.getConverter();
    if (converter == null)
    {
      DateTimeConverter datetime = new DateTimeConverter();
      datetime.setLocale(context.getViewRoot().getLocale());
      datetime.setTimeZone(TimeZone.getDefault());
      converter = datetime;
    }
    return converter;
  }

  private String determineDatePattern(
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

  private String determineTargetURL(
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