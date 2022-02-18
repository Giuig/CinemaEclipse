<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>Login - Cinema Eclipse</title>

<%@include file="/WEB-INF/jspf/navbar.jspf"%>
<%
String loginFallita=(String)request.getAttribute("loginFallita");
%>
    <div class="container">
    <div class="d-flex align-items-center justify-content-center">
   
        <form action="LoginServlet" method="post" class="py-5">
            <div class="row">
            
               <br/>
    <h2 class="text-center">${message}</h2>
    <br/>
            
                <div class="form-group col-12">
             
                    <label for="email">Email</label>
                    <input type="email" required class="form-control
                    <%
					if(loginFallita!=null){
					%>
						erroreRegistrazione
					<% 
					}
					%>
                    " id="email" name="email"
                        placeholder="rino.rano@esempio.com">
                </div>
                <div class="form-group col-12">
                    <label for="password">Password</label>
                    <input type="password" required class="form-control
                     <%
					if(loginFallita!=null){
					%>
						erroreRegistrazione
					<% 
					}
					%>
                    " id="password" name="password">
                     <%
					if(loginFallita!=null){
					%>
						<small class="erroreRegistrazione"><%=loginFallita%></small>
					<% 
					}
					%>
                </div>
            </div>
           
            <div class="text-center">
            <a href="SignupServlet" class="btn btn-lg" id="btn2">Signup</a>
             <button type="submit" class="btn btn-lg" id="btn1">Login</button>
            </div>
        </form>
        
        
        </div>
        </div>
        <a href="ForgotPasswordServlet" class="orange exo text-center">Ho dimenticato la password</a>
        
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
