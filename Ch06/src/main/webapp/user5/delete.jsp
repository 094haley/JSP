<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	
	
	try{
		
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("DELETE FROM `user5` WHERE `uid`='"+uid+"'");

		stmt.close();
		conn.close();

		
	}catch(Exception e){
		e.printStackTrace();
	}

	response.sendRedirect("./list.jsp");
%>