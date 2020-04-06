<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="center">
	<div>
		<a href="${pageContext.request.contextPath}">
			<img src="images/original.png" />
		</a>
	</div>
	<div>
		<form action="search_book" method="get">
			<input type="text" name="keyword" size="50" />
			<input type="button" value="Search" />
			&nbsp;
			<a href="login">Sign In</a> |
			<a href="register">Register</a> |
			<a href="view_cart">Cart</a>
		</form>
		
	</div>
	<div>&nbsp;</div>
	<div>
		<c:forEach var="category" items="${listCategory}" varStatus="loopCounter">
			<a href="view_category?id=${category.categoryId }&name=${category.name}">
				<font size="+1"><b><c:out value="${category.name}" /></b></font>
			</a>
			<c:if test="${!loopCounter.last}">
							&nbsp; | &nbsp;
			</c:if>
		</c:forEach>
	</div>
</div>

