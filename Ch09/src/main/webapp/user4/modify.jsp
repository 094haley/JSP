<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user4::modify</title>
	</head>
	<body>
		<h3>user4 수정</h3>
		<a href="/Ch09/">처음으로</a>
		<a href="/Ch09/user4/list.do">user4 목록</a>
		<form action="/Ch09/user4/modify.do" method="post">
		<input type="hidden" name="seq" value="${vo.seq}">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${vo.name}"/></td>
				</tr>			
				<tr>
					<td>성별</td>
					<td>
						<label><input type="radio" name="gender" value="1" ${vo.gender == 1 ? "checked": ""}/>남</label>
						<label><input type="radio" name="gender" value="2" ${vo.gender == 2 ? "checked": ""}/>여</label>
					</td>
				</tr>					
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value="${vo.age}"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${vo.addr}"/></td>
				</tr>			
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정하기"/>
					</td>
				</tr>			
			</table>
		</form>
		
	</body>
</html>