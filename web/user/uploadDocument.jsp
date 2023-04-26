<%-- 
    Document   : uploadDocument
    Created on : Aug 1, 2018, 6:40:44 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Upload Document</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <%
            String sessionId = (String) request.getAttribute("sessionId");
        %>
        <div class="row">
            <div class="col m3" style="margin-left: 100px;">
                <jsp:include page="pageParts/user_header.jsp" flush="false"/>
            </div>        
        </div>
        <div class="container">
             <div class="row">
                <div class="col m3">
                    <jsp:include page="pageParts/user_left_menu.jsp" flush="false"/>
                </div>                    
            </div>  
            <div class="col m9" style="margin-left: 100px;"> 
                <form enctype="multipart/form-data"  action="/cfs/UserController?user_action=upload_document"  method="post">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Upload Document</span>
                            
                            <div class="input-field col s12 m12">
                                <i class="material-icons prefix">edit</i>
                                <input id="document_title" type="text" class="validate" name="document_title" data-length="100">
                                <label for="document_title">Document Title</label>
                            </div>
                            
                            <div class="file-field input-field col s12 m12">
                                <div class="btn">
                                    <span>Upload Document</span>
                                    <input type="file" name="document">
                                </div>
                                <div class="file-path-wrapper">
                                    <input id="document" class="file-path validate" type="text" placeholder="Upload Document">
                                </div>
                            </div>
                        </div>
                        <div class="card-action"> 
                            <input type="hidden" value="<%=sessionId%>" name="sessionId"/>
                            <center><a class="waves-effect waves-light btn-large" type="submit">Upload</a></center>
                        </div>  
                    </div>
                </form>
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
