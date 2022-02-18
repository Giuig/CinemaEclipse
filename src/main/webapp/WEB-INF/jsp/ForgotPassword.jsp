<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="/WEB-INF/jspf/header.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reset Password</title>
    <link rel="stylesheet" href="/css/style.css" >
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jspf/navbar.jspf"%>
     

     <br/>
    <h2 class="text-center text-danger">${message}</h2>
    <br/>

             <div class="container">
               <h2 class="text-center">Resetta la tua password</h2>
        <p class="text-center">
        Inserirsci la mail, ti invieremo una nuova password casuale alla tua casella di posta
        </p>
    <div class="d-flex align-items-center justify-content-center">
      
         <div class="form-group">
        <form id="resetForm" action="reset_password" method="post" >
        
        <label for="email">Email</label>
            <input type="text" name="email" id="email" class="form-control mb-4">
             <div class="text-center">
             <button type="submit" class="btn btn-lg" id="btn1">Inviami una nuova password</button>
             </div>
        </form>
    </div>    
    </div>   
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