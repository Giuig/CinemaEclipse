<%@page import="com.cinemaeclipse.model.Utente"%>
<%@page import="com.cinemaeclipse.model.Spettacolo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prenotazione - Cinema Eclipse</title>
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/jspf/navbar.jspf"%>
	<div class="container exo">
	<%String aggiunta=(String)request.getAttribute("aggiunta");
	String message = (String)request.getAttribute("message");
	%>
	<%if(message==null&&aggiunta==null){
	 utente = (Utente)session.getAttribute("utenteLoggato");
     Spettacolo spettacolo = (Spettacolo)request.getAttribute("spettacoloSelezionato"); %>
	
	
	
	<form action="PrenotaFilmServlet" method="post">
	<div class="row">
	<input type="hidden" readonly class="form-control-plaintext" name="spettacoloSelezionato" value="<%=spettacolo.getIdSpettacolo()%>">
	
		<div class="col-lg-6 col-md-6 col-12 py-3">
              <img src="res/luna.png" width="35px">
              <span id="intestazione"> DETTAGLI PRENOTAZIONE</span>
		<p class="pt-3 pippo">
			Film: <%=spettacolo.getFilm().getTitolo() %><br>
			Data: <%=spettacolo.getData()%> <br> Orario: <%=spettacolo.getOra()%> <br> Prezzo: <%=spettacolo.getPrezzo()%> euro
		</p>
		</div>
		
		<div  class="col-lg-6 col-md-6 col-12 py-3 form-group">
              <img src="res/luna.png" width="35px">
              <span id="intestazione"> DETTAGLI UTENTE</span>
		<p class="pt-3 pippo">
			Email: <%=utente.getEmail() %>
		</p>
		
		<label for="nome">Nome</label>
        <input type="text" required class="form-control mb-2" id="nome" name="nome">
        
       
        <label for="cognome">Cognome</label>
        <input type="text" required class="form-control mb-2" id="cognome" name="cognome">
       
        
        <label for=""carta"">Numero Carta</label>
        <input type="number" required class="form-control mb-2" id="carta" name="carta" >
        
        <div class="row">
        
        <div class="form-group col-4">
        <label for="mese">Mese di Scadenza</label>
        <input type="number" required class="form-control mb-2" id="mese" name="mese" >
        </div>
         <div class="form-group col-4">
         <label for="anno">Anno di Scadenza</label>
        <input type="number" required class="form-control mb-2" id="anno" name="anno" >
        </div>
         <div class="form-group col-4">
        <label for="cvv">CVV</label>
        <input type="number" required class="form-control mb-2" id="cvv" name="cvv">
        </div>
        </div>
      

		
 </div>
 <div class="text-center col-12">

 
		<!-- INSERIRE METODO DI PAGAMENTO -->
			 <button type="submit" value="" name="" class="btn btn-lg" id="btn1">Prenota</button>
			 
			 </div>
			 </div>
			<%} %>
			
			
			<%if(aggiunta!=null){ %>
    <h2><%=aggiunta%></h2>
    <%if(message != null){%>
    <h2><%=message%></h2>

    <%} %>
     <%} %>
			

		</form>
</div>	


	<script type="text/javascript">
 
    $(document).ready(function() {
        $("#resetForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                }      
            },
             
            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                }
            }
        });
 
    });
</script>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>