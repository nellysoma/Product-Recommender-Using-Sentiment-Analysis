<%-- 
    Document   : mobile_phones_category
    Created on : Sep 15, 2018, 7:24:55 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="meta.jsp"/>
        <title>Mobile Phones Category</title>
        <jsp:include page="css.jsp"/>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <jsp:include page="mobilePhonesCategoryList.jsp"/>
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
