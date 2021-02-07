<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco</h1>
<table>
<thead>
	<td></td>
	<td>id</td>
	<td>Cap</td>
	<td>City</td>
	<td>Street</td>
	<td>Civ_num</td>
	<td></td>
</thead>
<c:forEach items="${addresses}" var="a">
<tr>
	<td>[<a href="<c:url value="/users/${a.address_id}/edit/" />">Modifica</a>]</td>
	<td>${a.address_id}</td>
	<td>${a.cap}</td>
	<td>${s.city}%</td>
	<td>${s.street}€</td>
	<td>${a.civ_num}</td>
	<td>[<a href="<c:url value="/users/${a.address_id}/delete/"/>">X</a>]</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/users/addressadd" />">Aggiungi sub</a>