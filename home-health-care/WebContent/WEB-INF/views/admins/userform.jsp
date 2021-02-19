<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
function Validate(el){
	var x = document.getElementById(el).value.length;
	var z = false;
	var max = null;
	var min = null;
	var name;
	switch(el){
	case 'name':
		max = 20;
		min = 3;
		name = "Il nome ";
		break;
	case 'surname':
		max = 20;
		min = 3;
		name = "Il cognome ";
		break;
	case 'email':
		max = 35;
		min = 4;
		name = "La mail ";
		break;
	case 'password':
		max = 20;
		min = 7;
		name = "La password ";
	default:
		break;
	}
	
	document.getElementById(el).style.border = "thin solid black";
	document.getElementById(el+"1").innerHTML= "";
	
	switch(true){
		case (x < min && x != 0):
			z = true;
			document.getElementById(el+'1').innerHTML =name+' deve avere almeno '+ min+' caratteri';
			break;
		case (x > max):
			z = true;
			document.getElementById(el+'1').innerHTML =name+' deve avere massimo '+ max+' caratteri';
			break;
		default:
			break;	
	}
	if(z == true){
		document.getElementById(el).style.border = "medium solid red";
		return false;
	}
}

function Validation(){
	var z = false;
	if(document.getElementById("name").value == "" ){
		z = true;
		document.getElementById("name").style.border = "medium solid red";
		document.getElementById("name1").innerHTML = "Compila questo campo!";
	}
	if(document.getElementById("surname").value == ""){
		z = true;
		document.getElementById("surname").style.border = "medium solid red";
		document.getElementById("surname1").innerHTML = "Compila questo campo!";
	}
	
	if(document.getElementById("email").value == ""){
		z = true;
		document.getElementById("email").style.border = "medium solid red";
		document.getElementById("email1").innerHTML = "Compila questo campo!";
	} 
	
	if(document.getElementById("password").value == ""){
		z = true;
		document.getElementById("password").style.border = "medium solid red";
		document.getElementById("password1").innerHTML = "Compila questo campo!";
	}
	
	if(z == true){
		return false;
	}
}

</script>
		<c:url value="/admins/usersave" var="action_url" />
<h3>Modifica i dati di un utente</h3>
<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
<c:forEach items="${errorMessage}" var="err">
    ${err}<br>
</c:forEach>
</div>
</c:if>
        <form:form method="POST" action="${action_url}" modelAttribute="user">
             <table>
				<c:if test="${empty user.user_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><input type ="text" id="email" name="email" onfocusout="return Validate(this.id);" value="${user.email}" style="border: thin solid black"/></td>
                	<td><label id="email1" style="color: red;font-weight:bold;"></label></td>
				</tr>
				<tr>
                    <td><form:label path="name">Nome</form:label></td>
                    <td><input type ="text" id="name" name="name" onfocusout="return Validate(this.id);" value="${user.name}" style="border: thin solid black"/></td>
                	<td><label id="name1" style="color: red;font-weight:bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="surname">Cognome</form:label></td>
                    <td><input type ="text" id="surname" name="surname" onfocusout="return Validate(this.id);" value="${user.surname}" style="border: thin solid black"/></td>
                	<td><label id="surname1" style="color: red;font-weight:bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><input type ="password" id="password" name="password" onfocusout="return Validate(this.id);" value="${user.password}" style="border: thin solid black"/></td>
                	<td><label id="password1" style="color: red;font-weight:bold;"></label></td>
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
					<td><input type="submit" value="Submit" onclick="return Validation(this.id);"/></td>
					<td><input type="reset" value="Reset"></td>
                </tr>

            </table>
		</form:form>
