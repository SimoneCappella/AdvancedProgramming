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

.btn {
  background-color: transparent;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.8;
  font-weight: bold;
}
.btn:hover {
  opacity: 1;
}
.styled-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    margin-left: 2%;
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

h3, table {
    margin-left: 2%;
}

</style>


 <c:if test="${not empty errorMessage1}">
	<div style="color: red; font-weight: bold; margin: 2% ">${errorMessage1}</div>
</c:if>

<c:choose> 


	<c:when test="${address eq null}">
		<div style="color: red; font-weight: bold; margin: 2% ;">${errorMessage}</div>
		<br><a href="<c:url value="/users/addressadd" />"><i class="fa fa-plus" style="color:#007bff; margin-left:2%" ></i>Aggiungi Nuovo Indirizzo</a>
		</c:when>
<c:otherwise>



<h3>Elenco</h3>

<a href="<c:url value="/users/addressadd" />"><i class="fa fa-plus" style="color:#007bff; margin-left:2%" ></i>Aggiungi Nuovo Indirizzo</a>
<table  class="styled-table">
<thead>
	<td></td>
	<td>Cap</td>
	<td>Città</td>
	<td>Via</td>
	<td>Numero Civico</td>
	<td></td>
	<td></td>
</thead>
<c:forEach items="${address}" var="a">
<tr class="active-row">

<form name='button' action ="<c:url value="/users/editaddress"/>" method='POST'>
	<td><button type="submit" class="btn" style="color:#007bff"/><i class="fa fa-pencil" style="color:#007bff"></i> Modifica</button></td>
	<input type="hidden" id="address_id" name="address_id" value= "${a.address_id}">
	</form>
	
	<td>${a.cap}</td>
	<td>${a.city}</td>
	<td>${a.street}</td>
	<td>${a.civ_num}</td>
	<form name='delete' action ="<c:url value="/users/deleteaddress"/>" method='POST'>
	<td><button type="submit" class="btn" style="color:#007bff"/><i class="fa fa-trash" style="color:#007bff"></i> Rimuovi</button></td>
	<td><input type="hidden" id="address_id" name="address_id" value= "${a.address_id}"></td>
	</form>
</tr>
</c:forEach>
</table>
<hr/>

</c:otherwise>
</c:choose>