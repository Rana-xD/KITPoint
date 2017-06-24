package com.DaoClasses;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.EntityClasses.Login;
import com.EntityClasses.User;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;
import com.ServiceClasses.usersService;



@Repository
public class userDaoImpl implements usersDao{
	
	
	public submit addUser1(submit model1) {
        Transaction trns = null;
        
        Integer id=model1.getId();
	    String name=model1.getName();
	    String salary =model1.getSalary();
	   
	    User user=new User();
		user.setUser_id(id);
		user.setUser_name(name);
		user.setUser_salary(salary);
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
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

    public boolean addUser2(User user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
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
    
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from User").list();
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
       
    	User user = null;
        Transaction trns = null;
        
        Integer id=model1.getId();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from User where user_id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            
            user = (User) query.uniqueResult();
            
            Integer id1=user.getUser_id();
    	    String name=user.getUser_name();
    	    String salary =user.getUser_salary();
    	    
    	    model1.setId(id1);
    	    model1.setName(name);
    	    model1.setSalary(salary);
			
        } catch (RuntimeException e) {
            e.printStackTrace();
            return model1;
        } finally {
            session.flush();
            session.close();
        }
        return model1;
    }
    
   
    
    
  public boolean deleteUser(User users) {
    	
    	Integer id1=0;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	id1=users.getUser_id();
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

   
    

    public void updateUser(User user) {
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
    
    public static Login validate(Login login)
    {
    	Transaction trns = null;
        
        String email= login.getEmail();
        String password= login.getPassword();
        System.out.println(email+"  "+password);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from User_Info where email = :email and password = :password";
            Query query = session.createQuery(queryString);
            query.setString("email", email);
            query.setString("password", password);
            
            login = (Login) query.uniqueResult();
            System.out.println(login);
			
        } catch (RuntimeException e) {
            e.printStackTrace();
            return login;
        } finally {
            session.flush();
            session.close();
        }
        return login;
    }
    


	
    
  //===========================================================  
    
    
}



