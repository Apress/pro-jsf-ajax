package com.apress.projsf.ch3.taglib;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.apress.projsf.ch3.component.ShowSource;
import com.apress.projsf.ch3.event.ShowListener;

/**
 * ShowListenerTag listener tag handler.
 */
public class ShowListenerTag extends TagSupport
{
  /**
   * Sets the fully qualified class name of the
   * {@link ShowListener} instance to be created.
   *
   * @param type  the class name
   */
  public void setType(
    String type) 
  {
    _type = type;
  }


  /**
   * Attaches a {@link ShowListener} to the surrounding {@link UIComponent}.
   * 
   * @return SKIP_BODY, always
   * 
   * @throws JspException  if an error condition occurs
   */
  public int doStartTag() throws JspException 
  {
    UIComponentTag tag = UIComponentTag.getParentUIComponentTag(pageContext);
    if (tag == null) 
      throw new JspException("Not inside UIComponentTag");

    if (tag.getCreated()) 
    {
      UIComponent component = tag.getComponentInstance();
      if (component == null)
        throw new JspException("Component instance is null");
      if (!(component instanceof ShowSource))
        throw new JspException("Component is not a ShowSource");

      ShowSource showSource = (ShowSource)component;
      String className = _type;

      if (UIComponentTag.isValueReference(_type))
      {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ValueBinding vb = application.createValueBinding(_type);
        className = (String)vb.getValue(context);
      }
       
      ShowListener listener = createShowListener(className);
      showSource.addShowListener(listener);
    }
    
    return (SKIP_BODY);
  }
   
  /**
   * Release resources.
   */
  public void release() 
  {
    _type = null;
  }

  /**
   * Create and return a new {@link ShowListener} to be registered
   * on the surrounding {@link UIComponent}.
   *
   * @throws JspException  if a new listener instance cannot be created
   */
  protected ShowListener createShowListener(
    String className) throws JspException 
  {
    try 
    {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      Class clazz = loader.loadClass(className);
      return ((ShowListener) clazz.newInstance());
    } 
    catch (Exception e) 
    {
      throw new JspException(e);
    }
  }

  /**
   * The fully qualified class name of the {@link ShowListener}
   * instance to be created.
   */
  private String _type;
}
