<%-- 
    Document   : EditStudent
    Created on : Feb 5, 2015, 2:55:33 PM
    Author     : deskind
--%>

<%@page import="com.deskind.mavenwebapp.dao.HibernateUniversityDao"%>
<%@page import="com.deskind.mavenwebapp.entity.Discipline"%>
<%@page import="java.util.List"%>
<%@page import="com.deskind.mavenwebapp.entity.University"%>
<%@page import="com.deskind.mavenwebapp.entity.Student"%>
<%@page import="com.deskind.mavenwebapp.dao.HibernateStudentDao"%>
<%@page import="com.deskind.mavenwebapp.servlet.SessionListener"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Long id =(Long) request.getAttribute("StudentId");
    HibernateStudentDao hsd = new HibernateStudentDao();
    HibernateUniversityDao hud = new HibernateUniversityDao();
    Student student = hsd.findStudent(id);
    
    String firstName = student.getStudentFirstName();
    String lastName = student.getStudentLastName();
    University university = student.getUniversity();
    List<Discipline> discipineList = student.getList();
    pageContext.setAttribute("StudentId", id, PageContext.SESSION_SCOPE);
    pageContext.setAttribute("StudentFirstName", firstName, PageContext.SESSION_SCOPE);
    
    List<University> universityList = hud.allUniversities();
    
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="EditFirstName" action="WebAppServlet">
            <%= firstName%>
            <input type="hidden" name="formName" value="EditFirstName">
            <input type="text" name="NewFirstName" value="NewName">
            <input type="submit" value="Change">
        </form>
            
        <form name="EditLastName" action="WebAppServlet">
            <%= lastName%>
            <input type="hidden" name="formName" value="EditLastName">
            <input type="text" name="NewLastName" value="NewName">
            <input type="submit" value="Change">
        </form>
            
        <form name="EditStudentUniversity" action="WebAppServlet">
            
            Current university is: 
            <%
            if(university==null){
            %>
            Not assigned
            <% } else{%>
            <%=university.getUniversityName()%>
            <%}%>
            
            . Change university to:
            
            <input type="hidden" name="formName" value="EditStudentUniversity">
            <select name="University">
                <option selected disabled>Chose the university</option>
                <%for(University universityTemp : universityList){
                    String temp = universityTemp.getUniversityName();%>
                    <option><%= temp%></option>
                <%}%>
                <option>Add new university</option>
                    
            </select>
                <input type="submit" value="Change">
        </form>
    </body>
</html>
