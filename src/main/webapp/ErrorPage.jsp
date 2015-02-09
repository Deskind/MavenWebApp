<%-- 
    Document   : userInputErrorPage
    Created on : 09.02.2015, 11:17:24
    Author     : Katya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String errorMessage = (String) request.getAttribute("userInputErrorMessage");
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= errorMessage%></h1><br><br><br>
        <h3><a href="Student.html">Back to student options</a></h3>
    </body>
</html>
