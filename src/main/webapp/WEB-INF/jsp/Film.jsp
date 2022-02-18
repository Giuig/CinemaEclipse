<%@include file="/WEB-INF/jspf/header.jspf"%>
<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%Film film = (Film)request.getAttribute("film");
List<Genere> listaGeneri=(List<Genere>)request.getAttribute("genere");
utente= (Utente)request.getAttribute("utente");
utente=(Utente)request.getSession().getAttribute("utenteLoggato");
String messaggio = (String)request.getAttribute("filmEliminato"); 

%>
<title><%=film.getTitolo()%> - Cinema Eclipse</title>


	<%@page
		import="java.util.List, com.cinemaeclipse.model.Genere, com.cinemaeclipse.model.Film, com.cinemaeclipse.model.Utente, com.cinemaeclipse.model.Spettacolo"%>

	<div class="container py-5">
		<div class="row">
			<div
				class="col-12 col-lg-6 col-md-6 d-flex align-items-center justify-content-center">
				<%if(messaggio!=null){
	%>
				<h2 class="exo"><%=messaggio %></h2>
				<% }%>
				

				<img id=locandinaFilm src="res/<%=film.getLocandina()%>" width="300"
					height="450">
			</div>
			<div class="col-12 col-lg-6 col-md-6">
				<div class="py-4">
					<img src="res/luna.png" width="35px"> <span id="intestazione"><%=film.getTitolo()%></span>
				</div>
				<div class="text-justify ">
					<p>
						<b>Genere:</b>
						<% for(Genere g : film.getListaGeneri()) {%>
						<%= g.getDescrizione() %>
						<%} %>

						<br> <b>Regista:</b>
						<%=film.getRegista()%>
						<br> <b>Attori:</b>
						<%=film.getAttori()%>
						<br> <b>Trama:</b>
						<%=film.getTrama()%>
						<br> <b>Durata:</b>
						<%=film.getDurata()%>
						<span> minuti</span>
					</p>
				</div>



			<%if(utente!=null){%>
					<div class="text-center">
				<%if(utente.getRuolo().getDescrizione().equals("staff")||utente.getRuolo().getDescrizione().equals("admin")){%>
					<form action="ModificaSpettacoloServlet" method="get">
					<a href="ModificaFilmServlet?id=<%=film.getIdFilm()%>"class="btn btn-lg" id="btn2">Modifica film</a> 
					<a href="EliminaFilmServlet?id=<%=film.getIdFilm()%>" class="btn btn-lg bg-danger" id="btn2">Elimina film</a>
				
				<%} else { %>

						<form action="PrenotaFilmServlet" method="get">
				<%} %>
						
						<div class="row">
						<div class="form-group col-12 pt-3">
						<label for="spettacolo">Scegliere data e ora</label> 
						<select id="spettacolo" class="form-control" name="spettacolo">


				<%for(Spettacolo s : film.getListaSpettacoli()){%>
						<option value=<%=s.getIdSpettacolo()%>><%=s.getData()+", "+ s.getOra() + ", " + s.getSala().getNomeSala() + ", Posti disponibili: " + s.getPostiDisponibili(s) %></option>
				<%}%>
						</select>
						</div>
						</div>
				<%if(utente.getRuolo().getDescrizione().equals("staff")||utente.getRuolo().getDescrizione().equals("admin")){%>
						<a href="AddSpettacoloServlet?id=<%=film.getIdFilm()%>"	class="btn btn-lg" id="btn2">Aggiungi Spettacolo</a>
						<button type="submit" value="" name="" class="btn btn-lg" id="btn2">Modifica spettacolo</button>
				<%}else if (utente.getRuolo().getDescrizione().equals("user")){%>
						<button type="submit" value="" name="" class="btn btn-lg"id="btn1">VAI ALLA PRENOTAZIONE</button>
			<%}%>
						

						
						</form>
			<%} else {%>
						<label for="spettacolo">Scegliere data e ora</label> 
						<select id="spettacolo" class="form-control" name="spettacolo">
			<%for(Spettacolo s : film.getListaSpettacoli()){%>
						<option value=<%=s.getIdSpettacolo()%>><%=s.getData()+", "+ s.getOra() + ", " + s.getSala().getNomeSala() + ", Posti disponibili: " + s.getPostiDisponibili(s) %></option>
				<%}%>
						</select>
						<div class="text-center">
						<a href="LoginServlet" class="btn btn-lg mt-3" id="btn1">Entra e prenota</a>
						</div>
						</div>
						</div>
			
				<%} %>
			</div>




		</div>
	</div>
	</div>
				


	<%@include file="/WEB-INF/jspf/footer.jspf"%>