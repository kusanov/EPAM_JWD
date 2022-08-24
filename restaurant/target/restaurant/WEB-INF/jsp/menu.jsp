<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
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
			<fmt:message bundle="${loc}" key="local.catalog.label.price" var="priceLabel" />
			<fmt:message bundle="${loc}" key="local.catalog.label.currency" var="priceCurrency" />
			<fmt:message bundle="${loc}" key="local.item.cart.addToCartBtn" var="addToCartBtn" />



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

						<TD WIDTH=60% VALIGN=TOP>

                                <div class="menu_block">

																<h3>Menu</h3>
																<table border="2" cellspacing="2" cellpadding="5" bgcolor="#9acd32">
																	<tr>
																		<td>Category</td>
																		<td>Dish</td>
																		<td>Description</td>
																		<td>Price</td>
																		<td>Delete</td>
																	</tr>

																	<c:forEach items="${requestScope.menu_req}" var="dish">
																		<tr>
																			<td>${dish.menuCategory.categoryName}</td>
																			<td>${dish.dishName}</td>
																			<td>${dish.dishDescription}</td>
																			<td>${dish.price}</td>
																			<td>
																				<form action="Controller" method="post">
																				<input type="hidden" name="command" value="del_from_menu" />
																				<input type="hidden" name="dishId" value="${dish.dishId}" />
																				<input type="submit" value="Удалить" />
																			</form>
																			</td>
																		</tr>
																	</c:forEach>
																	<tr>
																		<td>
																		<form action="Controller" method="post">
																			<input type="hidden" name="command" value="go_to_new_dish" />
																			<input type="hidden" name="dishId" value="${dish.dishId}" />
																			<input type="submit" value="Add Dish" />
																		</form>
																		</td>
																	</tr>
                                    </table>

                                </div>
						</TD>
					</TR>
				</TABLE>


			</main>
		</div>

		<footer>
			${copyright}
		</footer>

	</body>
</html>