<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin login</title>
<link rel="stylesheet" href="../css/style.css" >
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
		<h1>Book Store Administration</h1>
		<h2>Admin Login</h2>
			<c:if test="${message != null }">
				<div align="center">
					<h4 class="message">${message}</h4>
				</div>
			</c:if>
		<form id="formLogin" action="login" method="post">
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input id="email" size="20" type="text" name="email"
					size="20"/></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input id="password" size="20" type="password" name="password"
					size="20"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#formLogin").validate({
			rules: {
				email:{
					required: true,
					email: true
				},
				password: "required",
			},
			messages: {
				email:{
					required: "Please enter email",
					email: "Please enter a valid email address"
				},
				password: "Password is required!"
			}
		});
	});
</script>
</html>