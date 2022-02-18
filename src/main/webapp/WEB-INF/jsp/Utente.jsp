<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Profilo - Cinema Eclipse</title>


<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%@page import = "java.util.List, com.cinemaeclipse.model.Prenotazione, com.cinemaeclipse.model.Utente" %>
<%
utente=(Utente)request.getAttribute("utente");
utente=(Utente)request.getSession().getAttribute("utenteLoggato");
List<Prenotazione> listaPrenotazioni=(List<Prenotazione>)request.getAttribute("listaPrenotazioni");
String messaggio = (String)request.getAttribute("cancellazione");%>

<div class="container mt-4">

<h3 class="exo text-white">Welcome <%=utente.getNome()%></h3>
<div class = "row">
<div class="col-6 my-3">
  <img src="res/luna.png" width="35px">
  <span id="intestazione">DATI UTENTE</span>
</div>
<div class="col-6 text-right">
	<form action="LogoutServlet" method="post">
      	<button type="submit" class="btn btn-lg ban" value="" name="id" id="btn1">logout</button>
	</form>
</div>
</div>
<!--  TABELLA CON DATI UTENTE: NOME, COGNOME, MAIL,TELEFONO, DATA DI NASCITA, SESSO
CAMBIA PASSWORD-->
<p>Nome: <%=utente.getNome()%><br>
Cognome: <%=utente.getCognome()%><br>
Email: <%=utente.getEmail()%><br>
Telefono: <%=utente.getTelefono()%><br>
Data di nascita: <%=utente.getDataNascita()%><br>
Sesso: <%=utente.getSesso()%><br>
</p>



<%if (utente.getRuolo().getDescrizione().equals("user")){ %>
<div>
  <img src="res/luna.png" width="35px">
  <span id="intestazione">PRENOTAZIONI ATTIVE</span>
</div>

<div class="table-responsive mt-4">
 <table class="table table-striped exo">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">TITOLO</th>
      <th scope="col">DATA</th>
      <th scope="col">ORA</th>
      <th scope="col">SALA</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
  <%
  int i = 0;
  for(Prenotazione p:listaPrenotazioni){
  i++;%>
    <tr>
      <th scope="row"><%=i%></th>
      <td><%=p.getSpettacolo().getFilm().getTitolo() %></td>
      <td><%=p.getSpettacolo().getData() %></td>
      <td><%=p.getSpettacolo().getOra() %></td>
      <td><%=p.getSpettacolo().getSala().getNomeSala() %></td>
      <td><form action="EliminaPrenotazioneServlet" method="post">
      	<button type="submit" class="btn btn-sm ban" value="<%=p.getIdPrenotazione() %>" name="idPrenotazione" id="btn1">DISDICI</button>
		</form></td>
    </tr>
    <%}%>
  </tbody>
</table>


</div> 
 <%}%>
 
 				<%if(messaggio!=null){
	%>
				<h2 class="exo"><%=messaggio%></h2>
				<% }%>
 
</div> 

<%@include file="/WEB-INF/jspf/footer.jspf"%>