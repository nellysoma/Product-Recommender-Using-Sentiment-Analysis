<%-- 
    Document   : add_room
    Created on : Aug 6, 2018, 5:51:17 PM
    Author     : harmony
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.RoomType"%>
<%@page import="java.util.List"%>
<%@page import="dao.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Room</title>
        <jsp:include page="pageParts/css.jsp" flush="false"/>
    </head>
    <body>
        <%
            String sessionId = (String) request.getAttribute("sessionId");

            DbConnection dbobject = new DbConnection();

            getServletContext().setAttribute("viewRoomType", dbobject.viewRoomType());
            List<RoomType> viewRoomType = (List<RoomType>) application.getAttribute("viewRoomType");
            Iterator<RoomType> iterator = viewRoomType.iterator();
        %>
        <div class="row">
            <div class="col m3" style="margin-left: 100px;">
                <jsp:include page="pageParts/admin_header.jsp" flush="false"/>
            </div>        
        </div>
        <div class="container">
            <div class="row">
                <div class="col m3">
                    <jsp:include page="pageParts/admin_left_menu.jsp" flush="false"/>
                </div>                    
            </div>
            <div class="col m9" style="margin-left: 100px;">
                <form   action="/ProRec/AdministratorController?administrator_action=add_room"  method="post">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Add Room</span>

                            <div class="row">
                                <div class="input-field col m12">
                                    <i class="material-icons prefix">edit</i>
                                    <input type="text" class="validate" name="room_number" >
                                    <label for="icon_prefix">Room Number</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <select name="room_type" id="room_type" class="validate">
                                        <%                                            while (iterator.hasNext()) {
                                                RoomType rt = (RoomType) iterator.next();
                                        %>
                                        <option value="<%=rt.getRoomTypeName()%>"><%=rt.getRoomTypeName()%></option>
                                        <%}%>                                              
                                    </select>
                                    <label>Room Type</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <select name="availability_satus" id="availability_satus" class="validate">
                                        <option value="Available">Available</option>
                                        <option value="Unavailable">Unavailable</option>
                                    </select>
                                </div>       
                            </div> 
                                    
                            <div class="row">
                                <div class="input-field col m12">
                                    <i class="material-icons prefix">edit</i>
                                    <input type="text" class="validate" name="amount" >
                                    <label for="icon_prefix">Amount</label>
                                </div>
                            </div>

                            <div class="card-action"> 
                                <input type="hidden" value="<%=sessionId%>" name="sessionId"/>
                                <center><button class="waves-effect waves-light btn-large" type="submit">Add</button></center>
                            </div>  

                        </div>                       
                    </div>
                </form>   
            </div>    
        </div>
        <jsp:include page="pageParts/js.jsp" flush="false"/>
        <script>
            $(document).ready(function () {
                $('.collapsible').collapsible();
            });

            $(document).ready(function () {
                $('.sidenav').sidenav();
            });

            $(document).ready(function () {
                $('select').formSelect();
            });
        </script>
    </body>
</html>
