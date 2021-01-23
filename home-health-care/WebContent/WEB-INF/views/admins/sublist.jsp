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
	<td>[<a href="<c:url value="/admins/${s.sub_id}/subedit/" />">+</a>]</td>
	<td>${s.sub_id}</td>
	<td>${s.name}</td>
	<td>${s.discount}%</td>
	<td>${s.price}€</td>
	<td>[<a href="<c:url value="/admins/${s.sub_id}/subdelete/"/>">X</a>]</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/admins/subadd" />">Aggiungi sub</a>
