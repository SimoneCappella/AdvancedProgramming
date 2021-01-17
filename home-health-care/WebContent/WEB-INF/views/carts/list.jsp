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
	<td>Total</td>
	<td>Numero oggetti</td>
	<td></td>
</thead>
<c:forEach items="${carts}" var="s">
<tr>
	<td>[<a href="<c:url value="/carts/${s.cart_id}/edit/" />">+</a>]</td>
	<td>${s.cart_id}</td>
	<td>${s.total}</td>
	<td>${s.item_num}€</td>
	<td>[<a href="<c:url value="/carts/${s.cart_id}/delete/"/>">X</a>]</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/carts/add" />">Aggiungi cart</a>