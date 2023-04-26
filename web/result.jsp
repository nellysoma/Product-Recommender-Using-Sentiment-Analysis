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
    <title>Result</title>
    <jsp:include page="css.jsp"/>
</head>
<body>
    <%
        List<String> outputList = (List<String>) request.getAttribute("outputList");
        List<String> bestCategoryList = (List<String>) request.getAttribute("bestCategoryList");
        List<String> reviewList = (List<String>) request.getAttribute("reviewList");
        Iterator<String> iterator1 = outputList.iterator();
        Iterator<String> iterator2 = bestCategoryList.iterator();
        Iterator<String> iterator3 = reviewList.iterator();
        
        int negativeCounter = 0;
        int positiveCounter = 0;
    %>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
        
        <div class="card">
            <span class="card-title">Product Reviews</span>

            <ul class="collection">

                <%
                    while (iterator3.hasNext()) {
                        String reviewList1 = (String) iterator3.next();

                %>

                <li class="collection-item"><%=reviewList1%></li>

                <%
                    }
                %>
            </ul>
        </div>  
            
            
        <div class="card">
        <span class="card-title">Product Reviews</span>
        
            <ul class="collection">
                
                <%
                    while (iterator1.hasNext()) {
                        String outputList1 = (String) iterator1.next();

                %>
                                
                <li class="collection-item"><%=outputList1%></li>
                
                <%
                }
                %>
            </ul>

        </div>           
                <%
                    while (iterator2.hasNext()) {
                        String bestCategoryList1 = (String) iterator2.next();
                        
                        if(bestCategoryList1.equals("NEGATIVE")){
                            
                            negativeCounter++;
                        }
                        else{
                            
                            positiveCounter++;
                        }                       

                %>
                <%
                }
                %>
                
                <%
                    if(negativeCounter > positiveCounter){
                %>
                <div class="card">
                <span class="card-title">Recommendation</span>
                This products has too many negative sentiments, do not buy
                </div>
               <%
                    }
                    else{
               %>
            <div class="card">
                <span class="card-title">Recommendation</span>
                This products has too many positive sentiments, you can buy
                </div>
<%}%>
    </div>
    <jsp:include page="footer.jsp"/>
    <jsp:include page="js.jsp"/>
</body>
</html>



