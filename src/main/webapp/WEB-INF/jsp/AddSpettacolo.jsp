<%@page import="com.cinemaeclipse.model.Sala"%>
<%@page import="com.cinemaeclipse.model.Film"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Aggiungi Spettacolo - Cinema Eclipse</title>

<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%Film film = (Film)request.getAttribute("film"); %>
<%List<Sala> sala = (List<Sala>)request.getAttribute("sala");
String messaggio = (String)request.getAttribute("aggiunta");
%>
	 <div class="container">
    <%if(messaggio!=null){ %>
    <h2 class="text-center exo"><%=messaggio%></h2>
    <%}%>
    <div class="d-flex align-items-center justify-content-center">
        <form action="AddSpettacoloServlet" method="post" class="p-3 my-2" id="admin">
            <div class="row">
                <div class="form-group col-12">
                 <label for="film">Spettacolo da aggiungere in programmazione relativo al film:</label>
                 <h2 class="exo"><%= film.getTitolo() %></h2>
                </div>
                
                <div class="form-group col-4"> 
                <label for="sala">Scegliere sala</label>
                 <select id="sala" class="form-control" name="sala">
                 <%
					for(Sala s : sala) {
				%>
						<option value= "<%= s.getIdSala() %>"><%= s.getNomeSala() %></option>
				<%
					}
				%>	
                 </select>
                </div>
                 
                <div class="form-group col-4">
                <label for="dataSpettacolo">Data spettacolo</label>
                <input type="date" required class="form-control" id="dataSpettacolo" name="dataSpettacolo">
                </div>
                
                <div class="form-group col-4">
                <label for="orario">Orario inizio</label>
                <input type="time" required class="form-control" id="orario" name="orario">
                </div>
                
            </div>
            <div class="text-center">
            	<input type="hidden" value="<%=film.getIdFilm()%>" name="film">
                <button type="submit" class="btn btn-lg" id="btn1">Aggiungi spettacolo</button>
            </div>
        </form>
        </div>
        </div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>