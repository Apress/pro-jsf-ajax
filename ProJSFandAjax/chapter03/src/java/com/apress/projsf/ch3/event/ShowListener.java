package com.apress.projsf.ch3.event;

import javax.faces.event.FacesListener;

/**
 * The ShowListener listener.
 */
public interface ShowListener extends FacesListener
{
  /**
   * Processes a ShowEvent.
   * 
   * @param event  the show event
   */
  public void processShow(
    ShowEvent event);
}
