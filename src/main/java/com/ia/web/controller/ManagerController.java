package com.ia.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ia.web.Dao.CompanyDao;
import com.ia.web.Dao.DepartmentDao;
import com.ia.web.Dao.PersonContactDao;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.CompanyMaster;
import com.ia.web.Modal.DepartmentMaster;
import com.ia.web.Modal.PersonContact;
import com.ia.web.Modal.Project;

@Controller
public class ManagerController {

	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	PersonContactDao personContactDao;
	
	@Autowired
	ProjectDao projectDao; 
	
	
	/* Start Manage  Company */
    @RequestMapping(value = "/company")
	public String company(Model model) {
		
		model.addAttribute("companyList",companyDao.getCompany("all"));
		
	return "front/company";
	}
	
	@RequestMapping(value = "/manageCompany")
	@ResponseBody public List<CompanyMaster> manageCompany(CompanyMaster companyMaster,HttpSession session,String action) {
		
		
		System.out.println(companyMaster.getCompanyName()+"------------");
		companyMaster.setCreatedBy((Integer) session.getAttribute("userId"));
		if(action.equalsIgnoreCase("insert")) {
			companyDao.insertCompany(companyMaster);
		}else if(action.equalsIgnoreCase("update")) {
			companyDao.updateCompanytatus(companyMaster.getStatus(), companyMaster.getCompanyId());
		}
				
		return companyDao.getCompany("all");
	
	}
  
	/* End Manage  Company */
	
	/* Start Manage  Department */
	
	@RequestMapping(value = "/department")
	public String department(Model model) {
		model.addAttribute("companyList",companyDao.getCompany("all"));
	return "front/department";
	}
	
	@RequestMapping(value = "/getDepartment")
	@ResponseBody public List<DepartmentMaster> getDepartment(DepartmentMaster departmentMaster) {
		return departmentDao.getDepartment("all",departmentMaster.getCompanyId());
	}
	
	@RequestMapping(value = "/manageDepartment")
	@ResponseBody public String manageDepartment(DepartmentMaster departmentMaster,HttpSession session,String action) {
		System.out.println(departmentMaster.getDepartmentName()+"------------");
		departmentMaster.setCreatedBy((Integer) session.getAttribute("userId"));
		if(action.equalsIgnoreCase("insert")) {
			departmentDao.insertDepartment(departmentMaster);
		}else if(action.equalsIgnoreCase("update")) {
			departmentDao.updateDepartmentStatus(departmentMaster.getStatus(), departmentMaster.getDepartmentId());
		}
		
		return true+"";
	}
	
	
	
	/* End Manage  Department */
	
	@RequestMapping(value = "/manageProject")
	public String manageProject(Model model) {
		
		model.addAttribute("companyList",companyDao.getCompany("all"));
		model.addAttribute("projectTypeList",projectDao.getProjectType("active"));
		
		model.addAttribute("personContactList", personContactDao.getPersonContact("active", 1));
		model.addAttribute("projectOwnerList", personContactDao.getPersonContact("active", 2));
		model.addAttribute("taskOwnerList", personContactDao.getPersonContact("active", 3));
		 
		
	return "front/manage_project";
	}
	
	 @RequestMapping(value="/insertProject")
	 @ResponseBody public boolean insertProject(@RequestParam("exampleInputFile") MultipartFile file,Project project,HttpSession session){

		 System.out.println(project.getStartDate() +""+ project.getName());
		 
		 	String path = "";
			String orignalFileName = file.getName();

			if (!file.isEmpty()) {
				try {
					orignalFileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					path = rootPath + File.separator + "tmpFiles";
					File dir = new File(path);
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					path = dir.getAbsolutePath() + File.separator + orignalFileName;
					File serverFile = new File(dir.getAbsolutePath() + File.separator + orignalFileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					//System.out.println(ext+"---path-->"+path+"----"+orignalFileName+"--------------"+dir.getAbsolutePath());
					System.out.println("Server File Location="+ serverFile.getAbsolutePath());
					project.setSopPath(serverFile.getAbsolutePath());
					//return "You successfully uploaded file=" + name;
				} catch (Exception e) {
					//return "You failed to upload " + name + " => " + e.getMessage();
					System.out.println("Error ---"+e);
				}
			} else {
				//return "You failed to upload " + name + " because the file was empty.";
				System.out.println("File is empty");
			}
		 
		 
		 project.setCreatedBy((Integer) session.getAttribute("userId"));
		 return projectDao.insertProject(project);
		 
	 }
	
	 
	 @RequestMapping(value = "/updateProject/{projectId}")
		public String updateProject(Model model,@PathVariable("projectId")String projectId) {
			
		 	System.out.println(projectId);
		 	
		 	Project project =projectDao.getProjectById(Integer.parseInt(projectId));
		 	
		 	model.addAttribute("project",project);
		 
			model.addAttribute("companyList",companyDao.getCompany("all"));
			
			model.addAttribute("departmentList",departmentDao.getDepartment("all",project.getCompanyId()));
			
			
			model.addAttribute("projectTypeList",projectDao.getProjectType("active"));
			
			model.addAttribute("personContactList", personContactDao.getPersonContact("active", 1));
			model.addAttribute("projectOwnerList", personContactDao.getPersonContact("active", 2));
			model.addAttribute("taskOwnerList", personContactDao.getPersonContact("active", 3));
			 
			
		return "front/update_project";
		}
		
	
	@RequestMapping(value = "/personContact")
	public String personContact(Model model) {
		
		
		/*model.addAttribute("personList",personContactDao.getPersonContact("all"));*/
		model.addAttribute("userTypeList",personContactDao.getUserType("active"));
		
		
		
	return "front/person_to_contact";
	}
	
	@RequestMapping(value = "/getPersonContact")
	@ResponseBody public List<PersonContact> getPersonContact(PersonContact personContact) {
		return personContactDao.getPersonContact("all",personContact.getTypeId());
	}
	
	@RequestMapping(value = "/managePerson")
	@ResponseBody public String managePerson(PersonContact personContact,HttpSession session,String action) {
		
		//departmentMaster.setCreatedBy((Integer) session.getAttribute("userId"));
		if(action.equalsIgnoreCase("insert")) {
			personContactDao.insertPersonContact(personContact);
		}else if(action.equalsIgnoreCase("update")) {
			personContactDao.updatePersonContact(personContact.getStatus(),personContact.getPersonContactId());
		}
		 
		return true+"";
	}
}
