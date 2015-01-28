/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.mavenwebapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
@Entity
@Table(name="STUDENT")
public class Student {
    
    public Student(String fName, String lName){
        studentFirstName = fName;
        studentLastName = lName;
    }
    
    public Student(){
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="STUDENT_ID")
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    
    @ElementCollection()
    @JoinTable(name="DISCIPLINE", joinColumns = @JoinColumn(name = "STUDENT_ID"))
       protected List<Discipline> disciplineList = new ArrayList();
    
    public String myToString(){
        return "Student ID: "+this.studentId+"   "+this.getStudentFirstName()+"   "+this.getStudentLastName();
    }

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
    
    

    public int getStudentId() {
        return studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }


    
    
}