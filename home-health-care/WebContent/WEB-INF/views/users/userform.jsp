<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${fn:length(errorMessage) > 0}">
	<div
		style="color: red; padding: 20px; font-weight: bold; margin: 30px 0px;">
		<c:forEach items="${errorMessage}" var="error">
    ${error}<br>
		</c:forEach>

	</div>
</c:if>
<script>
	function Validate(el) {

		var x = document.getElementById(el).value.length;

		var z = false;
		var max = null;
		var min = null;
		var name;
		if (el === "email") {
			max = 20;
			min = 4;
			name = "La email";
		}
		if (el === "name") {
			max = 20;
			min = 3;
			name = "Il nome";
		}
		if (el === "surname") {
			max = 20;
			min = 3;
			name = "Il cognome";
		}

		document.getElementById(el).style.border = "thin solid black";
		document.getElementById(el + '1').innerHTML = "";

		if (x < min) {
			z = true;
			document.getElementById(el + '1').innerHTML = name
					+ ' deve avere almeno ' + min + ' caratteri';
		} else if (x > max) {
			z = true;
			document.getElementById(el + '1').innerHTML = name
					+ ' deve avere massimo ' + max + ' caratteri';
		}
		if (z == true) {
			document.getElementById(el).style.border = "medium solid red";
			return false;
		}

	}

	function Validation() {

		var c = false;
		
		if (document.getElementById("email").value.length <4 || document.getElementById("email").value.length >20) {
			c = true;
			document.getElementById("email").style.border = "medium solid red";
		}
		if (document.getElementById("name").value.length <3 || document.getElementById("name").value.length >20) {
			c = true;
			document.getElementById("name").style.border = "medium solid red";
		}
		if (document.getElementById("surname").value.length <3 || document.getElementById("surname").value.length >20) {
			c = true;
			document.getElementById("surname").style.border = "medium solid red";
		}

		if (c == true) {
			return false;
		}
	}
</script>

<c:url value="/users/save" var="action_url" />
        
<form:form method="POST" action="${action_url}" modelAttribute="user">
             <table>
		<c:if test="${empty user.user_id}">
               
				</c:if>
		               
		<tr>
			                    
			<td><form:label path="email">Email</form:label></td>
			                    
			<td><form:input id="email" path="email"
					onfocusout='return Validate(this.id);'
					placeholder='simone.panetti@gmail.com '
					style="border: thin solid black" /></td>
			<td><label id="email1" style="color: red; font-weight: bold;"></label></td>
			                
		</tr>
		<tr>
			                    
			<td><form:label path="name">Nome</form:label></td>
			                    
			<td><form:input id="name" path="name"
					onfocusout='return Validate(this.id);' placeholder='Simone'
					style="border: thin solid black" /></td>
			<td><label id="name1" style="color: red; font-weight: bold;"></label></td>
			                
		</tr>
		<tr>
			                    
			<td><form:label path="surname">cognome</form:label></td>
			                    
			<td><form:input id="surname" path="surname"
					onfocusout='return Validate(this.id);' placeholder='Panetti'
					style="border: thin solid black" /></td>
			<td><label id="surname1" style="color: red; font-weight: bold;"></label></td>
			                
		</tr>
		<tr>
			                    
			<td><form:hidden path="password" /></td>                 
		</tr>
		<tr>
			                    
			<td><form:hidden path="role" /></td>                 
		</tr>
		<tr>
			                    
			<td><form:hidden path="active" /></td>                 
		</tr>

		<tr>
			<td><form:hidden path="user_id" /></td>
		</tr>
		                
		<tr>
			<td><input type="submit" value="Modifica"
				onclick='return Validation();' /></td>
			<td><input type="reset" value="Reset"></td>                 
		</tr>
		<tr>
	</table>
</form:form>
<button style="margin-top: 10px"
	onclick="window.location.href='/home-health-care/users/editpass';">Modifica
	Password</button>

