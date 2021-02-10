<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:choose>
  <c:when test="${item eq null}">
<p>${errorMessage}</p>
  </c:when>

  <c:otherwise>

<table>

<tr>
	<td>${item.title}</td>
	<td>${item.price}</td>
	<td>${item.description}</td>
	<td>${item.image}</td>
	
	<c:url value="/users/addtocart" var="action_url" />
	<form:form method="POST" action="${action_url}">
			<td><input type="number" id="quantity" name="quantity"></td>
			<td><input type="hidden" id="itemId" name="itemId" value= "${item.item_id}"></td>
			
  
	 	<td><input type="submit" value="Aggiungi al carrello"/></td><br>
	</form:form>
	
</tr>
</table>
  </c:otherwise>
</c:choose>



