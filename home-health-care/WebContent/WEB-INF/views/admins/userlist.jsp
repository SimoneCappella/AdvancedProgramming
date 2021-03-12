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

h3 {
    margin-left: 2%;
}

</style>
<script>
function clicked(e)
{
	if(!confirm('Sei sicuro?')){
		e.preventDefault();
	}
}
</script>

<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h3>Elenco degli utenti</h3>
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>
<table class="styled-table">
<thead>
	<td>ID</td>
	<td>Nome e Cognome</td>
	<td>Scadenza Abbonamento</td>
	<td>Admin</td>
	<td>Attivo</td>
	<td></td>
	<td></td>
	<td></td>
</thead>
<c:forEach items="${users}" var="u">
<tr class="active-row">
	<td>${u.user_id}</td>
	<td>${u.name} ${u.surname}</td>
	<td>${u.subexp}</td>
	<td><c:if test="${u.role}"><i class="fa fa-check" style="color:#169925" aria-hidden="true"></c:if><c:if test="${not u.role}"><i class="fa fa-times" style="color:#ac0707" aria-hidden="true"></i></c:if></td>
	<td><c:if test="${u.active}"><i class="fa fa-check" style="color:#169925" aria-hidden="true"></i></c:if><c:if test="${not u.active}"><i class="fa fa-times" style="color:#ac0707" aria-hidden="true"></i></c:if></td>
	<td><a href="<c:url value="/admins/${u.user_id}/useredit/" />"><i class="fa fa-pencil"></i>Modifica</a></td>
	<td><a onclick="return confirm('Sei sicuro?')" href="<c:url value="/admins/${u.user_id}/userdelete"/>"><i class="fa fa-trash"></i>Rimuovi</a></td>
	<td style="padding:20px"><a href="<c:url value="/admins/${u.user_id}/userdisable"/>"><c:if test="${u.active}"><i class="fa fa-toggle-on"></i></c:if><c:if test="${not u.active}"><i class="fa fa-toggle-off"></i></c:if>Abilita/Disabilita</a></td>
	
</tr>
</c:forEach>
</table>
<hr/>
