<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="utils" uri="mytags.com/utils" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>


<fmt:message key="tariff.calls-in-network" var="calls_in_network" />
<fmt:message key="tariff.calls-out-of-network" var="calls_out_of_network" />
<fmt:message key="tariff.internet-megabytes" var="internet_megabytes" />
<fmt:message key="tariff.cost-per-month" var="cost_per_month" />
<fmt:message key="tariff.choose-cancel" var="choose_cancel" />
<fmt:message key="tariff.choose-ok" var="choose_ok" />
<fmt:message key="tariff.mins" var = "mins"/>
<fmt:message key="tariff.megabytes" var = "megabytes"/>

<fmt:message key="admin.tariffs.title" var="title" />
<fmt:message key="admin.tariffs.tariff-title" var="tariff_title" />
<fmt:message key="admin.tariffs.description" var="description" />
<fmt:message key="admin.tariffs.delete" var="delete_tariff" />
<fmt:message key="admin.tariffs.edit" var="edit_tariff" />
<fmt:message key="admin.tariffs.create" var="create_tariff" />
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

					<c:if test="${empty tariffs}">
						<p>${no_tariffs}</p>
					</c:if>
					<c:if test="${not empty tariffs}">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>${tariff_title}</th>
									<th>${description}</th>
									<th>${cost_per_month}</th>
									<th>${calls_in_network}</th>
									<th>${calls_out_of_network}</th>
									<th>${internet_megabytes}</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<c:forEach var="tariff" items="${tariffs}" varStatus="counter">
							<tbody>
									<tr>
										<td>${tariff.title}</td>
										<td>${tariff.description}</td>
										<td>${tariff.costPerMonth} <small>${uah}</small></td>

										<td>
										<utils:print ifTrue="${tariff.minutesOfCallsInNetwork < 0}"
													 out = "&infin;"
													 otherwise="${tariff.minutesOfCallsInNetwork}"/>
													 <small>${mins }</small>
										</td>
										<td>
										<utils:print ifTrue="${tariff.minutesOfCallsOutOfNetwork < 0}"
													 out = "&infin;"
													 otherwise="${tariff.minutesOfCallsOutOfNetwork}"/>
													 <small>${mins}</small>

										</td>

										<td>
										<utils:print ifTrue="${tariff.internetMegabytes < 0}"
													 out = "&infin;"
													 otherwise="${tariff.internetMegabytes}"/>
													 <small>${megabytes }</small>
										</td>
										<td>
											<button data-tariff-id="${tariff.id}" type="button"
											class="btn btn-default edit-button">
											${edit_tariff}
											</button>
										</td>
										<td class = "col-md-2">
											<button data-tariff-id="${tariff.id}" data-tariff-title = "${tariff.title}"
											 type="button"
											class="btn btn-default delete-button">
											${delete_tariff}
											</button>
										</td>
									</tr>

										<!-- Edit Tariff Modal -->
	<div id="edit-tariff-mod-${tariff.id}" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${edit_tariff}</h4>
				</div>
				<form id = "edit-form-${tariff.id}" method="post">
				<div class="modal-body">
				<div class = "form-group">
					<p class="contact">
						<label for="title">${tariff_title}</label>
					</p>
						<input class = "form-control" type="text" id="title" name="title" required="true"
							placeholder="${tariff_title}" value="${tariff.title }" style = "width: 100%">

					<p class="contact">
						<label for="description">${description}</label>
					</p>
						<textarea class = "form-control" id="description" name="description" rows="5" required="true"
								 style = "width: 100%">${tariff.description }</textarea>
					<p class="contact">
						<label for="costPerMonth">${cost_per_month} <small>(${uah})</small></label>
					</p>
						<input class = "form-control" type="text" id="costPerMonth" name="costPerMonth"
							required="true"
							placeholder="${cost_per_month}" value="${tariff.costPerMonth }"
							pattern = "[1-9]\d*(\.\d+)?">

					<p class="contact">
						<label for="minutesOfCallsInNetwork">${calls_in_network} <small>(${mins})</small></label>
					</p>
						<input class = "form-control" type="text" id="minutesOfCallsInNetwork"
							name="minutesOfCallsInNetwork"
							required="true"
							placeholder="${calls_in_network}"
							value="${tariff.minutesOfCallsInNetwork }"
							pattern = "(0)|(-?[1-9]\d*)">
					<p class="contact">
						<label for="minutesOfCallsOutOfNetwork">${calls_out_of_network} <small>(${mins})</small></label>
					</p>
						<input class = "form-control" type="text" id="minutesOfCallsOutOfNetwork"
							name="minutesOfCallsOutOfNetwork"
							required="true"
							placeholder="${calls_out_of_network}"
							value="${tariff.minutesOfCallsOutOfNetwork }"
							pattern = "(0)|(-?[1-9]\d*)">

						<p class="contact">
						<label for="internetMegabytes">${internet_megabytes} <small>(${megabytes})</small></label>
					</p>
						<input class = "form-control" type="text" id="internetMegabytes"
							name="internetMegabytes"
							required="true"
							placeholder="${internet_megabytes}"
							value="${tariff.internetMegabytes }"
							pattern = "(0)|(-?[1-9]\d*)">

						<input type="hidden" id = "id" name = "id" value = "${tariff.id}">
						<input type="hidden" id = "action" name = "action" value = "edit-tariff">
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button  type = "submit" id="edit-tariff-button" type="button" data-tariff-id = "${tariff.id}"
						class="btn btn-default edit-tariff" >${choose_ok}</button>
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
						${create_tariff}
					</button>

	<div id="create-tariff-mod" class="modal fade" role="dialog">
		<div class="modal-dialog">

							<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${edit_tariff}</h4>
				</div>
				<form id = "create-form" method="post">
				<div class="modal-body">
				<div class = "form-group">
					<p class="contact">
						<label for="title">${tariff_title}</label>
					</p>
						<input class = "form-control" type="text" id="title" name="title" required="true"
							placeholder="${tariff_title}"style = "width: 100%">

					<p class="contact">
						<label for="description">${description}</label>
					</p>
						<textarea class = "form-control" id="description" name="description" rows="5" required="true"
								 style = "width: 100%">${tariff.description }</textarea>
										<p class="contact">
						<label for="costPerMonth">${cost_per_month} <small>(${uah})</small></label>
					</p>
						<input class = "form-control" type="text" id="costPerMonth" name="costPerMonth"
							required="true"
							placeholder="${cost_per_month}"
							pattern = "[1-9]\d*(\.\d+)?">

					<p class="contact">
						<label for="minutesOfCallsInNetwork">${calls_in_network} <small>(${mins})</small></label>
					</p>
						<input class = "form-control" type="text" id="minutesOfCallsInNetwork"
							name="minutesOfCallsInNetwork"
							required="true"
							placeholder="${calls_in_network}"
							pattern = "(0)|(-?[1-9]\d*)">
					<p class="contact">
						<label for="minutesOfCallsOutOfNetwork">${calls_out_of_network} <small>(${mins})</small></label>
					</p>
						<input class = "form-control" type="text" id="minutesOfCallsOutOfNetwork"
							name="minutesOfCallsOutOfNetwork"
							required="true"
							placeholder="${calls_out_of_network}"
							pattern = "(0)|(-?[1-9]\d*)">

						<p class="contact">
						<label for="internetMegabytes">${internet_megabytes} <small>(${megabytes})</small></label>
					</p>
						<input class = "form-control" type="text" id="internetMegabytes"
							name="internetMegabytes"
							required="true"
							placeholder="${internet_megabytes}"
							pattern = "(0)|(-?[1-9]\d*)">
	<input type="hidden" id = "action" name = "action" value = "create-tariff">
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
					<button  type = "submit" id="edit-tariff-button" type="button" data-tariff-id = "${tariff.id}"
						class="btn btn-default edit-tariff" >${choose_ok}</button>
						</div>

						</form>
				</div>
			</div>
		</div>
</div>
</div>


				</div>
			</div>

		<script type = "text/javascript" src = "/resources/js/admin-tariffs.js"></script>

	</body>
</html>