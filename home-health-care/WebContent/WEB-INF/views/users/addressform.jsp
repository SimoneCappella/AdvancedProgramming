<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
 
		<c:url value="/addresses/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="sub">
             <table>
				<c:if test="${empty address.address_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="cap">Cap </form:label></td>
                    <td><form:input path="cap"/></td>
                </tr>
				<tr>
                    <td><form:label path="city">City</form:label></td>
                    <td><form:input path="city"/></td>
                </tr>
				<tr>
                    <td><form:label path="street">Street</form:label></td>
                    <td><form:input path="street"/></td>
                </tr>
				<tr>
				<td><form:label path="civ_num">Civ_num</form:label></td>
                    <td><form:input path="civ_num"/></td>
                </tr>
				<tr>
					<td><form:hidden path="address_id" /></td>
				</tr>
                <tr>
					<td><input type="submit" value="Submit"/></td>
                </tr>

            </table>
		</form:form>