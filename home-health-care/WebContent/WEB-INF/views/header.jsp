<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/css/my.css" />" rel="stylesheet">
	<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
	<sec:authorize access="hasRole('USER')" var="isUser" />
	<sec:authorize access="isAuthenticated()" var="isAuth" />
	<style>
    ul {     
        padding:0;     
        margin:0    
    }    
    a {    
        display:block;    
        text-decoration: none;    
    }    
    li {    
        display:block;     
        float:left;    
    }    
    li ul li {    
        float:none;    
    }    
    li ul {    
        display:none;     
        position:absolute;     
        z-index:1    
    }    
    li:hover ul {    
        display:block;    
    }    
        
    /*DROPDOWN STYLING:*/    
    #menu {    
        height:30px    
    }    
    #menu a {    
        color:#024E67;     
        padding:10px 15px 10px 15px;    
    }    
    #menu a:hover {    
        color:#ffffff;    
    }    
    #menu li {    
        background-color:#FFF;     
        border:solid 1px #CCC;      
        margin-left:-1px    
    }    
    #menu li:hover {    
        background-color:#0072BA    
    }  
	</style>
    <ul id='menu'>    
       <li><a href='#'>Home</a>    
         <ul>    
             <li><a href='#'>Menu1</a></li>    
             <li><a href='#'>Menu2</a></li>    
             <li><a href='#'>Menu3</a></li>    
         </ul>    
       </li>    
       <li><a href='#'>About Us</a>    
         <ul>    
             <li><a href='#'>Menu1</a></li>    
             <li><a href='#'>Menu2</a></li>    
             <li><a href='#'>Menu3</a></li>    
         </ul>    
       </li>    
       <li><a href='#'>Contact Us</a>    
         <ul>    
             <li><a href='#'>Menu1</a></li>    
             <li><a href='#'>Menu2</a></li>    
             <li><a href='#'>Menu3</a></li>    
         </ul>    
       </li>    
    </ul> 