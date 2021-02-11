<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/users/savepass" var="action_url" />
		<h1>Modifica Password</h1>
        <form:form method="POST" action="${action_url}">
             <table>
				<c:if test="${empty user.user_id}">
               
				</c:if>

				<tr>
					<td>Password<td/>
                    <td><input type='password' name ="password" value="${pass}"/></td>
                </tr>				
                <tr>
					<td><input type="submit" value="Salva Modifiche"/></td>
                </tr>
			</table>
		</form:form>