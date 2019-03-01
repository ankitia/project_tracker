package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.DepartmentMaster;

public interface DepartmentDao {

	//Get Department list
	List<DepartmentMaster> getDepartment(String action,int companyId);
	
	//Insert new Department
	boolean insertDepartment(DepartmentMaster departmentMaster);
	
	//Update Department status 
	boolean updateDepartmentStatus(String status,int departmentId);
	
}
