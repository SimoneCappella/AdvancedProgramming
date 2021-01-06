<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
Numero di album: ${fn:length(albums)}. 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco</h1>
<table>
<thead><td></td><td>Titolo</td><td></td><td>Cantante</td></thead>
<c:forEach items="${albums}" var="a">
<tr>
	<td>[<a href="<c:url value="/albums/${a.id}/edit/" />">+</a>]</td>
	<td>${a.title}</td>
	<td>[<a href="<c:url value="/albums/${a.id}/delete/"/>">X</a>]</td>
	<td>${a.singer.fullName}</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/albums/add" />">Aggiungi album</a>
