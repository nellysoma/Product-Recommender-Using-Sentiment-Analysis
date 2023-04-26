<%-- 
    Document   : phones_and_tablet_category
    Created on : Sep 15, 2018, 7:15:59 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="meta.jsp"/>
        <title>Phones and Tablets</title>
        <jsp:include page="css.jsp"/>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <jsp:include page="phonesAndTabletCategoryList.jsp"/>
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
