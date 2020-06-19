<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.menu.line.login" var="loginName" />
            <fmt:message bundle="${loc}" key="local.menu.line.logout" var="logoutName" />
            <fmt:message bundle="${loc}" key="local.menu.line.registration" var="RegistrationName" />
            <fmt:message bundle="${loc}" key="local.menu.line.main" var="MainName" />
            <fmt:message bundle="${loc}" key="local.menu.line.catalog" var="CatalogName" />
            <fmt:message bundle="${loc}" key="local.menu.line.news" var="NewsName" />
            <fmt:message bundle="${loc}" key="local.menu.line.delivery" var="DeliveryName" />

            <fmt:message bundle="${loc}" key="local.item.cart.cartname" var="CartName" />



        <div class="menu_line">
            <table border="0" width="100%" id="menu_line">
                <tr>
                    <td width="8%">
                        <a href="<c:url value="/showMain"/>">${MainName}</a>
                    </td>
                    <td width="8%">
                        <a href="<c:url value="/showCatalog"/>">${CatalogName}</a>
                    </td>
                    <td width="8%">
                        ${NewsName}
                    </td>
                    <td width="10%">
                        ${DeliveryName}
                    </td>
                    <td class="right_list">


                        <a href="<c:url value="/showCart"/>">${CartName}</a> |

                        <c:if test = "${sessionScope.user.name != null}">
                            <c:out value = "${sessionScope.user.name}"/> |
                            <a href="Controller?command=signout">${logoutName}</a>
                        </c:if>

                        <c:if test = "${sessionScope.user.name == null}">
                            <a href="Controller?command=go_to_login">${loginName}</a>
                        </c:if>
                    </td>

                </tr>
            </table>
        </div>
