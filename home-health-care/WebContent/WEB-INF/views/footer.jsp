<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/css/my.css" />" rel="stylesheet">

	<hr/>
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	
	<a href="<c:url value="/" />"><i class="fa fa-fw fa-home" ></i>Home</a> 

	
	<a href="<c:url value="/chisiamo" />"><i class="fa fa-globe" ></i>Chi Siamo</a>
	
	<a href="<c:url value="/obiettivoprogetto" />"><i class="fa fa-crosshairs" ></i>Obiettivo del Progetto</a>	
	
	<a href="<c:url value="/lavoraconnoi" />"><i class="fa fa-briefcase" ></i>Lavora con noi</a>
	
