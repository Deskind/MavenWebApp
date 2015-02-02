
package com.deskind.mavenwebapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Discipline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long disciplineId;
    
    @Basic
    String disciplineName;
    
    @ManyToMany
//    @JoinTable(name = "discipline_teacher", joinColumns = @JoinColumn(name = "disciplineId"), 
//                                            inverseJoinColumns = @JoinColumn(name = "TeacherId"))
    List<Teacher> list = new ArrayList<Teacher>();

    public long getDisciplineId() {
        return disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public List<Teacher> getList() {
        return list;
    }

    public void setDisciplineId(long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public void setList(List<Teacher> list) {
        this.list = list;
    }
   
    
}
