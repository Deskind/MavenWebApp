/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.mavenwebapp.dao;

import com.deskind.mavenwebapp.entity.Student;
import com.deskind.mavenwebapp.entity.University;
import com.deskind.mavenwebapp.servlet.SessionListener;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author deskind
 */
public interface UniversityDao {
    
    public List<University> allUniversities();
    public String addUniversity(String name);
    public University findUniversity(String name);
        
}
