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
	<li>Subs: <a href="<c:url value="/subs/list/" />">sub</a>
	<li>Cart: <a href="<c:url value="/carts/list/" />">cart</a> 
</ul>
