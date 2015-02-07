
package com.deskind.mavenwebapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long StudentId;
    
    @OneToOne
    University university;
    
    @Basic
    String studentFirstName;
    
    @Basic
    String studentLastName;
    
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "student_disciplines", joinColumns =  
			@JoinColumn(name = "StudentId"), 
			inverseJoinColumns =  @JoinColumn(name = "disciplineId") )
    List<Discipline> list = new ArrayList<Discipline>();
   
   @Override
   public String toString(){
       String firstName = this.getStudentFirstName();
       String lastName = this.getStudentLastName();
       return firstName+"  "+lastName;
   }

    public Long getStudentId() {
        return StudentId;
    }

    public University getUniversity() {
        return university;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public List<Discipline> getList() {
        return list;
    }

    public void setStudentId(Long StudentId) {
        this.StudentId = StudentId;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setStudentFirstName(String studentName) {
        this.studentFirstName = studentName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setList(List<Discipline> list) {
        this.list = list;
    }

    
}
