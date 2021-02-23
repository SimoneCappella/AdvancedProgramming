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
	case 'title':
		max = 20;
		min = 2;
		name = "Il nome dell' articolo";
		break;
	case 'description':
		max = 300;
		min = 5;
		name = "La descrizione";
		break;
	default:
		break;
	}
	
	document.getElementById(el).style.border = "thin solid black";
	document.getElementById(el+"1").innerHTML= "";
	
	switch(true){
		case (x < min):
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
	if (document.getElementById("title").value.length <2 || document.getElementById("title").value.length >20) {
		  c=true;
		  document.getElementById("title").style.border = "medium solid red";
		  document.getElementById("title1").innerHTML = ' Compila questo campo con almeno 3 caratteri';
	  }
	
	if (document.getElementById("description").value.length <2 || document.getElementById("description").value.length >20) {
		  c=true;
		  document.getElementById("description").style.border = "medium solid red";
		  document.getElementById("description1").innerHTML = ' Compila questo campo con almeno 3 caratteri';
	  }
	
	if(c == true){
		return false;
	}
}

</script>
<c:url value="/admins/itemsave" var="action_url" />
<h3>Aggiungi o modifica un prodotto o un servizio</h3>
<c:if test="${fn:length(errorMessage) > 0}">
<div style="color: red; padding:20px; font-weight: bold; margin: 30px 0px;">
<c:forEach items="${errorMessage}" var="err">
    ${err}<br>
</c:forEach>
</div>
</c:if>
        <form:form method="POST" action="${action_url}" modelAttribute="item">
             <table>
			<thead>
				<c:if test="${empty item.item_id}">
               
				</c:if>
               <tr>
                    <td><form:label path="title">Nome: </form:label></td>
                    <td><input type='text' id='title' name='title' onfocusout='return Validate(this.id);' value="${item.title}" style="border: thin solid black"/></td>
					<td><label id="title1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="description">Descrizione: </form:label></td>
                    <td><input type='text' id='description' name='description' onfocusout='return Validate(this.id);' value="${item.description}" style="border: thin solid black"/></td>
					<td><label id="description1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
                    <td><form:label path="price">Prezzo: </form:label></td>
                    <td><input type='text' id='price' name='price' value="${item.price}" style="border: thin solid black"/></td>
					<td><label id="price1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
					<td><form:label path="image">Immagine: </form:label></td>
                    <td><input type='text' id='image' name='image' value="${item.image}" style="border: thin solid black"/></td>
					<td><label id="image1" style="color: red;font-weight: bold;"></label></td>
                </tr>
				<tr>
					<td><form:hidden path="item_id" /></td>
				</tr>
                <tr>
					<td><input name ="submit" type="submit" value="Conferma" onclick='return Validation();'/></td>
                </tr>
			<thead/>
            </table>
		</form:form>