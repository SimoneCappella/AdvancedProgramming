<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>	
.btn {
  background-color: #007bff;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}
.btn:hover {
  opacity: 1;
}

</style>

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
		case (x < min ):
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
	var c = false;
	if (document.getElementById("name").value.length <3 || document.getElementById("name").value.length >20) {
		  c=true;
		  document.getElementById("name").style.border = "medium solid red";
		  document.getElementById("name1").innerHTML = ' Compila questo campo con almeno 3 caratteri';
	  }
	if (document.getElementById("surname").value.length <3 || document.getElementById("surname").value.length >20) {
		  c=true;
		  document.getElementById("surname").style.border = "medium solid red"; 	  
		  document.getElementById("surname1").innerHTML = ' Compila questo campo con almeno 3 caratteri';
	}
	if (document.getElementById("email").value.length <4 || document.getElementById("email").value.length >35) {
		  c=true;
		  document.getElementById("email").style.border = "medium solid red";
		  document.getElementById("email1").innerHTML = ' Compila questo campo con almeno 4 caratteri';
	}
	if(document.getElementById("password").value.length !=0 )
		if (document.getElementById("password").value.length <7 || document.getElementById("password").value.length >20) {{
			  c=true;
			  document.getElementById("password").style.border = "medium solid red";
			  document.getElementById("password1").innerHTML = ' Compila questo campo con almeno 7 caratteri';
		  }
	}
	if(c){
		return false;
	}
}

</script>
<div style="margin-left:2%">
		<c:url value="/admins/usersave" var="action_url" />
<div style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Modifica i Dati di un Utente</h3></div>
<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
<c:forEach items="${errorMessage}" var="err">
    ${err}<br>
</c:forEach>
</div>
</c:if>
        <form:form method="POST" action="${action_url}" modelAttribute="user">
             <table style="border:none">
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
                    <td><input type ="password" id="password" name="password" onfocusout="return Validate(this.id);" style="border: thin solid black"/></td>
                	<td><label id="password1" style="color: red;font-weight:bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="role">Ruolo</form:label></td>
                    <td><form:checkbox path="role"/></td>
                </tr>
				<tr>
                    <td><form:hidden path="active" /></td>
                </tr>
				<tr>
					<td><form:hidden path="user_id" /></td>
				</tr>
                <tr>
					<td><input type="submit" class="btn" value="Conferma" onclick="return Validation();"/></td>
					<td><input type="reset" class="btn" value="Resetta Valori"></td>
                </tr>

            </table>
		</form:form>
		</div>
