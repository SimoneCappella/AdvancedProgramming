<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<hr/>
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	
	<a href="<c:url value="/" />">Home</a> 

	
	<c:if test="${isAuth}"> - <a href="<c:url value="/logout" />">Logout</a></c:if> 
	
	<c:if test="${! isAuth}">- <a href="<c:url value="/login" />">Login</a></c:if>
	
	<c:if test="${isAdmin}"> - Sei un Admin</c:if>