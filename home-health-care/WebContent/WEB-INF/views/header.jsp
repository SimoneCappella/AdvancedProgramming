<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
<sec:authorize access="isAuthenticated()" var="isAuth" />

<h1>${appName}</h1>

<c:if test="${isAuth}">Benvenuto/a  <sec:authentication property="principal.username" /></c:if>

<hr/>
