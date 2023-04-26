<%-- 
    Document   : adminLogin
    Created on : May 27, 2018, 9:05:12 AM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Admin Login</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <jsp:include page="pageParts/navbar.jsp" flush="false"/>
    <body>
        <div class="row">
            <div class="container">
                <div class="col s12 m5 offset-m3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title"><h5>Administrator Login</h5></span>
                            
                                <form   action="/ProRec/AdministratorController?administrator_action=administrator_login"  method="post">
                                    <div class="row">
                                        <div class="input-field col m12">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input id="icon_prefix" type="text" class="validate" name="username" >
                                            <label for="icon_prefix">User Name</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col m12">
                                            <i class="material-icons prefix">security</i>
                                            <input id="icon_prefix2" type="password" class="validate" name="password">
                                            <label for="icon_prfix2">Password</label>
                                        </div>
                                    </div>
                                    <div class="row center">
                                        <div class="switch">
                                            <label>
                                                Don't
                                                <input type="checkbox">
                                                <span class="lever"></span>
                                                Remember me
                                            </label>
                                        </div>
                                    </div>
                                    
                                    <div class="row center">                                       
                                        <button class="waves-effect waves-light btn-large" type="submit">Sign In</button>
                                    </div>
                                    
                                </form>
                            
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="pageParts/footer.jsp" flush="false"/>
        <jsp:include page="pageParts/js.jsp" flush="false"/>
    </body>
</html>
