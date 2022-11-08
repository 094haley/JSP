<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String regStdNo = request.getParameter("regStdNo");
	String regLecNo = request.getParameter("regLecNo");

	int result = 0;
	
	try{
		
		Connection conn = DBCP.getConnection();
		String sql = "insert into `register`(`regStdNo`, `regLecNo`) values(?,?)";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, regStdNo);
		psmt.setString(2, regLecNo);

		result = psmt.executeUpdate();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	out.print(jsonData);

%>