<%-- 
    Document   : forMessage
    Created on : 28.01.2015, 7:02:57
    Author     : Katya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String message =(String)request.getAttribute("message");
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= message%></h1>
    </body>
</html>
