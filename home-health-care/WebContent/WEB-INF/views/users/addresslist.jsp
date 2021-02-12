<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:if test="${fn:length(message) > 0}">
<p>${message}</p>
</c:if>

<h1>Elenco</h1>
<table>
<thead>
	<td></td>
	<td>id</td>
	<td>Cap</td>
	<td>City</td>
	<td>Street</td>
	<td>Civ_num</td>
	<td></td>
</thead>
<c:forEach items="${address}" var="a">
<tr>

<form name='button' action ="<c:url value="/users/editaddress"/>" method='POST'>
	<td><input type="submit" value="Modifica"/></td>
	<td><input type="hidden" id="address_id" name="address_id" value= "${a.address_id}"></td>
	</form>
	
	<td>${a.address_id}</td>
	<td>${a.cap}</td>
	<td>${a.city}</td>
	<td>${a.street}</td>
	<td>${a.civ_num}</td>
	<td>[<a href="<c:url value="/users/deleteaddress/"/>">X</a>]</td>
	
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/users/addressadd" />">Aggiungi indirizzo</a>