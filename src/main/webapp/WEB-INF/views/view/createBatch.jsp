 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
 <spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
      <link rel="stylesheet" href="${alertStyle}">
<spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
      <script src="${alertJS}"></script>
</head>



<body onload="load();">
<script type="text/javascript">
	
	load = function(){	
		$.ajax({
			url:'semesterList',
			type:'POST',
			success: function(response){
					console.log(response);
					data = response.data;
					//$('.tr').remove();
					for(i=0; i<response.data.length; i++){					
						$("#semester").append("<option value="+response.data[i].id+">"+response.data[i].semester+" </option>");
						
					}
			}				
		});
		
	}
$(document).ready(function(){
	$("#btnSubmit").click(function(){		 
	$.ajax({
		url:'batchSubmit',
		type:'POST',
		data:{name:$("#name1").val(),semester_id:$("#semester").val()},
		success: function(response){
				if(response.status=="200")
					{
					$('#name1').val('');
					swal("Good job!", "You clicked the button!", "success")
					}
				//var obj = jQuery.parseJSON(response);
				    
				else 
					{
					$('#name1').val('');
					swal("Oops!", "Category name already existed", "error")
					
					}
				},
		error: function(err){
				console.log(JSON.stringify(err));
				}
		
			});			
	
	});
});	
</script>
 <div class="row" id="margin-body">
    
				 <div class="form-horizontal">
				 <div class="col-sm-8">
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Batch</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id = "name1" name="name" >
						    </div>
						  </div>
                            
                          <div class="form-group">
                                <label class="col-sm-2 control-label">Semester</label>
                                <div class="col-sm-10">
                                <select class="form-control" id="semester" name="semester_id">
                                 </select>
                                </div>
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button id="btnSubmit" class="btn btn-default">Create</button>
						      <button type="reset" class="btn btn-default">Cancel</button>
						  
						    </div>
						  </div>
						  </div>
						</div>
                    </div>	
          </body>
       </html>
       