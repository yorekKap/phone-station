<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pgn" uri="mytags.com/pagination" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="users.title" var =  "title"/>
<fmt:message key="users.first-name" var = "first_name"/>
<fmt:message key="users.last-name" var = "last_name"/>
<fmt:message key="users.no-additional-phone" var = "no_additional_phone"/>
<fmt:message key="users.registration-date" var = "registration_date"/>
<fmt:message key="users.disconnect-user" var = "disconnect_user"/>
<fmt:message key="home.username" var = "username"/>
<fmt:message key="home.balance" var = "balance"/>
<fmt:message key="home.phone" var = "phone"/>
<fmt:message key="home.additional-phone" var = "additional_phone"/>
<fmt:message key="home.tariff" var = "tariff"/>
<fmt:message key="uah" var = "uah"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>
	<%@ include file="/resources/jspf/admin-navbar.jspf" %>
            <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>${title}</h2>
                        </div>
                    </div>
			<div class="row">
				<div class="col-xs-12 col-md-4">

					<c:if test="${empty users}">
						<p>${no_users}</p>
					</c:if>
					<c:if test="${not empty users}">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>${first_name}</th>
									<th>${last_name}</th>
									<th>${username}</th>
									<th>${phone}</th>
									<th>${additional_phone}</th>
									<th>${balance}</th>
									<th>${registration_date}</th>
									<th>${delete_user}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${users}">
									<tr>
										<c:if test="${user.balance < 0 }">
											<c:set var="color" value="red" />
										</c:if>
										<c:if test="${user.balance >= 0 }">
											<c:set var="color" value="green" />
										</c:if>

										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td>${user.username}</td>
										<td>${user.phone}</td>
										<c:if test="${user.additionalPhone != null}">
											<td>${user.additionalPhone}</td>
										</c:if>
										<c:if test="${user.additionalPhone == null}">
											<td>${no_additionalPhone}</td>
										</c:if>
										<td style="color:${color}">${user.balance} <small>${uah}</small></td>
										<td>${user.registrationDate}</td>
										<td>
											<button data-uid="${user.id}" data-username = "${user.username}"
												type="button" class="btn btn-default disconnect-button">
												${disconnect_user}</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>

					<pgn:setPageIndexesList numOfPages="${numOfPages}" numOfPageIndexes="5" index="${pageIndex}" list="indexes"/>

					<ul class="pagination">
						<li><a href="/admin/users?page-index=1"><<</a></li>
						<c:forEach items="${indexes}" var="index">
							<c:choose>
								<c:when test="${index eq pageIndex}">
									<li class="active"><a href="/admin/users?page-index=${index}">${index}</a>
									<li>
								</c:when>
								<c:otherwise>
									<li><a href="/admin/users?page-index=${index}">${index}</a>
									<li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="/admin/users?page-index=${numOfPages}">>></a></li>
					</ul>

				</div>
			</div>

		<script type = "text/javascript" src = "/resources/js/admin-users.js"></script>

	</body>
</html>