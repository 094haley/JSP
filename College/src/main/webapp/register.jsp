<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.RegisterBean"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	List<RegisterBean> registers = new ArrayList<>();
	try {
		
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT `regStdNo`, `stdName`, `lecName` , `regLecNo`, `regMidScore`, ";
		   sql += "`regFinalScore`, `regTotalScore`, `regGrade` ";
		   sql += "FROM `register` AS a ";
		   sql += "JOIN `student` AS b ON a.`regStdNo` = b.`stdNo` ";
		   sql += "JOIN `lecture` AS c ON a.`regLecNo` = c.`lecNo`";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			RegisterBean register = new RegisterBean();
			
			register.setRegStdNo(rs.getString(1));
			register.setStdName(rs.getString(2));
			register.setLecName(rs.getString(3));
			register.setRegLecNo(rs.getInt(4));
			register.setRegMidScore(rs.getInt(5));
			register.setRegFinalScore(rs.getInt(6));
			register.setRegTotalScore(rs.getInt(7));
			register.setRegGrade(rs.getString(8));
			
			registers.add(register);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강관리</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
		$(function(){
			
			// 검색기능
			$('.btnSearch').click(function(){
				let search = $('input[name=search]').val();
				$('.row').remove();
				
				let jsonData = {
						"search":search
				}
				
				$.get('./proc/searchProc.jsp', jsonData, function(data){
					
					console.log(data);
					
                    for(let reg of data){

                        let tags = "<tr class='row'>";
							tags += "<td>" + reg.regStdNo + "</td>";
							tags += "<td>" + reg.stdName + "</td>";
							tags += "<td>" + reg.lecName + "</td>";
							tags += "<td>" + reg.regLecNo + "</td>";
							tags += "<td>" + reg.regMidScore + "</td>";
							tags += "<td>" + reg.regFinalScore + "</td>";
							tags += "<td>" + reg.regTotalScore + "</td>";
							tags += "<td>" + reg.regGrade + "</td>";
							tags += "</tr>";
							
                        $('.list').append(tags);
                    }
				});
				
			});

			
			// 수강신청 폼
			$('.btnRegister').click(function(){
				$('section').show();
			});
			
			// 강좌등록
			$('input[type=submit]').click(function(){
				
				let regStdNo = $('input[name=regStdNo]').val();
				let stdName = $('input[name=stdName]').val();
				let regLecNo = $('select[name=regLecNo]').val();
				let lecName = $('select[name=regLecNo]').data("name");

				
				let jsonData = {
						"regStdNo":regStdNo,
						"regLecNo":regLecNo
				};
				
				console.log(jsonData);
				
				$.post('./proc/registerProc.jsp', jsonData, function(data){
					
					console.log(data);
					
					if(data.result == 1) {
						alert('수강신청을 성공하였습니다.');
					}else{
						alert('수강신청을 실패하였습니다.');
					}
					
                    let tags = "<tr class='row'>";
					tags += "<td>" + regStdNo + "</td>";
					tags += "<td>" + stdName + "</td>";
					tags += "<td>" + lecName + "</td>";
					tags += "<td>" + regLecNo + "</td>";
					tags += "<td></td>";
					tags += "<td></td>";
					tags += "<td></td>";
					tags += "<td></td>";
					tags += "</tr>";
					
                $('.list').append(tags);
				
                
				});
			});
			
			// 닫기버튼
			$('.btnClose').click(function(){
				$('section').hide();
			});
			
			
			// 선택박스에 출력할 강좌 가져오기
			$.ajax({
				url:'./proc/getlecName.jsp',
				method:'get',
				dataType:'json'			
				success:function(data){
					
					for(let name of data){
					
						let tags = "<option value="none">강좌선택</option>"
					}
					
				}
				
			});
		});
		
		</script>
	</head>
	<body>
		<h3>수강관리</h3>
		<a href="./lecture.jsp">강좌관리</a>
		<a href="./register.jsp">수강관리</a>
		<a href="./student.jsp">학생관리</a>
		
		<h4>수강현황</h4>
		<input type="text" name="search"/>
		<button class="btnSearch">검색</button>
		<button class="btnRegister">수강신청</button>
		
		<table border="1" class="list">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>강좌명</th>
				<th>강좌코드</th>
				<th>중간시험</th>
				<th>기말시험</th>
				<th>총점</th>
				<th>등급</th>
			</tr>
			<% for(RegisterBean register : registers) { %>
			<tr class="row">
				<td><%= register.getRegStdNo() %></td>
				<td><%= register.getStdName() %></td>
				<td><%= register.getLecName() %></td>
				<td><%= register.getRegLecNo() %></td>
				<td><%= register.getRegMidScore() %></td>
				<td><%= register.getRegFinalScore() %></td>
				<td><%= register.getRegTotalScore() %></td>
				<td><%= register.getRegGrade() %></td>
			</tr>
			<% } %>
		</table>
		
		<section style="display: none">
				<h4>수강신청</h4>
				<button class="btnClose">닫기</button>
				<table border="1">
					<tr>
						<td>학번</td>
						<td><input type="text" name="regStdNo"/></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="stdName"/></td>
					</tr>
					<tr>
						<td>신청강좌</td>
						<td>
							<select name="regLecNo">
								<option value="none">강좌선택</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="submit" value="신청"/></td>
					</tr>
				</table>
		</section>
	</body>
</html>