<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<h1>Elenco</h1>
<table>
<thead>
	<td></td>
	<td>id   </td>
	<td>Titolo   </td>
	<td>Descrizione   </td>
	<td>Price   </td>
	<td>Image   </td>
	
</thead>
<c:forEach items="${items}" var="i">
<tr>
	<td>${i.item_id}</td>
	<td>${i.title}</td>
	<td>${i.description}</td>
	<td>${i.price}</td>
	<td>${i.image}</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/save" />">Aggiungi Oggetto</a>
