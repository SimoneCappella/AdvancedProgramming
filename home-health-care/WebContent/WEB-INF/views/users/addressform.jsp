<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    

<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
<c:forEach items="${errorMessage}" var="item">
    ${item}<br>
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
	if(el==="cap")
	{	
		max=8;	
		min=2;
		name="Il Cap";
	}
	if(el=== "city")
	{	
		max=20;	
		min=2;
		name="Il nome della città";
	}
	if(el==="street")
	{	
		max=20;	
		min=3;
		name="L'indirizzo";
	}
	if(el==="civ_num")
	{	
		max=5;	
		min=1;
		name="Il Numero civico";
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
	if (document.getElementById("cap").value =="") {
		  z=true;
		  document.getElementById("cap").style.border = "medium solid red"; 
		  document.getElementById("cap1").innerHTML =' Compila questo campo';
	  }
	if (document.getElementById("city").value =="") {
		  z=true;
		  document.getElementById("city").style.border = "medium solid red"; 
		  document.getElementById("city1").innerHTML =' Compila questo campo';
	  }
	if (document.getElementById("street").value =="") {
		  z=true;
		  document.getElementById("street").style.border = "medium solid red"; 
		  document.getElementById("street1").innerHTML =' Compila questo campo';
	  }
	if (document.getElementById("civ_num").value =="") {
		  z=true;
		  document.getElementById("civ_num").style.border = "medium solid red"; 
		  document.getElementById("civ_num1").innerHTML =' Compila questo campo';
	  }
	if(z==true)
	{
		return false;
	}
}



</script>

		<c:url value="/users/addresssave" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="address">
             <table>
				<c:if test="${empty address.address_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="cap">Cap: </form:label></td>
                    <td><form:input id="cap" path="cap" onfocusout='return Validate(this.id);'  placeholder='62018' style="border: thin solid black" /></td>
					<td><label id="cap1" style="color: red;  font-weight: bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="city">Città:</form:label></td>
                    <td><form:input id="city" path="city" onfocusout='return Validate(this.id);'  placeholder='Roma' style="border: thin solid black"/></td>
					<td><label id="city1" style="color: red;  font-weight: bold;" ></label></td>
				</tr>
				<tr>
                    <td><form:label path="street">Via:</form:label></td>
                    <td><form:input id="street" path="street" onfocusout='return Validate(this.id);'  placeholder='Via Liccardo Rosario' style="border: thin solid black"/></td>
					<td><label id="street1" style="color: red;  font-weight: bold;" ></label></td>
                </tr>
				<tr>
				<td><form:label path="civ_num">Numero Civico:</form:label></td>
                    <td><form:input id="civ_num" path="civ_num" onfocusout='return Validate(this.id);'  placeholder='65' style="border: thin solid black"/></td>
					<td><label id="civ_num1" style="color: red;  font-weight: bold;" ></label></td>
                </tr>
				<tr>
					<td><form:hidden path="address_id" /></td>
				</tr>
                <tr>
					<td><input type="submit" value="Submit" onclick='return Validation();' /></td>
                </tr>

            </table>
		</form:form>