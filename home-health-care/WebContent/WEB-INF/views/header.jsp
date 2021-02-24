<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/css/my.css" />" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>	
	<hr/>
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	



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
<c:if test="${isAuth}"> 
<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <i class="fa fa-cog" ></i> Opzioni Utente
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
    <button class="dropdown-item" type="button" onclick="window.location.href='/home-health-care/users/myorders'"><i class="fa fa-shopping-bag" ></i> Storico Ordini</button>
    <button class="dropdown-item" type="button" onclick="window.location.href='/home-health-care/users/view_sub'">
    	<c:if test="${a eq 0}">
    		<i class="fa fa-address-card" style="color:green;"></i>
    		</c:if> 
    		<c:if test="${a eq 1}">
    		<i class="fa fa-address-card" style="color:black;"></i>
    		</c:if>
    		<c:if test="${a eq 2}">
			<i class="fa fa-address-card" style="color:red;"></i>
			</c:if>
    		Abbonamento</button>
    <button class="dropdown-item" type="button"onclick="window.location.href='/home-health-care/users/addresslist/'"> <i class="fa fa-building" ></i> I Miei Indirizzi</button>
  </div>
</div>
</c:if>


  <c:if test="${! isAuth}"><a href="<c:url value="/login" />"><i class="fa fa-fw fa-user"></i> Login</a></c:if>
  <c:if test="${isAuth}"><a href="<c:url value="/logout" />"><i class="fa fa-sign-out"></i> Logout</a></c:if>

  
  

  
</div>