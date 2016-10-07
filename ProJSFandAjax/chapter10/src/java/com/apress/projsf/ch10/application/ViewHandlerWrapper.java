package com.apress.projsf.ch10.application;

import javax.faces.application.ViewHandler;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.component.UIViewRoot;
import java.io.IOException;
import javax.faces.FacesException;

public class ViewHandlerWrapper extends ViewHandler
{
  public ViewHandlerWrapper(
    ViewHandler delegate)
  {
    _delegate = delegate;
  }

  public Locale calculateLocale(
    FacesContext context)
  {
    return _delegate.calculateLocale(context);
  }

  public String calculateRenderKitId(
    FacesContext context)
  {
    return _delegate.calculateRenderKitId(context);
  }

  public UIViewRoot createView(
    FacesContext context,
    String       viewId)
  {
    return _delegate.createView(context, viewId);
  }

  public String getActionURL(
    FacesContext context,
    String       path)
  {
    return _delegate.getActionURL(context, path);
  }

  public String getResourceURL(
    FacesContext context,
    String       path)
  {
    return _delegate.getResourceURL(context, path);
  }

  public void renderView(
    FacesContext context,
    UIViewRoot   view) throws IOException, FacesException
  {
    _delegate.renderView(context, view);
  }

  public UIViewRoot restoreView(
    FacesContext context,
    String       viewId)
  {
    return _delegate.restoreView(context, viewId);
  }

  public void writeState(
    FacesContext context) throws IOException
  {
    _delegate.writeState(context);
  }

  private final ViewHandler _delegate;
}

