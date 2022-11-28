<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory3/css/style.css"/>
    <link rel="stylesheet" href="/Farmstory3/css/user.css"/>
    <link rel="stylesheet" href="/Farmstory3/css/board.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>    
    <script>
        $(function(){
            $('.slider > ul').bxSlider({
                slideWidth: 980,
                pager: false,
                controls: false,
                auto: true
            });

            $('#tabs').tabs();
        });
    </script>

</head>
<body>
    <div id="wrapper">
        <header>
            <a href="/Farmstory3" class="logo"><img src="/Farmstory3/img/logo.png" alt="로고"/></a>
            <p>
                <a href="/Farmstory3">HOME |</a>
                <a href="/Farmstory3/user/login.do">로그인 |</a>
                <a href="/Farmstory3/user/terms.do">회원가입 |</a>
                <a href="#">고객센터</a>
            </p>
            <img src="/Farmstory3/img/head_txt_img.png" alt="3만원 이상 무료배송"/>
            
            <ul class="gnb">
                <li><a href="/Farmstory3/introduction/hello.do">팜스토리소개</a></li>
                <li><a href="/Farmstory3/board/list.do?group=market&cate=market"><img src="/Farmstory3/img/head_menu_badge.png" alt="30%"/>장보기</a></li>
                <li><a href="/Farmstory3/board/list.do?group=croptalk&cate=story">농작물이야기</a></li>
                <li><a href="/Farmstory3/board/list.do?group=event&cate=event">이벤트</a></li>
                <li><a href="/Farmstory3/board/list.do?group=community&cate=notice">커뮤니티</a></li>
            </ul>
        </header>