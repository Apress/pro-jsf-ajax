package com.apress.projsf.ch3.event;

import javax.faces.component.StateHolder;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

/**
 * The ShowAdapter calls a MethodBinding with the same signature
 * as the <code>processShow</code> method.
 */
public class ShowAdapter implements ShowListener,
                                    StateHolder
{
  public static Class[] SIGNATURE = new Class[] { ShowEvent.class };

  /**
   * Creates a new ShowAdapter.
   * 
   * @param showMethod  the MethodBinding to adapt
   */
  public ShowAdapter(
    MethodBinding showMethod)
  {
    _showMethod = showMethod;
  }
  
  /**
   * Processes a ShowEvent.
   * 
   * @param event  the show event
   */
  public void processShow(
    ShowEvent event)
  {
    FacesContext context = FacesContext.getCurrentInstance();
    _showMethod.invoke(context, new Object[]{event});
  }

  /**
   * Saves the internal state of this ShowAdapter.
   * 
   * @param context  the Faces context
   * 
   * @return  the saved state
   */
  public Object saveState(
    FacesContext context)
  {
    return UIComponentBase.saveAttachedState(context, _showMethod);
  }

  /**
   * Restores the internal state of this ShowAdapter.
   * 
   * @param context  the Faces context
   * @param object   the state to restore
   */
  public void restoreState(
    FacesContext context, 
    Object       object)
  {
    _showMethod = (MethodBinding) 
                    UIComponentBase.restoreAttachedState(context, object);
  }

  /**
   * Returns true if this ShowAdapter is transient and should
   * not be state saved, otherwise false.
   * 
   * @return  the value of transient
   */
  public boolean isTransient()
  {
    return _transient;
  }

  /**
   * Indicates whether or not this ShowAdapter is transient and should
   * not be state saved.
   * 
   * @param isTransient  the new value for transient
   */
  public void setTransient(
    boolean isTransient)
  {
    _transient = isTransient;
  }

  private MethodBinding _showMethod;
  private boolean       _transient;
}
