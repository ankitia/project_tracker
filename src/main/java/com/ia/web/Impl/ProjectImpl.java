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
import com.ia.web.Modal.EmailConversion;
import com.ia.web.Modal.FeedBack;
import com.ia.web.Modal.FeedBackAttachment;
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
				project.setProjectOwner(rs.getString("project_owner"));
				project.setStartDate(rs.getString("start_date"));
				project.setEstimateTarget(rs.getString("estimate_target"));
				project.setDeliverySchedule(rs.getString("delivery_schedule"));
				project.setDeliveredTillNow(rs.getString("delivered_till_now"));
				project.setDelivery(rs.getString("delivery"));
				project.setNextUpdateDate(rs.getString("next_update_date"));
				project.setResources(rs.getString("resources"));
				project.setNotes(rs.getString("notes"));
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
					project.setDeliveredTillNow(rs.getString("delivered_till_now"));
					project.setDelivery(rs.getString("delivery"));
					project.setNextUpdateDate(rs.getString("next_update_date"));
					project.setResources(rs.getString("resources"));
					project.setNotes(rs.getString("notes"));
					project.setCreatedBy(rs.getInt("created_by"));
					
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return project;
	}

	@Override
	public boolean updateProject(Project project) {
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("updateProject"))){
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
			ps.setString(19, project.getDeliveredTillNow());
			ps.setString(20, project.getDelivery());
			ps.setString(21, project.getNextUpdateDate());
			ps.setString(22, project.getResources());
			ps.setString(23, project.getNotes());
			ps.setInt(24, project.getProjectId());
			
			
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
	public List<ProjectView> getProjects(int companyId, int departmentId) {
		List<ProjectView> projects = new ArrayList<>();
		 
		String query = userDao.getString("getProjects");
		try(PreparedStatement ps = con.prepareStatement(query);)
		{
			ps.setInt(1, companyId);
			ps.setInt(2, departmentId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ProjectView project = new ProjectView();
				project.setProjectId(rs.getInt("project_id"));
				project.setName(rs.getString("name"));
				project.setProjectCode(rs.getString("project_code"));
				project.setProjDesc(rs.getString("project_desc"));
				projects.add(project);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public List<FeedBack> getProjectFeedback(int projectId) {
		List<FeedBack> feedBacks = new ArrayList<>();
		 
		String query = userDao.getString("getFeedBack");
		try(PreparedStatement ps = con.prepareStatement(query);
			){
			ps.setInt(1, projectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FeedBack feedBack = new FeedBack();
				feedBack.setFeedbackId(rs.getInt("project_feedback_id"));
				feedBack.setFeedbackLog(rs.getString("fed_log"));
				feedBack.setEscalationLog(rs.getString("escalation_log"));
				feedBack.setProjectId(rs.getInt("project_feedback_id"));
				feedBack.setCreatedBy(rs.getInt("created_by"));
				feedBack.setCreatedDate(rs.getString("created_date"));
				feedBack.setUserName(rs.getString("username"));
				feedBack.setFullName(rs.getString("fname")+" "+rs.getString("lname"));
				feedBacks.add(feedBack);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return feedBacks;
	}

	@Override
	public List<FeedBackAttachment> getFeedbackAttechment(int feedbackId) {
		List<FeedBackAttachment> feedBacks = new ArrayList<>();
		 
		String query = userDao.getString("getFeedbackAttechment");
		try(PreparedStatement ps = con.prepareStatement(query);
			){
			ps.setInt(1, feedbackId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FeedBackAttachment feedBack = new FeedBackAttachment();
				feedBack.setFeedbackAttachmentId(rs.getInt("feedback_attachment_id"));
				feedBack.setFeedbackId(rs.getInt("feedBack_id"));
				feedBack.setFilePath(rs.getString("file_path"));
				feedBacks.add(feedBack);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return feedBacks;

	}

	@Override
	public boolean insertFeedback(FeedBack feedBack) {
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertFeedback"))){
			ps.setString(1,feedBack.getFeedbackLog());
			ps.setString(2,feedBack.getEscalationLog());
			ps.setInt(3,feedBack.getProjectId());
			ps.setInt(4, feedBack.getCreatedBy());
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
	public boolean insertFeedbackAttechment(FeedBackAttachment feedBackAttachment) {
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertFeedbackAttechment"))){
			ps.setString(1,feedBackAttachment.getFilePath());
			ps.setInt(2,feedBackAttachment.getFeedbackId());
			
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
	public List<EmailConversion> getProjectEmailConv(int projectId) {
		List<EmailConversion> conversions = new ArrayList<>();
		 
		String query = userDao.getString("getProjectEmailConv");
		try(PreparedStatement ps = con.prepareStatement(query);
			){
			ps.setInt(1, projectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmailConversion feedBack = new EmailConversion();
				feedBack.setProjectId(rs.getInt("project_id"));
				feedBack.setEmailLog(rs.getString("email_log"));
				feedBack.setCreatedBy(rs.getInt("created_by"));
				feedBack.setCreatedDate(rs.getString("created_date"));
				feedBack.setUserName(rs.getString("username"));
				feedBack.setFullName(rs.getString("fname")+" "+rs.getString("lname"));
				conversions.add(feedBack);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conversions;
	}

	@Override
	public boolean insertEmailConv(EmailConversion emailConversion) {
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertEmailConv"))){
			ps.setString(1,emailConversion.getEmailLog());
			ps.setInt(2,emailConversion.getProjectId());
			ps.setInt(3, emailConversion.getCreatedBy());
			int status = ps.executeUpdate();

			if(status>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	
}
