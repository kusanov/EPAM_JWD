<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.header.name" var="headerName" />
    </head>

    <body>

        <div class="menu_left">
            <table border="0">
                    <tr>
                        <td>
                            <c:if test="${sessionScope.user.role.roleId==1}">
                                <form action="Controller" method="get" class="button_side_menu">
                                    <input type="hidden" name="command" value="go_to_menu" />
                                    <input type="submit" value="Редактор меню" />
                                </form>
                            </c:if>
                        </td>
                    </tr>
                <tr>
                    <td>
                        <c:if test="${sessionScope.user.role.roleId==1}">
                            <form action="Controller" method="get" class="button_side_menu">
                                <input type="hidden" name="command" value="go_to_users" />
                                <input type="submit" value="Управление доступами" />
                            </form>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${sessionScope.user.role.roleId==1}">
                            <form action="Controller" method="get" class="button_side_menu">
                                <input type="hidden" name="command" value="go_to_kitchen" />
                                <input type="submit" value="Кухня" />
                            </form>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>

    </body>
</html>