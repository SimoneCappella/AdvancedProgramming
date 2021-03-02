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
	
	
<style>
.custom-ul {     
    padding:0;     
    margin:0    
}    
.custom-a {    
    display:block;    
    text-decoration: none;    
}    
.custom-li {    
    display:block;     
    float:left;    
}    
.custom-li .custom-ul .custom-li {    
    float:none;    
}    
.custom-li .custom-ul {    
    display:none;     
    position:absolute;     
    z-index:1    
}    
.custom-li:hover .custom-ul {    
    display:block;    
}     

#menu a1 {    
        
    padding:0px 16px 0px 0px;    
}
#menu a {    
       
    padding:10px 15px 10px 15px;    
}    
#menu a:hover {    
      
}    
#menu li {    
    background-color:#FFF;     
   
    margin-left:-1px    
}    
#menu li:hover {    
   
} 
</style>


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
<div class="navbar" style="color:#007bff;"> 
<span style="  display: flex; align-items: center;"><i class="fa fa-fw fa-home fa-3x"></i><span> Home Healt Care</span></span>


<div class="navbar" style="float: right;">
  <a class="active" href="<c:url value="/" />"><i class="fa fa-fw fa-home" ></i> Home</a>
  <a href="<c:url value="/itemlist" />"><i class="fa fa-shopping-bag"></i> Catalogo Articoli</a>
    
  <c:if test="${isAuth}"><a href="<c:url value="/users/cartlist/" />"><i class="fa fa-shopping-cart"></i> ${item_number} Carrello</a></c:if>
  <c:if test="${! isAuth}"><a href="<c:url value="/register/" />"><i class="fa fa-plus-square"></i> Registati</a></c:if>

<ul id='menu' class="custom-ul">    
   <c:if test="${isAdmin}"> 
   <li class="custom-li"><a class="custom-a1"><i class="fa fa-magic" ></i> Comandi Admin</a>    
     <ul class="custom-ul">    
         <li class="custom-li"><a class="custom-a" 
         	href="<c:url value="/admins/userlist" />">
         	<i class="fa fa-users" ></i> Gestisci Utenti</a></li>    
         <li class="custom-li"><a class="custom-a"
         	href="<c:url value="/admins/sublist/" />">
         	<i class="fa fa-id-card" ></i> Gestisci Sub</a></li>    
     </ul>    
  	</li> 
	</c:if>   
	<c:if test="${isAuth}">       
   		<li class="custom-li"><a class="custom-a1" href='#'>    <i class="fa fa-cog" ></i> Opzioni Utente</a>    
    	 <ul class="custom-ul">    
        	 <li class="custom-li"><a class="custom-a" href="<c:url value="/users/myorders" />">
         		<i class="fa fa-shopping-bag" ></i> Storico Ordini</a></li>    
        	 <li class="custom-li"><a class="custom-a" href="<c:url value="/users/view_sub" />">
         		<c:if test="${a eq 0}">
    				<i class="fa fa-address-card" style="color:green;"></i>
    			</c:if> 
    			<c:if test="${a eq 1}">
    				<i class="fa fa-address-card" style="color:default;"></i>
    			</c:if>
    			<c:if test="${a eq 2}">
					<i class="fa fa-address-card" style="color:red;"></i>
				</c:if>
    			Abbonamento</a></li>    
        	<li class="custom-li"><a class="custom-a" href="<c:url value="/users/addresslist" />">
         		<i class="fa fa-building" ></i> I Miei Indirizzi</a>
         	</li>    
    	 </ul>    
  	 </li>  
  	 </c:if>  
	</ul>   

  <c:if test="${! isAuth}"><a href="<c:url value="/login" />"><i class="fa fa-fw fa-user"></i> Login</a></c:if>
  <c:if test="${isAuth}"><a href="<c:url value="/logout" />"><i class="fa fa-sign-out"></i> Logout</a></c:if>

</div>
</div>