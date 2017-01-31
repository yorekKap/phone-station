<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pgn" uri = "mytags.com/pagination" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>


<fmt:message key = "admin.title" var =  "title"/>
<fmt:message key = "admin.news-title" var = "news_title"></fmt:message>
<fmt:message key = "admin.news-content" var = "news_content"></fmt:message>
<fmt:message key = "admin.news-photo" var = "news_photo"></fmt:message>
<fmt:message key = "admin.post-news" var = "post_news"/>
<fmt:message key = "admin.post" var = "post"/>
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
					<div class="col-md-7">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">${post_news}</h3>
							</div>
							<div class="panel-body">
								<form class="form-horizontal" method="post"
									enctype="multipart/form-data">
									<div class="form-group">
										<label class="control-label col-md-3" for="title">${news_title}</label>
										<div class="col-md-9">
											<input type="text" class="form-control id="
												title" name="title" required />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3" for="content">${news_content}</label>
										<div class="col-md-9">
											<textarea class="form-control" rows="5" id="content"
												name="content" required></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3" for="photo">${news_photo}</label>
										<div class="col-md-9">
											<input type="file" id="photo" name="photo"
												accept=".png,.jpg,.jpeg" />
										</div>
									</div>
									<div class="col-md-offset-3 col-md-9">
										<input type="submit" value="${post}">
									</div>
								</form>
							</div>
						</div>
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
							<img src="/resources/img/news/${news.id}.jpg"
								style="width: 60%; border: 1px groove black">
						</div>
					</div>
				</div>

			</c:forEach>
			<pgn:setPageIndexesList page = "${newsPage}" numOfPageIndexes="3" list="indexes"/>
			<ul class="pagination">
				<li><a href="/admin?page-index=1"><<</a></li>
				<c:forEach items="${indexes}" var="index">
					<c:choose>
						<c:when test="${index eq newsPage.pageIndex}">
							<li class="active"><a href="/admin?page-index=${index}">${index}</a>
							<li>
						</c:when>
						<c:otherwise>
							<li><a href="/admin?page-index=${index}">${index}</a>
							<li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="/admin?page-index=${newsPage.numOfPages}">>></a></li>
			</ul>
		</div>
	</div>



		</body>
</html>