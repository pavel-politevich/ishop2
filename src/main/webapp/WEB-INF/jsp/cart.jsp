<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<link href="<c:url value="/resources/newcss.css"/>" rel="stylesheet" type="text/css"/>

			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright" />
			<fmt:message bundle="${loc}" key="local.catalog.label.price" var="priceLabel" />
			<fmt:message bundle="${loc}" key="local.catalog.label.currency" var="priceCurrency" />
			<fmt:message bundle="${loc}" key="local.item.label.rating" var="ratingLabel" />

			<fmt:message bundle="${loc}" key="local.item.cart.cartname" var="CartName" />

			<fmt:message bundle="${loc}" key="local.order.confirm.comment.text" var="confirmComment" />
			<fmt:message bundle="${loc}" key="local.order.confirm.address.text" var="confirmAddress" />
			<fmt:message bundle="${loc}" key="local.order.confirm.paymentType.text" var="confirmPaymentType" />
			<fmt:message bundle="${loc}" key="local.order.confirm.submit.button" var="confirmSubmitBtn" />
			<fmt:message bundle="${loc}" key="local.order.confirm.ok.message1" var="confirmOkMessage1" />
			<fmt:message bundle="${loc}" key="local.order.confirm.ok.message2" var="confirmOkMessage2" />
			<fmt:message bundle="${loc}" key="local.order.cart.empty.text" var="cartEmptyText" />




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

                            <h2>${CartName}</h2>


                            <c:if test="${empty requestScope.order.orderDetails}">
                                ${cartEmptyText}
                            </c:if>

                            <c:if test="${param.confirm == 'ok'}">
                                <h3>${confirmOkMessage1} #${param.orderId}. ${confirmOkMessage2}</h3>
                            </c:if>

                            <c:forEach items="${requestScope.order.orderDetails}" var="entry">


                                <div class = "cart_item">

                                    <table BORDER=0 WIDTH=100%>
                                        <tr>
                                        	<c:url var="openLink" value="/showItemInfo">
												<c:param name="itemId" value="${entry.item.itemId}" />
											</c:url>
											
                                            <td width=55%><a href="${openLink}">${entry.item.nameFull}</a></td>
                                            <td width=10%>Цена: ${entry.item.price}</td>
                                            <td width=15%>Количество: ${entry.count}</td>
                                            <td width=10%>Сумма: ${entry.item.price * entry.count}</td>
                                            <td>
                                            	
                                            	<c:url value="/deleteFromCart" var="urlVar" />
                                                <form:form action="${urlVar}" method="post">
                                                    <input type="hidden" name="itemId" value="${entry.item.itemId}" />
                                                    <input type="submit" value="Удалить" />
                                                </form:form>
                                            </td>
                                        </tr>
                                    </table>

                                </div>


                            </c:forEach>

                            <c:if test="${not empty requestScope.order.orderDetails}">
                                <div class="order_confirm_block">
                                    <br />
                                    <hr>
                                    <c:url value="/confirmOrder" var="urlVar" />
                                    <form:form action="${urlVar}" method="post">

                                        <table class="confirm_tbl" width=100% valign=top>
                                            <tr>
                                                <td>
                                                    <label for="comment">${confirmComment}</label>
                                                    <br />
                                                    <textarea name="comment" id="comment" class="confirm_comment"></textarea>
                                                </td>
                                                <td>
                                                    <label for="address">${confirmAddress}</label>
                                                    <br />
                                                    <textarea name="address" id="address" class="confirm_comment"></textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="paymentType">${confirmPaymentType}</label>
                                                    <br /><br />

                                                    <c:forEach items="${paymentTypes}" var="entry">
                                                        <input type="radio" name="paymentType" value="${entry.id}" checked>${entry.descritpion}<Br>
                                                    </c:forEach>

                                                </td>
                                                <td>

                                                    <c:if test="${param.confirm == 'error'}">
                                                        <p style="color: red">Ошибка добавления заказа!</p>
                                                    </c:if>

                                                    <input type="submit" value="${confirmSubmitBtn}" />
                                                </td>
                                            </tr>
                                        </table>



                                    </form:form>
                                </div>
                            </c:if>


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