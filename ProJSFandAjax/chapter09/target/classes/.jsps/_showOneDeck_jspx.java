
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;


public class _showOneDeck_jspx extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "application/x-javaserver-faces");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _showOneDeck_jspx page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      {
        com.sun.faces.taglib.jsf_core.ViewTag __jsp_taghandler_1=(com.sun.faces.taglib.jsf_core.ViewTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ViewTag.class,"com.sun.faces.taglib.jsf_core.ViewTag");
        __jsp_taghandler_1.setParent(null);
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (OracleJspRuntime.checkStartBodyTagEval(__jsp_tag_starteval))
        {
          out=OracleJspRuntime.pushBodyIfNeeded(pageContext,__jsp_taghandler_1,__jsp_tag_starteval,out);
          do {
            {
              com.apress.projsf.ch8.taglib.pro.ProDocumentTag __jsp_taghandler_2=(com.apress.projsf.ch8.taglib.pro.ProDocumentTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch8.taglib.pro.ProDocumentTag.class,"com.apress.projsf.ch8.taglib.pro.ProDocumentTag styleClass title stylesheetURI");
              __jsp_taghandler_2.setParent(__jsp_taghandler_1);
              __jsp_taghandler_2.setStyleClass("MyDocument");
              __jsp_taghandler_2.setTitle("ProJSF : ProShowOneDeck");
              __jsp_taghandler_2.setStylesheetURI("/resources/stylesheet.css");
              __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
              {
                {
                  com.sun.faces.taglib.html_basic.FormTag __jsp_taghandler_3=(com.sun.faces.taglib.html_basic.FormTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.FormTag.class,"com.sun.faces.taglib.html_basic.FormTag id");
                  __jsp_taghandler_3.setParent(__jsp_taghandler_2);
                  __jsp_taghandler_3.setId("form");
                  __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    {
                      com.sun.faces.taglib.html_basic.PanelGridTag __jsp_taghandler_4=(com.sun.faces.taglib.html_basic.PanelGridTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.PanelGridTag.class,"com.sun.faces.taglib.html_basic.PanelGridTag width");
                      __jsp_taghandler_4.setParent(__jsp_taghandler_3);
                      __jsp_taghandler_4.setWidth("300px");
                      __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          com.apress.projsf.ch3.taglib.pro.ProShowOneDeckTag __jsp_taghandler_5=(com.apress.projsf.ch3.taglib.pro.ProShowOneDeckTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch3.taglib.pro.ProShowOneDeckTag.class,"com.apress.projsf.ch3.taglib.pro.ProShowOneDeckTag showItemId showListener");
                          __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                          __jsp_taghandler_5.setShowItemId("first");
                          __jsp_taghandler_5.setShowListener("#{showOneDeckBean.doShow}");
                          __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              com.apress.projsf.ch3.taglib.ShowItemTag __jsp_taghandler_6=(com.apress.projsf.ch3.taglib.ShowItemTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch3.taglib.ShowItemTag.class,"com.apress.projsf.ch3.taglib.ShowItemTag id");
                              __jsp_taghandler_6.setParent(__jsp_taghandler_5);
                              __jsp_taghandler_6.setId("first");
                              __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_7=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                                  __jsp_taghandler_7.setName("header");
                                  __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        com.sun.faces.taglib.html_basic.PanelGroupTag __jsp_taghandler_8=(com.sun.faces.taglib.html_basic.PanelGroupTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.PanelGroupTag.class,"com.sun.faces.taglib.html_basic.PanelGroupTag");
                                        __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                                        __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            com.sun.faces.taglib.html_basic.GraphicImageTag __jsp_taghandler_9=(com.sun.faces.taglib.html_basic.GraphicImageTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.GraphicImageTag.class,"com.sun.faces.taglib.html_basic.GraphicImageTag url alt style");
                                            __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                                            __jsp_taghandler_9.setUrl("/resources/java_small.jpg");
                                            __jsp_taghandler_9.setAlt("The Duke");
                                            __jsp_taghandler_9.setStyle("margin-right: 8px; vertical-align:bottom;");
                                            __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                                            if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,9);
                                          }
                                          {
                                            com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_10=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                            __jsp_taghandler_10.setParent(__jsp_taghandler_8);
                                            __jsp_taghandler_10.setValue("Java");
                                            __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                                            if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,9);
                                          }
                                        }
                                        if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,8);
                                      }
                                    } while (__jsp_taghandler_7.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,7);
                                }
                                {
                                  com.sun.faces.taglib.html_basic.PanelGridTag __jsp_taghandler_11=(com.sun.faces.taglib.html_basic.PanelGridTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.PanelGridTag.class,"com.sun.faces.taglib.html_basic.PanelGridTag columns");
                                  __jsp_taghandler_11.setParent(__jsp_taghandler_6);
                                  __jsp_taghandler_11.setColumns("1");
                                  __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      com.sun.faces.taglib.html_basic.OutputLinkTag __jsp_taghandler_12=(com.sun.faces.taglib.html_basic.OutputLinkTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputLinkTag.class,"com.sun.faces.taglib.html_basic.OutputLinkTag value");
                                      __jsp_taghandler_12.setParent(__jsp_taghandler_11);
                                      __jsp_taghandler_12.setValue("http://apress.com/book/bookDisplay.html?bID=10044");
                                      __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                      if (OracleJspRuntime.checkStartBodyTagEval(__jsp_tag_starteval))
                                      {
                                        out=OracleJspRuntime.pushBodyIfNeeded(pageContext,__jsp_taghandler_12,__jsp_tag_starteval,out);
                                        do {
                                          {
                                            com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_13=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                            __jsp_taghandler_13.setParent(__jsp_taghandler_12);
                                            __jsp_taghandler_13.setValue("Pro JSF: Building Rich Internet Components");
                                            __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                            if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,9);
                                          }
                                        } while (__jsp_taghandler_12.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                        out=OracleJspRuntime.popBodyIfNeeded(pageContext,out);
                                      }
                                      if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,8);
                                    }
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_14=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_14.setParent(__jsp_taghandler_11);
                                      __jsp_taghandler_14.setValue("Pro EJB 3");
                                      __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                      if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,8);
                                    }
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_15=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_15.setParent(__jsp_taghandler_11);
                                      __jsp_taghandler_15.setValue("Pro Apache Maven");
                                      __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                                      if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,8);
                                    }
                                  }
                                  if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,7);
                                }
                              }
                              if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,6);
                            }
                            {
                              com.apress.projsf.ch3.taglib.ShowItemTag __jsp_taghandler_16=(com.apress.projsf.ch3.taglib.ShowItemTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch3.taglib.ShowItemTag.class,"com.apress.projsf.ch3.taglib.ShowItemTag id");
                              __jsp_taghandler_16.setParent(__jsp_taghandler_5);
                              __jsp_taghandler_16.setId("second");
                              __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_17=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_17.setParent(__jsp_taghandler_16);
                                  __jsp_taghandler_17.setName("header");
                                  __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_18=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                        __jsp_taghandler_18.setParent(__jsp_taghandler_17);
                                        __jsp_taghandler_18.setValue("Open Source");
                                        __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                                        if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,8);
                                      }
                                    } while (__jsp_taghandler_17.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,7);
                                }
                                {
                                  com.sun.faces.taglib.html_basic.PanelGridTag __jsp_taghandler_19=(com.sun.faces.taglib.html_basic.PanelGridTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.PanelGridTag.class,"com.sun.faces.taglib.html_basic.PanelGridTag columns");
                                  __jsp_taghandler_19.setParent(__jsp_taghandler_16);
                                  __jsp_taghandler_19.setColumns("1");
                                  __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_20=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                                      __jsp_taghandler_20.setValue("Foundations of AJAX");
                                      __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                                      if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,8);
                                    }
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_21=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_21.setParent(__jsp_taghandler_19);
                                      __jsp_taghandler_21.setValue("Pro Apache Ant");
                                      __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                      if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,8);
                                    }
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_22=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_22.setParent(__jsp_taghandler_19);
                                      __jsp_taghandler_22.setValue("Pro PHP Security");
                                      __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                      if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,8);
                                    }
                                  }
                                  if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,7);
                                }
                              }
                              if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,6);
                            }
                            {
                              com.apress.projsf.ch3.taglib.ShowItemTag __jsp_taghandler_23=(com.apress.projsf.ch3.taglib.ShowItemTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch3.taglib.ShowItemTag.class,"com.apress.projsf.ch3.taglib.ShowItemTag id");
                              __jsp_taghandler_23.setParent(__jsp_taghandler_5);
                              __jsp_taghandler_23.setId("third");
                              __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_24=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_24.setParent(__jsp_taghandler_23);
                                  __jsp_taghandler_24.setName("header");
                                  __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_25=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                        __jsp_taghandler_25.setParent(__jsp_taghandler_24);
                                        __jsp_taghandler_25.setValue(".NET");
                                        __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                                        if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,8);
                                      }
                                    } while (__jsp_taghandler_24.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,7);
                                }
                                {
                                  com.sun.faces.taglib.html_basic.PanelGridTag __jsp_taghandler_26=(com.sun.faces.taglib.html_basic.PanelGridTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.PanelGridTag.class,"com.sun.faces.taglib.html_basic.PanelGridTag columns");
                                  __jsp_taghandler_26.setParent(__jsp_taghandler_23);
                                  __jsp_taghandler_26.setColumns("1");
                                  __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_27=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_27.setParent(__jsp_taghandler_26);
                                      __jsp_taghandler_27.setValue("Pro .NET Extreme Programming");
                                      __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                                      if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,8);
                                    }
                                    {
                                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_28=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                                      __jsp_taghandler_28.setParent(__jsp_taghandler_26);
                                      __jsp_taghandler_28.setValue(".NET for Delphi Programmers");
                                      __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                                      if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,8);
                                    }
                                  }
                                  if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,7);
                                }
                              }
                              if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,6);
                            }
                            {
                              com.apress.projsf.ch3.taglib.ShowListenerTag __jsp_taghandler_29=(com.apress.projsf.ch3.taglib.ShowListenerTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch3.taglib.ShowListenerTag.class,"com.apress.projsf.ch3.taglib.ShowListenerTag type");
                              __jsp_taghandler_29.setParent(__jsp_taghandler_5);
                              __jsp_taghandler_29.setType("com.apress.projsf.ch3.application.MyShowListener");
                              __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                              if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,6);
                            }
                          }
                          if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,5);
                        }
                      }
                      if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,4);
                    }
                  }
                  if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,3);
                }
              }
              if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                return;
              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,2);
            }
          } while (__jsp_taghandler_1.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
          out=OracleJspRuntime.popBodyIfNeeded(pageContext,out);
        }
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }


    }
    catch( Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch( Exception clearException) {
        }
        pageContext.handlePageException( e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext,true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
}
