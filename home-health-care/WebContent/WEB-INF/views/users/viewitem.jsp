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

.container {
    width: 250px;
    height: 250px;
}

.container img {
    width: 250px;
    height: 250px;
}
</style>

<c:choose>
  <c:when test="${item eq null}">
<p>${errorMessage}</p>
  </c:when>

  <c:otherwise>

<div>
    <div class="container"	 style="float: left;margin-right: 25px;">
    <img src="/home-health-care/${item.image}" alt="nonValida" >
    </div>
      <div class="containerone" style="margin-bottom: 150px">
      
      	<h3>${item.title}</h3>
        <p class="title" >${item.description}</p>
        <p>Prezzo: ${item.price}€</p>
     	
     	<c:url value="/users/addtocart" var="action_url" />
    <table style="border:none">
	<form:form method="POST" action="${action_url}">
			<tr>
				<td>
				<label>Quantità:</label>
				<select id="quantity" name="quantity">
	  				<option value=1>1</option>
	  				<option value=2>2</option>
	  				<option value=3>3</option>
	  				<option value=4>4</option>
	  				<option value=5>5</option>
	  				<option value=6>6</option>
	  				<option value=7>7</option>
	  				<option value=8>8</option>
	  				<option value=9>9</option>
	  				<option value=10>10</option>
				</select>
				</td>
			</tr>
			<tr>
						
				<td><input type="hidden" id="itemId" name="itemId" value= "${item.item_id}"></td>
			</tr>
			
  
	 	<td><input class="btn" type="submit" value="Aggiungi al carrello"/></td><br>
	</form:form>
	</table>
     	
     	
		</div>
  </div>
  </c:otherwise>
</c:choose>









