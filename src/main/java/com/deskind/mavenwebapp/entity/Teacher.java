
package com.deskind.mavenwebapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Teacher implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TeacherId;
    
    @Basic
    String teacherName;
    
    @OneToMany
    List<Discipline> list = new ArrayList<Discipline>();
    
    @OneToOne
    University university;

    public Long getTeacherId() {
        return TeacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public University getUniversity() {
        return university;
    }

    public List<Discipline> getList() {
        return list;
    }

    public void setTeacherId(Long TeacherId) {
        this.TeacherId = TeacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setList(List<Discipline> list) {
        this.list = list;
    }
    
}
