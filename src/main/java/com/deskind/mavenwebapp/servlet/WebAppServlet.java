    
package com.deskind.mavenwebapp.servlet;

import com.deskind.mavenwebapp.dao.HibernateStudentDao;
import com.deskind.mavenwebapp.dao.HibernateUniversityDao;
import com.deskind.mavenwebapp.entity.Student;
import com.deskind.mavenwebapp.entity.University;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//@WebServlet(urlPatterns = "/WebAppServlet")
public class WebAppServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Get session, invoke session listener method sessionCreated()
        HttpSession httpSession = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        
        
        //Read parameter from Student.jsp and Teacher.jsp
        String inputParameter = request.getParameter("formName");
        //Writer for add and delete forms
        PrintWriter out = response.getWriter();
        //Variables
        HibernateStudentDao hsd = new HibernateStudentDao();
        HibernateUniversityDao hud = new HibernateUniversityDao();
        Student student;
        University university;
        String message = null;
        Long l = null;
        
        switch(inputParameter){
            case "allStudents":
                List<Student> list = hsd.allStudents();
                request.setAttribute("ListOfStudents", list);
                request.getRequestDispatcher("AllStudents.jsp").forward(request, response);
                break;
                
            case "addStudent":
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Add student</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Add student</h1>");
                out.println("<form action=\"WebAppServlet\">");
                out.println("<input type=\"text\" name=\"FirstName\" value=\"FirstName\">");
                out.println("<input type=\"text\" name=\"LastName\" value=\"LastName\">");
                out.println("<input type=\"submit\" value=\"Add student\">");
                out.println("<input type=\"hidden\" name=\"formName\" value=\"addStudentForm\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                break;
                
            case "addStudentForm":
                message = hsd.addStudent(request.getParameter("FirstName"), 
                                            request.getParameter("LastName"));
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                break;
                
            case "delStudent":
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Delete student</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Delete student</h1>");
                    out.println("<form action=\"WebAppServlet\">");
                    out.println("<input type=\"text\" name=\"FirstName\" value=\"FirstName\">");
                    out.println("<input type=\"text\" name=\"LastName\" value=\"LastName\">");
                    out.println("<input type=\"submit\" value=\"Delete student\">");
                    out.println("<input type=\"hidden\" name=\"formName\" value=\"delStudentForm\">");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                break;
            case "allTeachers":
                
                break;
            case "addTeacher":
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Add teacher</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Add teacher</h1>");
                out.println("<form action=\"WebAppServlet\">");
                out.println("<input type=\"text\" name=\"FirstName\" value=\"FirstName\">");
                out.println("<input type=\"text\" name=\"LastName\" value=\"LastName\">");
                out.println("<input type=\"submit\" value=\"Add teacher\">");
                out.println("<input type=\"hidden\" name=\"formName\" value=\"addTeacherForm\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                break;
                
            case "addTeacherForm":
                
                break;
                
            case "delTeacher":
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Delete teacher</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Delete teacher</h1>");
                    out.println("<form action=\"WebAppServlet\">");
                    out.println("<input type=\"text\" name=\"FirstName\" value=\"FirstName\">");
                    out.println("<input type=\"text\" name=\"LastName\" value=\"LastName\">");
                    out.println("<input type=\"submit\" value=\"Delete teacher\">");
                    out.println("<input type=\"hidden\" name=\"formName\" value=\"delTeacherForm\">");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                break;
                
            case "DelFormFromAllStudents":                      
                l = Long.parseLong(request.getParameter("StudentId"));
                message = hsd.delStudentById(l);
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                break;
                
            case "EditFormFromAllStudents":
                l = Long.parseLong(request.getParameter("StudentId"));                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
                request.setAttribute("StudentId", l);                                                                                                                                                                                                                                                                                                        
                request.getRequestDispatcher("EditStudent.jsp").forward(request, response);
                out.println("Hello everything is ok!");
             break;
                
            case "EditFirstName":
                l = (Long)httpSession.getAttribute("StudentId");
                String newFirstName = (String) request.getParameter("NewFirstName");
                message = hsd.changeFirstName(l, newFirstName);
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                break;
                
            case "EditLastName":
                l = (Long)httpSession.getAttribute("StudentId");
                String newLastName = (String) request.getParameter("NewLastName");
                message = hsd.changeLastName(l, newLastName);
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                break;
            case "EditStudentUniversity":
                l = (Long)httpSession.getAttribute("StudentId");
                String universityName = (String) request.getParameter("University");
                String test = "Add new university";
                //If matches not found in select tag
                if(universityName.equals(test)){
                    request.getRequestDispatcher("AddDisciplineFromStudentEdit.html").forward(request, response);
                //If mathes was found in select tag
                }else{
                    student = hsd.findStudent(l);
                    university = hud.findUniversity(universityName);
                    message = hsd.assignUniversity(l, university);
                    request.setAttribute("Message", message);
                    request.getRequestDispatcher("Message.jsp").forward(request, response);
            }
                break;
                
            case "AddNewUniversity":
                String newUniversityName = (String) request.getParameter("UniversityName");
                message = (String) hud.addUniversity(newUniversityName);
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
        }
    }
}
