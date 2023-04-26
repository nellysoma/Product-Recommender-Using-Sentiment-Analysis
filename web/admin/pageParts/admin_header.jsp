<%-- 
    Document   : admin_header
    Created on : May 27, 2018, 10:39:33 AM
    Author     : harmony
--%>

<%
            String sessionId = (String)request.getAttribute("sessionId");
            
            String sessionUserName = (String)request.getAttribute("sessionUserName");
            
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
        <a id="logo-container" href="index.jsp" class="brand-logo">PRODUCT RECOMMENDATION</a>
        <ul class="right hide-on-med-and-down">
          <li><a class="waves-effect waves-light btn" href="/ProRec/AdministratorController?administrator_action=logout">Sign Out</a></li>
        </ul>
        
        <!--Mobile Nav-->
        <ul id="nav-mobile" class="sidenav">
            <li><a class="waves-effect waves-light btn">Sign Out</a></li>            
        </ul>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
      </div>
    </nav>
  </div>
