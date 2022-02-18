<%@page import="com.cinemaeclipse.model.Spettacolo"%>
<%@page import="com.cinemaeclipse.model.Sala"%>
<%@page import="com.cinemaeclipse.model.Film"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Aggiungi Spettacolo - Cinema Eclipse</title>
</head>
<body>
<%@include file="/WEB-INF/jspf/navbar.jspf"%>

<%List<Sala> sala = (List<Sala>)request.getAttribute("sala");
String messaggio = (String)request.getAttribute("modifica");
Spettacolo spettacolo = (Spettacolo)request.getAttribute("spettacolo");
String messaggio1 = (String)request.getAttribute("eliminato");

%>
	 <div class="container">
    <%if(messaggio!=null){ %>
    <h2 class="text-center exo"><%=messaggio%></h2>
    <%}%>
    <%if(messaggio1!=null){ %>
    <h2 class="text-center exo"><%=messaggio1%></h2>
    <%}%>
    <div class="d-flex align-items-center justify-content-center">
        <form action="ModificaSpettacoloServlet" method="post" class="p-3 my-2" id="admin">
            <div class="row">
                <div class="form-group col-12">
                 <label for="film">Spettacolo da modificare:</label>
                 <input type="hidden" value="<%=spettacolo.getFilm().getIdFilm()%>" name="id"><h2 class="exo"><%=spettacolo.getFilm().getTitolo()%> - <%=spettacolo.getSala().getNomeSala() %> - <%=spettacolo.getData()%> - <%=spettacolo.getOra()%></h2> 
                 <input type="hidden" value="<%=spettacolo.getIdSpettacolo()%>" name="idSpettacolo">
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
            <a href="EliminaSpettacoloServlet?id=<%=spettacolo.getIdSpettacolo()%>" class="btn btn-lg bg-danger" id="btn2">Elimina spettacolo</a>
                <button type="submit" class="btn btn-lg" id="btn1">Modifica spettacolo</button>
            </div>
        </form>
        </div>
        </div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>