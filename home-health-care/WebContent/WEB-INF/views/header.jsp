<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<hr/>
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	<h3>
	<a href="<c:url value="/" />">Home</a> 

	
	<c:if test="${isAuth}"> - <a href="<c:url value="/logout" />">Logout</a></c:if> 
	
	<c:if test="${! isAuth}">- <a href="<c:url value="/login" />">Login</a></c:if>
	
	<c:if test="${isAdmin}"> - Sei un Admin</c:if>
	
	-<a href="<c:url value="/itemlist" />"> Catalogo Articoli</a>
	
	<c:if test="${isAuth}"> - <a href="<c:url value="/users/addresslist/" />">Gestisci Indirizzi</a></c:if>
 
 	- ${item_number}<span class="material-icons "  >
	shopping_cart
</span>
</h3>