<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="pgn" uri = "mytags.com/pagination" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="payments.title" var = "title"/>
<fmt:message key="payments.pay-title" var = "pay_title"/>
<fmt:message key="payments.pay-description" var = "pay_description"/>
<fmt:message key="payments.pay-price" var = "pay_price"/>
<fmt:message key="payments.pay-date" var = "pay_date"/>
<fmt:message key="payments.no-payments" var = "no_payments"/>
<fmt:message key="uah" var = "uah"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>

	<%@ include file="/resources/jspf/navbar.jspf" %>
  <div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>${title}</h2>
				</div>
			</div>

			<div class="row">
<!-- You can make it whatever width you want. I'm making it full width
	on <= small devices and 4/12 page width on >= medium devices -->
	<div class="col-xs-12 col-md-4">

			<c:if test="${empty payments}">
				<p>${no_payments}</p>
			</c:if>
		<c:if test="${not empty payments}">
			 <table class="table table-bordered">
    <thead>
      <tr>
        <th>${pay_title}</th>
        <th>${pay_description}</th>
        <th>${pay_price}</th>
        <th>${pay_date}</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var = "payment" items="${ payments}">
      <tr>
      <c:if test="${payment.price < 0 }">
      	<c:set var = "color" value="red"/>
      </c:if>
      <c:if test="${payment.price > 0 }">
      	<c:set var = "color" value="green"/>
      </c:if>

        <td>${payment.title}</td>
        <td>${payment.description}</td>
        <td style="color:${color}">${payment.price} <small>${uah}</small></td>
        <td><fmt:formatDate type="both"
            value="${payment.date}" timeStyle="short" dateStyle="short" /></td>
      </tr>
      </c:forEach>
       </tbody>
  </table>
  </c:if>

	<pgn:setPageIndexesList numOfPages="${numOfPages}" numOfPageIndexes="10" index="${pageIndex}" list="indexes"/>

	<ul class = "pagination">
	<li><a href = "/payments?page-index=1"><<</a></li>
	<c:forEach items="${indexes}" var = "index">
		<c:choose>
		<c:when test="${index eq pageIndex}">
			<li class = "active"><a href = "/payments?page-index=${index}">${index}</a><li>
		</c:when>
		<c:otherwise>
			<li><a href = "/payments?page-index=${index}">${index}</a><li>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	<li><a href = "/payments?page-index=${numOfPages}">>></a></li>
	</ul>
		</div>
		</div>


</body>
</html>