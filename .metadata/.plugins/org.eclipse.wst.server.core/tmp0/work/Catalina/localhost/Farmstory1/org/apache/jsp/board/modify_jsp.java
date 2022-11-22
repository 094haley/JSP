/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.67
 * Generated at: 2022-11-17 03:45:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kr.co.farmstory1.bean.ArticleBean;
import kr.co.farmstory1.dao.ArticleDAO;
import kr.co.farmstory1.bean.UserBean;

public final class modify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/_footer.jsp", Long.valueOf(1668501256866L));
    _jspx_dependants.put("/_header.jsp", Long.valueOf(1668602119754L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("kr.co.farmstory1.dao.ArticleDAO");
    _jspx_imports_classes.add("kr.co.farmstory1.bean.UserBean");
    _jspx_imports_classes.add("kr.co.farmstory1.bean.ArticleBean");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String result = request.getParameter("result");
	UserBean sessUser = (UserBean)session.getAttribute("sessUser");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>팜스토리::메인</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/Farmstory1/css/style.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/Farmstory1/user/css/style.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/Farmstory1/board/css/style.css\">\r\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js\"></script>\r\n");
      out.write("	<script>\r\n");
      out.write("		let result = \"");
      out.print( result );
      out.write("\";\r\n");
      out.write("		if(result == '201') {\r\n");
      out.write("			alert('수정완료!') \r\n");
      out.write("		}else if(result == '202') {\r\n");
      out.write("			alert('삭제완료!');\r\n");
      out.write("		}\r\n");
      out.write("	</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div id=\"wrapper\">\r\n");
      out.write("        <header>\r\n");
      out.write("            <a href=\"/Farmstory1/index.jsp\" class=\"logo\"><img src=\"/Farmstory1/img/logo.png\" alt=\"로고\"></a>\r\n");
      out.write("            <p>\r\n");
      out.write("                <a href=\"/Farmstory1\">HOME |</a>\r\n");
      out.write("                ");
 if(sessUser == null) { 
      out.write("\r\n");
      out.write("                <a href=\"/Farmstory1/user/login.jsp\">로그인 |</a>\r\n");
      out.write("                <a href=\"/Farmstory1/user/terms.jsp\">회원가입 |</a>\r\n");
      out.write("                ");
 } else { 
      out.write("\r\n");
      out.write("                <a href=\"/Farmstory1/user/proc/logout.jsp\">로그아웃 |</a>\r\n");
      out.write("                ");
 }  
      out.write("\r\n");
      out.write("                <a href=\"#\">고객센터</a>\r\n");
      out.write("            </p>\r\n");
      out.write("            <img src=\"/Farmstory1/img/head_txt_img.png\" alt=\"3만원 이상 무료배송\">\r\n");
      out.write("            <ul class=\"gnb\">\r\n");
      out.write("                <li><a href=\"/Farmstory1/introduction/hello.jsp\"></a></li>\r\n");
      out.write("                <li><a href=\"/Farmstory1/board/list.jsp?group=market&cate=market\"><img src=\"/Farmstory1/img/head_menu_badge.png\" alt=\"30%\"></a></li>\r\n");
      out.write("                <li><a href=\"/Farmstory1/board/list.jsp?group=croptalk&cate=story\"></a></li>\r\n");
      out.write("                <li><a href=\"/Farmstory1/board/list.jsp?group=event&cate=event\"></a></li>\r\n");
      out.write("                <li><a href=\"/Farmstory1/board/list.jsp?group=community&cate=notice\"></a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </header>");
      out.write('\r');
      out.write('\n');

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	pageContext.include("/board/_"+group+".jsp");
	
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	// 글 불러오기
	ArticleBean article = ArticleDAO.getInstance().selectArticle(no);
	

      out.write("\r\n");
      out.write("<main id=\"board\">\r\n");
      out.write("    <section class=\"modify\">\r\n");
      out.write("        <form action=\"./proc/modifyProc.jsp\" method=\"post\">\r\n");
      out.write("        <input type=\"hidden\" name=\"group\" value=\"");
      out.print( group );
      out.write("\"/>\r\n");
      out.write("        <input type=\"hidden\" name=\"cate\" value=\"");
      out.print( cate );
      out.write("\"/>\r\n");
      out.write("        <input type=\"hidden\" name=\"no\" value=\"");
      out.print( no );
      out.write("\"/>\r\n");
      out.write("        <input type=\"hidden\" name=\"pg\" value=\"");
      out.print( pg );
      out.write("\"/>\r\n");
      out.write("            <table border=\"0\">\r\n");
      out.write("                <caption>글수정</caption>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>제목</th>\r\n");
      out.write("                    <td><input type=\"text\" name=\"title\" placeholder=\"제목을 입력하세요.\" value=\"");
      out.print( article.getTitle() );
      out.write("\"></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>내용</th>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <textarea name=\"content\">");
      out.print( article.getContent() );
      out.write("</textarea>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <!-- 글 수정시 파일 첨부 기능 추후 구현하기\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>파일</th>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input type=\"file\" name=\"fname\">\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                  -->\r\n");
      out.write("            </table>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <a href=\"./view.jsp?group=");
      out.print( group );
      out.write("&cate=");
      out.print( cate );
      out.write("&no=");
      out.print( no );
      out.write("&pg=");
      out.print( pg );
      out.write("\" class=\"btn btnCancel\">취소</a>\r\n");
      out.write("                <input type=\"submit\" value=\"수정완료\" class=\"btn btnComplete\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </section>\r\n");
      out.write("</main>\r\n");
      out.write("                </article>\r\n");
      out.write("    </section>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("        <footer>\r\n");
      out.write("            <img src=\"/Farmstory1/img/footer_logo.png\" alt=\"로고\">\r\n");
      out.write("            <p>\r\n");
      out.write("                (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-팜스토리구-123호 / 벤처기업확인 서울지방중소기업청 제 012345678-9-01234호<br/>\r\n");
      out.write("                등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동<br/>\r\n");
      out.write("                대표 : 홍길동 / 이메일 : email@mail.mail / 전화 : 01) 234-5678 / 경기도 성남시 잘한다구 신난다동 345<br/>\r\n");
      out.write("                <em>Copyright(C)홍길동 All rights reserved</em>\r\n");
      out.write("            </p>\r\n");
      out.write("        </footer>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
