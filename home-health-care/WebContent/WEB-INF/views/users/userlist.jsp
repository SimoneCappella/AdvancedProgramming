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
	<td>Name and Surname</td>
	<td>Subscription Expiration</td>
	<td>Is Admin</td>
	<td>Is Active</td>
</thead>
<c:forEach items="${users}" var="u">
<tr>
	<td>[<a href="<c:url value="/users/${u.user_id}/useredit/" />">+</a>]</td>
	<td>${u.user_id}</td>
	<td>${u.name} ${u.surname}</td>
	<td>${u.subexp}</td>
	<td>${u.role}</td>
	<td>${u.active}</td>
	
	<td>[<a href="<c:url value="/users/${u.user_id}/userdelete/"/>">X</a>]</td>
</tr>
</c:forEach>
</table>
<hr/>
