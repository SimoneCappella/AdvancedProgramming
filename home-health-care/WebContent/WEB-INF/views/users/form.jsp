<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/users/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="user">
             <table>
				<c:if test="${empty user.user_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/></td>
                </tr>
				<tr>
                    <td><form:label path="name">Nome</form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
				<tr>
                    <td><form:label path="surname">cognome</form:label></td>
                    <td><form:input path="surname"/></td>
                </tr>
				<tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:input type="password" path="password"/></td>
                </tr>
				<tr>
                    <td><form:label path="role">Ruolo</form:label></td>
                    <td><form:checkbox path="role"/></td>
                </tr>
				<tr>
                    <td><form:label path="active">Utente Abilitato</form:label></td>
                    <td><form:checkbox path="active"/></td>
                </tr>
				<tr>
					<td><form:hidden path="user_id" /></td>
				</tr>
                <tr>
					<td><input type="submit" value="Submit"/></td>
					<td><input type="reset" value="Reset"></td>
                </tr>

            </table>
		</form:form>
