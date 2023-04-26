<%-- 
    Document   : Error Page
    Created on : Jul 10, 2018, 6:20:46 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="meta.jsp"/>
        <title>Un-Match Password</title>
        <jsp:include page="css.jsp"/>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
            <div class="row" style="padding-top: 50px;">
                <div class="col s12 m6">
                    <div class="card blue-grey darken-1">
                        <div class="card-content white-text">
                            <span class="card-title">Error Page</span>
                            <p><strong>Error!</strong> Check to see that all fields are filled</p>                             
                        </div>
                        <div class="card-action">                            
                            <a  href="#"><< Back to Upload Document</a> 
                        </div>
                    </div>


                </div>
            </div>
               
        <jsp:include page="footer.jsp"/>
        <jsp:include page="js.jsp"/>
        <script>
$(document).ready(function(){
    $('.tabs').tabs();
  });
        </script>
    </body>

</html>
