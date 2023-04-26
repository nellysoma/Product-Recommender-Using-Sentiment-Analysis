<%-- 
    Document   : add_new_user
    Created on : May 29, 2018, 3:36:12 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Add New User</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <%
            String sessionId = (String) request.getAttribute("sessionId");

        %>
        <jsp:include page="pageParts/admin_header.jsp" flush="false"/>
        <div class="container">
            <div class="row">
                <jsp:include page="pageParts/admin_left_menu.jsp" flush="false"/>
                <div class="col m9">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title activator grey-text text-darken-4">Add New User</span>
                        </div>
                    </div>
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
