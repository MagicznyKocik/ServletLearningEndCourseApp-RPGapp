<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%--<!DOCTYPE html>--%>
<html>
<head>
    <title>Rpg Site</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>


<jsp:include page="fragments/navbar.jspf"/>

<c:if test="${not empty requestScope.ideas}">
    <c:forEach var="idea" items="${requestScope.ideas}">
        <div class="container">
        <div class="row bs-callout bs-callout-primary">
            <div class="col col-md-1 col-sm-2">
                <a href="${pageContext.request.contextPath}/vote?idea_id=${idea.id}&vote=VOTE_UP" class="btn btn-block btn-primary btn-success"><span class="glyphicon glyphicon-arrow-up"></span>  </a>
                <div class="well well-sm centered"><c:out value="${idea.upVote-idea.downVote}"/></div>
                <a href="${pageContext.request.contextPath}/vote?idea_id=${idea.id}&vote=VOTE_DOWN" class="btn btn-block btn-primary btn-warning"><span class="glyphicon glyphicon-arrow-down"></span>  </a>
            </div>
            <div class="col col-md-11 col-sm-10">
                <h3 class="centered"><a href="<c:out value="${idea.url}" />"><c:out value="${idea.name}" /></a></h3>
                <h6><small>Dodane przez: <c:out value="${idea.user.username}"/>,
                    Dnia: <fmt:formatDate value="${idea.timestamp}" pattern="dd/MM/YYY"/></small></h6>
                <p><c:out value="${idea.description}"/></p>
                <a href="<c:out value="${idea.url}" />" class="btn btn-default btn-xs">Przejdź do strony</a>
            </div>
        </div>
        </div>
    </c:forEach>
</c:if>



<jsp:include page="fragments/footer.jspf"/>


</body>
</html>
