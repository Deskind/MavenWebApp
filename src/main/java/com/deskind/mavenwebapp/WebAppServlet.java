/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.mavenwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

//MAVENWEBAPP SERVLET
public class WebAppServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        Student student;
        String message = "";
        Session hbmSession;
        Transaction transaction;
        List<Student> stList;
        List<Discipline> dscList;
        String value = request.getParameter("formName");
        HttpSession httpSession = request.getSession();
        PrintWriter out = response.getWriter();
        
        switch(value){
            case "addForm":
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Test</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> add student </h1>");
            out.println("<form action=\"WebAppServlet\">");
            out.println("<input type=\"text\" name=\"FirstName\" value=\"FirstName\">");
            out.println("<input type=\"text\" name=\"LastName\" value=\"LastName\">");
            out.println("<input type=\"submit\" value=\"add student\">");
            out.println("<input type=\"hidden\" name=\"formName\" value=\"addStudentForm\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
                break;
                
            case "addStudentForm":
                hbmSession = SessionListener.getSessionFactory().openSession();
                transaction = hbmSession.beginTransaction();
                student = new Student(request.getParameter("FirstName"), request.getParameter("LastName"));
                message = "Student "+student.getStudentFirstName()+ "  "+student.getStudentLastName()+"  successfully added into database!";
                hbmSession.save(student);
                transaction.commit();
                hbmSession.close();
                request.setAttribute("message", message);
                request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                break;
                
                case "delForm":
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet Test</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1> Delete student </h1>");
                    out.println("<form action=\"WebAppServlet\">");
                    out.println("<input type=\"text\" name=\"FirstName\" value=\"FirstName\">");
                    out.println("<input type=\"text\" name=\"LastName\" value=\"LastName\">");
                    out.println("<input type=\"submit\" value=\"Delete student\">");
                    out.println("<input type=\"hidden\" name=\"formName\" value=\"delStudentForm\">");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                        break;

                case "delStudentForm":
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", request.getParameter("FirstName"))).
                                                                add(Restrictions.ilike("studentLastName", request.getParameter("LastName"))).list();
                    if(!stList.isEmpty()&&stList.size()==1){
                    for(Student st : stList){
                        message = "Student "+st.getStudentFirstName()+ " "+st.getStudentLastName()+" sucsessfully deleted";
                        hbmSession.delete(st);
                    }
                    }if(!stList.isEmpty()&&stList.size()>1){
                        message = "Error, some matches was found";
                    }if(stList.isEmpty()){
                        message = "Student not found";
                    }
                    
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    transaction.commit();
                    hbmSession.close();
                    break;
                    
                
                case "getAllForm":
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).setMaxResults(5).list();
                    
                    request.setAttribute("studentsList", stList);
                    request.getRequestDispatcher("showAll.jsp").forward(request, response);
                    
                    transaction.commit();
                    hbmSession.close();
                    break;
                    
                case "findStudentForm":
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", request.getParameter("FirstName"))).
                                                                add(Restrictions.ilike("studentLastName", request.getParameter("LastName"))).list();
                    if(stList.isEmpty()){
                        message = "Student not found";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        Student st = stList.get(0);
                        message = st.myToString();
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }else{
                        message = "Error";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }
                    transaction.commit();
                    hbmSession.close();
                    break;
                    
                case "firstNameChangeForm":
                    String newFirstName = request.getParameter("NewName");
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", request.getParameter("FirstName"))).
                                                                add(Restrictions.ilike("studentLastName", request.getParameter("LastName"))).list();
                    if(stList.isEmpty()){
                        message = "Student not found";
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        for(Student st : stList){
                            message = "First name changed from "+st.getStudentFirstName()+" to " + newFirstName;
                            st.setStudentFirstName(newFirstName);
                        }
                    }else{
                        message = "Error";
                    }
                    transaction.commit();
                    hbmSession.close();
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                    
                case "lastNameChangeForm":
                    String newLastName = request.getParameter("NewName");
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", request.getParameter("FirstName"))).
                                                                add(Restrictions.ilike("studentLastName", request.getParameter("LastName"))).list();
                    if(stList.isEmpty()){
                        message = "Student not found";
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        for(Student st : stList){
                            message = "First name changed from "+st.getStudentFirstName()+" to " + newLastName;
                            st.setStudentLastName(newLastName);
                        }
                    }else{
                        message = "Error";
                    }
                    transaction.commit();
                    hbmSession.close();
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                    
                case "assingDisciplineForm":
                    String disciplineName = request.getParameter("Discipline");
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", request.getParameter("FirstName"))).
                                                                add(Restrictions.ilike("studentLastName", request.getParameter("LastName"))).list();
                    if(stList.isEmpty()){
                        message = "StudentaddDisciplineList not found";
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        for(Student st : stList){
                            st.getDisciplineList().add(new Discipline(disciplineName));
                            message = "Discipline "+disciplineName+" successfully added to "+st.getStudentFirstName();
                        }
                    }else{
                        message = "Error";
                    }
                    transaction.commit();
                    hbmSession.close();
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                    
                case "getDisciplineForm":
                    hbmSession = SessionListener.getSessionFactory().openSession();
                    transaction = hbmSession.beginTransaction();
                    stList = hbmSession.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", request.getParameter("FirstName"))).
                                                                add(Restrictions.ilike("studentLastName", request.getParameter("LastName"))).list();
                    if(stList.isEmpty()){
                        message = "Student not found";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        Student st = stList.get(0);
                        dscList = st.getDisciplineList();
                        request.setAttribute("dscList", dscList);
                        request.getRequestDispatcher("getDisciplines.jsp").forward(request, response);
                    }else{
                        message = "Error";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }
                    transaction.commit();
                    hbmSession.close();
                
                }
                    
}
}
