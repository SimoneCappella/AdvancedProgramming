<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<h1>Elenco</h1>
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>
<table>
<thead>
	<td></td>
	<td>ID</td>
	<td>Nome e Cognome</td>
	<td>Scadenza Abbonamento</td>
	<td>Admin</td>
	<td>Attivo</td>
</thead>
<c:forEach items="${users}" var="u">
<tr>
	<td>[<a href="<c:url value="/admins/${u.user_id}/useredit/" />">+</a>]</td>
	<td>${u.user_id}</td>
	<td>${u.name} ${u.surname}</td>
	<td>${u.subexp}</td>
	<td>${u.role}</td>
	<td>${u.active}</td>
	<td><a onclick="return confirm('Sei sicuro?')" href="<c:url value="/admins/${u.user_id}/userdelete"/>">Rimuovi</a></td>
	<td style="padding:20px"><a href="<c:url value="/admins/${u.user_id}/userdisable"/>">Abilita/Disabilita</a></td>
	
</tr>
</c:forEach>
</table>
<hr/>
