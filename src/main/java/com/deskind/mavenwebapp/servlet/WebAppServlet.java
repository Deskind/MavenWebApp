    
package com.deskind.mavenwebapp.servlet;

import chekers.FormCheker;
import com.deskind.mavenwebapp.dao.HibernateStudentDao;
import com.deskind.mavenwebapp.dao.HibernateUniversityDao;
import com.deskind.mavenwebapp.entity.Student;
import com.deskind.mavenwebapp.entity.University;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        //Entitys
        Student student;
        University university;
        //Messages for jsp pages
        String message = null;
        String errorMessage = null;
        //Patterns for resolve user input
        
        //Stores ID of the student entiry
        Long l = null;
        
        switch(inputParameter){
            case "allStudents":
                List<Student> list = hsd.allStudents();
                request.setAttribute("ListOfStudents", list);
                request.getRequestDispatcher("AllStudents.jsp").forward(request, response);
                break;
                
            case "addStudent":
                request.getRequestDispatcher("AddStudent.html").forward(request, response);
                break;
                
            case "addStudentForm":
                
                message = FormCheker.chekFormParameter(request.getParameter("FirstName"), request.getParameter("LastName"));
                if(message.equals("Operation success!")){
                    message = hsd.addStudent(request.getParameter("FirstName"), request.getParameter("LastName"));
                }
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                
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
                message = FormCheker.chekFormParameter(request.getParameter("NewFirstName"));
                if(message.equals("Operation success!")){
                message = hsd.changeFirstName(l, request.getParameter("NewFirstName"));
                }
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                break;
                
            case "EditLastName":
                l = (Long)httpSession.getAttribute("StudentId");
                message = FormCheker.chekFormParameter(request.getParameter("NewLastName"));
                if(message.equals("Operation success!")){
                message = hsd.changeLastName(l, request.getParameter("NewLastName"));
                }
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
                break;
            case "EditStudentUniversity":
                l = (Long)httpSession.getAttribute("StudentId");
                String universityName = (String) request.getParameter("University");
                String test = "Add new university";
                //If matches was not found in select tag
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
                message = FormCheker.chekFormParameter(request.getParameter("UniversityName"));
                if(message.equals("Operation success!")){
                message = (String) hud.addUniversity(request.getParameter("UniversityName"));
                }
                request.setAttribute("Message", message);
                request.getRequestDispatcher("Message.jsp").forward(request, response);
        }
    }
}
