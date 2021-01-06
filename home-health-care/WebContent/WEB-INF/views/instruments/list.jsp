<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
Numero di strumenti: ${numInstruments}. 

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco</h1>
<table>
<thead><td></td><td>Nome</td><td>id</td><td>Famiglia</td><td></td><td>Cantanti</td></thead>
<c:forEach items="${instruments}" var="i">
<tr><td>[<a href="<c:url value="/instruments/${i.instrumentId}/edit/" />">+</a>]</td><td>${i.name}</td><td>${i.instrumentId}</td><td>${i.family}</td><td>[<a href="<c:url value="/instruments/${i.instrumentId}/delete/"/>">X</a>]</td>
<td>
<ul>
<c:forEach items="${i.singers}" var="s">
<li>${s.fullName} [<a href="<c:url value="/singers/${s.id}/instrument/${i.instrumentId}/unlink/" />?next=/instruments/list/">X</a>]</li>
</c:forEach>
</ul>
</td></tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/instruments/add" />">Aggiungi strumento</a>
