<%-- 
    Document   : index
    Created on : Jun 23, 2018, 8:11:53 AM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="meta.jsp"/>
        <title>PRODUCT RECOMMENDATION Home</title>
        <jsp:include page="css.jsp"/>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <jsp:include page="homePage.jsp"/> 
        <jsp:include page="categoryList.jsp"/>
        <jsp:include page="footer.jsp"/>
        <jsp:include page="js.jsp"/>
        <script>
$(document).ready(function(){
    $('.collapsible').collapsible();
  });
  
  $(document).ready(function(){
    $('.sidenav').sidenav();
  });
</script>
    </body>
</html>
