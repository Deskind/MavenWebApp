
package com.deskind.mavenwebapp.dao;

import com.deskind.mavenwebapp.Discipline;
import com.deskind.mavenwebapp.SessionListener;
import com.deskind.mavenwebapp.Student;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



public class HibernateDaoStudent implements StudentDao{
    
    String message;
    Transaction transaction;
    Session session;
    Student student;
    List<Student> stList;
    List<Discipline> dscList;
    Discipline discipline;

    @Override
    public String addStudent(SessionFactory sessionFactory, String firstName, String lastName) {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                student = new Student();
                student.setStudentFirstName(firstName);
                student.setStudentLastName(lastName);
                message = "Student "+student.getStudentFirstName()+ "  "+student.getStudentLastName()+"  successfully added into database!";
                session.save(student);
                transaction.commit();
                session.close();
                return message;
                }

    @Override
    public List<Student> selectAllStudents(SessionFactory sessionFactory) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).list();
                    transaction.commit();
                    session.close();
                    return stList;
    }

    @Override
    public Student findStudent(SessionFactory sessionFactory, String firstName, String lastName) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", firstName)).
                                                                add(Restrictions.ilike("studentLastName", lastName)).list();
                    if(stList.isEmpty()){
                        student = new Student();
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        student = stList.get(0);
                    }
                    transaction.commit();
                    session.close();
                    return student;
    }

    @Override
    public String deleteStudent(SessionFactory sessionFactory, String firstName, String lastName) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", firstName)).
                                                                add(Restrictions.ilike("studentLastName", lastName)).list();
                    if(!stList.isEmpty()&&stList.size()==1){
                    for(Student st : stList){
                        message = "Student "+st.getStudentFirstName()+ " "+st.getStudentLastName()+" sucsessfully deleted";
                        session.delete(st);
                    }
                    }if(!stList.isEmpty()&&stList.size()>1){
                        message = "Error, some matches was found";
                    }if(stList.isEmpty()){
                        message = "Student not found";
                    }
                    transaction.commit();
                    session.close();   
                    return message;
    }

    @Override
    public String changeFirstName(SessionFactory sessionFactory, String firstName, String lastName, String newName) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", firstName)).
                                                                add(Restrictions.ilike("studentLastName", lastName)).list();
                    if(stList.isEmpty()){
                        message = "Student not found";
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        for(Student st : stList){
                            
                            st.setStudentFirstName(newName);
                            message = "First name chenged from "+ firstName+"    to "+newName;
                        }
                    }else{
                        message = "Error";
                    }
                    transaction.commit();
                    session.close();
                    return message;
    }

    @Override
    public String changeLastName(SessionFactory sessionFactory, String firstName, String lastName, String newName) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", firstName)).
                                                                add(Restrictions.ilike("studentLastName", lastName)).list();
                    if(stList.isEmpty()){
                        message = "Student not found";
                    }else if(!stList.isEmpty()&&stList.size()==1){
                        for(Student st : stList){
                            
                            st.setStudentLastName(newName);
                            message = "Last name changed from "+ lastName+"    to "+newName;
                        }
                    }else{
                        message = "Error";
                    }
                    transaction.commit();
                    session.close();
                    return message;
    }
    
    @Override
    public String assignDiscipline(SessionFactory sessionFactory, String firstName, String lastName, String disciplineName) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", firstName)).
                                                                add(Restrictions.ilike("studentLastName", lastName)).list();
                    
                    if(stList.isEmpty()){
                        message = "Student not found";
                    }else{
                            student = stList.get(0);
                            student.getDisciplineList().add(new Discipline(disciplineName));
                            message = "Discipline "+disciplineName+" successfully added to "+student.getStudentFirstName();
                        }
                    
                    transaction.commit();
                    session.close();
                    return message;
    }
    
    @Override
    public List<Discipline> getAllStudentsDisciplines(SessionFactory sessionFactory, String firstName, String lastName) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    stList = session.createCriteria(Student.class).add(Restrictions.ilike("studentFirstName", firstName)).
                                                                add(Restrictions.ilike("studentLastName", lastName)).list();
                    dscList = stList.get(0).getDisciplineList();
                    session.close();
                    return dscList;
    }
    
}