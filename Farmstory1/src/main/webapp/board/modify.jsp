<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	pageContext.include("/board/_"+group+".jsp");
	
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	// 글 불러오기
	ArticleBean article = ArticleDAO.getInstance().selectArticle(no);
	
%>
<main id="board">
    <section class="modify">
        <form action="./proc/modifyProc.jsp" method="post">
        <input type="hidden" name="group" value="<%= group %>"/>
        <input type="hidden" name="cate" value="<%= cate %>"/>
        <input type="hidden" name="no" value="<%= no %>"/>
        <input type="hidden" name="pg" value="<%= pg %>"/>
            <table border="0">
                <caption>글수정</caption>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" placeholder="제목을 입력하세요." value="<%= article.getTitle() %>"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content"><%= article.getContent() %></textarea>
                    </td>
                </tr>
                <!-- 글 수정시 파일 첨부 기능 추후 구현하기
                <tr>
                    <th>파일</th>
                    <td>
                        <input type="file" name="fname">
                    </td>
                </tr>
                  -->
            </table>

            <div>
                <a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>&pg=<%= pg %>" class="btn btnCancel">취소</a>
                <input type="submit" value="수정완료" class="btn btnComplete">
            </div>
        </form>
    </section>
</main>
                </article>
    </section>
</div>
<%@ include file="/_footer.jsp" %>