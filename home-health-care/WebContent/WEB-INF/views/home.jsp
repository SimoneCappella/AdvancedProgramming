<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.con {
    height: 100%;
}

.hero-image {
  /* Use "linear-gradient" to add a darken background effect to the image (photographer.jpg). This will make the text easier to read */
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/home-health-care/media/banner.jpg");

  /* Set a specific height */
  min-height: 380px;

  /* Position and center the image to scale nicely on all screens */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

/* Place text in the middle of the image */
.hero-text {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}



.hero-text button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 10px 25px;
  color: white;
  background-color: #007bff;
  text-align: center;
  cursor: pointer;
}

.hero-text button:hover {
	background-color: #555;
  	color: white;
}

</style>	
<div class="con">
<div class="hero-image">
  <div class="hero-text">
    <h1>Home Health Care</h1>
    <p>A healthy food for a wealthy mood</p>
    <button  onclick="window.location.href='/home-health-care/itemlist';">Acquista un Prodotto</button>
  </div>
</div>
</div>