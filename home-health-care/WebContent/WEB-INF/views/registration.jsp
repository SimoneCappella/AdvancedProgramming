<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Registrazione Form</h3>

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
		name="Il cognome ";
	}
	if(el=== "name")
	{	
		max=20;	
		min=3;
		name="Il nome ";
	}
	if(el==="pass")
	{	
		max=20;	
		min=7;
		name="La password ";
	}
	if(el==="email")
	{	
		max=20;	
		min=4;
		name="La mail ";
	}

		
    document.getElementById(el).style.border = "thin solid black";
    document.getElementById(el+'1').innerHTML= "";
	 
    if (x < min && x!=0 ) {
		  z=true;
		  document.getElementById(el+'1').innerHTML =name+' deve avere almeno '+ min+' caratteri';
	  }
	  else if (x >max) {
		  z=true;
		  document.getElementById(el+'1').innerHTML =name+' deve avere massimo '+ max+' caratteri';
	  }
	  if(z==true){
		  document.getElementById(el).value ="";
		  document.getElementById(el).style.border = "medium solid red"; 
		  return false;		  
	  }
		     
}

function Validation(){
	
	var z=false;
	if (document.getElementById("name").value =="") {
		  z=true;
		  document.getElementById("name").style.border = "medium solid red"; 
		  document.getElementById("name1").innerHTML =' Compila questo campo';
	  }
	if (document.getElementById("surname").value =="") {
		  z=true;
		  document.getElementById("surname").style.border = "medium solid red"; 
		  document.getElementById("surname1").innerHTML =' Compila questo campo';
	  }
	if (document.getElementById("email").value =="") {
		  z=true;
		  document.getElementById("email").style.border = "medium solid red"; 
		  document.getElementById("email1").innerHTML =' Compila questo campo';
	  }
	if (document.getElementById("pass").value =="") {
		  z=true;
		  document.getElementById("pass").style.border = "medium solid red"; 
		  document.getElementById("pass1").innerHTML =' Compila questo campo';
	  }
	if(z==true)
	{
		return false;
	}
	
}

</script>




<form name='registration' action="<c:url value="/save" />" method='POST' modelAttribute="user">
	<table>
		<tr>
                    <td><form:label path="name">Nome</form:label></td>
                    <td><input type='text' id='name' name='name' onfocusout='return Validate(this.id);'  placeholder='Mario' style="border: thin solid black"></td>
                	<td><label id="name1" ></label></td>
				</tr>
				<tr>
                    <td><form:label path="surname">Cognome</form:label></td>

                    <td><input id="surname" type='text' name='surname' onfocusout='return Validate(this.id);' placeholder='Rossi' style="border: thin solid black"></td>
					<td><label id="surname1" ></label></td>
                </tr>
				<tr>
                    <td><form:label path="email">email</form:label></td>
                    <td><input id="email"  type='text' name='email' onfocusout='return Validate(this.id);' placeholder='mariorossi@esempio.it' style="border: thin solid black"></td>
					<td><label id="email1" ></label></td>
                </tr>
				<tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><input id="pass" type='password' name='password' onfocusout='return Validate(this.id);' style="border: thin solid black"></td>
					<td><label id="pass1" ></label></td>
                </tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" onclick='return Validation();'
				value="submit" /></td>
		</tr>
	</table>

</form>
               
