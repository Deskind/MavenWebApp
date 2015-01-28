
package com.deskind.mavenwebapp;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionListener implements HttpSessionListener {
    
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
   
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Exception ex){
            System.out.println("Session creation error");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if(sessionFactory!=null){
            try{
            sessionFactory.close();
            }catch (Exception ex){
                System.out.println("Error in session destroy method");
            }
        }
    }
    
}
