<%@include file="/WEB-INF/jspf/header.jspf" %>

    <title>Lista Utenti - Cinema Eclipse</title>

 <%@include file="/WEB-INF/jspf/navbar.jspf" %>
  <%@page import = "java.util.List, com.cinemaeclipse.model.Utente" %>
<%List<Utente> listaUtenti = (List<Utente>)request.getAttribute("listaUtenti");%>
 <div class="container table-responsive mt-4">
 <table class="table table-striped exo">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">NOME</th>
      <th scope="col">COGNOME</th>
      <th scope="col">EMAIL</th>
      <th scope="col">RUOLO</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
  
  <%
  int i = 0;
  for(Utente u:listaUtenti){
  i++;%>
  <%if (u.getRuolo().getDescrizione().equals("user") || u.getRuolo().getDescrizione().equals("bannato")) {%>
    <tr>
      <th scope="row"><%=i%></th>
      
      <td><%=u.getNome() %></td>
      <td><%=u.getCognome()%></td>
      <td><%=u.getEmail()%></td>
      <td><%=u.getRuolo().getDescrizione()%></td>
      
      
      <td><form action="ListaUtentiServlet" method="post">
      	<button type="submit" class="btn btn-sm ban" value="<%=u.getIdUtente()%>" name="id" id="btn1">BAN/UNBAN</button>
		</form></td>
		<%} %>

    </tr>
    <%}%>
  </tbody>
</table>
</div> 
 
 <%@include file="/WEB-INF/jspf/footer.jspf"%>