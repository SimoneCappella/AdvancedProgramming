<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>
<h1>Elimina il mio profilo</h1>
<form name='checkpass' action="<c:url value="/users/checkanddelete" />" method='POST'>
	<table>
		<tr>
			<td>Digita nella casella di testo "CONFERMO" per eliminare il tuo account:</td>
			<td><input type='text' name='confirmation' ></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="Elimina il mio account" /></td>
		</tr>
	</table>