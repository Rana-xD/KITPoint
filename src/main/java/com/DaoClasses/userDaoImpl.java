package com.DaoClasses;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;


import com.EntityClasses.Batch_Master;
import com.EntityClasses.Project_Category_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Semester_Master;
import com.EntityClasses.Task_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.ProjectForViewModel;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
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
    public static List<ProjectForViewModel> getAllProject() {
        //List<Project_Model> projects= new ArrayList<Project_Model>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProjectForViewModel> projects = null;
		try {
            trns = session.beginTransaction();
           projects = session.createQuery("from Project_Master").list();
            System.out.print(projects);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return projects;
        } finally {
            session.flush();
            session.close();
        }
        return projects;
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
            Query query = session.createQuery(queryString);
            query.setString("email", email);
            query.setString("password", password);
            
            user = (User_Info) query.uniqueResult();
            System.out.println("Try "+user);
			
        } catch (RuntimeException e) {
            e.printStackTrace();
            return user;
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
  //===========================Save Batch==============================
    public static boolean saveBatch(Batch_Master batch)
    {
    	Batch_Master batch2 = batch;
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "FROM Batch_Master where name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",batch.getName());
            batch=(Batch_Master)query.uniqueResult();
            System.out.println("Batch is"+batch);
    			if(batch!=null)
    				return false;
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
        	batch2.setCreated_at(created_at);
            session.save(batch2);  
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
//===========================Update Batch==============================
    public static boolean updateBatch(Batch_Master batch)
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "FROM Batch_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",batch.getId());
            System.out.println("Name is"+batch.getName());
            Batch_Master batch2 = new Batch_Master();
            System.out.println("Name is"+batch2);
            batch2=(Batch_Master)query.uniqueResult();
            System.out.println("Name is"+batch2);
    		Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        	batch2.setUpdated_at(updated_at);
        	batch2.setSemester_id(batch.getSemester_id());
        	System.out.println("Before Up");
            session.update(batch2);  
            System.out.println("After Up");
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	System.out.println("Catch runs");
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

    
//=================================Save projectCategory=========================================
    public static boolean createProjectCategory(Project_Category_Master projectCategory)
    {
    	Transaction trns = null;
        
    	Project_Category_Master projectCategory2 = projectCategory;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            String queryString = "FROM Project_Category_Master where name=:name";
            Query query = session.createQuery(queryString);
            query.setString("name",projectCategory.getName());
            projectCategory=(Project_Category_Master)query.uniqueResult();
    			if(projectCategory!=null)
    				return false;
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
    		projectCategory2.setCreated_at(created_at);
            session.save(projectCategory2);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Save task=========================================
    public static boolean toSaveTask(Task_Model t) throws ParseException
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
            Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(t.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(t.getDeadline());
    		Task_Master tm = new Task_Master();
    		//tm.setProject_id(t.getProject_id());
    		tm.setName(t.getName());
    		tm.setAssigned_to(t.getAssigned_to());
    		tm.setDescription(t.getDescription());
    		tm.setStatus(t.getStatus());
    		tm.setTime_spend(t.getTime_spend());
    		tm.setDeadline(deadline);
    		tm.setStart_date(start_date);
    		tm.setEnd_date(end_date);
    		
            session.save(tm);  
            session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Save project=========================================
    public static boolean saveProject(Project_Model project) throws ParseException
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns = session.beginTransaction();
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(project.getDeadline());
    		Project_Master pm = new Project_Master();
    		pm.setProject_name(project.getProject_name());
    		pm.setProject_code(project.getProject_code());
    		pm.setSkillset(project.getSkillset());
    		pm.setProject_type(project.getProject_type());
    		pm.setProject_co(project.getProject_co());
    		pm.setProject_leader(project.getProject_leader());
    		pm.setProject_member(project.getProject_member());
    		pm.setDescription(project.getDescription());
    		pm.setInitially_planned(project.getInitially_planned());
    		pm.setBudget(project.getBudget());
    		pm.setKit_point(project.getKit_point());
    		pm.setStart_date(start_date);
    		pm.setEnd_date(end_date);
    		pm.setDeadline(deadline);
    		pm.setCreated_at(created_at);
    		session.save(pm); 
    	    //int id = pm.getId();
    		session.getTransaction().commit();
    		
    		
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Update project=========================================
    public static boolean updateProject(Project_Model project) throws ParseException
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            trns = session.beginTransaction();
    		Timestamp created_at = new Timestamp(System.currentTimeMillis());
    		Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getStart_date());
    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(project.getEnd_date());
    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(project.getDeadline());
    		Project_Master pm = new Project_Master();
    		System.out.println("ID in DAO is "+project.getId());
    		pm.setId(project.getId());
    		pm.setProject_name(project.getProject_name());
    		pm.setProject_code(project.getProject_code());
    		pm.setSkillset(project.getSkillset());
    		pm.setProject_type(project.getProject_type());
    		pm.setProject_co(project.getProject_co());
    		pm.setProject_leader(project.getProject_leader());
    		pm.setProject_member(project.getProject_member());
    		pm.setDescription(project.getDescription());
    		pm.setInitially_planned(project.getInitially_planned());
    		pm.setBudget(project.getBudget());
    		pm.setKit_point(project.getKit_point());
    		pm.setStart_date(start_date);
    		pm.setEnd_date(end_date);
    		pm.setDeadline(deadline);
    		pm.setCreated_at(created_at);
    		session.update(pm); 
    	    //int id = pm.getId();
    		session.getTransaction().commit();
    		
    		
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
            return false;
        } finally {
            session.flush();
            session.close();
        }
        return true;
    }
