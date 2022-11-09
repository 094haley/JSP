<%@page import="java.sql.ResultSet"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String regStdNo = request.getParameter("regStdNo");
	String stdName = request.getParameter("stdName");
	String regLecNo = request.getParameter("regLecNo");

	int result = 0;
	String lecName = null;
	
	try{
		
		Connection conn = DBCP.getConnection();
		String sql1 = "insert into `register`(`regStdNo`, `regLecNo`) values(?,?)";
		String sql2 = "select `lecName` from `lecture` where `lecNo`=?";
		
		PreparedStatement psmt1 = conn.prepareStatement(sql1);
		psmt1.setString(1, regStdNo);
		psmt1.setString(2, regLecNo);

		result = psmt1.executeUpdate();
		
		PreparedStatement psmt2 = conn.prepareStatement(sql2);
		psmt2.setString(1, regLecNo);
		
		ResultSet rs = psmt2.executeQuery();
		if(rs.next()) {
			lecName = rs.getString(1);
		}		
		
		psmt1.close();
		psmt2.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("regStdNo", regStdNo);
	json.addProperty("stdName", stdName);
	json.addProperty("lecName", lecName);
	json.addProperty("regLecNo", regLecNo);
	
	String jsonData = json.toString();
	out.print(jsonData);

%>