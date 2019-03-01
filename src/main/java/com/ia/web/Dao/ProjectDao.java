package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.Project;
import com.ia.web.Modal.ProjectType;
import com.ia.web.Modal.ProjectView;

public interface ProjectDao {

	//Get project list
	List<ProjectView> getProjects();
	
	//Insert new project
	boolean insertProject(Project project);
	
	//Update status
	boolean updateProjectStatus(String status,int projectId);
	
	
	//Get project type list
	List<ProjectType> getProjectType(String action);
	
	//Get project detail by id
	Project getProjectById(int projectId);
}
