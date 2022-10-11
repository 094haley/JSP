<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" errorPage="IsErrorPage.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>지시어 - errorPage, isErrorPage 속성</title>
		<!-- 
			날짜 : 2022/10/11
			이름 : 이해빈
			내용 : 에러 발생 페이지 P67
			
			1) try - catch 사용하기
			2) errorPage, isErrorPage 속성을 사용하기
		-->
	</head>
	<body>
		<%
			int myAge = Integer.parseInt(request.getParameter("age")) + 10;
			out.println("10년 후 당신의 나이는 " + myAge + "입니다.");
		%>
	</body>
</html>