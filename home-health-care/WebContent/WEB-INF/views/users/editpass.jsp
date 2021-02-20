<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
    ${errorMessage}<br>
	</div>
</c:if>
<script>
	function Validate(el) {

		var x = document.getElementById(el).value.length;

		var z = false;
		var max = null;
		var min = null;
		var name;
		if (el === "pass") {
			max = 20;
			min = 7;
			name = "La Password";
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

		var z = false;
		if (document.getElementById("pass").value.length <7 || document.getElementById("pass").value.length >20) {
			z = true;
			document.getElementById("pass").style.border = "medium solid red";
			document.getElementById("pass1").innerHTML = ' Compila questo campo con almeno 7 caratteri';
		}
		if (z == true) {
			return false;
		}
	}
</script>


<c:url value="/users/savepass" var="action_url" />
<h1>Modifica Password</h1>
        
<form:form method="POST" action="${action_url}">
             <table>
		<c:if test="${empty user.user_id}">
               
				</c:if>

		<tr>
			<td>Password
			<td />                     
			<td><input type='password' id="pass" name="password"
				onfocusout='return Validate(this.id);'
				style="border: thin solid black" /></td>
			<td><label id="pass1" style="color: red; font-weight: bold;"></label></td>
			                                 
		</tr>
		                
		<tr>
			<td><input type="submit" value="Salva Modifiche"
				onclick='return Validation();' /></td>                 
		</tr>
	</table>
</form:form>