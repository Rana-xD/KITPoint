<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper">
 <div class="row">
                 <form role="form">
                    <div class="col-lg-6">

                            <div class="form-group">
                                <label>Task Name</label>
                                <input class="form-control">
                        	</div>
                            <div class="form-group">
                                <label>Project: </label>
                                <input class="form-control">
                        	</div>
                            <div class="form-group">
                                <label>Assign to:</label>
                                <select class="form-control">
                                    <option>Rana</option>
                                    <option>Kimchheng</option>
                                    <option>Sopheak</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Planning Hour</label>
                                <input class="form-control">
                        	</div>
    						<div class="form-group">
                                <label>Description</label>
                                <input class="form-control">
                        	</div>
                                 
                            
                           
                        </div>
                           
                            
                        

                  		  
                         <div class="col-lg-6">
                           
                            <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Start Date</label>
                                  <input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
                            </div>
    					 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">End Date</label>
                              <input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
                            </div>
                                 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Deadline</label>
                                <input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
                        </div>
                                 <div class="form-group col-lg-6">
                                <label>Status</label>
                                <select class="form-control">
                                    <option>In Progress</option>
                                    <option>Completed</option>
                                    <option>Delayed</option>
                                    <option>Postponed</option>
                                    
                                </select>
                            </div>                      
                  		  </div>
                   
                    	
	                    </form>
	                    
        </div>            

</div>
<button type="submit" class="btn btn-default">Save Changes</button>
</body>
</html>