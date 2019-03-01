package com.ia.web.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.Project;
import com.ia.web.Modal.ProjectType;
import com.ia.web.Modal.ProjectView;
import com.mysql.jdbc.Connection;

@Component("projectDao")
public class ProjectImpl implements ProjectDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());
	
	@Autowired
	Connection con;
	
	@Override
	public List<ProjectView> getProjects() {
		// TODO Auto-generated method stub
		List<ProjectView> projects = new ArrayList<>();
		 
		String query = userDao.getString("getAllProjects");
		try(PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();){
			
			while (rs.next()) {
				ProjectView project = new ProjectView();
				project.setProjectId(rs.getInt("project_id"));
				project.setName(rs.getString("name"));
				project.setProjectCode(rs.getString("project_code"));
				project.setProjDesc(rs.getString("project_desc"));
				project.setPersonToContact(rs.getString("person_name"));
				project.setStartDate(rs.getString("start_date"));
				project.setEstimateTarget(rs.getString("estimate_target"));
				project.setDeliverySchedule(rs.getString("delivery_schedule"));
				projects.add(project);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public boolean insertProject(Project project) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertProject"))){
			ps.setString(1,project.getName());
			ps.setInt(2,project.getCompanyId());
			ps.setInt(3,project.getDepartmentId());
			ps.setInt(4,project.getPersonToContact());
			ps.setString(5,project.getProjectCode());
			ps.setInt(6,project.getProjectType());
			ps.setInt(7,project.getProjectOwner());
			ps.setString(8,project.getStartDate());
			ps.setString(9,project.getDeliveryDate());
			ps.setInt(10,project.getCreatedBy());
			ps.setString(11, project.getProjDesc());
			ps.setString(12, project.getEstimateTarget());
			ps.setString(13, project.getDeliverySchedule());
			ps.setString(14, project.getProjectStatus());
			ps.setString(15, project.getSopDesc());
			ps.setString(16, project.getSopPath());
			ps.setString(17, project.getClientInstruction());
			ps.setString(18, project.getBriefAssociate());
			
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
	public boolean updateProjectStatus(String status, int projectId) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("updateProjectStatus"))){
			ps.setString(1,status);
			ps.setInt(2,projectId);
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
	public List<ProjectType> getProjectType(String action) {
		// TODO Auto-generated method stub
		List<ProjectType> projects = new ArrayList<>();
		
		String query = "";
		if(action.equalsIgnoreCase("active")) {
			query = userDao.getString("getActiveProjectType");
		}
		
		try(PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();){
			
			while (rs.next()) {
				ProjectType project = new ProjectType();
				project.setProjectTypeId(rs.getInt("project_type_id"));
				project.setName(rs.getString("name"));
				project.setStatus(rs.getString("status")); 
				projects.add(project);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public Project getProjectById(int projectId) {
		Project project = new Project();
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("getProjectById"))){
			ps.setInt(1, projectId);
			ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					project.setProjectId(rs.getInt("project_id"));
					project.setName(rs.getString("name"));
					project.setStatus(rs.getString("status")); 
					project.setCompanyId(rs.getInt("company_id"));	
					project.setDepartmentId(rs.getInt("department_id"));	
					project.setPersonToContact(rs.getInt("person_to_contact"));	
					project.setProjectCode(rs.getString("project_code"));
					project.setProjectType(rs.getInt("project_type"));
					project.setProjectOwner(rs.getInt("project_owner"));
					project.setStartDate(rs.getString("start_date"));
					project.setDeliveryDate(rs.getString("delivery_date"));
					project.setSopDesc(rs.getString("sop_desc"));
					project.setSopPath(rs.getString("sop_path"));
					project.setClientInstruction(rs.getString("client_instruction"));
					project.setBriefAssociate(rs.getString("brief_associate"));
					project.setProjDesc(rs.getString("project_desc"));
					project.setEstimateTarget(rs.getString("estimate_target"));
					project.setDeliverySchedule(rs.getString("delivery_schedule"));
					project.setProjectStatus(rs.getString("project_status"));
					
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return project;
	}

	
}
