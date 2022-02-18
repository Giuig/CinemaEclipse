<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Signup - Cinema Eclipse</title>


<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%
String utenteDuplicato=(String)request.getAttribute("utenteDuplicato");
String passwordErrata=(String)request.getAttribute("passwordErrata");
String utenteRegistrato=(String)request.getAttribute("utenteRegistrato");
%>
    <div class="container">
    <%if(utenteRegistrato==null){ %>
        <form action="SignupServlet" method="post" class="py-5" id="formRegistrazione">
            <div class="row">
                <div class="form-group col-lg-6 col-md-6 col-12">
                    <label for="nome">Nome</label>
                    <input type="text" required class="form-control" id="nome" name="nome" placeholder="Rino">
                </div>
                <div class="form-group col-lg-6 col-md-6 col-12">
                    <label for="cognome">Cognome</label>
                    <input type="text" required class="form-control" id="cognome" name="cognome" placeholder="Rano">
                </div>

                <div class="form-group col-lg-6 col-md-6 col-12">
                    <label for="telefono">Telefono</label>
                    <input type="tel" required class="form-control" id="telefono" name="telefono">
                </div>
                <div class="form-group col-lg-3 col-md-3 col-6">
                    <label for="dataNascita">Data di nascita</label>
                    <input type="date" required class="form-control" id="dataNascita" name="dataNascita">
                </div>
                <div class="form-group col-lg-3 col-md-3 col-6">
                    <label for="sesso">Genere</label>
                    <select name="sesso" id="sesso" class="form-control">
                        <option value="nd">Non specificare</option>
                        <option value="uomo">Uomo</option>
                        <option value="donna">Donna</option>
                        <option value="altro">Altro</option>
                    </select>
                </div>

                <div class="form-group col-lg-6 col-md-6 col-12">
                    <label for="email">Email</label>
                    <input type="email" required class="form-control
                   <%
					if(utenteDuplicato!=null){
					%>
						erroreRegistrazione
					<% 
					}
					%>
                    " id="email" name="email"
                        placeholder="rino.rano@esempio.com">
                        <%
					if(utenteDuplicato!=null){
					%>
						<small class="erroreRegistrazione"><%=utenteDuplicato%></small>
					<% 
					}
					%>
                </div>
                <div class="form-group col-lg-6 col-md-6 col-12">
                    <label for="password">Password</label>
                    <input type="password" required class="form-control
                    <%
					if(passwordErrata!=null){
					%>
						erroreRegistrazione
					<% 
					}
					%>
                    " id="password" name="password">
                     <small 
                     <%
					if(passwordErrata!=null){
					%>
						class="erroreRegistrazione"
					<% 
					}
					%>
                     >La password deve contenere almeno 8 caratteri, almeno 1 numero, almeno 1 carattere speciale, almeno 1 maiuscola</small>
                </div>
            </div>
            <div class="text-right">
                <button type="submit" class="btn btn-lg" id="btn1">Signup</button>
            </div>
        </form>
        <%}
    else{%>
    <form action="LoginServlet" method="get" class="py-5" id="formRegistrazione">
    <h2 class="text-center exo"><%=utenteRegistrato%></h2>
    <div class="text-center">
                <button type="submit" class="btn btn-lg" id="btn1">Accedi</button>
            </div>
            </form>
    <%} %>
    
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
