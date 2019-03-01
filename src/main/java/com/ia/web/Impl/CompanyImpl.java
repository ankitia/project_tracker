package com.ia.web.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.CompanyDao;
import com.ia.web.Modal.CompanyMaster;
import com.mysql.jdbc.Connection;

@Component("companyDao")
public class CompanyImpl implements CompanyDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.company", Locale.getDefault());
	
	@Autowired
	Connection con;
	
	@Override
	public List<CompanyMaster> getCompany(String action) {
		// TODO Auto-generated method stub
		List<CompanyMaster> companyMasters = new ArrayList<>();
		
		String query = "";
		
		if(action.equalsIgnoreCase("all")) {
			query = userDao.getString("getCompany");
		}else if(action.equalsIgnoreCase("active")) {
			query = userDao.getString("getActiveCompany");
		}
		
		try(PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();){
			
			while (rs.next()) {
				CompanyMaster master = new CompanyMaster();
				master.setCompanyId(rs.getInt("company_id"));
				master.setCompanyName(rs.getString("company_name"));
				master.setCompanyDesc(rs.getString("company_desc"));
				master.setStatus(rs.getString("status")); 
				companyMasters.add(master);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return companyMasters;
	}

	@Override
	public boolean insertCompany(CompanyMaster companyMaster) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertCompany"))){
			ps.setString(1,companyMaster.getCompanyName());
			ps.setString(2,companyMaster.getCompanyDesc());
			ps.setInt(3,companyMaster.getCreatedBy());
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
	public boolean updateCompanytatus(String status, int companyId) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("updateCompanytatus"))){
			ps.setString(1,status);
			ps.setInt(2,companyId);
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
