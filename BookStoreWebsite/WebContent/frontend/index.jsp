<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Evergreen Books - Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<h3>This is main content:</h3>
		<br />
		<div align="center" style="margin: 0 auto;">
			<h2>New Books:</h2>
			<c:forEach items="${newBooks}" var="book">
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId}"> <img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
						</a>
					</div>
					<div>&nbsp;</div>
					<div>
						<a href="view_book?id=${book.bookId}&name=${book.title}"> <b>${book.title}</b>
						</a>
					</div>
					<div>Rating ******</div>
					<div>
						By <i>${book.author}</i>
					</div>
					<div>
						<b>$${book.price}</b>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="next-row">
			<h2>Best-Selling Books:</h2>
			<h2>Most-favored Books:</h2>	
		</div>
		<br />
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>