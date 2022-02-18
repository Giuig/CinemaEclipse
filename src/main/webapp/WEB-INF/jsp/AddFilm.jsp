<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Aggiungi Film - Cinema Eclipse</title>

<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%@ page import="com.cinemaeclipse.controller.AddFilmServlet" %>  
<%@page import = "java.util.List, com.cinemaeclipse.model.Genere" %>
<%String aggiunta=(String)request.getAttribute("aggiunta");
String controllaTitolo=(String)request.getAttribute("controllaTitolo");
List<Genere> listaGeneri=(List<Genere>)request.getAttribute("genere");
%>


    <div class="container">
    <%if(aggiunta!=null){ %>
    <h2 class="text-center exo"><%=aggiunta%></h2>
    <%} %>
    <div class="d-flex align-items-center justify-content-center">
    
        <form action="" method="post" class="p-3 my-2" id="admin" enctype="multipart/form-data">
            <div class="row">
                <div class="form-group col-12">
                    <label for="titolo">Titolo</label>
                    <input type="text" required class="form-control
                    <%
					if(controllaTitolo!=null){
					%>
						erroreRegistrazione
					<% 
					}
					%>
                    " id="titolo" name="titolo">
                                            <%
					if(controllaTitolo!=null){
					%>
						<small class="erroreRegistrazione"><%=controllaTitolo%></small>
					<%
					}
					%>
                    
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
                    <input type="text" required class="form-control" id="regista" name="regista">
                </div>
                <div class="form-group col-12">
                    <label for="attori">Attori</label>
                    <textarea required class="form-control" id="attori" name="attori"></textarea>
                </div>
                <div class="form-group col-12">
                    <label for="trama">Trama</label>
                    <textarea required class="form-control" id="trama" name="trama"></textarea>
                </div>
                <div class="form-group col-12">
                <label for="durata">Durata in minuti</label>
                <input type="number" required class="form-control" id="durata" name="durata">
                </div> 
                <div class="form-group col-12">
                    <label for="locandina">Locandina</label>
                    <input type="file" required class="form-control-file" id="locandina" name="locandina">
                </div> 
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-lg" id="btn1">Aggiungi film</button>
            </div>
        </form>
        </div>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>