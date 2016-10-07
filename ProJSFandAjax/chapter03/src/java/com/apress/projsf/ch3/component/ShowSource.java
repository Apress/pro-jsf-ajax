package com.apress.projsf.ch3.component;

import com.apress.projsf.ch3.event.ShowListener;

/**
 * A ShowSource is the source of ShowEvents.
 */
public interface ShowSource
{
  /**
   * Adds a ShowListener to this ShowSource.
   * 
   * @param listener  the show listener to be added
   */
  public void addShowListener(
    ShowListener listener);
  
  /**
   * Removes a ShowListener from this ShowSource.
   * 
   * @param listener  the show listener to be removed
   */
  public void removeShowListener(
    ShowListener listener);
  
  /**
   * Returns all ShowListeners for this ShowSource.
   * 
   * @return the show listener array
   */
  public ShowListener[] getShowListeners();
}
