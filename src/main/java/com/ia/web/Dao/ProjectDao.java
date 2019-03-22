package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.EmailConversion;
import com.ia.web.Modal.FeedBack;
import com.ia.web.Modal.FeedBackAttachment;
import com.ia.web.Modal.Project;
import com.ia.web.Modal.ProjectType;
import com.ia.web.Modal.ProjectView;

public interface ProjectDao {

	//Get project list
	List<ProjectView> getProjects();
	
	//Insert new project
	boolean insertProject(Project project);
	
	//Update new project
	boolean updateProject(Project project);
	
	//Update status
	boolean updateProjectStatus(String status,int projectId);
	
	
	//Get project type list
	List<ProjectType> getProjectType(String action);
	
	//Get project detail by id
	Project getProjectById(int projectId);
	
	//Get project list by company and departmet wise
	List<ProjectView> getProjects(int companyId,int departmentId);
	
	
	//Get project feedback
	List<FeedBack> getProjectFeedback(int projectId);

	//Get project feedback attachment
	List<FeedBackAttachment> getFeedbackAttechment(int feedbackId);

	//Insert new feedback
	int insertFeedback(FeedBack feedBack);
	
	//Insert new project
	boolean insertFeedbackAttechment(FeedBackAttachment feedBackAttachment);
	
	
	//Get project feedback
	List<EmailConversion> getProjectEmailConv(int projectId);

	/*//Get project feedback attachment
	List<FeedBackAttachment> getEmailConvAttechment(int feedbackId);*/

	//Insert new feedback
	int insertEmailConv(EmailConversion emailConversion);
	
	//Insert new project
	boolean insertEmailConvAttechment(int emailConId,String filePath);
		
	
}
