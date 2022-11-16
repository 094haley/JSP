<%@page import="kr.co.farmstory1.bean.UserBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String result = request.getParameter("result");
	UserBean sessUser = (UserBean)session.getAttribute("sessUser");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리::메인</title>
    <link rel="stylesheet" href="/Farmstory1/css/style.css">
    <link rel="stylesheet" href="/Farmstory1/user/css/style.css">
    <link rel="stylesheet" href="/Farmstory1/board/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		let result = "<%= result %>";
		if(result == '201') {
			alert('수정완료!') 
		}else if(result == '202') {
			alert('삭제완료!');
		}
	</script>
</head>
<body>
    <div id="wrapper">
        <header>
            <a href="/Farmstory1/index.jsp" class="logo"><img src="/Farmstory1/img/logo.png" alt="로고"></a>
            <p>
                <a href="/Farmstory1">HOME |</a>
                <% if(sessUser == null) { %>
                <a href="/Farmstory1/user/login.jsp">로그인 |</a>
                <a href="/Farmstory1/user/terms.jsp">회원가입 |</a>
                <% } else { %>
                <a href="/Farmstory1/user/proc/logout.jsp">로그아웃 |</a>
                <% }  %>
                <a href="#">고객센터</a>
            </p>
            <img src="/Farmstory1/img/head_txt_img.png" alt="3만원 이상 무료배송">
            <ul class="gnb">
                <li><a href="/Farmstory1/introduction/hello.jsp"></a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=market&cate=market"><img src="/Farmstory1/img/head_menu_badge.png" alt="30%"></a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=croptalk&cate=story"></a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=event&cate=event"></a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=community&cate=notice"></a></li>
            </ul>
        </header>