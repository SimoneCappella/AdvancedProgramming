<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>

<h1>Il mio carrello</h1>
<table>
<thead>

<tr>
	<td>Nome Articolo</td>
	<td>Quantità</td>
	<td>Prezzo unità</td>
	<td></td>
</tr>
</thead>
<c:forEach items="${items}" var="i">
<tr>
	<td>${i.item.title}</td>
	<td>
	<c:url value="/users/addtocart" var="action_url" />
		<form name='changeq' action="<c:url value="/users/editcart" />" method='POST'>
			<table>
			
				<select id="${i.quantity}" name="quantity" onchange="this.form.submit()">
					<option value="${i.quantity}">${i.quantity}</option>
 					<option value=1>1</option>
	 				<option value=2>2</option>
	 				<option value=3>3</option>
	 				<option value=4>4</option>
	 				<option value=5>5</option>
	 				<option value=6>6</option>
	 				<option value=7>7</option>
	 				<option value=8>8</option>
	 				<option value=9>9</option>
	 				<option value=10>10</option>	
				</select>
			</td>	
			
			
			<td><input type="hidden" id="cart_item_id" name="cart_item_id" value= "${i.cart_item_id}"></td>
			<td><input type="hidden" id="quantity" name="quantity" value= "${i.quantity}"></td>
			</table>
	</form>
	<td>${i.item.price}</td>
			</td>
	
	<td>[<a href="<c:url value="/users/${i.cart_item_id}/cartdelete/"/>">Rimuovi</a>]</td>
	

</c:forEach>
</table>
<tr>
	<td><h2>Il totale è di ${total}€</h2></td>	
	<td>Articoli Totali: ${item_number}</td>
</tr>
<br>
<button onclick="window.location.href='/home-health-care/users/purchase';">
     Procedi all'acquisto
</button>

