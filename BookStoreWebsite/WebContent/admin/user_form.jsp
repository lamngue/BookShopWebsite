<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" >
<title>Create New User</title>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<c:if test="${user != null}">
			<h2 class="pageheading">Edit User</h2>
		</c:if>
		<c:if test="${user == null}">
			<h2 class="pageheading">Create new user</h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" id="userForm">
			<input type="hidden" name="userId" value="${user.userId}"/>
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post" id="userForm">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Email</td>
				<td align="left"><input id="email" type="text" name="email"
					size="20" value="${user.email}" /></td>
			</tr>
			<tr>
				<td align="right">Full Name</td>
				<td align="left"><input id="fullName" type="text"
					name="fullname" size="20" value="${user.fullName}" /></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input id="password" type="password"
					name="password" size="20" value="" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button> 
					<button id="buttonCancel">Cancel</button>
				</td>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userForm").validate({
			rules: {
				email:{
					required: true,
					email: true
				},
				fullname: "required",
			},
			messages: {
				email:{
					required: "Please enter email",
					email: "Please enter a valid email address"
				},
				fullname: "Full name is required!",
				password: "Password is required!"
			}
		});
		$("#buttonCancel").on("click", function(){
			history.go(-1);
		});
	});
</script>
</html>