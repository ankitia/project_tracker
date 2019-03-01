package com.ia.web.Modal;

public class PersonContact 
{

	private int personContactId;
	private String name;
	private String status;
	private int typeId;
	
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getPersonContactId() {
		return personContactId;
	}
	public void setPersonContactId(int personContactId) {
		this.personContactId = personContactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
