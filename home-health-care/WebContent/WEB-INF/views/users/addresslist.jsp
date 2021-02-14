<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


 <c:if test="${not empty errorMessage1}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage1}</div>
</c:if>

<c:choose> 


	<c:when test="${address eq null}">
		<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
		<br> <a href="<c:url value="/users/addressadd" />">Aggiungi indirizzo</a>
		</c:when>
<c:otherwise>



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
	<form name='delete' action ="<c:url value="/users/deleteaddress"/>" method='POST'>
	<td><input type="submit" value="Rimuovi"/></td>
	<td><input type="hidden" id="address_id" name="address_id" value= "${a.address_id}"></td>
	</form>
	
</tr>
</c:forEach>
</table>
<hr/>
<a href="<c:url value="/users/addressadd" />">Aggiungi indirizzo</a>
</c:otherwise>
</c:choose>