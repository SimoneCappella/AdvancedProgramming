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
	<td>Name</td>
	<td>Discount</td>
	<td>Price</td>
	<td></td>
</thead>
<c:forEach items="${subs}" var="s">
<tr>
	<td>[<a href="<c:url value="/subs/${s.sub_id}/edit/" />">+</a>]</td>
	<td>${s.sub_id}</td>
	<td>${s.name}</td>
	<td>${s.discount}%</td>
	<td>${s.price}€</td>
	<td>[<a href="<c:url value="/subs/${s.sub_id}/delete/"/>">X</a>]</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/subs/add" />">Aggiungi sub</a>
