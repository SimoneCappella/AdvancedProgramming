<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}


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



/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
</style>
<div style="margin-left:2%">
<div style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Il Mio Abbonamento</h3></div>
<c:choose>
  <c:when test="${sub eq null}">
<p style="margin: 2% 0 0 0">${errorMessage}</p>
<div><a href="<c:url value="/users/link_sub" />"><i class="fa fa-hand-o-right" ></i>Clicca qui per acquistare un nuovo abbonamento. </a></div>
  </c:when>

  <c:otherwise>
<div><a onclick="return confirm('Sei sicuro? Nel caso acquistassi un nuovo abbonamento non sarai rimborsato.')" href="<c:url value="/users/link_sub" />"><i class="fa fa-plus" ></i>Cambia Abbonamento: </a></div>
<table class="styled-table" >
<thead>
	<td>Name</td>
	<td>Discount</td>
	<td>Price</td>
	<td>Scadenza</td>
	<td></td>
</thead>
<tr class="active-row">
	<td>${sub.name}</td>
	<td>${sub.discount}%</td>
	<td>${sub.price}€</td>
	<td>${user.subexp}</td>
	<td><a onclick="return confirm('Sei sicuro? Non verrai rimborsato')" href="<c:url value="/users/${sub.sub_id}/unlinksub/"/>"><i class="fa fa-trash"></i>Rimuovi</a></td>
	
</tr>
</table>
  </c:otherwise>
</c:choose>
</div>

