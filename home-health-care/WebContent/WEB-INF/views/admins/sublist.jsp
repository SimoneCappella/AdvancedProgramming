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
<div style="margin-left:2%;">
<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>
<div style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Elenco Degli Abbonamenti</h3></div>
<a style="display:block" href="<c:url value="/admins/subadd" />"><i class="fa fa-plus" style="color:#007bff" ></i>Aggiungi Nuovo Abbonamento</a>
<table class="styled-table" >
<thead>
	<td>id</td>
	<td>Name</td>
	<td>Discount</td>
	<td>Price</td>
	<td></td>
	<td></td>
</thead>
<c:forEach items="${subs}" var="s">
<tr class="active-row">
	<td>${s.sub_id}</td>
	<td>${s.name}</td>
	<td>${s.discount}%</td>
	<td>${s.price}€</td>
	<td><a href="<c:url value="/admins/${s.sub_id}/subedit/" />"><i class="fa fa-pencil"></i>Modifica</a></td>
	<td><a href="<c:url value="/admins/${s.sub_id}/subdelete/"/>"><i class="fa fa-trash"></i>Rimuovi</a></td>
</tr>
</c:forEach>
</table>
<hr/>
</div>







