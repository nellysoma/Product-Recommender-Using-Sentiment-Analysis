<%-- 
    Document   : enterProductDetails
    Created on : Sep 16, 2018, 3:13:39 AM
    Author     : harmony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="meta.jsp"/>
        <title>Product Details Form</title>
        <jsp:include page="css.jsp"/>
    </head>
    <body>
        <%
            String phoneType =  (String)request.getAttribute("phoneType");
        %>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
<!--            <form   action="/ProRec1/UserController?user_action=get_recomendation"  method="post">-->
            <form   action="/ProRec1/UserController?user_action=listAvailableProducts"  method="post">
            <div class="card">
                <span class="card-title">Enter Product Details</span>
                
                <div class="row">
                    <div class="input-field col m12">
                        <i class="material-icons prefix">edit</i>
                        <input type="text" class="validate" name="smartPhoneBrand" >
                        <label for="icon_prefix">Product Brand</label>
                    </div>
                </div>
                
<!--                <div class="row">
                    <div class="input-field col m12">
                        <i class="material-icons prefix">edit</i>
                        <input type="text" class="validate" name="brandType" >
                        <label for="icon_prefix">Brand Type</label>
                    </div>
                </div>-->
                
<!--                <div class="row">
                    <div class="input-field col m12">
                        <i class="material-icons prefix">edit</i>
                        <input type="text" class="validate" name="model" >
                        <label for="icon_prefix">Model</label>
                    </div>
                </div>-->
                
                <div class="card-action"> 
                    <input type="hidden" value="<%=phoneType%>" name="phoneType"/>
                    <center><button class="waves-effect waves-light btn-large" type="submit">Continue>></button></center>
                </div> 
            </div>
        </form>
        </div>
        
        <jsp:include page="footer.jsp"/>
        <jsp:include page="js.jsp"/>
    </body>
</html>
