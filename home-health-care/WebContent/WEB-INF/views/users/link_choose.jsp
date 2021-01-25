<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
<link href="<c:url value="/css/singers.css" />" rel="stylesheet">
</head>
<body>
<a href="<c:url value="/" />">Home</a>
<hr/>
Numero di Users: ${fn:length(users)}. Numero di abbonamenti: ${fn:length(subs)}.
<h1>Scegli</h1>
<form method="POST" action="<c:url value="/users/link" />">
<table>
<tr>
<td>Users</td>
<td>
<select name="user">
<c:forEach items="${user}" var="u">
<option value="${u.user_id}">${u.surname}</option> 
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>Subs</td>
<td>
<select name="sub">
<c:forEach items="${subs}" var="s">
<option value="${s.sub_id}">${s.name} </option> 
</c:forEach>
</select>
</td>
</tr>
</table>
<input type="submit" value="Scegli"/>
</form>
</body>
</html>