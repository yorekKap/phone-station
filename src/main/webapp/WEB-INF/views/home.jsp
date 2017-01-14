<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="utils" uri="mytags.com/utils"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="home.username" var = "username"/>
<fmt:message key="home.balance" var = "balance"/>
<fmt:message key="home.phone" var = "phone"/>
<fmt:message key="home.additional-phone" var = "additional_phone"/>
<fmt:message key="home.tariff" var = "tariff"/>
<fmt:message key="home.info" var = "info"/>
<fmt:message key="home.change-tariff" var = "change_tariff"/>
<fmt:message key="home.no-tariff" var = "no_tariff"/>
<fmt:message key="home.choose-tariff" var = "choose_tariff"/>
<fmt:message key="tariff.calls-in-network" var = "calls_in_network"/>
<fmt:message key="tariff.calls-out-of-network" var = "calls_out_of_network"/>
<fmt:message key="tariff.internet-megabytes" var = "internet_megabytes"/>
<fmt:message key="tariff.cost-per-month" var = "cost_per_month"/>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>${mainpage}</title>

    </head>
    <body>

	<%@ include file="/resources/jspf/navbar.jspf" %>
            <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>${user.firstName} ${user.lastName}</h2>
                        </div>
                    </div>
                    <!-- /. ROW  -->
                    <hr />
                    <div class = "row">
                        <div class="col-md-3 col-sm-3 col-xs-6">
                            <h5>${info}</h5>
                            <table class="table table-bordered">
                                <tbody>
                                    <tr>
                                        <td>${username}</td>
                                        <td>${user.username}</td>
                                    </tr>
                                    <tr>
                                        <td>${phone}</td>
                                        <td>${user.phone}</td>
                                    </tr>
                                    <c:if test="${user.additionalPhone != null}">
	                                    <tr>
	                                        <td>${additional_phone}</td>
	                                        <td>${user.additionalPhone}</td>
	                                    </tr>
                                    </c:if>
                                    <tr>
                                        <td>${balance}</td>
                                        <td>${user.balance}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>

					<div class="row">
                        <div class="col-md-9 col-sm-9 col-xs-9">
                    	<c:if test="${user.tariff != null }">
                            <h5>${tariff}</h5>
                            <div class="panel panel-primary text-center no-boder bg-color-blue">
                                <div class="panel-body">
                                    <i class="fa fa-bar-chart-o fa-5x"></i>
                                    <h3>${user.tariff.title}</h3>
                                    <h6>${user.tariff.description}</h6>
                                </div>
                                <table class = "table">
                                   <tr>
                                        <td>${calls_in_network}</td>

										<td>
										<utils:print ifTrue="${user.tariff.minutesOfCallsInNetwork >= 0}"
													 out="${user.tariff.minutesOfCallsInNetwork}"
													 otherwise = "&infin;"/>
										</td>

										<%-- I left it here to illustrate advantage of my custom tag compared
										to the JSTL one (in this particular use case)

										<c:choose>
											<c:when test="${user.tariff.minutesOfCallsInNetwork >= 0}">
												<td>${user.tariff.minutesOfCallsInNetwork}</td>
											</c:when>
											<c:otherwise>
												<td>&infin;</td>
											</c:otherwise>
										</c:choose> --%>

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
                                        <td>${user.tariff.costPerMonth}</td>
                                    </tr>
                                </table>
                                <a href="/tariffs"
                                class="btn btn-warning btn-lg btn-block">${change_tariff}</a>

                            </div>
						</c:if>
						<c:if test="${user.tariff == null}">
							<p>         ${no_tariff}!</p>
							 <a href="/tariffs"
                                class="btn btn-warning btn-lg btn-block">${choose_tariff}</a>

						</c:if>
                        </div>
                    </div>
                    </div>
                    </div>

                    <!-- /. ROW  -->
        <!-- /. WRAPPER  -->
    </body>
</html>
