<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Results for ${keyword} - Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<c:if test="${fn:length(result) == 0}">
			<h2>No results for keyword "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<div class="book_group">
			<h2>Results for "${keyword}"</h2>
				<c:forEach items="${result}" var="book">
					<div align="left" style="margin-left: 5%">
						<div id="search-image">
							<div>
								<a href="view_book?id=${book.bookId}"> <img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
								</a>
							</div>
						</div>
						<div id="search-description">
							<div>
								<h2><a href="view_book?id=${book.bookId}&name=${book.title}"> <b>${book.title}</b></h2></a>
							</div>
							<div>Rating ******</div>
							<div>
								By <i>${book.author}</i>
							</div>
							<div>
								<p>${fn:substring(book.description,0,100)}...</p>
							</div>
						</div>
						<div id="search-price">
							<h3>$${book.price}</h3>
							<h3><a>Add to Cart</a></h3>		
						</div>
					</div>
					
				</c:forEach>
			</div>
		</c:if>
		<br />
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>