<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
h3, table {
    margin-left: 2%;
}

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

<tr><td colspan="2"><lable>Nome: ${user.name}</lable></td></tr>
<tr><td colspan="2"><lable>Cognome: ${user.surname}</lable></td></tr>
<tr><td colspan="2"><lable>Email: ${user.email}</lable></td></tr>
<tr style="line-height:100px">
	<td ><button style="margin-top:2%" class="btn" onclick="window.location.href='/home-health-care/users/edit';">Modifica profilo</button></td>
	<td><button style="margin-top:2%" class="btn" onclick="window.location.href='/home-health-care/users/deletemyaccount';">Elimina il mio profilo</button></td>
</tr>
</table>

