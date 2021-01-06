<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/albums/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="album">
             <table>
				<c:if test="${empty album.id}">
               <tr>
					<td>Instrument Id</td>
					<td>${album.id}</td>
                </tr>
				</c:if>
                <tr>
                    <td><form:label path="title">Title</form:label></td>
                    <td><form:input path="title"/></td>
                </tr>
				<tr>
					<td><form:hidden path="id" /></td>
				</tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>

            </table>
		</form:form>
