
package com.deskind.mavenwebapp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name="DISCIPLINE")
public class Discipline {
    
    String disciplineName;
    
    public Discipline(){
        
    }
    
    public Discipline(String name){
        disciplineName = name;
    }
    
    public String myToString(){
        return this.getDisciplineName();
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }
    
    
}
