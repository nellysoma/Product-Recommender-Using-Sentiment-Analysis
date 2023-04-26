<%-- 
    Document   : training_successful
    Created on : Jul 19, 2018, 5:12:23 PM
    Author     : harmony
--%>
<%
    String sessionId = (String) request.getAttribute("sessionId");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Success Page</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <div class="row">
            <div class="col m3" style="margin-left: 100px;">
                <jsp:include page="pageParts/admin_header.jsp" flush="false"/>
            </div>        
        </div>

        <div class="container">
            <div class="row">
                <div class="col m3">
                    <jsp:include page="pageParts/admin_left_menu.jsp" flush="false"/>
                </div>                    
            </div>
            <div class="col m9" style="margin-left: 100px;">
                <div class="card green lighten-5">
                    <div class="card-content green-text">
                        <span class="card-title">Success</span>
                        <p><strong>Success!</strong> Training Successfully </p>                             
                    </div>
                    <div class="card-action">                            
                        <a  href="/ProRec/AdministratorController?administrator_action=go_to_upload_training_file&sessionId=<%=sessionId%>"><< Back to Upload Training File</a> 
                    </div>
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
