
package com.deskind.mavenwebapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class University implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UniversityId;
    
    @Basic
    String universityName;
    
    public University(){
        
    }
    
    @Override
    public String toString(){
        return this.getUniversityName();
    }
    
    public University(String universityName){
        this.universityName = universityName;
    }

    public Long getIniversityId() {
        return UniversityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setIniversityId(Long IniversityId) {
        this.UniversityId = IniversityId;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    
    
    
}
