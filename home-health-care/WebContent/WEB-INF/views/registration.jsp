<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>


.bg-img {
  /* The image used */
  background-image: url("/home-health-care/media/banner2.png");

  min-height: 380px;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

/* Add styles to the form container */
.container {
  margin: 20px;
  max-width: 600px;
  padding: 16px;
}

.containerino {
  margin: 20px;
  max-width: 600px;
  padding: 16px;
  position:absolute;
  background-color: white;
  right:0;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit button */
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
		name="Il cognome";
	}
	if(el=== "name")
	{	
		max=20;	
		min=3;
		name="Il nome";
	}
	if(el==="pass")
	{	
		max=20;	
		min=7;
		name="La password";
	}
	if(el==="email")
	{	
		max=35;	
		min=4;
		name="La email";
	}

		
    document.getElementById(el).style.border = "thin solid black";
    document.getElementById(el+'1').innerHTML= "";
	 
    if (x < min) {
		  z=true;
		  document.getElementById(el+'1').innerHTML =name+' deve avere almeno '+ min+' caratteri';
	  }
	  else if (x >max) {
		  z=true;
		  document.getElementById(el+'1').innerHTML =name+' deve avere massimo '+ max+' caratteri';
	  }
	  if(z==true){
		  document.getElementById(el).style.border = "medium solid red"; 
		  return false;		  
	  }
		     
}

function Validation(){
	
	var c=false;
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
	if (document.getElementById("pass").value.length <7 || document.getElementById("pass").value.length >20) {
		  c=true;
		  document.getElementById("pass").style.border = "medium solid red";
		  document.getElementById("pass1").innerHTML = ' Compila questo campo con almeno 7 caratteri';
	  }
	if(c==true)
	{
		return false;
	}
	
}

</script>

<div class="bg-img">

<div  class="container" style="padding:0; margin:0">
<div class="containerino">
<div  style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Registrazione</h3></div>
<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red ;font-weight: bold; margin-bottom:1%">
<c:forEach items="${errorMessage}" var="err">
    <br><lable>${err}</lable>
</c:forEach>
	
	</div>
</c:if>

<form name='registration'  action="<c:url value="/save" />" method='POST' modelAttribute="user">
	<table style="border:none">
		<tr>
		<tr>
			<td><form:label path="name"><b>Nome</b></form:label></td>
			<tr>
			<td><input type='text' id='name' name='name' onfocusout='return Validate(this.id);'  placeholder='Mario'></td>
			<td><label id="name1" ></label></td>
		</tr>
		<tr>
		<tr>	<td><form:label path="surname"><b>Cognome</b></form:label></td>
		</tr>
		<tr>
				<td><input id="surname" type='text' name='surname' onfocusout='return Validate(this.id);' placeholder='Rossi'></td>
					<td><label id="surname1" ></label></td>
		</tr>
		<tr>
		<tr>	
		<td><form:label path="email"><b>Email</b></form:label></td>
		</tr>
		<tr>
			<td><input id="email"  type='text' name='email' onfocusout='return Validate(this.id);' placeholder='mariorossi@esempio.it'></td>
			<td><label id="email1" ></label></td>
		</tr>
		<tr>
		<tr>
			<td><form:label path="password"><b>Password</b></form:label></td>
			</tr>
			<tr>
			<td><input id="pass" type='password' name='password' onfocusout='return Validate(this.id);'></td>
			<td><label id="pass1" ></label></td>
		</tr>
		<tr>
			<td colspan='2'>Creando un account accetti i nostri <a href="https://www.youtube.com/watch?v=G1IbRujko-A" target="popup" onclick="window.open('https://www.youtube.com/watch?v=G1IbRujko-A','name','width=600,height=400')">Termini & Privacy.</a></td>
		</tr>
		<tr>
			<td colspan='2'><input class="btn" name="submit" type="submit" onclick='return Validation();' value="Registrati" /></td>
		</tr>
	</table>

</form>
</div>  
</div>


 
