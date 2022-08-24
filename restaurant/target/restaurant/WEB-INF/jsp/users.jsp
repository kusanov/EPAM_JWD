<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib prefix="form" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs"%>

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
                                <div class="item_block">
																<h3>Users</h3>
																<table border="2" cellspacing="2" cellpadding="5" bgcolor="#9acd32">
																	<tr>
																		<td>UserId</td>
																		<td>Login</td>
																		<td>Role</td>
<%--																		<td>Status</td>--%>
																		<td>Active</td>
																	</tr>
																	<c:forEach items="${requestScope.users_req}" var="user">
																		<tr>
																			<td>${user.userId}</td>
																			<td>${user.login}</td>
																			<td>${user.role.roleName}</td>
<%--																			<td>--%>
<%--	<input type="checkbox" name="checkActive" <c:if test="${user.isActive()}"> checked = "checked" </c:if>/>--%>
<%--																			</td>--%>
																			<td>
																				<form action="Controller" method="post">
																				<input type="hidden" name="command" value="block_user" />
																				<input type="hidden" name="userId" value="${user.userId}" />
																					<input type="checkbox" name="active"
<%--																						   value="${user.isActive()}"--%>
																						   checked="${user.isActive()}"
<%--																							<c:if test="${user.isActive()==true}">--%>
<%--																								checked="checked"--%>
<%--&lt;%&ndash;																								   = "true"&ndash;%&gt;--%>
<%--																							</c:if>--%>
																					/>
<%--																				<input type="hidden" name="isActive" value="${user.isActive()}" />--%>

																				<input type="submit" value="Изменить статус" />
																			</form>
																			</td>
																		</tr>
																	</c:forEach>
                                    </table>
                                </div>
						</TD>
					</TR>
				</TABLE>
				<br />

			</main>
		</div>



		<footer>
			${copyright}
		</footer>


	</body>
</html>