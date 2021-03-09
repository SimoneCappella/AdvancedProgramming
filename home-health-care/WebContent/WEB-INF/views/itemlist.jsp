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
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div>
</c:if>



	
<c:forEach items="${items}" var="i">

    <div>
    <div class="container"	 style="float: left;margin-right: 25px;">
    <img src=' <c:url value= "${i.image}"/> ' alt="Cristian" >
    </div>
      <div class="containerone" style="margin-bottom: 100px"
      >
        <h3>${i.title}</h3>
        <p class="title">Java Developer</p>
        <p>${i.description}</p>
        <p>	${i.price} €</p>
        <p><button class="button">Contact</button></p>
      	
	<c:if test="${isUser}"> - <td><a href="<c:url value="/users/${i.item_id}/viewitemdetail" />">Dettagli Articolo</a></td></c:if>
<c:if test="${isAdmin}"> - <td><a href="<c:url value="/admins/${i.item_id}/deleteitem" />">Elimina Oggetto</a></td></c:if>
<c:if test="${isAdmin}"> - <td><a href="<c:url value="/admins/${i.item_id}/itemedit" />">  - Modifica Oggetto</a></td></c:if>
      </div>
  </div>


</c:forEach>




<c:if test="${isAdmin}"> - <a href="<c:url value="/admins/itemadd" />">Aggiungi Oggetto</a></c:if>