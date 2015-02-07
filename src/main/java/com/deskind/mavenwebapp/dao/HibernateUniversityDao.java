    
package com.deskind.mavenwebapp.dao;

import com.deskind.mavenwebapp.entity.University;
import com.deskind.mavenwebapp.servlet.SessionListener;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class HibernateUniversityDao implements UniversityDao{
    
    Serializable id;
    List<University> list;
   
    public String addUniversity(String name){
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        University university = new University();
        university.setUniversityName(name);
        session.save(university);
        transaction.commit();
        session.close();
        String message = "Operation success!";
        return message;
    }

    @Override
    public University findUniversity(String name) {
        SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(University.class);
        list = (List) criteria.add(Restrictions.ilike("universityName", name)).list();
        transaction.commit();
        session.close();
        return list.get(0);
    }

    @Override
    public List<University> allUniversities() {
    SessionFactory sessionFactory = SessionListener.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(University.class).list();
        transaction.commit();
        session.close();
        return list;
    }
    
}
