<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!-- questo non e` un commento di vista: ${mySecret} -->
<%--questo e` un commento di vista (JSTL) ${mySecret} --%>
<%-- NB questo codice non funziona:

Numero di cantanti: ${singers.size()}. <c:if test="${singers.size() ge 0}">Sono pochi!</c:if>

Invocare un metodo e` una di quelle operazioni piu` adatte ad un controller che non ad una vista. Opzioni disponibili:

- chiamare il metodo nel controller, salvare il risultato in un attributo del modello passato alla vista
- creare un tag personalizzato che "wrappi" l'invocazione del metodo desiderato, e richiamare il tag nella vista
- aggirare il limite con <jsp:useBean ...

 --%>
<%--Numero di cantanti censiti: ${fn:length(singers)}. <c:if test="${fn:length(singers) lt 10}">Si può fare di più!</c:if>  --%>
Numero di cantanti: ${numSingers}. <c:choose><c:when test="${numSingers lt 0}">Problemi di connessione al DB?</c:when><c:when test="${numSinger lt 10}">Sono pochi!</c:when></c:choose>
<h1>Elenco</h1>
<table>
<thead><td></td><td>First name</td><td>Last name</td><td>Birth date</td><td></td><td>Instruments</td></thead>
<c:forEach items="${singers}" var="s">
<tr><td>[<a href="<c:url value="/singers/${s.id}/edit/" />">+</a>]</td><td>${s.firstName}</td><td>${s.lastName}</td><td>${s.birthDate}</td><td>[<a href="<c:url value="/singers/${s.id}/delete/"/>">X</a>]</td>
	<td>
	Strumenti del cantante: ${fn:length(s.instruments)}
	<ul>
	<c:forEach items="${s.instruments}" var="i">
	<li>${i.instrumentId} - ${i.name} [<a href="<c:url value="/singers/${s.id}/instrument/${i.instrumentId}/unlink/"/>">X</a>]</li>
	</c:forEach>
	</ul>
	</td></tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/singers/add" />">Aggiungi cantante</a>
