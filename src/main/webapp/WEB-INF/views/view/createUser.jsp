 <div class="row" id="margin-body">
    
				 <form class="form-horizontal" action="submit" method="post">
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
						      <input type="text" class="form-control" name="name" >
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Email</label>
						    <div class="col-sm-10">
						      <input type="email" class="form-control" name="email" >
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Password</label>
						    <div class="col-sm-10">
						      <input type="password" class="form-control" name="password" >
						    </div>
						  </div>
						   <div class="form-group">
						     <label class="col-sm-2 control-label">User Type</label>
						   <div class="col-sm-10">
                              
                                <label class="radio-inline">
                                    <input type="radio" name="user_type" value="Super Admin" checked>Super admin
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="user_type"  value="Admin">Admin
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="user_type"  value="User">User
                                </label>
                            </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Batch</label>
                                <div class="col-sm-10">
                                <select class="form-control" name="Batch">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                </div>
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-default">Create</button>
						      <button type="reset" class="btn btn-default">Cancel</button>
						  
						    </div>
						  </div>
						  </div>
						</form>
                    </div>