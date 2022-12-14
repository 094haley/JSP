<%@page import="java.util.ArrayList"%>
<%@page import="bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="config.DB"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
List<StudentBean> students = null;

	// 데이터베이스 작업	
	try{
		Connection conn = DB.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `student`"); 
		
		students = new ArrayList<>();
		
		while(rs.next()){
	
	StudentBean sb = new StudentBean();
	sb.setStdNo(rs.getString(1));
	sb.setStdName(rs.getString(2));
	sb.setStdHp(rs.getString(3));
	sb.setStdYear(rs.getInt(4));
	sb.setStdAddress(rs.getString(5));
	
	students.add(sb);
	
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>student::list</title>
	</head>
	<body>
		
		<h3>student 목록</h3>
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./register.jsp">student 등록하기</a>
		<table border="1">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>학년</th>
				<th>주소</th>
				<th>관리</th>
			</tr>
			<%
			for(StudentBean sb : students){
			%>
				<tr>
					<td><%= sb.getStdNo() %></td>
					<td><%= sb.getStdName() %></td>
					<td><%= sb.getStdHp() %></td>
					<td><%= sb.getStdYear() %></td>
					<td><%= sb.getStdAddress() %></td>
					<td>
						<a href="./modify.jsp?stdNo=<%= sb.getStdNo() %>">수정</a>
						<a href="./delete.jsp?stdNo=<%= sb.getStdNo() %>">삭제</a>
					</td>
				</tr>
			<% } %>
		</table>
		
	</body>
</html>