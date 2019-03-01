package com.ia.web.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ia.web.Modal.Dataset;
import com.ia.web.Modal.ProjectList;
import com.ia.web.Modal.Scrap;
import com.ia.web.Modal.TempUrl;
import com.ia.web.Modal.User;

public interface HomeDAO {
	
	//Check valid user
	public User checkUser(String userName,String password) throws SQLException;
	
	//Get user deatails
	public User getUserDetails(int userId);
	
	//Get all users
	public List<User> getUserList();
	
	/*//get task wise list
	public ArrayList<TempUrl> getTaskReport(String taskId);*/
	
	//get dataset details 
	public Dataset getDatasetDetails(String taskId,String scrapField) throws Exception;
	
	
	//get total count tech install
	public int getCountTechOutput(String taskId,String category,String type);

	
	//Get deactive url set
	public List<TempUrl> getUrl(int dataSetId,String action);
	
	//All Project List
	public List<ProjectList> downloadFinalList(int userId);
	
	
	public boolean insertScrap(Scrap scrap);
	
	
 }
