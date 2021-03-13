<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div style="margin-left:2%">
<div style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">I miei ordini</h3></div>
<c:if test="${not empty message}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${message}</div>
</c:if>
<c:forEach items="${orders}" var="e">
<div style="border-bottom:2px solid #007bff">
<b>Data ordine:</b> ${e.date}
<br>
<b>Indirizzo di spedizione:</b> <c:if test="${empty e.address.street}">
	l'indirizzo non è più disponibile
</c:if>
 <c:set var="cont" value="${0}" scope="page" />
<c:if test="${not empty e.address.street}">
	${e.address.street} ${e.address.civ_num}, ${e.address.city}, CAP: ${e.address.cap}
</c:if>
<br>
<b>Metodo di pagamento:</b> ${e.paymeth}
<br>
<b>Totale:</b> ${e.total}
<br>

<c:forEach items="${e.items}" var="i">

<c:if test="${not empty i.key}">
		<b>Nome:</b> ${i.key.title} | <b>Descrizione:</b> ${i.key.description} | <b>Prezzo:</b> ${i.key.price}€ | Quantità: ${i.value}
		<c:set var="cont" value="${cont=cont+((i.key.price)*i.value)}"/>
</c:if>
<br>
</c:forEach> 


<c:if test="${(count)<=e.total}">

		Articolo non più disponibile
		<div style="visibility: hidden">		${count=0}</div>
</c:if>

</div>
</c:forEach>
</div>
