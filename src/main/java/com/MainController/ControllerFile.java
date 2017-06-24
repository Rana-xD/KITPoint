package com.MainController;


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
import com.EntityClasses.Login;
import com.EntityClasses.User;
import com.EntityClasses.User_Info;
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
	@RequestMapping("/login")
	public ModelAndView viewLogin() {
		return new ModelAndView("login");
	}
//	=================projectDetail============================
	@RequestMapping("/projectDetail")
	public ModelAndView viewProjectdetail() {
		//String message = "Hello World";
		return new ModelAndView("projectDetail");
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
		String message = "Hello World";
		return new ModelAndView("viewProjectCaterory", "message", message);
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

//1. First Method using model classes	
	
//=================Submit Users==============================
	
/*	@RequestMapping(value="/add1", method = RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view =new ModelAndView("/views/submit");
		return view;
	}*/
	
	
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
	
	
//=================Retrieve Users=====================
	
	
	@RequestMapping(value="/retrieve", method = RequestMethod.GET)
	public ModelAndView getResult(){
		ModelAndView view =new ModelAndView("retrieve");
		return view;
	}
	
	
	@RequestMapping(value="/result.html", method=RequestMethod.POST)                                            
	public ModelAndView Retrieve(retrieve model1,BindingResult result)        
	{                                                                                                
	   if(result.hasErrors())
	   {
		ModelAndView model2 = new ModelAndView("retrieve");  //if it is error send it back to retrieve.jsp 
		model2.addObject("headerMsg", "Enter the correct format");  
		return model2;	
	   }
	  
	  // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();	   	
		
	    model1=usersService1.getUserById(model1);
		
		ModelAndView model2 = new ModelAndView("retrieveOutput");
		model2.addObject("headerMsg", "Your Output is");  
		
		model2.addObject("msg",  model1);                                                 

		return model2;
	}	
	
//=================================================================
	
	
	
	
	
	
	
	
	
	
//2.Second Method using Restful API
	
//==================Restful API====================================
	
	
	@RequestMapping(value="/allusers", method = RequestMethod.GET)
	public ModelAndView getPage2(){
		ModelAndView view =new ModelAndView("allusersOutput");
		return view;
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAll(){
				
		 Map<String,Object> map = new HashMap<String,Object>();
	
		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<User_Info> list = usersService1.getAllUsers();
			 		
			if (list != null){
				map.put("status","200");
				map.put("message","Data found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","Data not found");			
			}
		
		  return map;
	}
	
	
 //===========================================================
	
	
	@RequestMapping(value="/add2", method = RequestMethod.GET)
	public ModelAndView getPage4(){
		ModelAndView view =new ModelAndView("save");
		return view;
	}

	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSaved(User_Info users){
		
		Map<String,Object> map = new HashMap<String,Object>();		
		 //DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
		if(usersService1.addUser2(users)){
			map.put("status","200");
			map.put("message","Your record has been saved successfully");
		}
		
		return map;
	}
		
	
//=========================================================

	@RequestMapping(value="/remove", method = RequestMethod.GET)
	public ModelAndView getPage5(){
		ModelAndView view =new ModelAndView("delete");
		return view;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(User_Info users){
		Map<String,Object> map = new HashMap<String,Object>();		
		 //DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
		if(usersService1.deleteUser(users)){
			map.put("status","200");
			map.put("message","Your record has been deleted successfully");
		}
		
		return map;
	}
	
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
		
}

//=============================================================





	

	
	






	