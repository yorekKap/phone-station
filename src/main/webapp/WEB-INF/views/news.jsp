<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pgn" uri = "mytags.com/pagination" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>


<fmt:message key = "news.title" var =  "title"/>

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
			<c:forEach var="news" items="${newsPage.records}">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>${news.title}</h3>
						<h6> <fmt:formatDate pattern="yyyy-MM-dd" value="${news.date}" /></h6>
					</div>
					<div class="panel-body">
						${news.content}
						<div style="width: 460px; margin-top: 20px;">
							<img src="/resources/img/news/${news.id}.jpg" alt=""
								style="width: 60%;">
						</div>
					</div>
				</div>

			</c:forEach>
			<pgn:setPageIndexesList page = "${newsPage}" numOfPageIndexes="3" list="indexes"/>
			<ul class="pagination">
				<li><a href="/news?page-index=1"><<</a></li>
				<c:forEach items="${indexes}" var="index">
					<c:choose>
						<c:when test="${index eq newsPage.pageIndex}">
							<li class="active"><a href="/news?page-index=${index}">${index}</a>
							<li>
						</c:when>
						<c:otherwise>
							<li><a href="/news?page-index=${index}">${index}</a>
							<li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="/news?page-index=${newsPage.numOfPages}">>></a></li>
			</ul>
		</div>
	</div>



		</body>
</html>