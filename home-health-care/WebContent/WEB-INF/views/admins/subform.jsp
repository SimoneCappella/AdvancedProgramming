<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
 
		<c:url value="/admins/subsave" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="sub">
             <table>
				<c:if test="${empty sub.sub_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="name">Name sub</form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
				<tr>
                    <td><form:label path="discount">discount</form:label></td>
                    <td><form:input path="discount"/></td>
                </tr>
				<tr>
                    <td><form:label path="price">Price</form:label></td>
                    <td><form:input path="price"/></td>
                </tr>
				<tr>
					<td><form:hidden path="sub_id" /></td>
				</tr>
                <tr>
					<td><input type="submit" value="Submit"/></td>
                </tr>

            </table>
		</form:form>
