<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" >
<title>Create New Category</title>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<c:if test="${category != null}">
			<h2 class="pageheading">Edit Category</h2>
		</c:if>
		<c:if test="${category == null}">
			<h2 class="pageheading">Create new category</h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post" id="categoryForm">
			<input type="hidden" name="categoryId" value="${category.categoryId}"/>
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post" id="categoryForm">
		</c:if>
		<table class="form">
			
			<tr>
				<td align="right">Name</td>
				<td align="left"><input id="name" type="text"
					name="name" size="20" value="${category.name}" /></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="submit"
					>Save</button> <button id="buttonCancel"> Cancel</td>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#categoryForm").validate({
			rules: {
				name: "required"
			},
			messages: {
				name: "Please enter a name"
			}
		});
		$("#buttonCancel").on("click", function(){
			history.go(-1);
		});
	});
</script>
</html>