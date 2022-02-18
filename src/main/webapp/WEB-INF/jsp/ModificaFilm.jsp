<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Modifica Film - Cinema Eclipse</title>
</head>
<body>
<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%@page import = "java.util.List, com.cinemaeclipse.model.Genere, com.cinemaeclipse.model.Film" %>
<%String modifica=(String)request.getAttribute("modifica");
String controllaTitolo=(String)request.getAttribute("controllaTitolo");
List<Genere> listaGeneri=(List<Genere>)request.getAttribute("genere");
Film film = (Film)request.getAttribute("film");
%>


    <div class="container">
    <%if(modifica!=null){ %>
    <h2 class="text-center exo"><%=modifica%></h2>
    <%} %>
    <div class="d-flex align-items-center justify-content-center">
    
        <form action="" method="post" class="p-3 my-2" id="admin">
            <div class="row">
                <div class="form-group col-12">
                    <label for="titolo">Titolo</label>
                    <input type="text" required value="<%=film.getTitolo()%>" class="form-control" id="titolo" name="titolo">
                </div>
                <div class="form-group col-12">
                <label for="genere">Genere</label>
                <div class="form-check">
                <%for(Genere g:listaGeneri){ %>
  
  <label class="checkbox-inline" for="genere">
 <input class="" type="checkbox" value="" id="genere" name="<%= g.getIdGenere()%>"><%= g.getDescrizione()%></label>
  <%} %>
				</div>
                </div>
                <div class="form-group col-12">
                    <label for="regista">Regista</label>
                    <input type="text" required value="<%=film.getRegista()%>" class="form-control" id="regista" name="regista">
                </div>
                <div class="form-group col-12">
                    <label for="attori">Attori</label>
                    <input required value="<%=film.getAttori()%>" class="form-control" id="attori" name="attori">
                </div>
                <div class="form-group col-12">
                    <label for="trama">Trama</label>
                    <input required value="<%=film.getTrama()%>" class="form-control" id="trama" name="trama">
                </div>
                <div class="form-group col-12">
                <label for="durata">Durata in minuti</label>
                <input type="number" required value="<%=film.getDurata()%>" class="form-control" id="durata" name="durata">
                </div> 
                <div class="form-group col-12">
                    <label for="locandina">Locandina</label>
                    <input type="file" required value="<%=film.getLocandina()%>" class="form-control-file" id="locandina" name="locandina">
                </div> 
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-lg" id="btn1">Modifica film</button>
            </div>
            <input type="hidden" name="id_film" value= <%= film.getIdFilm() %>>
        </form>
        </div>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>