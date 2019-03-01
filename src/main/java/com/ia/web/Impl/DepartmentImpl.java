package com.ia.web.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.DepartmentDao;
import com.ia.web.Modal.DepartmentMaster;
import com.mysql.jdbc.Connection;

@Component("departmentDao")
public class DepartmentImpl implements DepartmentDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.company", Locale.getDefault());
	
	@Autowired
	Connection con;
	

	@Override
	public List<DepartmentMaster> getDepartment(String action,int companyId) {
			// TODO Auto-generated method stub
			List<DepartmentMaster> companyMasters = new ArrayList<>();
			
			String query = "";
			
			if(action.equalsIgnoreCase("all")) {
				query = userDao.getString("getDepartment");
			}else if(action.equalsIgnoreCase("active")) {
				query = userDao.getString("getActiveDepartment");
			}
			
			try(PreparedStatement ps = con.prepareStatement(query);){
				ps.setInt(1, companyId);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					DepartmentMaster master = new DepartmentMaster();
					master.setDepartmentId(rs.getInt("department_id"));
					master.setCompanyId(rs.getInt("company_id"));
					master.setDepartmentName(rs.getString("department_name"));
					master.setDepartmentDesc(rs.getString("department_desc"));
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
	public boolean insertDepartment(DepartmentMaster departmentMaster) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertDepartment"))){
			ps.setString(1,departmentMaster.getDepartmentName());
			ps.setString(2,departmentMaster.getDepartmentDesc());
			ps.setInt(3,departmentMaster.getCreatedBy());
			ps.setInt(4, departmentMaster.getCompanyId());
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
	public boolean updateDepartmentStatus(String status, int departmentId) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("updateDepartmenttatus"))){
			ps.setString(1,status);
			ps.setInt(2,departmentId);
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
