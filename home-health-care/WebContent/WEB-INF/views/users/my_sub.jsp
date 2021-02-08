<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:choose>
  <c:when test="${sub eq null}">
<p>${errorMessage}</p>
<li>Aggiungi una sub a un utente: <a href="<c:url value="/users/link_sub" />">Add</a>
  </c:when>

  <c:otherwise>
    <h1>Vedi Abbonamento</h1>
<table>
<thead>
	<td>Name</td>
	<td>Discount</td>
	<td>Price</td>
	<td>Scadenza</td>
	<td></td>
</thead>
<tr>
	<td>${sub.name}</td>
	<td>${sub.discount}%</td>
	<td>${sub.price}€</td>
	<td>${user.subexp}</td>
	<td>[<a href="<c:url value="/users/${sub.sub_id}/unlinksub/"/>">Cancella abbonamento</a>]</td>
</tr>
</table>
  </c:otherwise>
</c:choose>

