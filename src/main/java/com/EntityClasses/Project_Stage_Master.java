package com.EntityClasses;

import java.security.Timestamp;

public class Project_Stage_Master {
		private int id;
		private String stage_name;
		private Timestamp created_at;
		private Timestamp update;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getStage_name() {
			return stage_name;
		}
		public void setStage_name(String stage_name) {
			this.stage_name = stage_name;
		}
		public Timestamp getCreated_at() {
			return created_at;
		}
		public void setCreated_at(Timestamp created_at) {
			this.created_at = created_at;
		}
		public Timestamp getUpdate() {
			return update;
		}
		public void setUpdate(Timestamp update) {
			this.update = update;
		}
		
		
}
