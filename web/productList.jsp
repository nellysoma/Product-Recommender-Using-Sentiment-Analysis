<%-- 
    Document   : result
    Created on : Sep 16, 2018, 2:34:33 PM
    Author     : harmony
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <jsp:include page="meta.jsp"/>
    <title>Product List</title>
    <jsp:include page="css.jsp"/>
</head>
<body>
    <%
        List<String> productList = (List<String>) request.getAttribute("productList");
        
        Iterator<String> iterator1 = productList.iterator();
        
        
        int negativeCounter = 0;
        int positiveCounter = 0;
    %>
    <jsp:include page="navbar.jsp"/>
    
    <div class="container">
        <div class="card">
        <span class="card-title">Product Reviews</span>
        
            <ul class="collection">
                
                <%
                    while (iterator1.hasNext()) {
                        String productList1 = (String) iterator1.next();

                %>
                                
                <li class="collection-item"><a href="/ProRec1/UserController?user_action=getReviews&productURL=<%=productList1%>"><%=productList1%></a></li>
                
                <%
                }
                %>
            </ul>

        </div>           
               
    </div>
    <jsp:include page="footer.jsp"/>
    <jsp:include page="js.jsp"/>
</body>
</html>



