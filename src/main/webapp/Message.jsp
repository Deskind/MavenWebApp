<%-- 
    Document   : Message
    Created on : 03.02.2015, 21:45:37
    Author     : Katya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String message =(String)request.getAttribute("Message");
        %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= message%></h1><br><br><br>
        <h3><a href="Student.html">Back to student options</a></h3>
    </body>
</html>
