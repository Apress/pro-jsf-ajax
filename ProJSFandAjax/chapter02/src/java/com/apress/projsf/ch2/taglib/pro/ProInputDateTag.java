package com.apress.projsf.ch2.taglib.pro;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import com.apress.projsf.ch2.component.pro.ProInputDate;
import com.apress.projsf.ch2.taglib.UIComponentTagSupport;

/**
 * ProInputDateTag component tag handler.
 */
public class ProInputDateTag extends UIComponentTagSupport
{
  /**
   * Returns the component type.
   *
   * @return  the component type
   */
  public String getComponentType()
  {
    return ProInputDate.COMPONENT_TYPE;
  }

  /**
   * Returns the renderer type.
   *
   * @return  the renderer type
   */
  public String getRendererType()
  {
    return ProInputDate.RENDERER_TYPE;
  }

  /**
   * Sets the converter attribute value.
   *
   * @param converter  the converter attribute value
   */
  public void setConverter(
    String converter)
  {
    _converter = converter;
  }


  /**
   * Sets the immediate attribute value.
   *
   * @param immediate  the immediate attribute value
   */
  public void setImmediate(
    String immediate)
  {
    _immediate = immediate;
  }


  /**
   * Sets the required attribute value.
   *
   * @param required  the required attribute value
   */
  public void setRequired(
    String required)
  {
    _required = required;
  }


  /**
   * Sets the validator attribute value.
   *
   * @param validator  the validator attribute value
   */
  public void setValidator(
    String validator)
  {
    _validator = validator;
  }


  /**
   * Sets the value attribute value.
   *
   * @param value  the value attribute value
   */
  public void setValue(
    String value)
  {
    _value = value;
  }

  /**
   * Sets the valueChangeListener attribute value.
   *
   * @param valueChangeListener  the valueChangeListener attribute value
   */
  public void setValueChangeListener(
    String valueChangeListener)
  {
    _valueChangeListener = valueChangeListener;
  }

  /**
   * Sets the onchange attribute value.
   *
   * @param onchange  the onblur attribute value
   */
  public void setOnchange(
    String onchange)
  {
    _onchange = onchange;
  }

  /**
   * Sets the title attribute value.
   *
   * @param title  the title attribute value
   */
  public void setTitle(
    String title)
  {
    _title = title;
  }

  /**
   * Releases the internal state used by the tag.
   */
  public void release()
  {
    _converter = null;
    _immediate = null;
    _required = null;
    _validator = null;
    _value = null;
    _valueChangeListener = null;
    _onchange = null;
    _title = null;
  }

  /**
   * Transfers the property values from this tag to the component.
   *
   * @param component  the target component
   */
  protected void setProperties(
    UIComponent component)
  {
    super.setProperties(component);

    // Behavioral properties
    setValueBindingProperty(component, "converter", _converter);
    setBooleanProperty(component, "immediate", _immediate);
    setBooleanProperty(component, "required", _required);
    setValueBindingProperty(component, "validator", _validator);
    setStringProperty(component, "value", _value);
    setMethodBindingProperty(component, "valueChangeListener",
                             _valueChangeListener,
                             new Class[] { ValueChangeEvent.class });

    // Renderer-specific attributes
    setStringProperty(component, "onchange", _onchange);
    setStringProperty(component, "title", _title);
  }

  /**
   * The converter attribute.
   */
  private String _converter;

  /**
   * The immediate attribute.
   */
  private String _immediate;

  /**
   * The required attribute.
   */
  private String _required;

  /**
   * The validator attribute.
   */
  private String _validator;

  /**
   * The value attribute.
   */
  private String _value;

  /**
   * The valueChangeListener attribute.
   */
  private String _valueChangeListener;

  /**
   * The onchange attribute.
   */
  private String _onchange;

  /**
   * The title attribute.
   */
  private String _title;
}