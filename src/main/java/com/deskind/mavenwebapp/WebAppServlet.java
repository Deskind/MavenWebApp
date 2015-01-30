
package com.deskind.mavenwebapp;

import com.deskind.mavenwebapp.dao.HibernateDaoStudent;
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

public class WebAppServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HibernateDaoStudent hds;
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

                hds = new HibernateDaoStudent();
                message = hds.addStudent(SessionListener.getSessionFactory(), request.getParameter("FirstName"), request.getParameter("LastName"));
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
                    
                    hds = new HibernateDaoStudent();
                    message = hds.deleteStudent(SessionListener.getSessionFactory(), request.getParameter("FirstName"), request.getParameter("LastName"));
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                
                case "getAllForm":
                    
                    hds = new HibernateDaoStudent();
                    stList = hds.selectAllStudents(SessionListener.getSessionFactory());
                    request.setAttribute("studentsList", stList);
                    request.getRequestDispatcher("showAll.jsp").forward(request, response);
                    
                    break;
                    
                case "findStudentForm":
                  
                    hds = new HibernateDaoStudent();
                    student = hds.findStudent(SessionListener.getSessionFactory(), request.getParameter("FirstName"), request.getParameter("LastName"));
                    if(student.getStudentFirstName()==null&&student.getStudentLastName()==null){
                        message = "Student not found";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }else{
                        message = student.myToString();
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    }
                    break;
                    
                case "firstNameChangeForm":
                   
                    hds = new HibernateDaoStudent();
                    message = hds.changeFirstName(SessionListener.getSessionFactory(), request.getParameter("FirstName"), 
                            request.getParameter("LastName"), request.getParameter("NewName"));
                    
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                    
                case "lastNameChangeForm":

                    hds = new HibernateDaoStudent();
                    message = hds.changeLastName(SessionListener.getSessionFactory(), request.getParameter("FirstName"), request.getParameter("LastName"),
                            request.getParameter("NewName"));
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                    
                case "assingDisciplineForm":

                    hds = new HibernateDaoStudent();
                    message = hds.assignDiscipline(SessionListener.getSessionFactory(), request.getParameter("FirstName"), request.getParameter("LastName"), 
                            request.getParameter("Discipline"));
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("forMessage.jsp").forward(request, response);
                    break;
                    
                case "getDisciplineForm":

                    hds = new HibernateDaoStudent();
                    dscList = hds.getAllStudentsDisciplines(SessionListener.getSessionFactory(), request.getParameter("FirstName"),
                            request.getParameter("LastName"));
                    request.setAttribute("dscList", dscList);
                    request.getRequestDispatcher("getDisciplines.jsp").forward(request, response);
                    break;
                }
                    
}
}
