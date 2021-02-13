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
				
		<form action="<c:url value="/users/addsub" />" method='POST'>
			
	<c:forEach items="${subs}" var="s">
		<tr>	
			<td>${s.name}</td>
			<td>${s.discount}%</td>
			<td>${s.price}€</td>
			<td><input type="radio" checked id="${s.sub_id}" name="sub" value="${s.sub_id}"></td>
			
		</tr>
	</c:forEach>
		
			<td>
			Metodo di Pagamento: <select  name="paymethod">
					<option value="paypal">PayPal</option>
 					<option value="prepagata">Carta Prepagata</option>
 					<option value="mastercard">Mastercard</option>
 			</select>
 			</td>
 			
 			<tfoot>
			<td><input type="submit" value="Conferma Acquisto"/></td>
			</form>
			</tfoot>
</table>

	
			


