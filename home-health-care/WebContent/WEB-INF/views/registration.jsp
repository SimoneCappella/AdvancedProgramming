<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Registrazione</h2>

<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
<c:forEach items="${errorMessage}" var="err">
    ${err}<br>
</c:forEach>
	
	</div>
</c:if>





<script>

function Validate(el) {
	 
	var x = document.getElementById(el).value.length;
	
	var z=false;
	var max=null;
	var min=null;
	var name;
	if(el==="surname")
	{	
		max=20;	
		min=3;
		name="Valore del cognome ";
	}
	if(el=== "name")
	{	
		max=20;	
		min=3;
		name="Valore del nome ";
	}
	if(el==="pass")
	{	
		max=20;	
		min=7;
		name="Valore della password ";
	}
	if(el==="email")
	{	
		max=20;	
		min=4;
		name="Valore della mail ";
	}

		
    document.getElementById(el).style.border = "thin solid black";
    document.getElementById(el+'1').innerHTML= "";
	 
	  if (x == '') {
		  z=true;
		  document.getElementById(el+'1').innerHTML =' Compila questo campo';
	  }
	  else if (x < min ) {
		  z=true;
		  document.getElementById(el+'1').innerHTML =name+' troppo corto';
	  }
	  else if (x >max) {
		  z=true;
		  document.getElementById(el+'1').innerHTML =name+' troppo lungo';
	  }
	  if(z==true){
		  document.getElementById(el).value ="";
		  document.getElementById(el).style.border = "medium solid red"; 
		  return false;		  
	  }
		     
}

function Validation(){
	
	if( document.getElementById("name").value ==""||document.getElementById("surname").value ==""||document.getElementById("email").value ==""||document.getElementById("pass").value =="")
	{
		alert("completa tutti i campi!");
		return false;
	}
	
}

</script>




<form name='registration' action="<c:url value="/save" />" method='POST' modelAttribute="user">
	<table>
		<tr>
                    <td><form:label path="name">Nome</form:label></td>
                    <td><input type='text' id='name' name='name' onfocusout='return Validate(this.id);'  placeholder='Mario'></td>
                	<td><label id="name1" ></label></td>
				</tr>
				<tr>
                    <td><form:label path="surname">Cognome</form:label></td>

                    <td><input id="surname" type='text' name='surname' onfocusout='return Validate(this.id);' placeholder='Rossi'></td>
					<td><label id="surname1" ></label></td>
                </tr>
				<tr>
                    <td><form:label path="email">email</form:label></td>
                    <td><input id="email"  type='text' name='email' onfocusout='return Validate(this.id);' placeholder='mariorossi@esempio.it'></td>
					<td><label id="email1" ></label></td>
                </tr>
				<tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><input id="pass" type='password' name='password' onfocusout='return Validate(this.id);'></td>
					<td><label id="pass1" ></label></td>
                </tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" onclick='return Validation();'
				value="submit" /></td>
		</tr>
	</table>

</form>
               
