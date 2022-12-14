<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DB"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String stdNo = request.getParameter("stdNo");

	// 데이터베이스 작업
	
	try{
		
		Connection conn = DB.getInstance().getConnection();
		String sql = "DELETE FROM `student` WHERE `stdNo`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		psmt.setString(1, stdNo);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 학생목록 이동
	response.sendRedirect("./list.jsp");
%>