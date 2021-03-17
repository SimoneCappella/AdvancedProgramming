<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.btn {
  background-color: #007bff;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 15%;
  opacity: 0.9;
  margin-top: 2%;
}
.btn:hover {
  opacity: 1;
}
</style>

<div style="margin-left:2%; display:block">
<div style="display:inline-block; margin-bottom:1%"><h3 style="border-bottom:2px solid #007bff">Conferma Acquisto</h3></div>
<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
${errorMessage}
</div>
</c:if>
<c:url value="/users/savepurchase" var="action_url" />
<form method='POST' action="${action_url}">
Metodo di pagamento: <select name="paymeth" id="paymeth">
	<option value="Paypal">PayPal</option>
	<option value="Visa">Carta Prepagata</option>
	<option value="MasterCard">MasterCard</option>	
</select>
<br><br>
Indirizzo di spedizione:<select name="addr" id="addr" value">
<option value="0"> ---Scegli un indirizzo---</option>
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
<input type="hidden" name="tot" value="${total}">
<input type="hidden" name="discount" value="${discount}">

<input class = "btn" style="display:block" type="submit" value="Acquista!"/>
</form>
</div>