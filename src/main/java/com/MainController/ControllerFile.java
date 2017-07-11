package com.MainController;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;











import com.DaoClasses.userDaoImpl;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Login;
import com.EntityClasses.Project_Category_Master;
import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.Semester_Master;
import com.EntityClasses.User;
import com.EntityClasses.User_Info;
import com.ModelClasses.Project_Model;
import com.ModelClasses.Task_Model;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;
import com.ServiceClasses.usersService;
import com.ServiceClasses.usersServiceImpl;


@Controller
@RequestMapping("users")
public class ControllerFile {
		
	@Autowired
	usersService usersService1;	
	
//	=================project============================
	@RequestMapping("/project")
	public ModelAndView viewProject() {
		String message = "Hello World";
		return new ModelAndView("project", "message", message);
	}

//	=================login============================
	@RequestMapping("/")
	public ModelAndView viewLogin() {
		return new ModelAndView("login");
	}
// ================================Login Validate================================================	
		@RequestMapping(value="/validate", method=RequestMethod.POST)
		public ModelAndView toValidate(User_Info user, BindingResult result)
		{
			if(result.hasErrors())
			   {
				ModelAndView model2 = new ModelAndView("login");  //if it is error send it back to retrieve.jsp 
				model2.addObject("message", "Enter the correct format");  
				return model2;	
			   }
			user = userDaoImpl.validate(user);
			if(user!=null)
				{return new ModelAndView("project");}
			else 
			{	
				ModelAndView model = new ModelAndView("login");
				model.addObject("message","Please input the correct username and password");
				return model;
			}
		}
//	=================projectDetail============================
	@RequestMapping("/projectDetail")
	public ModelAndView viewProjectdetail() {
		//String message = "Hello World";
		return new ModelAndView("projectDetail");
	}
//============================Retreive all project category from DB send through Ajax========================
			@RequestMapping(value="/projectCategoryList", method=RequestMethod.POST)
			public @ResponseBody Map<String,Object> getProjectCategoryList(){
						
				 Map<String,Object> map = new HashMap<String,Object>();
			
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Project_Category_Master> list = userDaoImpl.getProjectCategories();
					 		
					if (list != null)
						map.put("data", list);
					else
						map.put("message","Data not found");			
					
					return map;
			}
//============================Get Project========================
			@RequestMapping(value="/getProject", method=RequestMethod.POST)
			public @ResponseBody Map<String,Object> getProject(){
						
				 Map<String,Object> map = new HashMap<String,Object>();
			
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Project_Master> list = userDaoImpl.getAllProject();
					 		
					if (list != null)
						map.put("project", list);
					else
						map.put("message","Data not found");			
					
					return map;
			}
			
//============================Retreive all users and ProjectCategory from DB send through Ajax========================
			@RequestMapping(value="/userNProjectCategoryListNStage", method=RequestMethod.POST)
			public @ResponseBody Map<String,?> getUserNProjectCategoryListNStage(){
						
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Project_Category_Master> listProjectCategory = userDaoImpl.getProjectCategories();
					List<User_Info> listUser = userDaoImpl.getAllUser();
					//List<Project_Stage_Master> listProjectStage = userDaoImpl.getAllStages();
					 		
					if (listProjectCategory == null || listUser == null)
						{
							error.put("message","Data not found");
							return error;
						}
						
					else
						{
							map.put("category", listProjectCategory);
							map.put("user", listUser);
							//map.put("stage",listProjectStage);
							return map;
						}	
					}
//========================Save Project========================================================
			@RequestMapping(value="/saveProject", method=RequestMethod.POST)
			public @ResponseBody Map<String,Object> toSaveProject(Project_Model pm) throws ParseException{
	        		//int[] s = pm.getStage();
					Map<String,Object> map = new HashMap<String,Object>();				
					if(userDaoImpl.saveProject(pm))
					{
						map.put("status","200");
						map.put("message","Your record has been saved successfully");
						return map;
					}
					else {
						System.out.println("Else Runs");
						map.put("status","999");
						map.put("message","Failed");
						return map;
					}
				}
//	=================taskDetails============================
	@RequestMapping("/taskDetail")
	public ModelAndView viewTaskdetail() {
		//String message = "Hello World";
		return new ModelAndView("taskDetail");
	}
//	=================taskDetails============================
	@RequestMapping("/task")
	public ModelAndView viewTaskView() {
		//String message = "Hello World";
		return new ModelAndView("taskView");
	}
//==================get Project and User===========================
	@RequestMapping(value="/ProjectNUser", method=RequestMethod.POST)
	public @ResponseBody Map<String,?> getProjectNUser(){
				
		 Map<String,List> map = new HashMap<String,List>();
		 Map<String,Object> error = new HashMap<String,Object>();
		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Project_Master> listProject = userDaoImpl.getAllProject();
			List<User_Info> listUser = userDaoImpl.getAllUser();
			 		
			if (listProject == null || listUser == null)
				{
					error.put("message","Data not found");
					return error;
				}
				
			else
				{
					map.put("user", listUser);
					map.put("project", listProject);
					return map;
				}	
			}

//======================save task===============================
	@RequestMapping(value="/saveTask", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> toSaveTask(Task_Model task) throws ParseException{
			
			//System.out.println("Name is: "+projectCategoryName.getName());
			Map<String,Object> map = new HashMap<String,Object>();				
			if(userDaoImpl.toSaveTask(task)){
				map.put("status","200");
				map.put("message","Your record has been saved successfully");
				return map;
			}
			else {
				System.out.println("Else Runs");
				map.put("status","999");
				map.put("message","Your record already existed");
				return map;
			}
		}
//	=================setting============================
	@RequestMapping("/setting")
	public ModelAndView viewSetting() {
		String message = "Hello World";
		return new ModelAndView("viewSetting", "message", message);
	}
//	=================view batch============================
	@RequestMapping("/batch")
	public ModelAndView viewBatch() {
		String message = "Hello World";
		return new ModelAndView("viewBatch", "message", message);
	}
//	=================kit point============================
	@RequestMapping("/kitpoint")
	public ModelAndView viewkitpoint() {
		String message = "Hello World";
		return new ModelAndView("viewKitPoint", "message", message);
	}
//	=================view project category============================
	@RequestMapping("/projectCategory")
	public ModelAndView viewProjectCategory() {
		return new ModelAndView("viewProjectCaterory");
	}
//================================Project Category Create============================================
		@RequestMapping(value="/projectCategoryCreate", method=RequestMethod.POST)
		public @ResponseBody Map<String,Object> toCreateProjectCategory(Project_Category_Master projectCategoryName){
				
				Map<String,Object> map = new HashMap<String,Object>();				
				if(userDaoImpl.createProjectCategory(projectCategoryName)){
					map.put("status","200");
					map.put("message","Your record has been saved successfully");
					return map;
				}
				else {
					map.put("status","999");
					map.put("message","Your record already existed");
					return map;
				}
				
			}
//	=================view Value Per Hour============================
	@RequestMapping("/valuePerHour")
	public ModelAndView viewValuePerHour() {
		String message = "Hello World";
		return new ModelAndView("viewValuePerHour", "message", message);
	}
//	=================create new user============================
	@RequestMapping("/newUser")
	public ModelAndView createUser() {
		String message = "Hello World";
		return new ModelAndView("createUser", "message", message);
	}
//	=================view profile & change password============================
	@RequestMapping("/profile")
	public ModelAndView changePassword() {
		String message = "Hello World";
		return new ModelAndView("changePassword", "message", message);
	}
//	=================Create Batch============================
	@RequestMapping("/createBatch")
	public ModelAndView createBatch() {
		return new ModelAndView("createBatch");
	}
//============================Retreive all semesters from DB send through Ajax========================
		@RequestMapping(value="/semesterList", method=RequestMethod.POST)
		public @ResponseBody Map<String,Object> getSemesterList(){
					
			 Map<String,Object> map = new HashMap<String,Object>();
		
			   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
				List<Semester_Master> list = userDaoImpl.getAllSemester();
				 		
				if (list != null)
					map.put("data", list);
				else
					map.put("message","Data not found");			
				
				return map;
		}
//================================Save Batch============================================
				@RequestMapping(value="/batchSubmit", method=RequestMethod.POST)
				public @ResponseBody Map<String,Object> toCreateProjectCategory(Batch_Master batch){
						
						//System.out.println("Name is: "+projectCategoryName.getName());
						Map<String,Object> map = new HashMap<String,Object>();				
						if(userDaoImpl.saveBatch(batch)){
							map.put("status","200");
							map.put("message","Your record has been saved successfully");
							return map;
						}
						else {
							System.out.println("Else Runs");
							map.put("status","999");
							map.put("message","Your record already existed");
							return map;
						}
					}

//===================Update Batch======================================
	@RequestMapping("/updateBatch")
	public ModelAndView updateBatch() {
		return new ModelAndView("updateBatch");
	}
//============================Retreive all semesters and batches from DB send through Ajax========================
			@RequestMapping(value="/semesterAndBatchList", method=RequestMethod.POST)
			public @ResponseBody Map<String,?> getSemesterAndBatchList(){
						
				 Map<String,List> map = new HashMap<String,List>();
				 Map<String,Object> error = new HashMap<String,Object>();
				   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
					List<Semester_Master> listSemester = userDaoImpl.getAllSemester();
					List<Batch_Master> listBatch = userDaoImpl.getAllBatch();
					 		
					if (listSemester == null || listBatch == null)
						{
							error.put("message","Data not found");
							return error;
						}
						
					else
						{
							map.put("semester", listSemester);
							map.put("batch", listBatch);
							return map;
						}	
					}

//=========================create user=========================================	
	@RequestMapping(value="/submit", method=RequestMethod.POST)                                            
	public ModelAndView Submit(submit newUser,BindingResult result)        
	{                                                                                                
	   if(result.hasErrors())
	   {
		ModelAndView model2 = new ModelAndView("createUser");  //if it is error send it back to submit.jsp 
		model2.addObject("headerMsg", "Enter the correct format");  
		return model2;	
	   }
	   
	  // usersServiceImpl dao = new usersServiceImpl();
	    		
		newUser=usersService1.addUser1(newUser);
				
		ModelAndView model2 = new ModelAndView("createUser");
		model2.addObject("headerMsg", "Value inserted successfully");  
		
		model2.addObject("msg", newUser);    //This name will store the value in msg

		return model2;
	}

}







	

	
	






	