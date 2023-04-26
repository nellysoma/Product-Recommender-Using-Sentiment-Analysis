<%-- 
    Document   : user_dashboard
    Created on : Jul 16, 2018, 2:40:57 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>User Dashboard</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>          
        <div class="container-fluid">              
            <div class="row">
                <div class="col m3">
                    <jsp:include page="pageParts/user_left_menu.jsp" flush="false"/>
                </div>                
                <div class="col m9" style="padding-left: 0px; margin-left: -150px;">
                    <jsp:include page="pageParts/user_header.jsp" flush="false"/>
                </div>
            </div>
        </div>
        <jsp:include page="pageParts/js.jsp" flush="false"/>
        <script>
            $(document).ready(function () {
                $('.collapsible').collapsible();
            });

            $(document).ready(function () {
                $('.sidenav').sidenav();
            });
        </script>
    </body>
</html>
