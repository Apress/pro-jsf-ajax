package com.apress.projsf.ch3.application;

import com.apress.projsf.ch3.event.ShowEvent;
import com.apress.projsf.ch3.event.ShowListener;

public class MyShowListener implements ShowListener
{
  public void processShow(
    ShowEvent event)
  {
    String oldShowItemId = event.getOldShowItemId();
    String newShowItemId = event.getNewShowItemId();
    System.out.println("MyShowListener [oldShowItemId=" + oldShowItemId + "," +
                        "newShowItemId=" + newShowItemId + "]");
  }
}
