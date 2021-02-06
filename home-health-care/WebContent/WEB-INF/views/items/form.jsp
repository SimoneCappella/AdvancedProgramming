<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/items/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="item">
             <table>
				<c:if test="${empty item.itemId}">
               
				</c:if>
               <tr>
                    <td><form:label path="title">Title</form:label></td>
                    <td><form:input path="title"/></td>
                </tr>
				<tr>
                    <td><form:label path="description">Description</form:label></td>
                    <td><form:input path="description"/></td>
                </tr>
				<tr>
                    <td><form:label path="price">Price</form:label></td>
                    <td><form:input path="price"/></td>
                </tr>
				<tr>
                    <td><form:label path="image">Image</form:label></td>
                    <td><form:input path="image"/></td>
                </tr>
				<tr>
					<td><form:hidden path="itemId" /></td>
				</tr>
                <tr>
					<td><input type="submit" value="Submit"/></td>
                </tr>

            </table>
		</form:form>
