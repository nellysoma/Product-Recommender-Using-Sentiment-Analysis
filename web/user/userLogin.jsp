<%-- 
    Document   : userLogin
    Created on : Jul 11, 2018, 2:56:55 PM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>User Login</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <jsp:include page="pageParts/navbar.jsp"/>
        <div class="row">
            <div class="container">
                <div class="col s12 m5 offset-m3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title"><h5>User Login</h5></span>

                            <form   action="/cfs/UserController?user_action=user_login"  method="post">
                                <div class="row">
                                    <div class="input-field col m12">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input id="icon_prefix" type="text" class="validate" name="username" />
                                        <label for="icon_prefix">User Name</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col m12">
                                        <i class="material-icons prefix">security</i>
                                        <input id="icon_prefix2" type="password" class="validate" name="password"/>
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
                                    <input type="hidden" name="lastLogonForm" value="<%=System.currentTimeMillis()%>"/>
                                    <button class="waves-effect waves-light btn-large" type="submit">Sign In</button>
                                </div>

                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="pageParts/footer.jsp"/>
        <jsp:include page="pageParts/js.jsp" flush="false"/>
    </body>
</html>
