<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="register.title" var = "title"/>
<fmt:message key="register.first-name" var = "firstname"/>
<fmt:message key="register.last-name" var = "lastname"/>
<fmt:message key="register.phone" var = "phone"/>
<fmt:message key="register.username" var = "username"/>
<fmt:message key="register.password" var = "password"/>
<fmt:message key="register.repeat-password" var = "repeatpass"/>
<fmt:message key="register.sign-up" var = "signup"/>
<fmt:message key="register.to-login" var = "tologin"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/login-style.css" media="all" />
    <script type="text/javascript" src="resources/js/register.js"></script>
</head>
<body>
<a href = "/register?lang=ua">ua</a> | <a href = "/register?lang=en">en</a>
<div class="container">
      <div  class="form">
    		<form id="contactform" method="post">
    		          <legend>${title}</legend>
    			<p class="contact"><label for="first_name">${firstname}</label></p>
    			<input id="first_name" name="first_name" placeholder="${firstname}" required="true"
    												tabindex="1" type="text" value="${user.firstName}">

                <p class="contact"><label for="last_name">${lastname}</label></p>
    			<input id="last_name" name="last_name" placeholder="${lastname }" required="true"
    												tabindex="2" type="text" value = "${user.lastName}">

                <p class="contact"><label for="phone">${phone}</label></p>
            	<input id="phone" name="phone" placeholder="380XXXXXXXXX" required="true"
            							tabindex="3" type="tel" pattern="380[0-9]{9}" value = "${user.phone }">

                <p class="contact"><label for="username">${username }</label></p>
    			<input id="username" name="username" placeholder="${username }" required=""
    							 tabindex="4" type="text" value = "${user.username}">

                <p class="contact"><label for="password">${password}</label></p>
    			<input type="password" class = "pass" id="password" name="password" required=""
    						value = "${user.password}">

                <p class="contact"><label for="repassword">${repeatpass}</label></p>
    			<input type="password" class = "" id="repassword" name="repassword" required=""
    						value = "${user.password}">

            <input class="button" name="submit" id="submit" tabindex="5" value="${signup}" type="submit">
                <br/>
                <a href = "/login">${tologin}</a>
                <br/>
                <br/>
   </form>
</div>
</div>
<c:if test="${failed}">
		<script type="text/javascript">
			setInvalid();
		</script>
	</c:if>
</body>
</html>
