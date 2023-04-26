<%-- 
    Document   : upload_training_file
    Created on : Jul 18, 2018, 10:30:22 PM
    Author     : harmony
--%>


<%@page import="dao.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Upload Training File</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <%
            String sessionId = (String) request.getAttribute("sessionId");
        %>
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
                <form   action="/ProRec/AdministratorController?administrator_action=upload_train_file"  method="post" enctype="multipart/form-data">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Upload Training File</span>
                            
                            <div class="file-field input-field col s12 m12">
                                    <div class="btn">
                                        <span>Training File</span>
                                        <input type="file" name="train_file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input id="cac_certificate" class="file-path validate" type="text" placeholder="Upload Training File">
                                    </div>
                                </div>
                                                   
                        </div>
                        <div class="card-action"> 
                            <input type="hidden" value="<%=sessionId%>" name="sessionId"/>
                            <center><button class="waves-effect waves-light btn-large" type="submit">UPLOAD AND TRAIN</button></center>
                        </div>                
                    </div>
                </form>
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

        $(document).ready(function () {
            $('select').formSelect();
        });
    </script>
</body>
</html>
