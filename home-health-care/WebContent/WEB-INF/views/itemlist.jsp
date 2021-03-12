<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>


.container {
    width: 250px;
    height: 250px;
}

.container img {
    width: 250px;
    height: 250px;
}
</style>

	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
<c:if test="${empty items}">
	<div style="color: red; font-weight: bold; margin: 30px 2%;">Non ci sono articoli</div>
</c:if>


<c:if test="${isAdmin}"> <a href="<c:url value="/admins/itemadd" />"><i class="fa fa-plus" style="color:#007bff; margin-left:2%" ></i>Aggiungi Nuovo Articolo</a></c:if>
	
<c:forEach items="${items}" var="i">

    <div>
    <div class="container"	 style="float: left;margin-right: 25px;">
    <img src=' <c:url value= "${i.image}"/> ' alt="nonValida" >
    </div>
      <div class="containerone" style="margin-bottom: 150px"
      >
        <h3>${i.title}</h3>
        <p class="title" >${i.description}</p>
        <p>Prezzo: ${i.price}€</p>
     	
		<c:if test="${isUser}"><td><a href="<c:url value="/users/${i.item_id}/viewitemdetail" />"><i class="fa fa-cart-plus"></i>
		Acquista Articolo</a></td></c:if>
		<c:if test="${isAdmin}"><td><a href="<c:url value="/users/${i.item_id}/viewitemdetail" />"><i class="fa fa-cart-plus"></i>
		Acquista Articolo </a></td></c:if>	
		<c:if test="${isAdmin}"><td><a href="<c:url value="/admins/${i.item_id}/itemedit" />"><i class="fa fa-pencil"></i>Modifica Oggetto</a></td></c:if>
		<c:if test="${isAdmin}"><td><a href="<c:url value="/admins/${i.item_id}/deleteitem"/>"><i class="fa fa-trash"></i>Elimina Oggetto</a></td></c:if>

	  </div>
  </div>


</c:forEach>




