<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/css/my.css" />" rel="stylesheet">

	<hr/>
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	
	<script>

function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
  if (!event.target.matches('.dropbtn2')) {
	    var dropdowns = document.getElementsByClassName("dropdown-content2");
	    var i;
	    for (i = 0; i < dropdowns.length; i++) {
	      var openDropdown = dropdowns[i];
	      if (openDropdown.classList.contains('show')) {
	        openDropdown.classList.remove('show');
	      }
	    }
		}
	}

function myFunction2() {
	  document.getElementById("myDropdown2").classList.toggle("show");
	}

	
	 
	
</script>


<c:if test="${isAdmin}"> - Sei un Admin</c:if>
	


<%
    int a = 1;
    request.setAttribute("a", a);
%>


<c:if test="${not empty nomeSub}">
   <%
    request.setAttribute("a", a=0);
	%>
</c:if>
<c:if test="${empty nomeSub && empty messaggio}">
   <%
    request.setAttribute("a", a=1);
	%>
</c:if>
<c:if test="${empty nomeSub && not empty messaggio}">
   <%
    request.setAttribute("a", a=2);
	%>
</c:if>

<div class="navbar" style="float: right;">
  <a class="active" href="<c:url value="/" />"><i class="fa fa-fw fa-home" ></i> Home</a>
  <a href="<c:url value="/itemlist" />"><i class="fa fa-shopping-bag"></i> Catalogo Articoli</a>
    
  <c:if test="${isAuth}"><a href="<c:url value="/users/cartlist/" />"><i class="fa fa-shopping-cart"></i> ${item_number} Carrello</a></c:if>
  <c:if test="${! isAuth}"><a href="<c:url value="/register/" />"><i class="fa fa-plus-square"></i> Registati</a></c:if>

<c:if test="${isAuth}">   
  <div class="dropdown">
  <a onclick="myFunction()" class="dropbtn">
  <i class="fa fa-cog" ></i> Opzioni</a>
  <div id="myDropdown" class="dropdown-content">
    <a href="<c:url value="/users/myorders" />"><i class="fa fa-shopping-bag" ></i> Storico Ordini</a>
    <a href="<c:url value="/users/view_sub" />">
    	<c:if test="${a eq 0}">
    		<i class="fa fa-address-card" style="color:green;"></i>
    		</c:if> 
    		<c:if test="${a eq 1}">
    		<i class="fa fa-address-card" style="color:black;"></i>
    		</c:if>
    		<c:if test="${a eq 2}">
			<i class="fa fa-address-card" style="color:red;"></i>
			</c:if>
    		Abbonamento</a>
    <a href="<c:url value="/users/sublist/" />"><i class="fa fa-building" ></i> I Miei Indirizzi</a>

  </div>
</div>
</c:if>
<c:if test="${isAdmin}">   
  <div class="dropdown2">
  <a onclick="myFunction2()" class="dropbtn2">
  <i class="fa fa-magic" ></i> Comandi Admin</a>
  <div id="myDropdown2" class="dropdown-content2">
    <a href="<c:url value="/admins/userlist" />"><i class="fa fa-users" ></i> Gestisci Utenti</a>
    
    <a href="<c:url value="/admins/sublist/" />"><i class="fa fa-id-card" ></i> Gestisci Sub</a>

  </div>
</div>
</c:if>
  

  <c:if test="${! isAuth}"><a href="<c:url value="/login" />"><i class="fa fa-fw fa-user"></i> Login</a></c:if>
  <c:if test="${isAuth}"><a href="<c:url value="/logout" />"><i class="fa fa-sign-out"></i> Logout</a></c:if>

  
  

  
</div>