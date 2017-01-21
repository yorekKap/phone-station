<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "utils" uri ="mytags.com/utils" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="tariff.calls-in-network" var = "calls_in_network"/>
<fmt:message key="tariff.calls-out-of-network" var = "calls_out_of_network"/>
<fmt:message key="tariff.internet-megabytes" var = "internet_megabytes"/>
<fmt:message key="tariff.cost-per-month" var = "cost_per_month"/>
<fmt:message key="tariff.tariffs-title" var = "tariffs_title"/>
<fmt:message key="tariff.choose-tariff" var = "choose_tariff"/>
<fmt:message key="tariff.cancel-tariff" var = "cancel_tariff"/>
<fmt:message key="tariff.choose-assurance" var = "choose_assurance"/>
<fmt:message key="tariff.choose-cancel" var = "choose_cancel"/>
<fmt:message key="tariff.choose-ok" var = "choose_ok"/>
<fmt:message key="tariff.disconnect" var = "disconnect"/>
<fmt:message key="tariff.disconnect-question" var = "disconnect_question"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${tariffs_title}</title>
</head>
<body>
		<%@ include file="/resources/jspf/navbar.jspf" %>
	  <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>${tariffs_title}</h2>
                        </div>
                    </div>
                    <!-- /. ROW  -->
                    <hr />
                    <c:set var="length" value="${fn:length(tariffs)}"></c:set>
                    <c:forEach var = "i" begin="1" step = "2" end = "${length}">
					<div class="row">

						<c:set var = "end" value = "${i}"></c:set>

						<c:if test="${ end >= (length - 1)}">
							<c:set var="end" value = "${length - 1}"></c:set>
						</c:if>

						<c:forEach var = "j" begin="${i - 1}" end = "${end}">
                         <c:set var = "tariff" value = "${tariffs[j]}"></c:set>
	                        <div class="col-md-4 col-sm-4 col-xs-4">
                            <div class="panel panel-primary text-center no-boder bg-color-blue">
                                <div class="panel-body">
                                    <i class="fa fa-bar-chart-o fa-5x"></i>
                                    <h3>${tariff.title}</h3>
                                    <h6>${tariff.description}</h6>
                                </div>
                                <table class = "table">
                                    <tr>
									<td>${calls_in_network}</td>
										<td><utils:print
												ifTrue="${user.tariff.minutesOfCallsInNetwork >= 0}"
												out="${user.tariff.minutesOfCallsInNetwork}"
												otherwise="&infin;" />
										</td>
									</tr>
                                    <tr>
                                        <td>${calls_out_of_network}</td>
                         				<td>
                                        <utils:print ifTrue="${user.tariff.minutesOfCallsOutOfNetwork >= 0}"
													 out="${user.tariff.minutesOfCallsOutOfNetwork}"
													 otherwise = "&infin;"/>
										</td>
                                    </tr>
                                    <tr>
                                        <td>${internet_megabytes}</td>
                                        <td>
                                        <utils:print ifTrue="${user.tariff.internetMegabytes >= 0}"
													 out="${user.tariff.internetMegabytes}"
													 otherwise = "&infin;"/>
										</td>
										</tr>

                                    <tr>
                                        <td>${cost_per_month}</td>
                                        <td>${tariff.costPerMonth}</td>
                                    </tr>
                                </table>

                               <c:if test = "${currentTariffId == tariff.id}">
                                <a id = "disconnect"
                                class="disconnect btn btn-warning btn-lg btn-block" >${cancel_tariff}</a>
								</c:if>

								<c:if test = "${currentTariffId != tariff.id}">
                                <a id = "choose" data-tariff = "${tariff.title}" data-tariff-id = "${tariff.id}"
                                class="choose btn btn-warning btn-lg btn-block" >${choose_tariff}</a>
								</c:if>
                            </div>

                        </div>
                        </c:forEach>
                    </div>
                    </c:forEach>
                    </div>
                    </div>

                    <!-- Modal -->
	<div id="choose-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${choose_tariff}</h4>
				</div>
				<div class="modal-body">
					<p>${choose_assurance} '<snap id = "chosen-tariff"></snap>' ?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
				    <button id = "ok-button" type="button" class="btn btn-default" >${choose_ok}</button>
				</div>
			</div>
</div>
</div>
			                    <!-- Modal -->
	<div id="disconnect-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${disconnect}</h4>
				</div>
				<div class="modal-body">
					<p>${disconnect_question}</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">${choose_cancel}</button>
				    <button id = "disconnect-ok-button" type="button" class="btn btn-default" >${choose_ok}</button>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript" src="/resources/js/tariffs.js"></script>

</body>
</html>