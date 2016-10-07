package com.apress.projsf.ch5.render.html.basic;

import java.io.IOException;

import java.util.Map;
import java.util.TimeZone;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;

import com.apress.projsf.ch2.render.html.HtmlRenderer;

/**
 * Renders the UIInput component as a date field.
 */
public class HtmlInputDateRenderer extends HtmlRenderer
{
  /**
   * The title attribute.
   */
  public static String TITLE_ATTR = "title";

  /**
   * The onchange attribute.
   */
  public static String ONCHANGE_ATTR = "onchange";

  /**
   * Decodes the incoming request to detect the submitted value.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   */
  public void decode(
    FacesContext context,
    UIComponent  component)
  {
    UIInput input = (UIInput)component;
    String clientId = input.getClientId(context);

    ExternalContext external = context.getExternalContext();
    Map requestParams = external.getRequestParameterMap();
    String submittedValue = (String)requestParams.get(clientId);

    // store the submitted value from the request on the input component
    input.setSubmittedValue(submittedValue);
  }

  /**
   * Completes the encoded output for this component.
   *
   * Note: it is important to delay writing the markup for an input
   *       component until encodeEnd, so that a nested converter tags
   *       is guaranteed to have been executed, possibly changing before
   *       the component's converter is used during rendering.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   *
   * @throws IOException  if an I/O error occurs during rendering
   */
  public void encodeEnd(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    String valueString = getValueAsString(context, component);
    String clientId = component.getClientId(context);

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
    out.writeAttribute("id", clientId, null);
    out.writeAttribute("name", clientId, null);
    if (valueString != null)
      out.writeAttribute("value", valueString, null);
    if (onchange != null)
      out.writeAttribute("onchange", onchange, ONCHANGE_ATTR);
    out.endElement("input");
    
    // <img class="ProInputDateOverlay"
    //      src="weblet://com.apress.projsf.ch5/inputDateOverlay.gif" >
    ViewHandler handler = context.getApplication().getViewHandler();
    String overlayURL = handler.getResourceURL(context, 
                                               "weblet://com.apress.projsf.ch5/inputDateOverlay.gif");
    out.startElement("img", null);
    out.writeAttribute("class", "ProInputDateOverlay", null);
    out.writeAttribute("src", overlayURL, null);
    out.endElement("img");
    
    out.endElement("div");
  }

  /**
   * Returns the submitted value converted to a strongly typed object
   * for this input component.
   *
   * @param context         the Faces context
   * @param component       the Faces component
   * @param submittedValue  the submitted value
   *
   * @return  the strongly-typed, converted value
   *
   * @throws ConverterException  if an error occurs during conversion
   */
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

  /**
   * Indicates that this Renderer is responsible for rendering any
   * child components.
   */
  public boolean getRendersChildren()
  {
    return true;
  }

  /**
   * Returns the Converter to use when converting the newly submitted
   * request parameter value to a strongly-typed object.
   *
   * @param context  the Faces context
   * @param input    the Faces input component
   *
   * @return  the Faces Converter to be used
   */
  protected final Converter getConverter(
    FacesContext context,
    UIInput      input)
  {
    Converter converter = input.getConverter();
    if (converter == null)
    {
      // default the converter
      DateTimeConverter datetime = new DateTimeConverter();
      datetime.setLocale(context.getViewRoot().getLocale());
      datetime.setTimeZone(TimeZone.getDefault());
      converter = datetime;
    }
    return converter;
  }

  /**
   * Write out the HtmlShowOneDeck resources.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   */
  protected void encodeResources(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    writeStyleResource(context,
                       "weblet://com.apress.projsf.ch5/inputDate.css");
  }

  /**
   * Returns the submitted value, if present, otherwise returns 
   * the value attribute converted to string.
   *
   * @param context    the Faces context
   * @param component  the Faces component
   * 
   * @return  the value string for the specified component
   *
   * @throws IOException  if an I/O exception occurs during rendering
   */
  protected String getValueAsString(
    FacesContext context,
    UIComponent  component) throws IOException
  {
    // lookup the submitted value
    UIInput input = (UIInput)component;
    String valueString = (String)input.getSubmittedValue();

    // on initial render (or after a successful postback)
    // the submitted value will be null
    if (valueString == null)
    {
      // lookup the strongly-typed value for this input
      Object value = input.getValue();
      if (value != null)
      {
        // if present, convert the strongly-typed value
        // to a string for rendering
        Converter converter = getConverter(context, input);
        valueString = converter.getAsString(context, component, value);
      }
    }
      
    return valueString;
  }
    
}
