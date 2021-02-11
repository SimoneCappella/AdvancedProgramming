<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
 
		<c:url value="/admins/itemsave" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="item">
             <table>
			<thead>
				<c:if test="${empty item.item_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="title">Nome </form:label></td>
                    <td><form:input path="title"/></td>
                </tr>
				<tr>
                    <td><form:label path="description">Descrizione</form:label></td>
                    <td><form:input path="description"/></td>
                </tr>
				<tr>
                    <td><form:label path="price">Prezzo</form:label></td>
                    <td><form:input path="price"/></td>
                </tr>
				<tr>
				<td><form:label path="image">Immagine</form:label></td>
                    <td><form:input path="image"/></td>
                </tr>
				<tr>
					<td><form:hidden path="item_id" /></td>
				</tr>
                <tr>
					<td><input type="submit" value="Submit"/></td>
                </tr>
			<thead/>
            </table>
		</form:form>