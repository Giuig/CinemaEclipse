<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Crea Staff - Cinema Eclipse</title>

<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%String messaggio = (String)request.getAttribute("aggiunta"); 
String email = (String)request.getAttribute("email");
String password = (String)request.getAttribute("password");
utente=(Utente)request.getAttribute("utente");
%>

    <div class="container">
     <%if(messaggio!=null){ %>
    <h2 class="text-center exo"><%=messaggio%> <br>Email: <ins class="text-white"> <%=email%> </ins>  <br> Password: <ins class="text-white"><%=password%> </ins> </h2>
    <%}%>
    <div class="d-flex align-items-center justify-content-center">
   
    
        <form action="CreaStaffServlet" method="post" class="p-3 my-2" id="admin">  
           <div class="row">
                <div class="form-group col-12">
                    <label for="nome">Nome</label>
                    <input type="text" required class="form-control" id="nome" name="nome">
                </div>
                <div class="form-group col-12">
                    <label for="cognome">Cognome</label>
                    <input type="text" required class="form-control" id="cognome" name="cognome">
                </div>
                 <div class="form-group col-12">
                    <label for="telefono">Telefono</label>
                    <input type="tel" required class="form-control" id="telefono" name="telefono">
                </div>
                <div class="form-group col-6">
                    <label for="dataNascita">Data di nascita</label>
                    <input type="date" required class="form-control" id="dataNascita" name="dataNascita">
                </div>
                <div class="form-group col-6">
                    <label for="sesso">Genere</label>
                    <select name="sesso" id="sesso" class="form-control">
                        <option value="nd">Non specificare</option>
                        <option value="uomo">Uomo</option>
                        <option value="donna">Donna</option>
                        <option value="altro">Altro</option>
                    </select>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-lg" id="btn1">Crea staff</button>
            </div>
        </form>
        </div>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
