

<body onload='load();'>
 <div class="row" id="margin-body">
    
				 <form class="form-horizontal" type="post">
				 <div class="col-sm-2">
                            <div class="form-group">
                            <img src="#" alt="" class="img-thumbnail">
                                <label>image</label>
                        </div>
               	</div>
				 <div class="col-sm-8"> 
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Name</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" name="name" id="name">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Email</label>
						    <div class="col-sm-10">
						      <input type="email" class="form-control" name="email" id="email" >
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Password</label>
						    <div class="col-sm-10">
						      <input type="password" class="form-control" name="password" id="password" >
						    </div>
						  </div>
						   <div class="form-group">
						     <label class="col-sm-2 control-label">User Type</label>
								  <div class="col-sm-10">
		                                <label class="radio-inline">
		                                    <input type="radio" id="user_type" name="optionsRadiosInline" value="Super Admin" checked>Super admin
		                                </label>
		                                <label class="radio-inline">
		                                    <input type="radio" id="user_type"  name="optionsRadiosInline" value="Admin">Admin
		                                </label>
		                                <label class="radio-inline">
		                                    <input type="radio" id="user_type" name="optionsRadiosInline"  value="User">User
		                                </label>
		                           </div>
		                            
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Batch</label>
                                <div class="col-sm-10">
                                <select class="form-control" name="Batch" id="batch">
                                </select>
                                </div>
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button onclick='submit_();' class="btn btn-default">Create</button>
						      <button type="reset" class="btn btn-default">Cancel</button>
						  
						    </div>
						  </div>
						  </div>
						</form>
                    </div>
                    
                    
                 	
 <script type="text/javascript">
			load = function(){	
				$.ajax({
					url:'getBatchList',
					type:'POST',
					success: function(response){
							console.log(response);
							data = response.batch;
							for(i=0; i<response.batch.length; i++){					
								$("#batch").append("<option value="+response.batch[i].id+">"+response.batch[i].name+" </option>");
							}
					}				
				});
				
			}
			var data = "";
			submit_ = function(){
					$.ajax({
						url:'addUser',
						type:"POST",
						data:{name:$('#name').val(),
							email:$('#email').val(), 
							password:$('#password').val(),
							user_type:$('#user_type').val(),
							batch:$('#batch').val()},
						success: function(response){
								alert(response.message);
						}				
					});			
			}
				
</script>

                    