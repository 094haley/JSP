<%@page import="bean.LectureBean"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	List<LectureBean> lectures = new ArrayList<>();
	try{
		
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from `lecture`");
		
		while(rs.next()){
			LectureBean lecture = new LectureBean();
			lecture.setLecNo(rs.getInt(1));
			lecture.setLecName(rs.getString(2));
			lecture.setLecCredit(rs.getInt(3));
			lecture.setLecTime(rs.getInt(4));
			lecture.setLecClass(rs.getString(5));
			
			lectures.add(lecture);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	Gson gson = new Gson();
	String jsonData = gson.toJson(lectures);
	
	out.print(jsonData);

%>