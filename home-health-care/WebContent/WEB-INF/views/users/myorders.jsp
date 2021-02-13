<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1>I miei ordini</h1>
<c:if test="${not empty message}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${message}</div>
</c:if>
<c:forEach items="${items}" var="e">
<div style="border:1px solid black">
<c:forEach items="${e}" var="i">
Nome: ${i.title} | Descrizione: ${i.description} | Prezzo: ${i.price}€ 
<br>
</c:forEach> 
</div>
</c:forEach>
