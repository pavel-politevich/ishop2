<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>



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

						<sec:authorize access="authenticated" var="authenticated" />

                     
                        <c:choose>
							<c:when test="${authenticated}">
								<sec:authentication property="name" /> |
                            	
                            	<a id="logout" href="#" onclick="document.getElementById('logout-form').submit();">${logoutName}</a>
                            	
                            	<form id="logout-form" action="<c:url value="/logout"/>" method="POST">
									<sec:csrfInput/>
								</form>
                            	
							</c:when>
							<c:otherwise>
								<a href="<c:url value="/login"/>">${loginName}</a>
							</c:otherwise>
						</c:choose>
                        
                        
                    </td>

                </tr>
            </table>
        </div>
