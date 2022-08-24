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

    <fmt:message bundle="${loc}" key="local.registration.login.name" var="loginNameLbl" />
    <fmt:message bundle="${loc}" key="local.registration.password.name" var="passwordName" />
	<fmt:message bundle="${loc}" key="local.registration.btnSubmit.name" var="btnSubmitName" />
	<fmt:message bundle="${loc}" key="local.registration.fail.message" var="registrationFailMessage" />
	<fmt:message bundle="${loc}" key="local.registration.fail.unique.message" var="registrationFailUnique" />


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

        <c:if test="${param.register == 'error'}">
            <h2 class="login_fail_message">${registrationFailMessage}</h2>
        </c:if>
        <c:if test="${param.error == 'unique'}">
            <h2 class="login_fail_message">${registrationFailUnique}</h2>
        </c:if>

		<div class="registration_container">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="registration" />

				<TABLE class="tbl_cnt">
					<tr>
						<td>
							<label for="login">${loginNameLbl}</label>
						</td>
						<td>
							<input type="text" id="login" name="login" >
								<br />
								<br />
						</td>
					</tr>

					<tr>
						<td>
							<label for="password">${passwordName}</label>
						</td>
						<td>
							<input type="password" id="psw" name="password" >
								<br />
								<br />
						</td>
					</tr>
					</table>


				<input type="submit" value="${btnSubmitName}">
			</form>
		</div>


    </main>
    </div>

    <footer>
		${copyright}
    </footer>

</body>
</html>