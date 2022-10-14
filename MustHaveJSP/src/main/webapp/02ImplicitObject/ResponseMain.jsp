<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내장객체 - response</title>
		<!-- 
			날짜 : 2022/10/14
			이름 : 이해빈
			내용 : 로그인 폼과 응답 헤더 설정 페이지 P95
		 -->
	</head>
	<body>
		<h2>1. 로그인 폼</h2>
		<%
			String loginErr = request.getParameter("loginErr");
			if(loginErr != null){
				out.print("로그인 실패");
			}
		%>
		<form action="./Resonselogin.jsp" method="post">
			아이디 : <input type="text" name="user_id"/><br/>
			패스워드 : <input type="text" name="user_pwd"/><br/>
			<input type="submit" value="로그인"/>
		</form>
		
		<h2>HTTP 응답 헤더 설정하기</h2>
		<form action="./ResponseHeader.jsp" method="get">
			날짜 형식 : <input type="text" name="add_date" value="2022-10-14"/><br/>
			숫자 형식 : <input type="text" name="add_int" value="8282"/><br/>
			문자 형식 : <input type="text" name="add_str" value="홍길동"/><br/>
			<input type="submit" value="응답 헤더 설정 & 출력"/>		
		</form>
	</body>
</html>