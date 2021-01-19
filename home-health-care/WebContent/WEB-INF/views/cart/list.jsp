<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco carrelli</h1>
<table>
<thead>
	<td></td>
	<td>Id oggetto</td>
	<td>Quantità</td>
	<td>Carrello associato</td>
	<td></td>
</thead>
<c:forEach items="${items}" var="i">
<tr>
	<td>[<a href="<c:url value="/cart/${i.cart_item_id}/edit/" />">+</a>]</td>
	<td>${i.item_code}</td>
	<td>${i.quantity}</td>
	<td>${i.cart.cart_id}</td>
	<td>[<a href="<c:url value="/cart/${i.cart_item_id}/delete/"/>">Rimuovia</a>]</td>
</tr>
</c:forEach>
</table>
