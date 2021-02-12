<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Conferma acquisto</h1>
<c:url value="/users/savepurchase" var="action_url" />
<form method='POST' action="${action_url}">
Metodo di pagamento: <select name="paymeth" id="paymeth">
	<option value="paypal">PayPal</option>
	<option value="visa">Carta Prepagata</option>
	<option value="master">MasterCard</option>	
</select>
<br><br>
Indirizzo di spedizione:<select name="addr" id="addr">
<c:forEach items="${addresses}" var="a">
			<option value="${a.address_id}">${a.street} ${a.civ_num}</option> 
</c:forEach>
</select>
<br><br>
Totale: ${total}€
<br>
Sconto applicato: ${discount}%
<br>
Totale scontato: ${newtotal}€
<input type="hidden" name="total" value="${newtotal}">

<input type="submit" value="Acqusita!"/>
</form>