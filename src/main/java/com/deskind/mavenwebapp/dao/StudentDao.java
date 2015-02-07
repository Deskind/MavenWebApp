package com.deskind.mavenwebapp.dao;

import com.deskind.mavenwebapp.entity.Student;
import com.deskind.mavenwebapp.entity.University;
import java.util.List;

public interface StudentDao {
    public List<Student> allStudents();
    public String addStudent(String firstName, String lastName);
    public String delStudentById(Long l);
    public Student findStudent(Long l);
    public String changeFirstName(Long id, String newFirstName);
    public String changeLastName(Long id, String newLastName);
    public String assignUniversity(Long id, University university);
    
}
