<%-- 
    Document   : admin_left_menu
    Created on : May 27, 2018, 9:33:59 AM
    Author     : harmony
--%>

<%
    String sessionId = (String) request.getAttribute("sessionId");
    String sessionFirstName = (String) request.getAttribute("sessionFirstName");
    String sessionLastName = (String) request.getAttribute("sessionLastName");
    String sessionUserName = (String) request.getAttribute("sessionUserName");
%>

<div class="col s3 m3">
    <ul id="slide-out" class="sidenav sidenav-fixed">

        <li>
            <div class="user-view">
                <div class="background grey">
                    <img src="images/mum.jpg">
                </div>
                <a href="#user"><img class="circle" src="images/dummy_passport.jpg"></a>
                <a href="#name"><span class="white-text name"><%=sessionFirstName%>  <%=sessionLastName%></span></a>
                <!--<a href="#email"><span class="white-text email">jdandturk@gmail.com</span></a>-->
            </div>
        </li>


        <ul class="collapsible popout" >

            <li class="active">
                <div class="collapsible-header"><h5><i class="material-icons dp48">build</i>System Setup</h5></div>
                <div class="collapsible-body">
                    <div class="collection">
                        <a href="/ProRec/AdministratorController?administrator_action=go_to_upload_training_file&sessionId=<%=sessionId%>" class="collection-item"><i class="material-icons dp48">create</i>Upload Training File</a>  
                        
                    </div>
                </div>
            </li>

            
            
            
        </ul>

    </ul>
    <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>

</div>
