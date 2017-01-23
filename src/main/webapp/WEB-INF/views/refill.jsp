<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key = "refill.title" var = "title"/>
<fmt:message key = "refill.payment-details" var = "payment_details"/>
<fmt:message key = "refill.card-number" var = "card_number"/>
<fmt:message key = "refill.expiration-date" var = "expiration_date"/>
<fmt:message key = "refill.cvv" var = "cvv"/>
<fmt:message key = "refill.sum" var = "sum"/>
<fmt:message key = "refill.refill" var = "refill"/>
<fmt:message key = "refill.successful" var = "is_successful"/>
<fmt:message key = "uah" var = "uah"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
</head>
<body>
	<input id = "successful" hidden data-successful = "${successful}" data-sum = "${refillSum}"></input>

	<%@ include file="/resources/jspf/navbar.jspf" %>
  <div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>${title}</h2>
				</div>
			</div>
	<div class="row">
  <div class="col-xs-12 col-md-6">


    <!-- CREDIT CARD FORM STARTS HERE -->
    <div class="panel panel-default credit-card-box">
      <div class="panel-heading display-table" >
        <div class="row display-tr" >
          <h3 class="panel-title display-td" >${payment_details}</h3>
          <div class="display-td" >
            <img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png">
          </div>
        </div>
      </div>
      <div class="panel-body">
        <form role="form" id="payment-form" method = "post">
          <div class="row">
            <div class="col-xs-12">
              <div class="form-group">
                <label for="cardNumber">${card_number}</label>
                <div class="input-group">
                  <input
                  id = "number"
                  type="text"
                  class="form-control"
                  name="cardNumber"
                  placeholder="Valid Card Number"
                  autocomplete="cc-number"
                  required autofocus
                  pattern = "\d{4}-\d{4}-\d{4}-\d{4}"
                  />
                  <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-7 col-md-7">
              <div class="form-group">
                <label for="cardExpiry">${expiration_date}</label>
                <input
                id = "expiration"
                type="tel"
                class="form-control"
                name="cardExpiry"
                placeholder="MM / YY"
                autocomplete="cc-exp"
                required
                pattern = "(0[1-9])|(1[12])/\d{2}"
                />
              </div>
            </div>
            <div class="col-xs-5 col-md-5 pull-right">
              <div class="form-group">
                <label for="cardCVC">${cvv}</label>
                <input
                id = "cv"
                type="tel"
                class="form-control"
                name="cardCVC"
                placeholder="CVC"
                autocomplete="cc-csc"
                required
                pattern = "\d{3}"
                />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="form-group">
                <label for="sum">${sum}<small>(${uah})</small></label>
                <input type="text"
                 id = "sum"
                 class="form-control"
                 name="sum"
                 required
                 pattern = "[1-9]\d*(.\d+)?" />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <button class="btn btn-success btn-lg btn-block" type="submit">${refill}</button>
            </div>
          </div>
          <div class="row" style="display:none;">
            <div class="col-xs-12">
              <p class="payment-errors"></p>
            </div>
          </div>
        </form>
      </div>
    </div>
    <!-- CREDIT CARD FORM ENDS HERE -->


  </div>

</div>
</div>

		</div>
		</div>

	<script type="text/javascript" src = "resources/js/refill.js"></script>

</body>
</html>