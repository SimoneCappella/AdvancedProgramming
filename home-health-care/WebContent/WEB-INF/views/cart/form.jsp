<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/cart/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="item">
			 <h1>Modifica quantità dell'oggetto ${item.item_code}</h1>
             <table>
				<c:if test="${empty item.cart_item_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="quantity">Quantità</form:label></td>
                    <td><form:input path="quantity"/></td>
               </tr>
			   <tr>
					<td><form:hidden path="cart_item_id"/></td>
			   </tr>
				<tr>
					<td><form:hidden path="item_code" /></td>
				</tr>
			   <tr>
					<td><input type="submit" value="Conferma"/></td>
               </tr>
            </table>
		</form:form>
