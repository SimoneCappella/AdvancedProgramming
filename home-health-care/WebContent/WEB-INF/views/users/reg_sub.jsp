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
  width: 100%;
  opacity: 0.9;
  margin-top:4%;
}
.btn:hover {
  opacity: 1;
}

</style>    
<div style="margin-left:2%">
<div style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Acquisto Nuovo Abbonamento</h3></div>
<table style="border:none">

<thead>
	<td>Tipo Abbonamento</td>
	<td>Sconto</td>
	<td>Prezzo</td>
</thead>
	<form action="<c:url value="/users/addsub" />" method='POST'>
			
	<c:forEach items="${subs}" var="s">
		<tr>	
			<td>${s.name}</td>
			<td>${s.discount}%</td>
			<td>${s.price}€</td>
			<td><input type="radio" checked id="${s.sub_id}" name="sub" value="${s.sub_id}"></td>
			
		</tr>
	</c:forEach>
		<tr>
			<td>
			<div style="margin-top:2%" >
			Metodo di Pagamento: <select  name="paymethod">
					<option value="paypal">PayPal</option>
 					<option value="prepagata">Carta Prepagata</option>
 					<option value="mastercard">Mastercard</option>
 			</select>
 			</div>
 			</td>
 		</tr>	
 		<tr">
 			
			<td colspan="2" ><input type="submit" class="btn" value="Conferma Acquisto"/></td>
			</form>
		</tr>
</table>
</div>

	
			


