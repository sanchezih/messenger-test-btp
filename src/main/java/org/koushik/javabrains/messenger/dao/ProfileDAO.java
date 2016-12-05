package org.koushik.javabrains.messenger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.koushik.javabrains.messenger.model.Profile;


 
public class ProfileDAO {
    
    public void addProfile(Profile beanp){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addProfile(session,beanp);        
        tx.commit();
        session.close();
    }
    
    private void addProfile(Session session, Profile beanp){
        Profile profile = new Profile();
        
        profile.setProfileName(beanp.getProfileName());
        profile.setFirstName(beanp.getFirstName());
        profile.setLastName(beanp.getLastName());
        profile.setCreated(beanp.getCreated());
        session.save(profile);
    }
    
    public List<Profile> getAllProfiles(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Profile");
        List<Profile> profile =  query.list();
        return profile;
    }
    
    public Profile getProfile(String profileName){
    	Session session = SessionUtil.getSession();
    	Profile profile = (Profile)session.get(Profile.class,profileName);
    	session.close();
    	return profile;
    }
   
    public int deleteProfile(String profileName) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Profile where profileName = :profileName";
        Query query = session.createQuery(hql);
        query.setString("profileName",profileName);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateProfile(String profileName, Profile prf){
         if(profileName == null)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Profile set firstName=:firstName, lastName=:lastName, created=:created where profileName = :profileName";
            Query query = session.createQuery(hql);
            query.setString("profileName",profileName);
            query.setString("profileName",prf.getProfileName());
            query.setString("firstName",prf.getFirstName());
            query.setString("lastName", prf.getLastName());
            query.setDate("created", prf.getCreated());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}