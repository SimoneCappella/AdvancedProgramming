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
Numero di cantanti: ${fn:length(singers)}. Numero di strumenti: ${fn:length(instruments)}.
<h1>Scegli</h1>
<form method="POST" action="<c:url value="/singers/link" />">
<table>
<tr>
<td>Cantanti</td>
<td>
<select name="singer">
<c:forEach items="${singers}" var="s">
<option value="${s.id}">${s.fullName}</option> 
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>Strumenti</td>
<td>
<select name="instrument">
<c:forEach items="${instruments}" var="i">
<option value="${i.instrumentId}">${i.name} (${i.family})</option> 
</c:forEach>
</select>
</td>
</tr>
</table>
<input type="submit" value="Scegli"/>
</form>
</body>
</html>