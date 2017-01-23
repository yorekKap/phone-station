<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="services.title" var = "title"/>
<fmt:message key="services.choose-cancel" var = "choose_cancel"/>
<fmt:message key="services.choose-ok" var = "choose_ok"/>

<fmt:message key = "admin.services.service-title" var = "service_title"/>
<fmt:message key = "admin.services.description" var = "description"/>
<fmt:message key = "admin.services.cost" var = "cost"/>
<fmt:message key = "admin.services.delete" var = "delete_service"/>
<fmt:message key = "admin.services.edit" var = "edit_service"/>
<fmt:message key = "admin.services.create" var = "create_service"/>
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
				<div class="col-xs-12 col-md-7">

					<c:if test="${empty services}">
						<p>${no_services}</p>
					</c:if>
					<c:if test="${not empty services}">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th style="width:20%">${service_title}</th>
									<th style="width:40%">${description}</th>
									<th style="width:20%">${cost}</th>
									<th style="width:10%"></th>
									<th style="width:10%"></th>
								</tr>
							</thead>
							<c:forEach var="service" items="${services}" varStatus="counter">
							<tbody>
									<tr>
										<td class = "col-md-2">${service.title}</td>
										<td class = "col-md-4">${service.description}</td>
										<td class = "col-md-2">${service.cost} <small>${uah}</small></td>
										<td class = "col-md-2">
											<button data-service-id="${service.id}" type="button"
											class="btn btn-default edit-button">
											${edit_service}
											</button>
										</td>
										<td class = "col-md-2">
											<button data-service-id="${service.id}" data-service-title = "${service.title}"
											 type="button"
											class="btn btn-default delete-button">
											${delete_service}
											</button>
										</td>
									</tr>

										<!-- Exclusive Number Modal -->
	<div id="edit-service-mod-${service.id}" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${edit_service}</h4>
				</div>
				<form id = "edit-form-${service.id}" method="post">
				<div class="modal-body">
				<div class = "form-group">
					<p class="contact">
						<label for="title">${service_title}</label>
					</p>
						<input class = "form-control" type="text" id="title" name="title" required="true"
							placeholder="${service_title}" value="${service.title }" style = "width: 100%">

					<p class="contact">
						<label for="description">${description}</label>
					</p>
						<textarea class = "form-control" id="description" name="description" rows="5" required="true"
								 style = "width: 100%">${service.description }</textarea>
					<p class="contact">
						<label for="cost">${cost}<small>(${uah})</small></label>
					</p>
						<input class = "form-control" type="" id="cost" name="cost" required="true"
							placeholder="${cost}" value="${service.cost }" pattern = "[1-9]\d+(\.\d+)?">
						<input type="hidden" id = "id" name = "id" value = "${service.id}">
						<input type="hidden" id = "action" name = "action" value = "edit-service">
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button  type = "submit" id="edit-service-button" type="button" data-service-id = "${service.id}"
						class="btn btn-default edit-service" >${choose_ok}</button>
						</div>

						</form>
				</div>
			</div>
		</div>
	</div>
									</c:forEach>
							</tbody>
						</table>
					</c:if>

					<button type="button" class="btn btn-default create-button">
						${create_service}
					</button>

	<div id="create-service-mod" class="modal fade" role="dialog">
		<div class="modal-dialog">

							<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${edit_service}</h4>
				</div>
				<form id = "create-form" method="post">
				<div class="modal-body">
				<div class = "form-group">
					<p class="contact">
						<label for="title">${service_title}</label>
					</p>
						<input class = "form-control" type="text" id="title" name="title" required="true"
							placeholder="${service_title}"style = "width: 100%">

					<p class="contact">
						<label for="description">${description}</label>
					</p>
						<textarea class = "form-control" id="description" name="description" rows="5" required="true"
								 style = "width: 100%">${service.description }</textarea>
					<p class="contact">
						<label for="cost">${cost}<small>(${uah})</small></label>
					</p>
						<input class = "form-control" type="" id="cost" name="cost" required="true"
							placeholder="${cost}" pattern = "[1-9]\d+(\.\d+)?">
						<input type="hidden" id = "action" name = "action" value = "create-service">
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button  type = "submit" id="edit-service-button" type="button" data-service-id = "${service.id}"
						class="btn btn-default edit-service" >${choose_ok}</button>
						</div>

						</form>
				</div>
			</div>
		</div>
</div>
</div>


				</div>
			</div>

		<script type = "text/javascript" src = "/resources/js/admin-services.js"></script>

	</body>
</html>