<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>OH NO - Cinema Eclipse</title>

<%@include file="/WEB-INF/jspf/navbar.jspf"%>

 <div class="container mt-4 text-center">
 
<h1 class="exo orange"> OH NO, QUESTA PAGINA NON ESISTE! </h1>
<img src="res/404.png" class="w-100">

<form action="HomeServlet" method="post">
<button type="submit" class="btn btn-lg" id="btn1">torna alla home</button>
</form>

</div>



 <%@include file="/WEB-INF/jspf/footer.jspf"%>
