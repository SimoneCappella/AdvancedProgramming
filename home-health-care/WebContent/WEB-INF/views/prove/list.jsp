<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
Numero di prove: ${numProve}. 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco</h1>
<table>
<thead>
	<td></td>
	<td>id</td>
	<td>Titolo</td>
	<td></td>
	<td>Descrizione</td>
</thead>
<c:forEach items="${prove}" var="p">
<tr>
	<td>[<a href="<c:url value="/prove/${p.provaId}/edit/" />">+</a>]</td>
	<td>${p.provaId}</td>
	<td>[<a href="<c:url value="/prove/${p.provaId}/delete/"/>">X</a>]</td>
	<td>${p.title}</td>
	<td>${p.description}</td>
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/prove/add" />">Aggiungi prova</a>
