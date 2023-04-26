<%-- 
    Document   : user_header
    Created on : Jul 11, 2018, 3:01:38 PM
    Author     : harmony
--%>

<%
    String sessionId = (String) request.getAttribute("sessionId");

    String sessionUserName = (String) request.getAttribute("sessionUserName");

    if (null == sessionUserName) {
        request.setAttribute("Error", "Session has ended.  Please login.");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/adminLogin.jsp");
        rd.forward(request, response);
    }
%>

    <!--Fixed Navbar-->
    <div class="navbar-fixed">
        <nav class="green" role="navigation">
            <div class="nav-wrapper container">
                <a id="logo-container" href="#!" class="brand-logo">PRODUCT RECOMMENDATION</a>
                <ul class="right hide-on-med-and-down">
                    <li><a class="waves-effect waves-light btn" href="/cfs/UserController?user_action=logout">Sign Out</a></li>
                </ul>

                <!--Mobile Nav-->
                <ul id="nav-mobile" class="sidenav">
                    <li><a class="waves-effect waves-light btn">Sign Out</a></li>            
                </ul>
                <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            </div>
        </nav>
    </div>


