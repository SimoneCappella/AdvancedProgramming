<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<hr/>
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>
	<h3>Elenco</h3>
	<table>
	<thead>
	<tr>
	<td>id</td>
	<td>Titolo   </td>	
	<td>Descrizione   </td>
	<td>Price   </td>	
	<td>Image   </td>	
	<tr/>
	</thead>

<c:forEach items="${items}" var="i">
<tr>
	<td>${i.item_id}</td>
	<td>${i.title}</td>
	<td>${i.description}</td>
	<td>${i.price} €</td>
	<td>${i.image}</td>
	<c:if test="${isUser}"> - <td><a href="<c:url value="/users/${i.item_id}/viewitemdetail" />">Dettagli Articolo</a></td></c:if>
<c:if test="${isAdmin}"> - <td><a href="<c:url value="/admins/${i.item_id}/deleteitem" />">Elimina Oggetto</a></td></c:if>
<c:if test="${isAdmin}"> - <td><a href="<c:url value="/admins/${i.item_id}/itemedit" />">  - Modifica Oggetto</a></td></c:if>
</tr>
</c:forEach>
</table>
<hr/>

<c:if test="${isAdmin}"> - <a href="<c:url value="/admins/itemadd" />">Aggiungi Oggetto</a></c:if>