/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.mavenwebapp.dao;

import com.deskind.mavenwebapp.Discipline;
import com.deskind.mavenwebapp.Student;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author Katya
 */
public interface StudentDao {
    public String addStudent(SessionFactory sessionFactory, String firstName, String lastName);
    public List<Student> selectAllStudents(SessionFactory sessionFactory);
    public Student findStudent(SessionFactory sessionFactory, String firstName, String lastName);
    public String deleteStudent(SessionFactory sessionFactory, String firstName, String lastName);
    public List<Discipline> getAllStudentsDisciplines(SessionFactory sessionFactory, String firstName, String lastName);
    public String changeFirstName(SessionFactory sessionFactory, String firstName, String lastName, String newName);
    public String changeLastName(SessionFactory sessionFactory, String firstName, String lastName, String newName);
    public String assignDiscipline(SessionFactory sessionFactory, String firstName, String lastName, String disciplineName);
}
