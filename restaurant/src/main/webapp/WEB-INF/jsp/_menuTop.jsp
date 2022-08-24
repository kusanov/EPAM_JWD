<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.menu.line.login" var="loginName" />
            <fmt:message bundle="${loc}" key="local.menu.line.logout" var="logoutName" />
            <fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />
            <fmt:message bundle="${loc}" key="local.menu.line.main" var="MainName" />
            <fmt:message bundle="${loc}" key="local.menu.line.user_orders" var="UserOrders" />
        <fmt:message bundle="${loc}" key="local.item.cart.neworder" var="NewOrder" />

        <fmt:message bundle="${loc}" key="local.message" var="message" />

    </head>

    <body>


        <div class="menu_line">
            <table border="1" width="100%" id="menu_line">
                <tr>
                    <td width="25%">
                        <a href="Controller?command=go_to_main">${MainName}</a>
                    </td>
                    <td width="25%">
                        <a href="Controller?command=go_to_user_orders">${UserOrders}</a>
                    </td>

                    <td width="25%">
                        <a href="Controller?command=get_order">${NewOrder}</a>
                    </td>
                    <td width="25%" class="right_list">
                        <c:if test = "${sessionScope.user.login != null}">
                            <c:out value = "${sessionScope.user.login}"/>
                            <a href="Controller?command=sign_out">${logoutName}</a>
                        </c:if>
                        <c:if test = "${sessionScope.user.login == null}">
                        ${message}
                        </c:if>

                        <c:if test = "${sessionScope.user.login == null}">
                            <a href="Controller?command=go_to_login">${loginName}</a>
                        </c:if>
                    </td>

                </tr>
            </table>
        </div>

    </body>
</html>