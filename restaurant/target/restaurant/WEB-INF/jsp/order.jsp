<%@ page import="by.epam.kusanov.restaurant.bean.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<link href="allstyle.css" rel="stylesheet" type="text/css"/>

			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />
			<fmt:message bundle="${loc}" key="local.order.dish" var="Dish" />
			<fmt:message bundle="${loc}" key="local.order.description" var="Description" />
			<fmt:message bundle="${loc}" key="local.order.price" var="Price" />
			<fmt:message bundle="${loc}" key="local.order.quantity" var="Quantity" />
			<fmt:message bundle="${loc}" key="local.order.cost" var="Cost" />
			<fmt:message bundle="${loc}" key="local.order.delete" var="Delete" />
			<fmt:message bundle="${loc}" key="local.order.add" var="AddDish" />
			<fmt:message bundle="${loc}" key="local.order.category" var="Category" />
			<fmt:message bundle="${loc}" key="local.order.neworder" var="NewOrder" />


	</head>
	<body>
		<div id="wrap">

			<header>
				<jsp:include page="/WEB-INF/jsp/_header.jsp" />
			</header>

			<main>

				<br />

                <jsp:include page="/WEB-INF/jsp/_menuTop.jsp" />

				<br />


				<TABLE BORDER=0 WIDTH=100%>
					<TR>
						<TD WIDTH=20% VALIGN=TOP>

							<jsp:include page="/WEB-INF/jsp/_sidebar.jsp" />

							<br />
						</TD>

						<TD WIDTH=80% VALIGN=TOP>
							<c:out value="${user.login}"/>

                            <h2>${newOrder} №${order.id}</h2>

							<br />
								<form action="Controller" method="post">
									<input type="hidden" name="command" value="save_order" />
							<div class = "order_dish">

								<table border="2" cellspacing="2" cellpadding="5" bgcolor="#9acd32">
									<tr>
										<td>${Dish}</td>
										<td>${Description}</td>
										<td>${Price}</td>
										<td>${Quantity}</td>
										<td>${Cost}</td>
										<td>${Delete}</td>
									</tr>
									<c:forEach items="${requestScope.order.dishes}" var="entry">

									<tr>
										<td>${entry.key.dishName}</td>
										<td>${entry.key.dishDescription}</td>
										<td>${entry.key.price}</td>
										<td>${entry.value}</td>
										<td>${entry.key.price * entry.value}</td>
										<td>
											<form action="Controller" method="post">
												<input type="hidden" name="command" value="del_from_order" />
												<input type="hidden" name="dishId" value="${entry.key.dishId}" />
												<input type="submit" value="Удалить" />
											</form>
										</td>
									</tr>
									</c:forEach>
								</table>
							</div>
								<br />
<div class = "order_dish">
								<h3>Menu</h3>
							<table border="2" cellspacing="2" cellpadding="5" bgcolor="#9acd32">
								<tr>
									<td>${Category}</td>
									<td>${Dish}</td>
									<td>${Description}</td>
									<td>${Price}</td>
									<td>${AddDish}</td>
								</tr>

								<c:forEach items="${requestScope.menu}" var="dish">
									<tr>
										<td>${dish.menuCategory.categoryName}</td>
										<td>${dish.dishName}</td>
										<td>${dish.dishDescription}</td>
										<td>${dish.price}</td>
										<td>
											<form action="Controller" method="post">
												<input type="hidden" name="command" value="add_dish" />
												<input type="hidden" name="dishId" value="${dish.dishId}" />
												<input type="number" size=2 name="quantity" min="1" max="100" value="1">
												<input type="submit" value="${AddDish}" />
											</form>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td>
									<input type="submit" value="Save order">
									</td>
								</tr>
							</table>

						</div>
								</form>
						</TD>
						</TABLE>
			</main>
		</div>
		<br/>
		<footer>
			${copyright}
		</footer>
	</body>
</html>