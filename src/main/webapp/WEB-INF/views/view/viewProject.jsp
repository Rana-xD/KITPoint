
<script type="text/javascript">
	load = function(){	
		$("li#id1").addClass("active");
		$.ajax({
			url:'getProject',
			type:'POST',
			success: function(response){
					console.log(response);
					project_task=response.project[0];
					task=response.project[1]
					for(i=0; i<response.project.length; i++){
						var projectDiv =
						"<div class='col-sm-4' data-project-status='"+project_task[i].status+
						"'>"+
						"<div class='panel panel-success'>"+
							"<div class='panel-heading'>"+
								"<h3 class='panel-title'>"+ response.project[i].project_name+"</h3>"+
                            "</div>"+
                            "<div class='panel-body'>"+
                               "<label>Progress</label>"+
                  	           "<div class='progress'>"+
                  			   "<div class='progress-bar' role='progressbar' aria-valuenow='40' aria-valuemin='0' aria-valuemax='100' style='width: 90%;'><span class='sr-only'>60% Complete</span>"+
                    		   "</div>"+
                			   "</div>"+
                
                               "<div class='row'>"+
                                    "<div class='col-xs-2'>"+reponse.project[i].num_task+
                                    "</div>"+
                                    "<div class='col-xs-8 text-right'>"+ 
                                    "<div class='huge'>"+response.project[i].kit_point+"</div>"+ 
                                        "<div>KIT point</div>"+ 
                                        "</div>"+
                               "</div>"+
                            "</div>"+
                              "<a href='#'>"+
                                "<div class='panel-footer'>"+
                                    "<span class='pull-left'>View Details</span>"+
                                    "<span class='pull-right'><i class='fa fa-arrow-circle-right'></i></span>"+
                                    "<div class='clearfix'></div>"+
                                "</div>"+
                            "</a>"+
                        "</div>"+
                        "</div>";
						
						$("#project").append(projectDiv);

					}
			}				
		});	
	}

	

	showProjectBasedStatus=function(statusData){
		var project_status;
		if (statusData=="Completed Project")
		{
			$("li#id5").addClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			project_status="Completed Project";
		}
		else if (statusData=="Approved Project")
		{
			$("li#id2").addClass("active");
			$("#id1").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			
			project_status="Approved Project";
		}
		else if (statusData=="To approve Project")
		{
			$("li#id3").addClass("active");			
			$("#id2").removeClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			project_status="To approve Project";
		}
		else if (statusData=="Pending Project")
		{
			$("li#id4").addClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id5").removeClass("active");
			project_status="Pending Project";
		}	else 
		{
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			$("div #project").html('');
			load();
			return;
		}
		$.ajax({
			url:'getProjectBasedOnStatus',
			type:'POST',
			data:{status:project_status},
			success: function(response){
				console.log(response);
				project=response.project;
				$("div #project").html('');
				for(i=0; i<response.project.length; i++){
					var projectDiv =
					"<div class='col-sm-4' data-project-status='"+response.project[i].status+
					"'>"+
					"<div class='panel panel-success'>"+
						"<div class='panel-heading'>"+
							"<h3 class='panel-title'>"+ response.project[i].project_name+"</h3>"+
                        "</div>"+
                        "<div class='panel-body'>"+
                           "<label>Progress</label>"+
              	           "<div class='progress'>"+
              			   "<div class='progress-bar' role='progressbar' aria-valuenow='40' aria-valuemin='0' aria-valuemax='100' style='width: 90%;'><span class='sr-only'>60% Complete</span>"+
                		   "</div>"+
            			   "</div>"+
            
                           "<div class='row'>"+
                                "<div class='col-xs-2'>"+
                                "</div>"+
                                "<div class='col-xs-8 text-right'>"+ 
                                "<div class='huge'>"+response.project[i].kit_point+"</div>"+ 
                                    "<div>KIT point</div>"+ 
                                    "</div>"+
                           "</div>"+
                        "</div>"+
                          "<a href='#'>"+
                            "<div class='panel-footer'>"+
                                "<span class='pull-left'>View Details</span>"+
                                "<span class='pull-right'><i class='fa fa-arrow-circle-right'></i></span>"+
                                "<div class='clearfix'></div>"+
                            "</div>"+
                        "</a>"+
                    "</div>"+
                    "</div>";
					$("#project").append(projectDiv);
					}
				}
			});
		
	}

</script>
<body onload="load();">
	    <div class="navbar navbar-default">
                    <div class="container" data-project-status="">
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav" >
                                <li id="id1" onclick='showProjectBasedStatus("all");'><a>ALL</a>
                                </li>
                                <li id="id2" onclick='showProjectBasedStatus("Approved Project");'><a >Approved Project</a>
                                </li>
                                <li id="id3" onclick='showProjectBasedStatus("To approve Project");'><a>To approve Project</a>
                                </li>
                                <li id="id4" onclick='showProjectBasedStatus("Pending Project");'><a>Pending Project</a>
                                </li>
                                <li id="id5" onclick='showProjectBasedStatus("Completed Project");'><a>Completed Project</a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
                <a href="projectDetail" class="btn btn-default">Create</a>
                <br><br>
                <div class="row">
                
                <div id="project">
                </div>
         </div>

                
                
                
               
                


