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

		<fmt:message bundle="${loc}" key="local.new_dish.category" var="menuCategory" />
		<fmt:message bundle="${loc}" key="local.new_dish.name" var="dishName" />
		<fmt:message bundle="${loc}" key="local.new_dish.name.description" var="dishDesc" />
		<fmt:message bundle="${loc}" key="local.new_dish.name.price" var="price" />
		<fmt:message bundle="${loc}" key="local.new_dish.btnSubmit.name" var="btnSubmitName" />

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

						<TD VALIGN=TOP>


                            <div>

                                <table>
                                    <tr>

                                        <td WIDTH=60% VALIGN=TOP>
											<div class="new_dish">
												<form action="Controller" method="post">
													<input type="hidden" name="command" value="add_to_menu" />

													<TABLE class="tbl_cnt">
														<tr>
															<td>
																<%--@declare id="menucategory"--%><label for="menuCategory">${menuCategory}</label>
															</td>
															<td>
																<select name="category">
																	<c:forEach items="${applicationScope.menu_categories_app}" var="category">
																		<option value="${category.id}">
																				${category.categoryName}
																		</option>
																	</c:forEach>
																</select>																<br />
																<br />
															</td>
														</tr>
														<tr>
															<td>
																<label for="dishName">${dishName}</label>
															</td>
															<td>
																<input type="text" id="dishName" name="dishName" >
																<br />
																<br />
															</td>
														</tr>

														<tr>
															<td>
																<label for="dishDescription">${dishDesc}</label>
															</td>
															<td>
																<input type="text" id="dishDescription" name="dishDescription" >
																<br />
																<br />
															</td>
														</tr>
														<tr>
															<td>
																<label>${price}</label>
															</td>
															<td>
																<input type="number" step="0.01" id="price" name="price" >
																<br />
																<br />
															</td>
														</tr>
													</table>

													<input type="submit" value="${btnSubmitName}">
												</form>
											</div>
                                        </td>


                                    </tr>

                                </table>

                            </div>
				</TABLE>
				<br />
			</main>
		</div>


		<footer>
			${copyright}
		</footer>


	</body>
</html>