<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="services.title" var = "title"/>
<fmt:message key="services.my-services" var = "my_services"/>
<fmt:message key="services.price" var = "price"/>
<fmt:message key="services.choose" var = "choose"/>
<fmt:message key="services.additional-number" var = "additional_number"/>
<fmt:message key="services.choose-cancel" var = "choose_cancel"/>
<fmt:message key="services.choose-ok" var = "choose_ok"/>
<fmt:message key="services.choose-service" var ="choose_service"/>
<fmt:message key="services.choose-service-question" var ="choose_service_question"/>
<fmt:message key="services.exclusive-number" var = "exclusive_number"/>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body data-balance = "${user.balance}">

	<%@ include file="/resources/jspf/navbar.jspf" %>
  <div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>${title}</h2>
				</div>
			</div>

			<c:forEach var="service" items = "${services}" varStatus="counter">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapse${counter.index}">${service.title}</a>
						</h4>
					</div>
					<div id="collapse${counter.index}" class="panel-collapse collapse">
						<div class="panel-body">
							${service.description }
							<br/>
							${price} : ${service.cost}
						</div>
						<div class="panel-footer">
				  		  <button data-action = "${service.title}" data-serviceid = "${service.id}"
				  		  data-cost = "${service.cost}"
				  		   type="button" class="btn btn-default choose-button" >
				  		  	${choose}
				  		  </button>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>


		<c:if test="${my_services != null}">
		<div class="row">
				<div class="col-md-12">
					<h2>${my_services}</h2>
				</div>
			</div>

			<c:forEach var="service" items = "${myServices}" varStatus="counter">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapse1${counter.index}">${service.title}</a>
						</h4>
					</div>
					<div id="collapse1${counter.index}" class="panel-collapse collapse">
						<div class="panel-body">
							${service.description}
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
			</c:if>

			</div>
		</div>


	<!-- Additional Number Modal -->
	<div id="additional-number-mod" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${additional_number}</h4>
				</div>
				<div class="modal-body">
					<p class="contact">
						<label for="phone">${additional_number}</label>
					</p>
					<input id="additional-number" name="phone"
						placeholder="380XXXXXXXXX" required="true" tabindex="3" type="tel"
						pattern="380[0-9]{9}" value="${user.phone}">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button id="additional-number-button" type="button"
						class="btn btn-default">${choose_ok}</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Exclusive Number Modal -->
	<div id="exclusive-number-mod" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${exclusive_number}</h4>
				</div>
				<div class="modal-body">
					<p class="contact">
						<label for="phone">${exclusive_number}</label>
					</p>
					<input id="exclusive-number" name="phone"
						placeholder="380XXXXXXXXX" required="true" tabindex="3" type="tel"
						pattern="380[0-9]{9}" value="${user.phone}">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button id="exclusive-number-button" type="button"
						class="btn btn-default" >${choose_ok}</button>
				</div>
			</div>
		</div>
	</div>



	<!-- Modal -->
	<div id="service-mod" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${choose_service}</h4>
				</div>
				<div class="modal-body">
					<p>${choose_service_question} '<snap id = "service-title"></snap>'?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button id="choose-ok-button" type="button"
						class="btn btn-default">${choose_ok}</button>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript" src="/resources/js/services.js"></script>


</body>
</html>