package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.CompanyMaster;

public interface CompanyDao {

	//Get Company list
	List<CompanyMaster> getCompany(String action);
	
	//Insert new Company
	boolean insertCompany(CompanyMaster companyMaster);
	
	//Update Company status 
	boolean updateCompanytatus(String status,int companyId);
	
}
