<%@include file="/WEB-INF/jspf/header.jspf" %>

    <title>Home - Cinema Eclipse</title>

 <%@include file="/WEB-INF/jspf/navbar.jspf" %>
 <%@page import = "java.util.List, com.cinemaeclipse.model.Film, com.cinemaeclipse.model.Utente" %>
<%List<Film> listaFilm = (List<Film>)request.getAttribute("listaFilm");
      utente=(Utente)request.getSession().getAttribute("utenteLoggato");
      String messaggio = (String)request.getAttribute("filmEliminato"); %>
      <div class="container">
      <%if(utente!=null){ 
    	  if(utente.getRuolo().getDescrizione().equals("admin")){%>
      <a href="ListaUtentiServlet" class="btn btn-lg" id="btn2">Lista Utenti</a>
      <a href="CreaStaffServlet" class="btn btn-lg" id="btn2">Crea Staff</a>
      <%}} %>
      <%if(messaggio!=null){
	%>
	<h2 class="exo"><%=messaggio %></h2>
<% }%>
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
              <ol class="carousel-indicators">
                  <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
              </ol>
              <div class="carousel-inner">
                  <div class="carousel-item active">
                      <img src="./res/Carosello1.png" class="w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                      <img src="./res/Carosello2.png" class="w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                      <img src="./res/Carosello3.png" class="w-100" alt="...">
                  </div>
              </div>
              <button class="carousel-control-prev" type="button" data-target="#carouselExampleIndicators"
                  data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
              </button>
              <button class="carousel-control-next" type="button" data-target="#carouselExampleIndicators"
                  data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
              </button>
          </div>

          <div class="pb-2">
              <img src="res/luna.png" width="35px">
              <span id="intestazione">FILM IN SALA</span>
          </div>



          <div class="container px-5">
              <div class="row">
              <%if(utente!=null){
            	  %>
              
              <%if(utente.getRuolo().getDescrizione().equals("staff")||utente.getRuolo().getDescrizione().equals("admin")){%>
              <div class="col-lg-3 col-md-4 col-6 d-block">
                      <a href="AddFilmServlet">
                          <img class="shadow w-100 locandina" src="res/aggiungi.png" alt="">
                      </a>
                      <legend class="pt-2">Aggiungi Film</legend>
                  </div>
                  <%} }%>
				<%
				for(Film f:listaFilm){
				%>
				<div class="col-lg-3 col-md-4 col-6 d-block">
				<a href="FilmServlet?id=<%=f.getIdFilm()%>">
				<img class="shadow w-100 locandina" src="res/<%=f.getLocandina()%>" alt="<%=f.getTitolo()%>">
				</a>
                      <!--  <img class="shadow w-100 h-75 locandina" src="res/<%=f.getLocandina()%>" alt="">
                      <form action="FilmServlet" method="post">
                       <input type="hidden" name="id" value="<%=f.getIdFilm()%>">
                        <button type="submit" id="btn1" class="btn btn-sm"><%=f.getTitolo()%></button>
                         </form>-->
                      <legend class="pt-2"><%=f.getTitolo()%></legend>
                  </div>
				<%
				}
				%>
              </div>
          </div>
      </div>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>