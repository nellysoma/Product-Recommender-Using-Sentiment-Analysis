<%-- 
    Document   : enterOTP
    Created on : Jul 17, 2018, 2:57:31 PM
    Author     : harmony
--%>

<%
    String sessionId = (String) request.getAttribute("sessionId");
    String sessionFirstName = (String) request.getAttribute("sessionFirstName");
    String sessionLastName = (String) request.getAttribute("sessionLastName");
    String sessionUserName = (String) request.getAttribute("sessionUserName");
    String sessionOTP = (String) request.getAttribute("sessionOTP");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pageParts/meta.jsp"/>
        <title>Enter OTP</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <jsp:include page="pageParts/navbar.jsp"/>
        
        <div class="row">
            <div class="container">
                <div class="col s12 m5 offset-m3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title"><h5>Enter OTP</h5></span>
                            <form   action="/cfs/UserController?user_action=compareOTP"  method="post">
                                <div class="row">
                                    <div class="col m12">
                                        <center><h4><strong><%=sessionOTP%></strong></h4></center>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col m12">
                                        <i class="material-icons prefix">security</i>
                                        <input id="icon_prefix2" type="password" class="validate" name="otpValue"/>
                                        <label for="icon_prfix2">OTP</label>
                                    </div>
                                </div>
                                

                                <div class="row center">
                                    <input  type="hidden"  name="sessionFirstName" value="<%=sessionFirstName%>"/>
                                    <input  type="hidden"  name="sessionLastName" value="<%=sessionLastName%>"/>
                                    <input  type="hidden"  name="sessionUserName" value="<%=sessionUserName%>"/>
                                    <input  type="hidden"  name="sessionId" value="<%=sessionId%>"/>
                                    <input  type="hidden"  name="sessionOTP" value="<%=sessionOTP%>"/>
                                    <button class="waves-effect waves-light btn-large" type="submit">Submit</button>
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
