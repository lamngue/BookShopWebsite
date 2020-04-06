<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" >
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<title>Create New Book</title>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<c:if test="${book != null}">
			<h2 class="pageheading">Edit Book</h2>
		</c:if>
		<c:if test="${book == null}">
			<h2 class="pageheading">Create new book</h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post" id="bookForm" enctype="multipart/form-data">
			<input type="hidden" name="bookId" value="${book.bookId}" >
		</c:if>
		<c:if test="${book == null}">
			<form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td>Category</td>
				<td>
					<select name="category">
						<c:forEach items="${categories}" var="category">
							<c:if test="${category.categoryId eq book.category.categoryId}">
									<option selected id="category" value="${category.categoryId}" >
							</c:if>
							<c:if test="${category.categoryId ne book.category.categoryId}">
									<option id="category" value="${category.categoryId}">
							</c:if>
									${category.name}
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Title</td>
				<td align="left"><input id="title" type="text" name="title"
					size="20" value="${book.title}" /></td>
			</tr>
			<tr>
				<td align="right">Author</td>
				<td align="left"><input id="author" type="text"
					name="author" size="20" value="${book.author}" /></td>
			</tr>
			<tr>
				<td align="right">ISBN</td>
				<td align="left"><input id="isbn" type="text"
					name="isbn" size="20" value="${book.isbn}" /></td>
			</tr>
			<tr>
				<td align="right">Publish Date</td>
				<td align="left"><input id="publishDate" type="text"
					name="publishDate" size="20" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate}' />" /></td>
			</tr>
			<tr>
				<td align="right">Book Image</td>
				<td align="left">
					<input id="bookImage" type="file"
					name="bookImage" size="20" /> <br/>
					<img src="data:image/jpg;base64,${book.base64Image}"
					id="thumbnail" alt="Image Preview" style="width:20%;margin-top:10px" />
				</td>
			</tr>
			<tr>
				<td align="right">Price</td>
				<td align="left"><input id="price" type="text"
					name="price" size="20" value="${book.price}" /></td>
			</tr>
			<tr>
				<td align="right">Description</td>
				<td align="left">
					<textarea rows="5" cols="50" name="description" id="description">
						${book.description} 
					</textarea>
				</td>
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
		$("#bookImage").change(function(){
			showImageThumbnail(this);
		});
		$("#bookForm").validate({
			rules: {
				category: "required",
				title: "required",
				author: "required",
				isbn: "required",
				publishDate: "required",
				
				<c:if test="${book == null}">
				bookImage: "required",
				</c:if>
				
				price: "required",
				description: "required"
			},
			messages: {
				category: "Please select a category",
				title: "Please enter book title",
				author: "Please enter book author",
				isbn: "Please enter isbn",
				publishDate: "Please enter publishDate",
				bookImage: "Please enter bookimage",
				price: "Please enter book price",
				description: "Please enter book description"
			}
		});

		$( "#publishDate").datepicker();
		$("#description").richText();
		$("#buttonCancel").on("click", function(){
			history.go(-1);
		});
	});
	function showImageThumbnail(fileInput){
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e){
			$("#thumbnail").attr("src", e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
</script>
</html>