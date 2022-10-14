<%@page import="java.util.Collection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 응답 헤더에 추가할 값 준비
	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	long add_date = s.parse(request.getParameter("add_date")).getTime();
	int add_int = Integer.parseInt(request.getParameter("add_int"));
	String add_str = request.getParameter("add_str");
	
	
	// 응답 헤더에 값 추가
	response.addDateHeader("myBirthday", add_date);
	response.addIntHeader("myNumber", add_int);
	response.addIntHeader("myNumber", 1004); // 추가
	response.addHeader("myName", add_str);
	response.setHeader("myName", "안중근"); // 수정
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내장 객체 - response</title>
		<!-- 
			날짜 : 2022/10/14
			이름 : 이해빈
			내용 : 응답 헤더에 값 추가하기 P99
			
			
			add 계열 메서드 : 새로운 헤더명으로 값을 추가. 동일한 헤더명이 있으면 동일한 이름으로 값을 추가
			set 계열 메서드 : 기존의 헤더값을 수정. 단, 동일한 헤더명이 존재하지 않는다면 새롭게 추가
		 -->
	</head>
	<body>
	
		<h2>응답 헤더 정보 출력하기</h2>
		<%
			Collection<String> headerNames = response.getHeaderNames();
			for(String hName : headerNames){
				String hValue = response.getHeader(hName);
		%>
		
			<li><%= hName %> : <%= hValue %> </li>
		
		<%
			}
		%>
		
		
		<h2>myNumber만 출력하기</h2>
		<%
			Collection<String> myNumber = response.getHeaders("myNumber");
		 	for(String myNum : myNumber) {
		%>
		
			<li>myNumber : <%= myNum %></li>
		<% 
			}
		%>
		
	</body>
</html>