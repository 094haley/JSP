<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bean.RegisterBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String search = request.getParameter("search");
	
	
	List<RegisterBean> registrations = new ArrayList<>();
	try{
		
		Connection conn = DBCP.getConnection();
		String sql = "SELECT `regStdNo`, `stdName`, `lecName` , `regLecNo`, `regMidScore`, ";
		   sql += "`regFinalScore`, `regTotalScore`, `regGrade` ";
		   sql += "FROM `register` AS a ";
		   sql += "JOIN `student` AS b ON a.`regStdNo` = b.`stdNo` ";
		   sql += "JOIN `lecture` AS c ON a.`regLecNo` = c.`lecNo`";
		   sql += "WHERE `regStdNo`='"+search+"'";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			RegisterBean rb = new RegisterBean();
			rb.setRegStdNo(rs.getString(1));
			rb.setStdName(rs.getString(2));
			rb.setLecName(rs.getString(3));
			rb.setRegLecNo(rs.getInt(4));
			rb.setRegMidScore(rs.getInt(5));
			rb.setRegFinalScore(rs.getInt(6));
			rb.setRegTotalScore(rs.getInt(7));
			rb.setRegGrade(rs.getString(8));
			
			registrations.add(rb);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	Gson gson = new Gson();
	String jsonData = gson.toJson(registrations);
	
	out.print(jsonData);

%>