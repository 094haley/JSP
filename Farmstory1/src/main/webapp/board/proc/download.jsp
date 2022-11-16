<%@page import="kr.co.farmstory1.bean.FileBean"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String parent = request.getParameter("parent");
	
	// 파일 정보 가져오기 & 파일 다운로드 카운트 +1 
	ArticleDAO dao = ArticleDAO.getInstance();
	
	FileBean fb = dao.selectFile(parent);
	dao.updateFileDownload(fb.getFno());
	
	
	

%>