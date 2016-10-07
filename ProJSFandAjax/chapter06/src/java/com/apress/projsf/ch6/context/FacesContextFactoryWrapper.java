package com.apress.projsf.ch6.context;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;

/**
 * FacesContextFactoryWrapper implements the decorator pattern 
 * for FacesContextFactory.
 */
public class FacesContextFactoryWrapper extends FacesContextFactory
{
  /**
   * Creates a new FacesContextFactoryWrapper.
   * 
   * @param delegate  the FacesContextFactory delegate
   */
  public FacesContextFactoryWrapper(
    FacesContextFactory delegate)
  {
    _delegate = delegate;
  }

  /**
   * Returns the new FacesContext instance.
   * 
   * @param context     the servlet or portlet context
   * @param request     the servlet or portlet request
   * @param response    the servlet or portlet response
   * @param lifecycle   the Faces lifecycle
   * 
   * @return the new FacesContext instance
   * 
   * @throws FacesException  if an error occurs
   */
  public FacesContext getFacesContext(
    Object    context, 
    Object    request, 
    Object    response, 
    Lifecycle lifecycle) throws FacesException
  {
    return _delegate.getFacesContext(context, request, response, lifecycle);
  }
  
  private final FacesContextFactory _delegate;
}
