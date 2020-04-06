<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" >
<title>Manage Category - Evergreen BookStore Administration</title>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3>
			<a href="category_form.jsp">Create new category</a>
		</h3>
	</div>
	<div align="center">
		<c:if test="${message != null}">
			<h4 class="message">
				${message}
			</h4>
		</c:if>
	</div>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="cat" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${cat.categoryId}</td>
					<td>${cat.name }</td>
					<td>
						<a href="edit_category?id=${cat.categoryId}">Edit</a> &nbsp; 
						<a href="javascript:void(0)" class="deleteLink" id="${cat.categoryId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center"></div>
	<div align="center"></div>
	<div align="center"></div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click", function(){
				var catId = $(this).attr("id");
				if(confirm('Are you sure you want to delete the category with ID ' + catId + '?')){
					window.location = 'delete_category?id=' + catId;
				}
			});
		});
	});
</script>
</html>