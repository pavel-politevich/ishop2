<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



			<fmt:setLocale value="${sessionScope.local}" />
			<fmt:setBundle basename="local" var="loc" />

			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"	var="ru_button" />
			<fmt:message bundle="${loc}" key="local.locbutton.name.en"	var="en_button" />
			<fmt:message bundle="${loc}" key="local.header.name" var="headerName" />
			
 
        ${headerName}
        <div class="languages">
            <table>
                <tr>
                    <td>
                        <form action="changeLocale" method="post">
                            <input type="hidden" name="command" value="changeLocale" />
                            <input type="hidden" name="local" value="ru" />
                            <input type="submit" value="${ru_button}" />
                            <br />
                        </form>
                    </td>
                    <td>
                        <form action="changeLocale" method="post">
                            <input type="hidden" name="command" value="changeLocale" />
                            <input type="hidden" name="local" value="en" />
                            <input type="submit" value="${en_button}" />
                            <br />
                        </form>
                    </td>
                </tr>
            </table>
        </div>

