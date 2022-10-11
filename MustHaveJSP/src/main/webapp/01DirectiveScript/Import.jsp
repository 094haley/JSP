<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>page 지시어 - import 속성</title>
		<!-- 
			날짜 : 2022/10/11
			이름 : 이해빈
			내용 : import 속성으로 외부 클래스 불러오기 P66
		 -->
	</head>
	<body>
		<%
			Date today = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String todayStr = dateFormat.format(today);
			out.print("오늘 날짜 : " + todayStr); // 오늘 날짜를 웹 브라우저에 출력
		%>
	</body>
</html>