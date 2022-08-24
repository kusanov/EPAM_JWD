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
			<fmt:message bundle="${loc}" key="local.catalog.welcome.message" var="chooseMessage" />

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

							<h3>Orders</h3>
							<table border="2" cellspacing="2" cellpadding="5" bgcolor="#9acd32">
								<tr>
									<td>Orders</td>
									<td>Date</td>
									<td>Status</td>
									<td>Ready</td>
								</tr>
								<c:forEach items="${requestScope.kitchen_orders}" var="order">
									<tr>
										<td>${order.id}</td>
										<td>${order.date}</td>
										<td>${order.orderStatus.orderStatus}</td>
										<td>
											<c:if test="${order.orderStatus.id==1}">
											<form action="Controller" method="post">
												<input type="hidden" name="command" value="confirm_order" />
												<input type="hidden" name="orderId" value="${order.id}" />
												<input type="submit" value="Ready" />
											</form>
											</c:if>

										</td>
									</tr>
								</c:forEach>
							</table>
			<br/>

						</TD>
					</TR>
					<br />
		</TABLE>

		</main>

		<footer>
			${copyright}
		</footer>

		</div>
	</body>
</html>