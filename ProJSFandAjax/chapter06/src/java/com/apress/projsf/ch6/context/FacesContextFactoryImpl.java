package com.apress.projsf.ch6.context;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;

import javax.servlet.http.HttpServletResponse;

import com.apress.projsf.ch6.external.servlet.DeferredContentTypeResponse;

/**
 * FacesContextFactoryImpl supports additional processing of the response.
 */
public class FacesContextFactoryImpl extends FacesContextFactoryWrapper
{
  /**
   * Creates a new FacesContextFactoryImpl.
   * 
   * @param delegate  the FacesContextFactory delegate
   */
  public FacesContextFactoryImpl(
    FacesContextFactory delegate)
  {
    super(delegate);
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
    if (response instanceof HttpServletResponse)
    {
      response = new DeferredContentTypeResponse((HttpServletResponse)response);
    }
    // TODO: Portlet API
    
    return super.getFacesContext(context, request, response, lifecycle);
  }
}
