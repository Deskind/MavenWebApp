<%-- 
    Document   : findStudent
    Created on : 26.01.2015, 23:04:07
    Author     : Katya
--%>

<%@page import="java.util.List"%>
<%@page import="com.deskind.mavenwebapp.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<Student> list =(List)request.getAttribute("studentsList");
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <% for(int i = 0; i < list.size(); i++) { %>
        <tr>      
            <td><%=list.get(i).myToString()%></td><br>
        </tr>
    <% } %>
        </h1>
    </body>
</html>
