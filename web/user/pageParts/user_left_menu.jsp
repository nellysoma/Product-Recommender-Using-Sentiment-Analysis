<%-- 
    Document   : user_left_menu
    Created on : Jul 11, 2018, 3:08:55 PM
    Author     : harmony
--%>

<%
    String sessionId = (String) request.getAttribute("sessionId");
    String sessionFirstName = (String) request.getAttribute("sessionFirstName");
    String sessionLastName = (String) request.getAttribute("sessionLastName");
    String sessionUserName = (String) request.getAttribute("sessionUserName");
%>


    <ul id="slide-out" class="sidenav sidenav-fixed">

        <li>
            <div class="user-view">
                <div class="background grey">             
                    <img src="images/user_background.png">
                </div>
                <a href="#user"><img class="circle" src="/cfs/user/user_image.jsp?imgid=<%=sessionUserName%>">                
                <a href="#name"><span class="white-text name"><%=sessionFirstName%>  <%=sessionLastName%></span></a>
                <a href="#email"><span class="white-text email"><%=sessionUserName%></span></a>
            </div>
        </li>


        <ul class="collapsible popout" >

            <li class="active">
                <div class="collapsible-header"><h5><i class="material-icons dp48">build</i>Documents</h5></div>
                <div class="collapsible-body">
                    <div class="collection">
                        <a href="/cfs/UserController?user_action=go_to_upload_document&sessionId=<%=sessionId%>" class="collection-item"><i class="material-icons dp48">create</i>Upload Document</a> 
                        <a href="/cfs/UserController?user_action=go_to_create_new_user&sessionId=<%=sessionId%>" class="collection-item"><i class="material-icons dp48">create</i>Download Document</a>
                        <a href="/cfs/UserController?user_action=go_to_create_new_user&sessionId=<%=sessionId%>" class="collection-item"><i class="material-icons dp48">create</i>Send Document</a>
                    </div>
                </div>
            </li>

            <li>
                <div class="collapsible-header">Profile</div>
                <div class="collapsible-body">
                    <a href="#!" class="collection-item">Add Essential Drug</a>
                </div>
            </li>
                       
        </ul>

    </ul>
    <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>



