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
		max = 30;
		min = 2;
		name = "Il nome dell'abbonamento ";
		break;
	default:
		break;
	}
	
	document.getElementById(el).style.border = "thin solid black";
	document.getElementById(el+"1").innerHTML= "";
	
	switch(true){
		case (x < min ):
			z = true;
		  	document.getElementById(el+'1').innerHTML = name + 'deve avere almeno ' + min + ' caratteri';
			break;
		case (x > max):
			z = true;
		  	document.getElementById(el+'1').innerHTML = name + 'deve avere massimo ' + max + ' caratteri';
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
	if (document.getElementById("name").value.length <2 || document.getElementById("name").value.length >20) {
		  c=true;
		  document.getElementById("name").style.border = "medium solid red";
		  document.getElementById("name1").innerHTML = ' Compila questo campo con almeno 3 caratteri';
	  }
	
	if(c == true){
		return false;
	}
}

</script>
<div style="margin-left:2%">
<div style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Aggiungi o Modifica un Abbonamento</h3></div>
<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
<c:forEach items="${errorMessage}" var="err">
    ${err}<br>
</c:forEach>
</div>
</c:if>
		<c:url value="/admins/subsave" var="action_url" />
        <form:form method="POST" action="${action_url}" modelAttribute="sub">
             <table style="border:none">
				<c:if test="${empty sub.sub_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="name">Nome abbonamento:</form:label></td>
                    <td><input type='text' id='name' name='name' onfocusout='return Validate(this.id);' value="${sub.name}" style="border: thin solid black"/></td>
					<td><label id="name1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="discount">Sconto (%):</form:label></td>
                    <td><input type='text' id='discount' name='discount' value="${sub.discount}" style="border: thin solid black"/></td>
					<td><label id="discount1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="price">Prezzo mensile (€):</form:label></td>
                    <td><input type='text' id='price' name='price' value="${sub.price}" style="border: thin solid black"/></td>
					<td><label id="price1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
					<td><form:hidden path="sub_id" /></td>
				</tr>
                <tr>
					<td colspan="2"><input type="submit" value="Conferma" class="btn" onclick='return Validation();'/></td>
                </tr>

            </table>
		</form:form>
</div>