<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    

<h1>Acquista Nuovo Abbonamento</h1>
<table>
<thead>
	<td>Tipo Abbonamento</td>
	<td>Sconto</td>
	<td>Prezzo</td>
</thead>
<c:forEach items="${subs}" var="s">
<tr>
	<td>${s.name}</td>
	<td>${s.discount}%</td>
	<td>${s.price}€</td>
	<td><a href="<c:url value="/users/${s.sub_id}/addsub/" />">Aggiungi abbonamento</a></td>	
	
</tr>
</c:forEach>
</table>


