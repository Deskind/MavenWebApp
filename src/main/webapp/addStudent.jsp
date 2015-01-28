<%-- 
    Document   : addStudent
    Created on : 23.01.2015, 20:25:12
    Author     : Katya
--%>

<%@page import="com.deskind.mavenwebapp.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Student st =(Student)request.getAttribute("student");
    String fName = st.getStudentFirstName();
    String lName = st.getStudentLastName();
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Student <%= fName%> <%= lName%> added into STUDENT table</h1>
    </body>
</html>
