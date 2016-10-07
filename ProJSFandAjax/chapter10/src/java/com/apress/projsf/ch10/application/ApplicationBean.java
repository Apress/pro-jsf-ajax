package com.apress.projsf.ch10.application;

import javax.faces.render.RenderKitFactory;

import oracle.adf.view.faces.context.AdfFacesContext;
import oracle.adf.view.faces.context.Agent;

public class ApplicationBean
{
  public String getRenderKitId()
  {
    AdfFacesContext afc = AdfFacesContext.getCurrentInstance();
    Agent agent = afc.getAgent();

    if (Agent.AGENT_GECKO.equals(agent.getAgentName()))
    {
      return "com.apress.projsf.xul.ajax";
    }
    else if (Agent.AGENT_IE.equals(agent.getAgentName()) &&
             Agent.TYPE_DESKTOP.equals(agent.getType()))
    {
      return "com.apress.projsf.htc.ajax";
    }
    else if (Agent.AGENT_WEBKIT.equals(agent.getAgentName()))
    {
      return "com.apress.projsf.html.ajax";
    }
    else
    {
      // default to standard HTML Basic for PDAs, etc.
      return RenderKitFactory.HTML_BASIC_RENDER_KIT;
    }
  }
}