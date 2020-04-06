<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>${book.title} - Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<table class="book bookDetail">
			<tr>
				<td colspan="3" align="left">
					<p id="book-title">${book.title}<p>
					by <span id="author">${book.author}</span>
				</td>
			</tr>		
			<tr>
				<td rowSpan="2">
					<img class="book-large" src="data:image/jpg;base64,${book.base64Image}" />
				</td>
				<td valign="top" align="left">
					Rating *****
				</td>
				<td valign="top" rowSpan="2" width="20%">
					<h2>$${book.price}</h2>
					<br/>
					&nbsp;
					<button type="submit">Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td id="description">
					${book.description}
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>Customer Reviews</td>
				<td colSpan="2" align="center">
					<button>Write a customer review</button>
				</td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>