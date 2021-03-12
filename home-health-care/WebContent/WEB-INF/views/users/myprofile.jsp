<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
h3, table {
    margin-left: 2%;
}
h4{ font-weight: normal;}
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



<h3>Panoramica Profilo</h3>
<table style="border:none">

<tr><td colspan="2"><h4>Nome: ${user.name}</h4></td></tr>
<tr><td colspan="2"><h4>Cognome: ${user.surname}</h4></td></tr>
<tr><td colspan="2"><h4>Email: ${user.email}</h4></td></tr>
<tr>
	<td><button class="btn" onclick="window.location.href='/home-health-care/users/edit';">Modifica profilo</button></td>
	<td><button class="btn" onclick="window.location.href='/home-health-care/users/deletemyaccount';">Elimina il mio profilo</button></td>
</tr>
</table>