//=================================Save Stage==================================================
    public static void saveStage(int projectid, int arr[])
    {
    	Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	
            trns = session.beginTransaction();
    		for (int i = 0; i<arr.length; i++)
    		{
    			Project_Stage ps = new Project_Stage();
    			ps.setProject_id(projectid);
    			ps.setStage_id(arr[i]);
        		//ps.setCreated_at(created_at);
    			session.save(ps);
    			
    		}  
    		session.getTransaction().commit();
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            System.out.println("Catch runs");
        } finally {
            session.flush();
            session.close();
        }
       
    }

//===================Get a list of semesters=================================
    public static List<Semester_Master> getAllSemester() {
        List<Semester_Master> semesters= new ArrayList<Semester_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            semesters = session.createQuery("from Semester_Master").list();
            System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return semesters;
        } finally {
            session.flush();
            session.close();
        }
        return semesters;
    }
//===================Get a list of project categories=================================
    public static List<Project_Category_Master> getProjectCategories() {
        List<Project_Category_Master> p= new ArrayList<Project_Category_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            p = session.createQuery("from Project_Category_Master").list();
            System.out.println(p);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return p;
        } finally {
            session.flush();
            session.close();
        }
        return p;
    }
//===================Get a list of batch=================================
    public static List<Batch_Master> getAllBatch() {
        List<Batch_Master> batch= new ArrayList<Batch_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            batch = session.createQuery("from Batch_Master").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return batch;
        } finally {
            session.flush();
            session.close();
        }
        return batch;
    }  
//===================Get a list of user=================================
    public static List<User_Info> getAllUser() {
        List<User_Info> users= new ArrayList<User_Info>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from User_Info").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return users;
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }  
//===================Get a list of stages=================================
    public static List<Project_Stage_Master> getAllStages() {
        List<Project_Stage_Master> stages= new ArrayList<Project_Stage_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            stages = session.createQuery("from Project_Stage_Master").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return stages;
        } finally {
            session.flush();
            session.close();
        }
        return stages;
    }  
//===========================Get all task===================================
    public static List<Task_Master> getAllTask()
    {
    	List<Task_Master> tasks= new ArrayList<Task_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            tasks = session.createQuery("from Task_Master").list();
            //System.out.println(semesters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return tasks;
        } finally {
            session.flush();
            session.close();
        }
        return tasks;
    }
//==================Get project by id=======================
    public static Project_Master getProjectByUserId(int id) throws ParseException
    {
    	Project_Master project= new Project_Master();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Project_Master where id=:id";
            Query query = session.createQuery(queryString);
            query.setInteger("id",id);
            project=(Project_Master)query.uniqueResult();
//            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//            String s_date = df.format(project.getStart_date());
//            String e_date = df.format(project.getEnd_date());
//            String dline = df.format(project.getDeadline());
//            Date start_date = new SimpleDateFormat("MM/dd/yyyy").parse(s_date);
//    		Date end_date = new SimpleDateFormat("MM/dd/yyyy").parse(e_date);
//    		Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(dline);
//    		project.setStart_date(start_date);
//    		project.setEnd_date(end_date);
//    		project.setDeadline(deadline);            
        } catch (RuntimeException e) {
            e.printStackTrace();
            return project;
        } finally {
            session.flush();
            session.close();
        }
        return project;
    }  
    
  //===========================================================  
    
    
}



