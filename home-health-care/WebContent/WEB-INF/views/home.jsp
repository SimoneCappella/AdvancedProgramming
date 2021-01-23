<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<p>Adesso sono le: ${serverTime}.</p>
<ul>
	<li>Prove: <a href="<c:url value="/prove/list/" />">lista</a>
	<li>Oggetti nel carrello: ${item_number} Totale: 40€<a href="<c:url value="/users/${cart_id = 1}/cartlist/" />">Vai a Carrello</a> 
	<li>Subs: <a href="<c:url value="/admins/sublist/" />">sub</a>
	<li>Users: <a href="<c:url value="/admins/userlist/" />">user</a> 
	<li>Registrati: <a href="<c:url value="/register/" />">user</a> 
</ul>
