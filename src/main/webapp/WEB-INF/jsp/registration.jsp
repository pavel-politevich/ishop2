<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value="/resources/newcss.css"/>" rel="stylesheet"
	type="text/css" />

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="local.footer.copyright"
	var="copyright" />

<fmt:message bundle="${loc}" key="local.registration.login.name"
	var="loginNameLbl" />
<fmt:message bundle="${loc}" key="local.registration.password.name"
	var="passwordName" />
<fmt:message bundle="${loc}" key="local.registration.firstName.name"
	var="firstName" />
<fmt:message bundle="${loc}" key="local.registration.LastName.name"
	var="lastName" />
<fmt:message bundle="${loc}" key="local.registration.email.name"
	var="emailName" />
<fmt:message bundle="${loc}" key="local.registration.phone.name"
	var="phoneName" />
<fmt:message bundle="${loc}" key="local.registration.address.name"
	var="addressName" />
<fmt:message bundle="${loc}" key="local.registration.dateBirth.name"
	var="dateBirth" />
<fmt:message bundle="${loc}" key="local.registration.btnSubmit.name"
	var="btnSubmitName" />
<fmt:message bundle="${loc}" key="local.registration.fail.message"
	var="registrationFailMessage" />
<fmt:message bundle="${loc}"
	key="local.registration.fail.unique.message"
	var="registrationFailUnique" />



</head>
<body>

	<div id="wrap">

		<header> <jsp:include page="/WEB-INF/jsp/_header.jsp" /> </header>


		<main> <br />

		<jsp:include page="/WEB-INF/jsp/_menuTop.jsp" /> <br />

		<c:if test="${param.register == 'error'}">
			<h2 class="login_fail_message">${registrationFailMessage}</h2>
		</c:if> <c:if test="${param.error == 'unique'}">
			<h2 class="login_fail_message">${registrationFailUnique}</h2>
		</c:if>

		<div class="registration_container">

			<form:form id="regForm" modelAttribute="user"
				action="registerProcess" method="post">

				<TABLE class="tbl_cnt">
					<tr>
						<td><form:label path="login">${loginNameLbl}</form:label></td>
						<td><form:input path="login" type="text" id="login"
								name="login" /> <br /> <br /></td>
					</tr>

					<tr>
						<td><form:label path="password">${passwordName}</form:label>
						</td>
						<td><form:input path="password" type="password" id="psw"
								name="password" /> <br /> <br /></td>
					</tr>

					<tr>
						<td><form:label path="name">${firstName}</form:label></td>
						<td><form:input path="name" type="text" id="name" name="name" />
							<br /> <br /></td>
					</tr>

					<tr>
						<td><form:label path="surname">${lastName}</form:label></td>
						<td><form:input path="surname" type="text" id="surname"
								name="surname" /> <br /> <br /></td>
					</tr>

					<tr>
						<td><form:label path="email">${emailName}</form:label></td>
						<td><form:input path="email" type="email" id="email"
								name="email" /> <br /> <br /></td>
					</tr>

					<tr>
						<td><form:label path="phone">${phoneName}</form:label></td>
						<td><form:input path="phone" type="text" id="phone"
								name="phone" /> <br /> <br /></td>
					</tr>

					<tr>
						<td><form:label path="address">${addressName}</form:label></td>
						<td><form:input path="address" type="text" id="address"
								name="address" /> <br /> <br /></td>
					</tr>

					<tr>
					

					
						<td><form:label path="dateOfBirth">${dateBirth}</form:label>
						</td>
						<td><form:input path="dateOfBirth" type="date"
								id="dateOfBirth" name="dateOfBirth"  class= "date"
								/> 
								
								<br /> <br /></td>
					</tr>
				</table>


				<form:button id="register" name="register">${btnSubmitName}</form:button>

			</form:form>
		</div>


		</main>
	</div>

	<footer> ${copyright} </footer>

</body>
</html>