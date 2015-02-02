
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
    private Long IniversityId;
    
    @Basic
    String universityName;

    public Long getIniversityId() {
        return IniversityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setIniversityId(Long IniversityId) {
        this.IniversityId = IniversityId;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    
    
    
}
