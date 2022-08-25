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
			<fmt:message bundle="${loc}" key="local.users.users" var="Users" />
			<fmt:message bundle="${loc}" key="local.users.id" var="userId" />
			<fmt:message bundle="${loc}" key="local.users.login" var="Login" />
			<fmt:message bundle="${loc}" key="local.users.role" var="Role" />
			<fmt:message bundle="${loc}" key="local.users.active" var="Active" />
			<fmt:message bundle="${loc}" key="local.users.change" var="ChangeStatus" />



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
																<h3>${Users}</h3>
																<table border="2" cellspacing="2" cellpadding="5" bgcolor="#9acd32">
																	<tr>
																		<td>${userId}</td>
																		<td>${Login}</td>
																		<td>${Role}</td>
																		<td>${Active}</td>
																	</tr>
																	<c:forEach items="${requestScope.users_req}" var="user">
																		<tr>
																			<td>${user.userId}</td>
																			<td>${user.login}</td>
																			<td>${user.role.roleName}</td>
																			<td>
																				<form action="Controller" method="post">
																				<input type="hidden" name="command" value="block_user" />
																				<input type="hidden" name="userId" value="${user.userId}" />
																					<input type="checkbox" name="active"
																						   value="${user.isActive()}"
																							<c:if test="${user.isActive()}">
																								checked="checked"
																							</c:if>
																					/>
																				<input type="submit" value="${ChangeStatus}" />
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