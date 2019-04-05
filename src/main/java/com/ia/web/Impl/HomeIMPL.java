package com.ia.web.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.config.SpringWebConfig;
import com.ia.web.Dao.HomeDAO;
import com.ia.web.Modal.Dataset;
import com.ia.web.Modal.ProjectList;
import com.ia.web.Modal.Scrap;
import com.ia.web.Modal.TempUrl;
import com.ia.web.Modal.User;
import com.mysql.jdbc.Connection;


@Component("homeDao")
public class HomeIMPL implements HomeDAO {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());
	@Autowired
	Connection con;
	
	@Override
	public User checkUser(String userName, String password) throws SQLException {
		// TODO Auto-generated method stub
		//int userId = 0;
		User user = new User();
				
		try{			 
			System.out.println("con----->>>"+con);
			//if(con==null){
				con = SpringWebConfig.connection();
			//}
			System.out.println("con----->>>"+con);
			PreparedStatement statement = con.prepareStatement(userDao.getString("checkUser")); 
			statement.setString(1,userName);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();	
				while (rs.next()) {
					//userId = rs.getInt("user_id");					
					user.setUserId(rs.getInt("user_id"));
					user.setFname(rs.getString("fname"));
					user.setLimit(rs.getInt("limit"));
					user.setLname(rs.getString("lname"));
					user.setServerIp(rs.getString("server_ip"));
					user.setUserName(rs.getString("username"));
					user.setUsageLimit(rs.getInt("usage_limit"));
					user.setUserRoleId(rs.getInt("role_id"));
					user.setPendingRequest(rs.getString("pending_request")==null?0:rs.getInt("pending_request"));
				}			
			}catch (Exception e) {
				
				if(con!=null)
					con.close();
				
				e.printStackTrace();
			}
		return user;
	}

	

	/*@Override
	public ArrayList<TempUrl> getTaskReport(String taskId) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<TempUrl> datasets = new ArrayList<>();
		try {
			
			ps = con.prepareStatement(userDao.getString("getTaskReport"));
			ps.setString(1, taskId);
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				TempUrl tempUrl = new TempUrl();
				tempUrl.setUrl(rs.getString("url")==null?"":rs.getString("url"));
				tempUrl.setUrlName(rs.getString("url_name")==null?"":rs.getString("url_name"));
				tempUrl.setAddress(rs.getString("address")==null?"":rs.getString("address"));
				tempUrl.setFouncation(rs.getString("foundation")==null?"":rs.getString("foundation"));
				tempUrl.setKeyContacts(rs.getString("key_contacts")==null?"":rs.getString("key_contacts"));
				tempUrl.setStatus(rs.getString("status")==null?"":rs.getString("status"));
				tempUrl.setTechInstall(rs.getString("tech_install")==null?"":rs.getString("tech_install"));
				tempUrl.setUrlStatus(rs.getString("url_status")==null?"":rs.getString("url_status"));
				datasets.add(tempUrl);
			}
			
			return datasets;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}*/

	@Override
	public Dataset getDatasetDetails(String taskId,String scrapField) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs =null;
		Dataset dataset = new Dataset();
		try {
			ps = con.prepareStatement(userDao.getString("getDatasetDetails"));
			ps.setString(1, taskId);
			ps.setString(2, scrapField);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setEndTime(rs.getString("end_time")!=null?rs.getString("end_time"):"");
				dataset.setScrapOption(rs.getString("scrap_option")!=null?rs.getString("scrap_option"):"");
				dataset.setStatus(rs.getString("status")!=null?rs.getString("status"):"");
				dataset.setTotalRecord(rs.getInt("total_record"));
				dataset.setTotalUniqueRecord(rs.getInt("total_unique_record"));
				dataset.setStartTime(rs.getString("start_time")!=null?rs.getString("start_time"):"");
				dataset.setProcessStartTime(rs.getString("process_start_time")!=null?rs.getString("process_start_time"):"");
				dataset.setActiveUrl(rs.getString("active_url")!=null?rs.getString("active_url"):"");
				dataset.setProcessStatus(rs.getString("process_status")!=null?rs.getString("process_status"):"");
				dataset.setExistingRecord(rs.getString("existing_count")!=null?rs.getString("existing_count"):"");
				dataset.setUniqueAddress(rs.getInt("unique_url_address"));
				dataset.setTotalAddress(rs.getInt("total_address"));
				dataset.setNoOfRecordScrap(rs.getInt("no_of_record_scrap"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
			
			e.printStackTrace();
		}
		
		return dataset;
	}

	@Override
	public int getCountTechOutput(String taskId, String category,String type) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			if(type.equalsIgnoreCase("techCount")){
				ps = con.prepareStatement(userDao.getString("getCountFoundTechInstall"));
				ps.setString(1, taskId);
			}else if(type.equalsIgnoreCase("others")){ 
				ps = con.prepareStatement(userDao.getString("getCountOtherTechInstall"));	
				ps.setString(1, taskId);
			}else if(type.equalsIgnoreCase("uniqueAddress")){ 
				ps = con.prepareStatement(userDao.getString("getCountUniqueAdrees"));	
				ps.setString(1, taskId);
			}else if(type.equalsIgnoreCase("totalAddress")){ 
				ps = con.prepareStatement(userDao.getString("getCountTotalAddressFound"));	
				ps.setString(1, taskId);
			}
			else { 
				ps = con.prepareStatement(userDao.getString("getCountTechOutput"));	
				ps.setString(1, taskId);
				ps.setString(2, category);
			}
			
				rs = ps.executeQuery();
			
			while (rs.next())
			{
				return rs.getInt("total");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	

	@Override
	public User getUserDetails(int userId) {
		// TODO Auto-generated method stub
		User user = new User();
		try(PreparedStatement statement = con.prepareStatement(userDao.getString("getUserDetails"));){			 
			statement.setInt(1,userId);
			ResultSet rs = statement.executeQuery();	
				while (rs.next()) {
					user.setUserId(rs.getInt("user_id"));
					user.setFname(rs.getString("fname"));
					user.setLimit(rs.getInt("limit"));
					user.setLname(rs.getString("lname"));
					user.setServerIp(rs.getString("server_ip"));
					user.setUserName(rs.getString("username"));
					user.setUsageLimit(rs.getInt("usage_limit"));
					user.setUserRoleId(rs.getInt("role_id"));
					user.setPendingRequest(rs.getString("pending_request")==null?0:rs.getInt("pending_request"));
				}			
			}catch (Exception e) {
				e.printStackTrace();
			}
		return user;
		
		
	}



	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		try(PreparedStatement statement = con.prepareStatement(userDao.getString("getUserList"));
			ResultSet rs = statement.executeQuery();) {
			
			while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setFname(rs.getString("fname"));
			user.setLimit(rs.getInt("limit"));
			user.setLname(rs.getString("lname"));
			user.setServerIp(rs.getString("server_ip"));
			user.setUserName(rs.getString("username"));
			user.setUsageLimit(rs.getInt("usage_limit"));
			user.setPassword(rs.getString("password"));
			user.setUserRoleId(rs.getInt("role_id"));
			user.setPendingRequest(rs.getString("pending_request")==null?0:rs.getInt("pending_request"));
			users.add(user);
			
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return users;
	}



	@Override
	public List<TempUrl> getUrl(int dataSetId, String action) {
		// TODO Auto-generated method stub
		List<TempUrl> tempUrls = new ArrayList<>();
		String query = "";
		if(action.equalsIgnoreCase("InActive")) {
			query =  userDao.getString("inActiveUrl");
		}else if(action.equalsIgnoreCase("InActiveNLP")) {
			query =  userDao.getString("inActiveURLNLP");
		}
		
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, dataSetId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TempUrl tempUrl = new TempUrl();  
				tempUrl.setUrl(rs.getString("url"));
				
				if(action.equalsIgnoreCase("InActive")) {
					tempUrl.setUrlStatus(rs.getString("status_code"));
				}else {
					tempUrl.setUrlStatus(rs.getString("google_status_code"));	
				}
				
				
				
				tempUrls.add(tempUrl);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tempUrls;
	}



	@Override
	public List<ProjectList> downloadFinalList(int userId) {
		// TODO Auto-generated method stub
		List<ProjectList> projectLists = new ArrayList<>();
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("downloadFinalList"));){
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ProjectList list = new ProjectList();
			list.setDataSetId(rs.getInt("data_set_id"));
			list.setTaskId(rs.getString("task_id"));
			list.setProjectName(rs.getString("name"));
			list.setStartDate(rs.getString("start_time"));
			list.setTotalRecord(rs.getString("total_record"));
			list.setWebStatus(rs.getString("process_status"));
			list.setWebActive(rs.getInt("active"));
			list.setWebInActive(rs.getInt("inactive"));
			list.setNlpInActive(rs.getInt("inactive_nlp"));
			list.setNlpActive(rs.getInt("active_nlp"));
			list.setWebStartTime(rs.getString("process_start_time"));
			list.setWebEndTime(rs.getString("end_time"));
			list.setNlpStartTime(rs.getString("start_time_nlp"));
			list.setNlpEndTime(rs.getString("end_time_nlp"));
			list.setNlpStatus(rs.getString("nlp_status"));
			
			projectLists.add(list);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return projectLists;
	}



	@Override
	public boolean insertScrap(Scrap scrap) {
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertScrap"))){
			ps.setString(1,scrap.getName());
			ps.setString(2,scrap.getCurrent_org());
			ps.setString(3,scrap.getCurrent_position());
			ps.setString(4,scrap.getLocation());
			ps.setString(5,scrap.getUrl());
			int status = ps.executeUpdate();
			
			if(status>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}


 

	@Override
	public boolean deleteData(int id, String action, int deletedBy) {
		
		String sqlQuery = "";
		
		if(action.equalsIgnoreCase("company")) {
			sqlQuery = userDao.getString("deleteCompany");
		}else if(action.equalsIgnoreCase("department")) {
			sqlQuery = userDao.getString("deleteDepartment");
		}else if(action.equalsIgnoreCase("type")) {
			sqlQuery = userDao.getString("deleteType");
		}
		try(PreparedStatement ps = con.prepareStatement(sqlQuery)){
			ps.setInt(1,deletedBy);
			ps.setInt(2,id);
			int updateStatus = ps.executeUpdate();
			if(updateStatus>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	
	
}
