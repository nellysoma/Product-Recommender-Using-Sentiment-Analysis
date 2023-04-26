<%-- 
    Document   : administrator_dashboard
    Created on : May 27, 2018, 10:59:59 AM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Administrator Dashboard</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    
    <body>
        <div class="row">
            <div class="col m3" style="padding-left: 100px;">
                <jsp:include page="pageParts/admin_header.jsp" flush="false"/>
            </div>        
        </div>
        <div class="container">
            <div class="row">
                <div class="col m3">
                    <jsp:include page="pageParts/admin_left_menu.jsp" flush="false"/>
                </div>
                
                <div class="col m9">
                    
                </div>
            </div>
        </div>
     <jsp:include page="pageParts/js.jsp" flush="false"/>
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
