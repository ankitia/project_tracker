package com.ia.web.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.config.CommonUtility;
import com.ia.web.Dao.DatasetDao;
import com.ia.web.Modal.DataSetHistoryLog;
import com.ia.web.Modal.Dataset;
import com.mysql.jdbc.Connection;

@Component("datasetDao")
public class DatasetImpl implements DatasetDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());

	@Autowired
	Connection con;
	
	@Override
	public ArrayList<Dataset> getDatasetList(int userId) {
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getDatasetList"));
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Dataset dataset = new Dataset();
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setDataSetName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setProcessName(rs.getString("process_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setStartTime(rs.getString("process_start_time"));
				dataset.setEndTime(rs.getString("end_time"));
				dataset.setTotalRecord(rs.getDouble("total_record"));
				dataset.setTotalUniqueRecord(rs.getDouble("total_unique_record"));
				dataset.setExistingRecord(rs.getString("existing_count"));
				datasets.add(dataset);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return datasets;
	}
	
	@Override
	public ArrayList<Dataset> getDatasetList(int userId,int startLimit) {
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			
			PreparedStatement ps = con.prepareStatement(userDao.getString("getDatasetLists"));
			ps.setInt(1, userId);
			ps.setInt(2, (startLimit-1)* CommonUtility.NO_OFF_RECORDS );
			ps.setInt(3, CommonUtility.NO_OFF_RECORDS);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Dataset dataset = new Dataset();
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setDataSetName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setProcessName(rs.getString("process_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setStartTime(rs.getString("start_time"));
				dataset.setEndTime(rs.getString("end_time"));
				dataset.setTotalRecord(rs.getDouble("total_record"));
				dataset.setTotalUniqueRecord(rs.getDouble("total_unique_record"));
				dataset.setTaskId(rs.getString("task_id"));
				dataset.setScrapOption(rs.getString("scrap_option"));
				dataset.setProcessStartTime(rs.getString("process_start_time"));
				dataset.setActiveUrl(rs.getString("active_url"));
				dataset.setProcessStatus(rs.getString("process_status"));
				dataset.setExistingRecord(rs.getString("existing_count"));
				dataset.setScrapCount(rs.getInt("scrap_count"));
				dataset.setOutputFile(rs.getString("output_file")); 
				dataset.setProjectName(rs.getString("project_name"));
				dataset.setStartTimeNlp(rs.getString("start_time_nlp"));
				dataset.setEndTimeNlp(rs.getString("end_time_nlp"));
				dataset.setNlpStatus(rs.getString("nlp_status"));
				datasets.add(dataset);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return datasets;
	}

	@Override
	public boolean insertDataset(Dataset dataset) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertDataset"));
			ps.setInt(1, dataset.getUserId());
			ps.setString(2,dataset.getDataSetName());
			ps.setString(3,dataset.getFileName());
			ps.setString(4,dataset.getProcessName());
			ps.setString(5,dataset.getStatus());
			ps.setDouble(6,dataset.getTotalRecord());
			ps.setString(7,dataset.getScrapOption());
			ps.setDouble(8, dataset.getTotalUniqueRecord());
			ps.setString(9,dataset.getTaskId());
			ps.setInt(10,dataset.getProjectId());
			status = ps.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(status==1) 
			return true;
		else		
			return false;
	}

	@Override
	public int lastInsertedRecord() {
		// TODO Auto-generated method stub
		int dataSetId = 0;
		try {
			PreparedStatement ps = con.prepareStatement(userDao.getString("lastInsertedRecord"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				dataSetId = rs.getInt("data_set_id");				 
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataSetId;
	}

	@Override
	public String getTaskId() {
		// TODO Auto-generated method stub
		String taskId = "";
		try {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getTaskId"));
			ResultSet rs = ps.executeQuery();		
			while (rs.next()) {
				
				if(rs.getString("task")!=null)				
					taskId = "Task_"+rs.getString("task");
				else
					taskId = "Task_1";
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return taskId;
	}

	@Override
	public void loadFile(String filePath,String taskId) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = con.prepareStatement(userDao.getString("loadFileInDb"));
			ps.setString(1,filePath);
			ps.setString(2,taskId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int updateURLStatus(String taskId,String address,String keyContacts,String foundation,String techInstall) {
		// TODO Auto-generated method stub
		
		System.out.println(address +"--"+keyContacts+"---"+techInstall +"---"+foundation);
		
		PreparedStatement ps = null;
		try {
			
			if(address!=null && address!=""){
				ps = con.prepareStatement(userDao.getString("updateURLAddressStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();	
			} if(keyContacts!=null && keyContacts!="") {
				ps = con.prepareStatement(userDao.getString("updateURLKeyContactsStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();				
			}if(foundation!=null && foundation!="") {
				ps = con.prepareStatement(userDao.getString("updateURLFoundationStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();	
			}if(techInstall!=null && techInstall!="") {
				ps = con.prepareStatement(userDao.getString("updateURLTechInstallStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();	
			}
				ps = con.prepareStatement(userDao.getString("updateURLStatus"));
				ps.setString(1, taskId);
				return ps.executeUpdate();	
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateStatus(int dataSetId, String status,int scrapCount) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(userDao.getString("updateStatus"));
			System.out.println(status +" -- --"+dataSetId);
			ps.setString(1,status);
			ps.setInt(2, scrapCount);
			ps.setInt(3, dataSetId);
			return ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			if(ps!=null)
				ps.close();
			e.printStackTrace();
		}
				
		return  0;
	}
	
	@Override
	public ArrayList<DataSetHistoryLog> getDataSetLog(int dataSetId) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<DataSetHistoryLog> datasets = new ArrayList<>();
		try {
			ps = con.prepareStatement(userDao.getString("getDataSetLog"));
			ps.setInt(1, dataSetId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				DataSetHistoryLog dataset = new DataSetHistoryLog();
				dataset.setEndDate(rs.getString("end_time"));
				dataset.setExitstingRecord(rs.getString("existing_count"));
				dataset.setActiveUrl(rs.getString("active_url"));
				dataset.setScrapRecordCount(rs.getString("no_of_record_scrap"));
				dataset.setStartDate(rs.getString("process_start_time"));
				dataset.setTaskId(rs.getString("task_id"));
				dataset.setTotalAddress(rs.getString("total_address"));
				dataset.setTotalRecord(rs.getInt("total_record"));
				dataset.setUniqueAddress(rs.getInt("unique_url_address"));
				datasets.add(dataset);
				
			}
			return datasets;
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		
		return null;
	}

	

	@Override
	public boolean setPendingRequest(int userId,int count) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("setPendingRequest"))){
			ps.setInt(1,count);
			ps.setInt(2,userId);
			int updateStatus = ps.executeUpdate();
			if(updateStatus>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateNlpStatus(int dataSetId) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("updateNlpStatus"))){
			ps.setInt(1,dataSetId);
			 
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
