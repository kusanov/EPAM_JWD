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
    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.main.greeting" var="greeting" />
    <fmt:message bundle="${loc}" key="local.main.greeting.text" var="greetingText" />
    <fmt:message bundle="${loc}" key="local.registration.success" var="successRegistrationMessage" />
    <fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />

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

                    <div class="main_greeting">

                    <c:if test="${param.register == 'success'}">
                        <h2>${successRegistrationMessage}</h2>
                    </c:if>

                    <c:if test="${param.login == 'success'}">
                        <c:if test = "${user.login != null}">
                            <h2>
                                    ${message},  <c:out value = "${user.login}"/>
                            </h2>
                        </c:if>
                    </c:if>

        <br />


                    <h1>
                        ${greeting}
                    </h1>
                        <h3>${greetingText}</h3>
                    </div>
<%--        <h3>Menu</h3>--%>
<%--        <table border="2" cellspacing="2" cellpadding="5" bgcolor="9acd32">--%>
<%--            <tr>--%>
<%--                <td>Category</td>--%>
<%--                <td>Dish</td>--%>
<%--                <td>Description</td>--%>
<%--                <td>Price</td>--%>
<%--            </tr>--%>

<%--            <c:forEach items="${applicationScope.menu_app}" var="dish">--%>
<%--                <tr>--%>
<%--                    <td>${dish.menuCategory.categoryName}</td>--%>
<%--                    <td>${dish.dishName}</td>--%>
<%--                    <td>${dish.dishDescription}</td>--%>
<%--                    <td>${dish.price}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>

            </TD>
            <TD WIDTH=20% VALIGN=TOP>
                <iframe src="" frameborder=0 height=200px width=200px>
                </iframe>
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