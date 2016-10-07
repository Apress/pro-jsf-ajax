package com.apress.projsf.ch3.component;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.apress.projsf.ch3.event.ShowListener;

/**
 * The UIShowOne behavioral component.
 */
public class UIShowOne extends UIComponentBase
                       implements ShowSource, NamingContainer
{
  /**
   * The component type for this component.
   */
  public static final String COMPONENT_TYPE = "com.apress.projsf.ShowOne";

  /**
   * The component family for this component.
   */
  public static final String COMPONENT_FAMILY = "com.apress.projsf.ShowOne";

  /**
   * Creates a new UIShowOne.
   */
  public UIShowOne()
  {
  }

  /**
   * Returns the component family for this component.
   * 
   * @return the component family
   */
  public String getFamily()
  {
    return COMPONENT_FAMILY;
  }
  
  /**
   * Sets the show item child id to show.
   * 
   * @param showItemId  the new show item child id to show.
   */
  public void setShowItemId(
    String showItemId)
  {
    _showItemId = showItemId;
  }

  /**
   * Returns the show item child id to show.
   * 
   * @return  the show item child id to show
   */
  public String getShowItemId()
  {
    if (_showItemId != null)
      return _showItemId;
  
    ValueBinding binding = getValueBinding("showItemId");
    if (binding != null)
    {
      FacesContext context = FacesContext.getCurrentInstance();
      return (String)binding.getValue(context);
    }

    return null;
  }

  /**
   * Adds a ShowListener to this UIShowOne component.
   * 
   * @param listener  the show listener to be added
   */
  public void addShowListener(
    ShowListener listener)
  {
    addFacesListener(listener);
  }
  
  /**
   * Removes a ShowListener to this UIShowOne component.
   * 
   * @param listener  the show listener to be removed
   */
  public void removeShowListener(
    ShowListener listener)
  {
    removeFacesListener(listener);
  }
  
  /**
   * Returns all ShowListeners for this UIShowOne component.
   * 
   * @return the show listener array
   */
  public ShowListener[] getShowListeners()
  {
    return (ShowListener[])getFacesListeners(ShowListener.class);
  }

  /**
   * Returns the saved state for this component.
   * 
   * @param context the Faces context
   */
  public Object saveState(
    FacesContext context)
  {
    Object values[] = new Object[2];
    values[0] = super.saveState(context);
    values[1] = _showItemId;
    return values;
  }
  
  /**
   * Restores the state of this component.
   * 
   * @param context the Faces context
   * @param state   the saved state
   */
  public void restoreState(
    FacesContext context,
    Object       state)
  {
    Object values[] = (Object[])state;
    super.restoreState(context, values[0]);
    _showItemId = (String)values[1];
  }

  public void processDecodes(
    FacesContext context)
  {
    if (context == null) 
      throw new NullPointerException();

    if (!isRendered())
      return;

    String showItemId = getShowItemId();
    if (showItemId != null && getChildCount() > 0)
    {
      List children = getChildren();
      for (Iterator iter = children.iterator(); iter.hasNext();)
      {
        UIShowItem showItem = (UIShowItem)iter.next();
        if (showItemId.equals(showItem.getId()))
          showItem.processDecodes(context);
      }
    }

    // decode the showOne component last
    decode(context);
  }

  private String        _showItemId;  
}
