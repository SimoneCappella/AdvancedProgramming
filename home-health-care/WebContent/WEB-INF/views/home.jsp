<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<p>Adesso sono le: ${serverTime}.</p>
<c:if test="${not empty nomeSub}">
	<td><p> Abbonamento attivo: ${nomeSub} </p></td> 
	<td><p> Il tuo abbonamento scade il: ${scadenza} </p></td>
	</c:if>
<c:if test="${not empty messaggio}">
	<td><p> ${messaggio} </p></td> 
	</c:if>
<ul>

	<li>Oggetti nel carrello: ${item_number} Totale: ${total}<a href="<c:url value="/users/cartlist/" />">Vai a Carrello</a> 
	<li>Subs: <a href="<c:url value="/admins/sublist/" />">sub</a>
	<li>Users: <a href="<c:url value="/admins/userlist/" />">user</a> 
	<li>Aggiungi una sub a un utente: <a href="<c:url value="/users/link_sub" />">Add</a>
	<li>visualizza abbonamento utente: <a href="<c:url value="/users/view_sub" />">view</a>
	<li>Il mio profilo: <a href="<c:url value="/users/myprofile" />">Vai</a>
	<li>I miei ordini: <a href="<c:url value="/users/myorders" />">Vai</a>  
	
</ul>
