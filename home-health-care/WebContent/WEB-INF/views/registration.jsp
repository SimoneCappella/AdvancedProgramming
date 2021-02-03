ù<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>${appName} - Registrazione Form</h2>

<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>

<form name='registration' action="<c:url value="/save" />" method='POST' modelAttribute="user">
	<table>
		<tr>
                    <td><form:label path="name">Nome</form:label></td>
                    <td><input type='text' name='name' value=''></td>
                </tr>
				<tr>
                    <td><form:label path="surname">Cognome</form:label></td>
                    <td><input type='text' name='surname' value=''></td>
                </tr>
				<tr>
                    <td><form:label path="email">email</form:label></td>
                    <td><input type='text' name='email' value=''></td>
                </tr>
				<tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><input type='text' name='password' value=''></td>
                </tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit"
				value="submit" /></td>
		</tr>
	</table>

</form>
               
