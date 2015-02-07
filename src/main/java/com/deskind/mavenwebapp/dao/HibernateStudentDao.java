
package com.deskind.mavenwebapp.dao;

import com.deskind.mavenwebapp.entity.Student;
import com.deskind.mavenwebapp.entity.University;
import com.deskind.mavenwebapp.servlet.SessionListener;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateStudentDao  implements StudentDao{
    
    Serializable id;

    @Override
    public List<Student> allStudents() {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createCriteria(Student.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String addStudent(String firstName, String lastName) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setStudentFirstName(firstName);
        student.setStudentLastName(lastName);
        session.save(student);
        transaction.commit();
        session.close();
        String message = "Operation success!";
        return message;
    }

    @Override
    public String delStudentById(Long l) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = new Long(l);
        Object persistentInstance = session.get(Student.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
        transaction.commit();
        session.close();
        String message = "Student deleted";
        return message;
    }

    @Override
    public Student findStudent(Long l) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = new Long(l);
        Student student =(Student) session.get(Student.class, id);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public String changeFirstName(Long id, String newFirstName) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = new Long(id);
        Student student =(Student) session.get(Student.class, id);
        student.setStudentFirstName(newFirstName);
        session.save(student);
        transaction.commit();
        session.close();
        return "OK!"; 
    }

    @Override
    public String changeLastName(Long id, String newLastName) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = new Long(id);
        Student student =(Student) session.get(Student.class, id);
        student.setStudentLastName(newLastName);
        session.save(student);
        transaction.commit();
        session.close();
        return "OK!";
    }

    @Override
    public String assignUniversity(Long id, University university) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = new Long(id);
        Student student =(Student) session.get(Student.class, id);
        student.setUniversity(university);
        session.save(student);
        transaction.commit();
        session.close();
        return "OK!";
    }
    
}
