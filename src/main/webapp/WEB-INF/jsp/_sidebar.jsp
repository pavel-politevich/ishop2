<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.header.name" var="headerName" />


        <div class="menu_left">

            <c:forEach items="${categories}" var="element">
            
            	<c:url var="openLink" value="/showItems">
					<c:param name="categoryId" value="${element.categoryId}" />
				</c:url>
            
                <p><a href="${openLink}">${element.name}</a></p>
            </c:forEach>

        </div>



