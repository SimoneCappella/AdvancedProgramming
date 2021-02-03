<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<hr/>
	<sec:authorize access="hasRole('ROLE_admin')" var="isAdmin" />
	<sec:authorize access="hasRole('ROLE_user')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	
	<a href="<c:url value="/" />">Home</a> 
	- <a href="<c:url value="/singers/list" />">Cantanti</a> 
	- <a href="/albums/list">Album</a> 
	
	<c:if test="${isAdmin}">- <a href="<c:url value="/instruments/list" />">Strumenti</a></c:if> 
	
	<c:if test="${isAuth}"> - <a href="<c:url value="/logout" />">Logout</a></c:if> 
	
	<c:if test="${! isAuth}">- <a href="<c:url value="/login" />">Login</a></c:if>