<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Il mio profilo</h1>
<ul>
	<li>Nome: ${user.name}
	<li>Cognome: ${user.surname}
	<li>Email: ${user.email}
</ul>
<button onclick="window.location.href='/home-health-care/users/edit';">
      Modifica profilo
</button>
<button onclick="window.location.href='/home-health-care/users/deletemyaccount';">
      Elimina il mio profilo
</button>