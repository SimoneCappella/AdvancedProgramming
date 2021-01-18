<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<p>Adesso sono le: ${serverTime}.</p>
<ul>
	<li>Cantanti: <a href="<c:url value="/singers/list/" />">lista</a>
		- <a href="<c:url value="/singers/add/" />">aggiungi</a>
	<li>Album: <a href="<c:url value="/albums/list/" />">lista</a> - <a href="<c:url value="/albums/add/" />">aggiungi</a> 
	<li>Strumenti: <a href="<c:url value="/instruments/list/" />">lista</a> - <a href="<c:url value="/instruments/add/" />">aggiungi</a> 
	<li>Prove: <a href="<c:url value="/prove/list/" />">lista</a> 
	<li>Cart: <a href="<c:url value="/carts/${cart_id = 1}/list/" />">cart</a> 
	<li>Subs: <a href="<c:url value="/subs/list/" />">sub</a> 
	<li>Users: <a href="<c:url value="/users/list/" />">user</a> 
	<li>Registrati: <a href="<c:url value="/register/" />">user</a> 
</ul>
