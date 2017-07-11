package com.DaoClasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;




import com.EntityClasses.Batch_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;
//import com.ServiceClasses.usersService;



@Repository
public class userDaoImpl implements usersDao{
	
	
	public submit addUser1(submit model1) {
        Transaction trns = null;
        
	    String name=model1.getName();
	    String email =model1.getEmail();
        String password=model1.getPassword();
        Integer batch=model1.getBatch();
        String user_type= model1.getUser_type();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
	   
	    User_Info user=new User_Info();
		
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setBatch(batch);
		user.setUser_type(user_type);
		user.setCreated_at(created_at);
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } 
        catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return model1;
        } finally {
            session.flush();
            session.close();
        }
		return model1;
    }

    public boolean addUser2(User_Info user) {
        Transaction trns = null;
        int id=user.getBatch();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
 	   
        try {

            String queryString = "from Batch_Master where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            Batch_Master batch=(Batch_Master)query.uniqueResult();
            int semester =batch.getSemester_id();
            user.setSemester(semester);
            user.setCreated_at(created_at);
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.flush();
            session.close();
        }
		return true;
    }
    
    
    public List<User_Info> getAllUsers() {
        List<User_Info> users= new ArrayList<User_Info>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from User_Info").list();
            System.out.println(users);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return users;
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    
    public retrieve getUserById(retrieve model1) {
       
    	User_Info user = null;
        Transaction trns = null;
        
        Integer id=model1.getId();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from User where user_id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            
            user = (User_Info) query.uniqueResult();
            
            Integer id1=user.getId();
    	    String name=user.getName();
    	    String email =user.getEmail();
    	    
    	    model1.setId(id1);
    	    model1.setName(name);
    	    model1.setSalary(email);
			
        } catch (RuntimeException e) {
            e.printStackTrace();
            return model1;
        } finally {
            session.flush();
            session.close();
        }
        return model1;
    }
    
   
    
    
  public boolean deleteUser(User_Info users) {
    	
    	Integer id1=0;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	id1=users.getId();
        	trns = session.beginTransaction();
            String queryString = "delete from User where user_id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id1); 
            query.executeUpdate();
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
		return true;
		
    }
 
    
  //======= we haven't written any code for the following functions in MainController====================

   
    

    public void updateUser(User_Info user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public static User_Info validate(User_Info user)
    {
    	Transaction trns = null;
        
        String email= user.getEmail();
        String password= user.getPassword();
        
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "from User_Info where email = :email and password = :password";
            System.out.println("After Query");
            Query query = session.createQuery(queryString);
            query.setString("email", email);
            query.setString("password", password);
            
            user = (User_Info) query.uniqueResult();
            System.out.println("Try "+user);
			
        } catch (RuntimeException e) {
        	System.out.println("Exeption "+user);
            e.printStackTrace();
            return user;
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
    


	
    
  //===========================================================  
    
    
}



