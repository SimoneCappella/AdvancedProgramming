<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
		<c:url value="/instruments/save" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="instrument">
             <table>
				<c:if test="${empty instrument.instrumentId }">
               <tr>
                    <td><form:label path="instrumentId">Instrument Id</form:label></td>
                    <td><form:input path="instrumentId"/></td>
                </tr>
				</c:if>
               <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
				<tr>
                    <td><form:label path="family">Family</form:label></td>
                    <td><form:input path="family"/></td>
                </tr>
				<tr>
					<td><form:hidden path="instrumentId" /></td>
				</tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>

            </table>
		</form:form>
