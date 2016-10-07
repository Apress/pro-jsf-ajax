
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;


public class _inputDate_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _inputDate_jspx page = this;
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
              com.apress.projsf.ch8.taglib.pro.ProDocumentTag __jsp_taghandler_2=(com.apress.projsf.ch8.taglib.pro.ProDocumentTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch8.taglib.pro.ProDocumentTag.class,"com.apress.projsf.ch8.taglib.pro.ProDocumentTag title");
              __jsp_taghandler_2.setParent(__jsp_taghandler_1);
              __jsp_taghandler_2.setTitle("ProJSF : ProInputDate");
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
                    out.write(__oracle_jsp_text[0]);
                    out.write( "<br/>\r\n");
                    {
                      com.apress.projsf.ch2.taglib.pro.ProInputDateTag __jsp_taghandler_4=(com.apress.projsf.ch2.taglib.pro.ProInputDateTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch2.taglib.pro.ProInputDateTag.class,"com.apress.projsf.ch2.taglib.pro.ProInputDateTag id value title");
                      __jsp_taghandler_4.setParent(__jsp_taghandler_3);
                      __jsp_taghandler_4.setId("dateField");
                      __jsp_taghandler_4.setValue("#{inputDateBean.date}");
                      __jsp_taghandler_4.setTitle("Date Field Component");
                      __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          com.sun.faces.taglib.jsf_core.ConvertDateTimeTag __jsp_taghandler_5=(com.sun.faces.taglib.jsf_core.ConvertDateTimeTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ConvertDateTimeTag.class,"com.sun.faces.taglib.jsf_core.ConvertDateTimeTag pattern");
                          __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                          __jsp_taghandler_5.setPattern("d MMMMM yyyy");
                          __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                          if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,5);
                        }
                        {
                          com.apress.projsf.ch7.taglib.ValidateDateTag __jsp_taghandler_6=(com.apress.projsf.ch7.taglib.ValidateDateTag)OracleJspRuntime.getTagHandler(pageContext,com.apress.projsf.ch7.taglib.ValidateDateTag.class,"com.apress.projsf.ch7.taglib.ValidateDateTag availability");
                          __jsp_taghandler_6.setParent(__jsp_taghandler_4);
                          __jsp_taghandler_6.setAvailability("#{inputDateBean.getAvailability}");
                          __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                          if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,5);
                        }
                      }
                      if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,4);
                    }
                    out.write( "<br/>\r\n");
                    {
                      com.sun.faces.taglib.html_basic.MessageTag __jsp_taghandler_7=(com.sun.faces.taglib.html_basic.MessageTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.MessageTag.class,"com.sun.faces.taglib.html_basic.MessageTag for");
                      __jsp_taghandler_7.setParent(__jsp_taghandler_3);
                      __jsp_taghandler_7.setFor("dateField");
                      __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                      if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,4);
                    }
                    out.write( "<br/>\r\n");
                    {
                      com.sun.faces.taglib.html_basic.CommandButtonTag __jsp_taghandler_8=(com.sun.faces.taglib.html_basic.CommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.CommandButtonTag.class,"com.sun.faces.taglib.html_basic.CommandButtonTag value");
                      __jsp_taghandler_8.setParent(__jsp_taghandler_3);
                      __jsp_taghandler_8.setValue("Submit");
                      __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                      if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,4);
                    }
                    out.write( "<br/>\r\n");
                    {
                      com.sun.faces.taglib.html_basic.OutputTextTag __jsp_taghandler_9=(com.sun.faces.taglib.html_basic.OutputTextTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputTextTag.class,"com.sun.faces.taglib.html_basic.OutputTextTag value");
                      __jsp_taghandler_9.setParent(__jsp_taghandler_3);
                      __jsp_taghandler_9.setValue("#{inputDateBean.date}");
                      __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          com.sun.faces.taglib.jsf_core.ConvertDateTimeTag __jsp_taghandler_10=(com.sun.faces.taglib.jsf_core.ConvertDateTimeTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ConvertDateTimeTag.class,"com.sun.faces.taglib.jsf_core.ConvertDateTimeTag pattern");
                          __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                          __jsp_taghandler_10.setPattern("d MMMMM yyyy");
                          __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                          if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,5);
                        }
                      }
                      if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,4);
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
  private static final char __oracle_jsp_text[][]=new char[1][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "\n        Please enter a date with the pattern \"d MMMMM yyyy\".\n        ".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
