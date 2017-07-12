<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Kanban Board</title>
  <spring:url value="/resources/Bootstrap/js/kanban.js" var="kanbanJS"/>
  <spring:url value="/resources/Bootstrap/css/kanban.css" var="kanbanStyle"/>
  <link rel="stylesheet" href="${kanbanStyle}">
  <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
<script src="https://www.polymer-project.org/1.0/components/webcomponentsjs/webcomponents.min.js"></script>
<link rel="import" href="https://rawgit.com/Download/polymer-cdn/master/lib/polymer/polymer.html">

 	
  
</head>


 <body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'ProjectNTask',
			type:'POST',
			success: function(response){
				console.log(response);
				project = response.project;
				task = response.task;
				for(i=0;i<project.length;i++)
				{
				$("#board").append("<kanban-column name="+project[i].project_name+"></kanban-column>")
				
				}
				
			},
		error: function(err){
			console.log("KKKKKKK");
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
</script>		
  <kanban-board title="uie" id="board">
		
</kanban-board>


<dom-module id="kanban-board">
		<template>
				<header></header>
				<content></content>
				<footer></footer>
		</template>
		<script>
				Polymer({
						is: 'kanban-board',
						properties: {
								title: String,
						},
						ready: function() {

						}
				});
		</script>
</dom-module>
<dom-module id="kanban-column">
		<template>
				<header>{{name}}</header>
				<section class="wrapper">
					<content></content>
				</section>
				<footer><a href="taskDetail">Add Task</a></footer>
		</template>
		<script>
				Polymer({
						is: 'kanban-column',
						properties: {
								name: String,
						},
						ready: function() {

						},
						handleClick: function(name) {
								var nameOfCard = name;
								var card = document.createElement('kanban-card');
								card.innerHTML = nameOfCard;
								card.draggable = true;
								this.getElementsByClassName('wrapper')[0].appendChild(card);
						}
				});	
		</script>
</dom-module>
<dom-module id="kanban-card">
		<template>
				<content></content>
		</template>
		<script>
				Polymer({
						is: 'kanban-card',
						properties: {
								draggable: Boolean,
						},
						ready: function() {

						}
				});
		</script>
</dom-module>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>

    <script src="${kanbanJS}"></script>
</div>
</body>
</html>
