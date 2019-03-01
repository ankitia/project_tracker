package com.ia.web.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.PersonContactDao;
import com.ia.web.Modal.PersonContact;
import com.ia.web.Modal.UserType;
import com.mysql.jdbc.Connection;

@Component("personContactDao")
public class PersonContactImpl implements PersonContactDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.company", Locale.getDefault());
	
	@Autowired
	Connection con;

	@Override
	public List<PersonContact> getPersonContact(String action,int typeId) {
		List<PersonContact> personContacts = new ArrayList<>();
		
		String query = "";
		
		if(action.equalsIgnoreCase("all")) {
			query = userDao.getString("getPersonContact");
		}else if(action.equalsIgnoreCase("active")) {
			query = userDao.getString("getActivePersonContact");
		}
		
		try(PreparedStatement ps = con.prepareStatement(query);){
			ps.setInt(1, typeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PersonContact master = new PersonContact();
				master.setPersonContactId(rs.getInt("person_contact_id"));
				master.setName(rs.getString("name"));
				master.setStatus(rs.getString("status")); 
				personContacts.add(master);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return personContacts;
	}

	@Override
	public boolean insertPersonContact(PersonContact personContact) {
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("insertPersonContact"))){
			ps.setString(1,personContact.getName());
			ps.setInt(2,personContact.getTypeId());
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
	public boolean updatePersonContact(String status, int departmentId) {
		// TODO Auto-generated method stub
		try(PreparedStatement ps = con.prepareStatement(userDao.getString("updatePersonContact"))){
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
	
	@Override
	public List<UserType> getUserType(String action) {
		List<UserType> projects = new ArrayList<>();
		String query = "";
		if(action.equalsIgnoreCase("active")) {
			query = userDao.getString("getActiveUserType");
		}
		
		try(PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();){
			
			while (rs.next()) {
				UserType userType = new UserType();
				userType.setUserTypeId(rs.getInt("user_type_id"));
				userType.setName(rs.getString("name"));
				userType.setStatus(rs.getString("status")); 
				projects.add(userType);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projects;
	}
}
