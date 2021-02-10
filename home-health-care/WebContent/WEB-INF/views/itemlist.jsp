<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<h1>Elenco</h1>
<table>
<thead>
	<td>Titolo   </td>
	<td>Price   </td>
	<td>Image   </td>
	<td></td>
</thead>
<c:forEach items="${items}" var="i">
<tr>
	<td>${i.title}</td>
	<td>${i.price}</td>
	<td>${i.image}</td>
	<td>[<a href="<c:url value="/users/${i.item_id}/viewitemdetail/" />">Visualizza Articolo</a>]</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/save" />">Aggiungi Oggetto</a>
