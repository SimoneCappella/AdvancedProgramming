<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}


html {
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

.column {
  float: left;
  width: 25%;
  margin-bottom: 16px;
  padding: 0 8px;
}

@media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}
.container img{
 width: 100%;
    height: auto;

}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.button:hover {
  background-color: #555;
}

</style>

<h1>Chi Siamo</h1>
	<p style="padding:10px">
	
	Uno dei maggiori fenomeni della musica rock e di quella pop contemporanea, ha inizio a Liverpool il 6 luglio 1957, quando i giovanissimi John Lennon e Paul McCartney incrociano i loro destini sul palcoscenico della Woolton Parish Church, nel corso di un concerto rock in cui sono chitarristi in due diversi gruppi musicali. Dalla loro collaborazione nasceranno, tra il 1962 e il 1970, una serie memorabile di successi musicali.

Ai due si aggiunge, nel 1958, un compagno di scuola di Paul, George Harrison. I futuri Beatles, all’epoca si chiamano “The Quarrymen” e propongono una miscela di musica skiffle e rock’n’roll. In quello stesso anno, la vita dell’anticonformista John Lennon viene indelebilmente segnata dalla tragica morte della madre ed è allora che la musica diventa per lui la prima ragione di vita. Sarà proprio Lennon a guidare i futuri Beatles verso il successo. Nel 1959, entra nel gruppo il poco esperto bassista Stuart Sutcliffe e un anno dopo, il batterista Pete Best.

Nel 1960, il gruppo, con il nome di Beatles, va ad Amburgo dove viene creato il look che, dagli abiti al taglio di capelli, entrerà nella storia. Tornati a Liverpool, i Beatles, si esibiscono al “Cavern Club” e la loro fama incomincia a diffondersi. Nel 1961 incidono il loro primo 45 giri, “My Bonnie”, come gruppo spalla del cantante Tony Sheridan. È la loro prima esperienza e vede Paul McCarteny sostituire Stuard Sutcliffe al basso. Nel 1962, i Beatles, fanno un provino alla casa discografica “Decca”, ma il loro potenziale non viene intuito e viene così commesso quello che viene ricordato come uno degli errori più grandi della storia del mercato discografico.
</p>

<img loading="lazy" class="center" title="Beatles: The Abbey Road cover"  src="https://www.vistanet.it/wp-content/uploads/2019/08/Abbey-Road.jpg" alt="The Beatles: The Abbey Road cover" ">
<br>
<h2>Chi siamo veramente</h2>
<br>
<div class="row" style="padding: 0 13% 0 13%">
 <div class="column">
    <div class="card">
      <img src="media/cri.jpg" alt="Cristian" style="width:100%">
      <div class="container">
        <h3>Cristian Colavito</h3>
        <p class="title">Java Developer</p>
        <p>Tante belle cose.</p>
        <p>cristian@hhc.com</p>
        <p><button class="button" onclick="window.open('https://www.youtube.com/watch?v=G1IbRujko-A','name','width=600,height=400')">Contact</button></p>
        </div>
    </div>
  </div>


 <div class="column">
    <div class="card">
      <img src="media/simo.jpg" alt="simone" style="width:100%">
      <div class="container">
        <h3>Simone Cappella</h3>
        <p class="title">Java Developer</p>
        <p>Tante belle cose.</p>
        <p>simone@hhc.com</p>
		<p><button class="button" onclick="window.open('https://www.youtube.com/watch?v=3aNMGWadgWA','name','width=600,height=400')">Contact</button></p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <img src="media/manila.jpg" alt="manila" style="width:100%">
      <div class="container">
        <h3>Manila Cuicchi</h3>
        <p class="title">Java Developer</p>
        <p>Tante belle cose.</p>
        <p>manila@hhc.com</p>
		<p><button class="button" onclick="window.open('https://www.youtube.com/watch?v=X_sVrg1pGX0','name','width=600,height=400')">Contact</button></p>
        </div>
    </div>
  </div>
  
  <div class="column">
    <div class="card">
      <img src="media/gabriele.jpg" alt="gabriele" style="width:100%">
      <div class="container">
        <h3>Gabriele De Bartolomeo</h3>
        <p class="title">Java Developer</p>
        <p>Tante belle cose.</p>
        <p>gabriele@hhc.com</p>
		<p><button class="button" onclick="window.open('https://www.youtube.com/watch?v=DKP16d_WdZM','name','width=600,height=400')">Contact</button></p>
      </div>
    </div>
  </div>
</div>