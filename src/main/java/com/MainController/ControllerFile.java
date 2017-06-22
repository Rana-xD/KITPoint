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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;






import com.DaoClasses.userDaoImpl;
import com.EntityClasses.User;
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
	
//	=================project============================
	@RequestMapping("/projectDetail")
	public ModelAndView viewProjectdetail() {
		String message = "Hello World";
		return new ModelAndView("projectDetail", "message", message);
	}
//1. First Method using model classes	
	
//=================Submit Users==============================
	
	@RequestMapping(value="/add1", method = RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view =new ModelAndView("submit");
		return view;
	}
	
	
	@RequestMapping(value="/submit.html", method=RequestMethod.POST)                                            
	public ModelAndView Submit(submit model1,BindingResult result)        
	{                                                                                                
	   if(result.hasErrors())
	   {
		ModelAndView model2 = new ModelAndView("submit");  //if it is error send it back to submit.jsp 
		model2.addObject("headerMsg", "Enter the correct format");  
		return model2;	
	   }
	   
	  // usersServiceImpl dao = new usersServiceImpl();
	    		
		model1=usersService1.addUser1(model1);
				
		ModelAndView model2 = new ModelAndView("submitOutput");
		model2.addObject("headerMsg", "Value inserted successfully");  
		
		model2.addObject("msg", model1);                                                  //This name will store the value in msg

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
			List<User> list = usersService1.getAllUsers();
			 		
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
	public @ResponseBody Map<String,Object> getSaved(User users){
		
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
	public @ResponseBody Map<String,Object> delete(User users){
		Map<String,Object> map = new HashMap<String,Object>();		
		 //DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
		if(usersService1.deleteUser(users)){
			map.put("status","200");
			map.put("message","Your record has been deleted successfully");
		}
		
		return map;
	}
		
}

//=============================================================





	

	
	






	