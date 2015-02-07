<%-- 
    Document   : allStudents
    Created on : 03.02.2015, 17:01:57
    Author     : Katya
--%>

<%@page import="com.deskind.mavenwebapp.entity.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Student> list =(List)request.getAttribute("ListOfStudents");
    int i = list.size();
    %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        for(Student st : list){
        Long stId = st.getStudentId();
        %>
        <h1><%= st.toString()%><br></h1>
        <form action="WebAppServlet">
            <input type="hidden" name="formName" value="DelFormFromAllStudents">
            <input type="hidden" name="StudentId" value="<%= stId%>">
            <input type="submit" name ="AllStudentButton" value="DEL">
        </form>
            <form action="WebAppServlet">
            <input type="hidden" name="formName" value="EditFormFromAllStudents">
            <input type="hidden" name="StudentId" value="<%= stId%>">
            <input type="submit" name ="AllStudentButton" value="EDIT">
        </form>
        <% } %>
            
            
            
        
    </body>
</html>
