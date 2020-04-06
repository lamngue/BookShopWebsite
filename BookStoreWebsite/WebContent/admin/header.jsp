<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<a href="${pageContext.request.contextPath}/admin">
			<img alt="companylogo" src="../images/admin.png">
		</a>
		<div>
			
			Welcome, <c:out value="${sessionScope.useremail}"/> | <a href="logout">Logout</a>
			<br/>
			&nbsp;
		</div>
		
		<div id="headermenu">
			<div>
				<a href="list_users">
					<img src="../images/users.png" />
					<br />
					Users
				</a>
			</div>
			<div>
				<a href="list_category">
					<img src="../images/category.png" />
					<br />
					Categories
				</a>
			</div>
			<div>
				<a href="list_books">
					<img src="../images/bookstack.png" />
					<br />
					Books
				</a>
			</div>
			<div>
				<a href="list_users">
					<img src="../images/customer.png" />
					<br />
					Customers
				</a>
			</div>
			<div>
				<a href="list_users">
					<img src="../images/review.png" />
					<br />
					Review
				</a>
			</div>
			<div>
				<a href="list_users">
					<img src="../images/order.png" />
					<br />
					Orders
				</a>
			</div>
		</div>
	</div>
</body>
</html>