<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	pageContext.include("/board/_"+group+".jsp");
	
	// 게시물 목록 처리 관련 변수 선언
	int start = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 게시물 DAO 객체 가져오기
	ArticleDAO dao = ArticleDAO.getInstance();
	
	// 전체 게시물 갯수 구하기
	total = dao.selectCountTotal(cate);
	
	// 페이지 마지막 번호 계산하기
	if(total % 10 == 0) {
		lastPageNum = (total / 10);
	}else {
		lastPageNum = (total / 10) +1;
	}
	
	// 현재 페이지 게시물 limit 시작값 계산
	if(pg != null) {
		currentPage = Integer.parseInt(pg);
	}
	
	start = (currentPage -1) * 10;
	
	// 페이지 그룹 계산
	pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum ) {
		pageGroupEnd = lastPageNum;
	}
	
	// 페이지 시작 번호 계산
	pageStartNum = total - start;
	
	// 게시물 가져오기
	List<ArticleBean> articles = dao.selectArticles(cate, start);
	
%>
<main id="board">
    <section class="list">
        <table border="0">
            <caption>글목록</caption>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
                <th>조회</th>
            </tr>
            <% for(ArticleBean ab : articles) { %>
            <tr>
                <td><%= pageStartNum-- %></td>
                <td><a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= ab.getNo()%>&pg=<%= currentPage %>"><%= ab.getTitle() %>[<%= ab.getComment() %>]</a></td>
                <td><%= ab.getNick() %></td>
                <td><%= ab.getRdate().substring(2, 10) %></td>
                <td><%= ab.getHit() %></td>
            </tr>
            <% } %>
            <% if(total ==0 ) { %>
            <tr>
            	<td colspan="5" align="center">등록된 게시글이 없습니다.</td>
            </tr>
            <% } %>
        </table>

        <div class="page">
        	<% if(pageGroupStart > 1) {%>
            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupStart - 1 %>" class="prev">이전</a>
            <% } %> 
            <% for(int i=pageGroupStart; i<=pageGroupEnd; i++){ %>   
            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= i %>" class="num <%= (currentPage == i) ? "current" : "off" %>"><%= i %></a>    
            <% } %> 
            <% if(pageGroupEnd < lastPageNum) { %>
            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupEnd + 1 %>" class="next">다음</a>
            <% } %>
        </div>
			<a href="./write.jsp?group=<%= group %>&cate=<%= cate %>" class="btn btnWrite">글쓰기</a>

    </section>
</main>
                </article>
    </section>
</div>
<%@ include file="/_footer.jsp" %>
