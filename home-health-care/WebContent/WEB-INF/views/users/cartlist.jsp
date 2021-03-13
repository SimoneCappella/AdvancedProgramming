<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.styled-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
    background-color: #007bff;
    color: #ffffff;
    text-align: left;
}

.btn {
  background-color: #007bff;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 15%;
  opacity: 0.9;
}
.btn:hover {
  opacity: 1;
}

.styled-table th,
.styled-table td {
    padding: 12px 15px;
}

.styled-table tbody tr {
    border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
    border-bottom: 2px solid #007bff
}

.styled-table tbody tr.active-row {
    font-weight: bold;
    color: #000000;
}
</style>

<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>

<div style="margin-left:2%">
<h3>Il mio carrello</h3>
<table class="styled-table">
<thead>
<c:if test="${not empty items}">
<tr>
	<td>Nome Articolo</td>
	<td>Quantità</td>
	<td>Prezzo unità</td>
	<td></td>
</tr>
</c:if>
<c:if test="${empty items}"><h6>Non hai nessun'oggetto nel carrello</h6></c:if>
<c:if test="${not empty items}">
</thead>
<c:forEach items="${items}" var="i">
<tr class="active-row">
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
			
			
			<input type="hidden" id="cart_item_id" name="cart_item_id" value= "${i.cart_item_id}">
			<input type="hidden" id="quantity" name="quantity" value= "${i.quantity}">
			</table>
	</form>
	<td>${i.item.price}</td>
			</td>
	
	<td><a href="<c:url value="/users/${i.cart_item_id}/cartdelete/"/>"><i class="fa fa-trash" style="color:#007bff"></i>Rimuovi</a></td>
</tr>
</c:forEach>
<tfoot>
<tr>
	<td>Articoli Totali: ${item_number}</td>
	<td></td>
	<td></td>	
	<td>Il totale è di ${total}€</td>	
</tr>
</tfoot>
</table>
<br>
<button class="btn"  onclick="window.location.href='/home-health-care/users/purchase';">
     Procedi all'acquisto
</button>
</div>
</c:if>
