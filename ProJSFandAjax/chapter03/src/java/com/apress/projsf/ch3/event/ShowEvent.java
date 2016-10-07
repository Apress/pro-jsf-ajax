package com.apress.projsf.ch3.event;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;
import javax.faces.event.PhaseId;

/**
 * The ShowEvent event.
 */
public class ShowEvent extends FacesEvent
{
  /**
   * Creates a new ShowEvent.
   * 
   * @param source         the source of the event
   * @param oldShowItemId  the previously showing item identifier
   * @param newShowItemId  the currently showing item identifier
   */
  public ShowEvent(
    UIComponent source,
    String      oldShowItemId,
    String      newShowItemId)
  {
    super(source);
    setPhaseId(PhaseId.INVOKE_APPLICATION);
    _oldShowItemId = oldShowItemId;
    _newShowItemId = newShowItemId;
  }

  /**
   * Returns the previously showing item id.
   * 
   * @return  the old show item id
   */
  public String getOldShowItemId()
  {
    return _oldShowItemId;
  }

  /**
   * Returns the newly showing item id.
   * 
   * @return  the new show item id
   */
  public String getNewShowItemId()
  {
    return _newShowItemId;
  }

  /**
   * Returns true if the listener is a ShowListener.
   * 
   * @param listener  the listener to check
   */
  public boolean isAppropriateListener(FacesListener listener)
  {
    return (listener instanceof ShowListener);
  }
  
  /**
   * Delivers this event to the ShowListener.
   * 
   * @param listener  the show listener
   */
  public void processListener(FacesListener listener)
  {
    ((ShowListener) listener).processShow(this);
  }

  private String  _oldShowItemId;
  private String  _newShowItemId;
}
