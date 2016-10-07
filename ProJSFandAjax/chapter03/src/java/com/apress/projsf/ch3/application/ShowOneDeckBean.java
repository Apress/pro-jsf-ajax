package com.apress.projsf.ch3.application;

import com.apress.projsf.ch3.event.ShowEvent;

/**
 * ShowOneDeckBean is a backing bean for showOneDeck.jspx document.
 */
public class ShowOneDeckBean
{
  /**
   * The ShowListener method binding.
   *
   * @param event  the show event
   */
  public void doShow(
    ShowEvent event)
  {
    String oldShowItemId = event.getOldShowItemId();
    String newShowItemId = event.getNewShowItemId();
    System.out.println("BackingBean [oldShowItemId=" + oldShowItemId + "," +
                                    "newShowItemId=" + newShowItemId + "]");
  }
}