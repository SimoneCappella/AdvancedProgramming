
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.bg-img {
  /* The image used */
  background-image: url("/home-health-care/media/banner1.jpg");

  min-height: 380px;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

/* Add styles to the form container */
.container {
  position: absolute;
  right: 0;
  margin: 20px;
  max-width: 300px;
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit button */
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


<div class="bg-img">


<form name='login' class="container" action="<c:url value="/login" />" method='POST'> 
		<div  style="display:inline-block"><h3 style="border-bottom:2px solid #007bff">Login</h3></div>
				 <c:if test="${not empty errorMessage}">
	<div style="color: red ;font-weight: bold; margin-bottom:1%"><lable>${errorMessage}</lable></div>
</c:if>
			<label for="email" style="display:block"><b>Email</b></label>
			<input type='text' placeholder="Inserisci Email" name='username' required/>
		
			<label for="psw"><b>Password</b></label>
   			<input type='password' placeholder="Inserisci Password" name='password' required/>
			
			<input name="submit" type="submit"
				value="submit" class="btn" />
	
</form>
</div>
