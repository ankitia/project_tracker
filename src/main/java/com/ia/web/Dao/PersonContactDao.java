package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.PersonContact;
import com.ia.web.Modal.UserType;

public interface PersonContactDao {

	//Get PersonContact list
	List<PersonContact> getPersonContact(String action,int typeId);
	
	//Insert new PersonContact
	boolean insertPersonContact(PersonContact personContact);
	
	//Update PersonContact status 
	boolean updatePersonContact(String status,int departmentId);
	
	//Get user type list
	List<UserType> getUserType(String action);
}
