<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		<%-- NB il tag <c:url ... /> non puo` comparire dentro <form:form ... , per questa ragione
		lo usiamo "fuori" da esso, e ne memorizziamo il risultato in una variabile ausiliaria --%>
		<c:url value="/singers/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="newSinger">
             <table>
<%--
				<c:if test="${newSinger.id > 0}">

				<tr>
                    <td><form:label path="id">Id</form:label></td>
                    <td><form:input path="id" /></td>
                </tr>
				
				</c:if>
 --%>
               <tr>
                    <td><form:label path="firstName">First name</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
				<tr>
                    <td><form:label path="lastName">Last name</form:label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
				<tr>
                    <td><form:label path="birthDate">Birth date</form:label></td>
                    <td><form:input path="birthDate"/></td>
                </tr>
<%--
				<tr>
					<td><form:label path="version">Version</form:label></td>
                    <td><form:input path="version"/></td>
				</tr>
--%>
				<tr>
					<td><form:hidden path="id" /></td>
					<td><form:hidden path="version" /></td>
				</tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>

            </table>
		</form:form>
