<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="login.title" var = "title"/>
<fmt:message key="login.username" var = "username"/>
<fmt:message key="login.password" var = "password"/>
<fmt:message key="login.login-button" var="logbutton" />
<fmt:message key="login.to-registration" var = "registration"/>
<fmt:message key="login.wrong-username" var = "w_username"/>
<fmt:message key="login.wrong-password" var = "wpassword"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="resources/css/login-style.css" media="all" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type = "text/javascript" src = "resources/js/login.js"></script>
</head>
<body>
<a href = "/login?lang=ua">ua</a> | <a href = "/login?lang=en">en</a>
<div class="container">
      <div  class="form">
    		<form id="contactform" method="post">
       		   <legend>${title}</legend>
    	      <p class="contact"><label for="username">${username}</label></p>
    			<input id="username" name="username" placeholder="${username}" required="true" tabindex="1"
    			type="text" value="${fusername}">

			    <p class="contact"><label for="password">${password}</label></p>
    			<input type="password" id="password" name="password" required="true" placeholder = "${password}"
    				value = "${fpassword }">

            <input class="button" name="submit" id="submit" tabindex="5" value="${logbutton}" type="submit">
                <br/>
                <a href = "/register">${registration}</a>
                <br/>
                <br/>
   </form>
</div>
</div>


 <p id = "iusername" >${w_username}</p>
 <p id = "ipassword" >${wpassword}</p>

</body>
<c:if test="${fail == 'wrong-username'}">
<p>fuck</p>]
		<script type="text/javascript">
			console.log("hereasd");
			invalid($("#username"), $("#iusername").text());
		</script>
	</c:if>

<c:if test="${fail == 'wrong-password'}">
		<script type="text/javascript">
			invalid($("#password"), $("#ipassword").text());
		</script>
	</c:if>
</html>