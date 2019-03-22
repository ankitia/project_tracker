package com.ia.web.Modal;

import org.springframework.web.multipart.MultipartFile;

public class FeedBack {

	private int feedbackId;
	private int projectId;
	private String feedbackLog;
	private int createdBy;
	private String escalationLog;
	private String createdDate;
	private String userName;
	private String fullName;
	private String filePath;
	private MultipartFile filePaths;
	
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getFeedbackLog() {
		return feedbackLog;
	}
	public void setFeedbackLog(String feedbackLog) {
		this.feedbackLog = feedbackLog;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getEscalationLog() {
		return escalationLog;
	}
	public void setEscalationLog(String escalationLog) {
		this.escalationLog = escalationLog;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	 
}
