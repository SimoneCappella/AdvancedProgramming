<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/subs/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="cart">
             <table>
				<c:if test="${empty cart.cart_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="cart_id">Id carrello</form:label></td>
                    <td><form:input path="cart_id"/></td>
                </tr>
            </table>
		</form:form>
