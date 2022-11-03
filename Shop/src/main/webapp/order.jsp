<%@page import="bean.OrderBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	List<OrderBean> orders = new ArrayList<>();
	try{
		
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT `orderNo`, b.`name`, c.`prodName`, `orderCount` ,`orderDate` ";
			   sql += "FROM `order` AS a "; 
			   sql += "JOIN `customer` AS b ON a.`orderId`= b.`custId` ";
			   sql += "JOIN `product` AS c ON a.`orderProduct` = c.`prodNo`";
				
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			OrderBean ob = new OrderBean();
			ob.setOrderNo(rs.getInt(1));
			ob.setName(rs.getString(2));
			ob.setProduct(rs.getString(3));
			ob.setCount(rs.getInt(4));
			ob.setOrderdate(rs.getString(5));
			
			orders.add(ob);
		}

		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shop::order</title>
	</head>
	<body>
		<h3>주문목록</h3>
		<a href="./customer.jsp">고객목록</a>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		<table border="1">
			<tr>
				<th>주문번호</th>
				<th>주문자</th>
				<th>주문상품</th>
				<th>주문수량</th>
				<th>주문일</th>
			</tr>
			<% for(OrderBean ob : orders) { %>
			<tr>
				<td><%= ob.getOrderNo() %></td>
				<td><%= ob.getName() %></td>
				<td><%= ob.getProduct() %></td>
				<td><%= ob.getCount() %></td>
				<td><%= ob.getOrderdate() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>