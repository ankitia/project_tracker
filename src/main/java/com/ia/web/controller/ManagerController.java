package com.ia.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ia.config.CommonUtility;
import com.ia.web.Dao.CompanyDao;
import com.ia.web.Dao.DepartmentDao;
import com.ia.web.Dao.PersonContactDao;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.CompanyMaster;
import com.ia.web.Modal.DepartmentMaster;
import com.ia.web.Modal.EmailConversion;
import com.ia.web.Modal.FeedBack;
import com.ia.web.Modal.PersonContact;
import com.ia.web.Modal.Project;
import com.ia.web.Modal.ProjectView;

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
	
	@RequestMapping(value="/insertProjects", method= RequestMethod.POST)
	 public String insertProjects(Project project,HttpSession session,HttpServletRequest request){
		
		   project.setStartDate(CommonUtility.getDate(project.getStartDate()));
		   project.setDeliveryDate(CommonUtility.getDate(project.getDeliveryDate()));
		
		 projectDao.insertProject(project);
		 return "redirect:dashboard";
	}
	
	 @RequestMapping(value="/insertProject")
	 public String insertProject(@RequestParam("exampleInputFile") MultipartFile file,Project project,HttpSession session,HttpServletRequest request,FeedBack feedBack,EmailConversion emailConversion){

		 System.out.println(project.getStartDate() +""+ project.getName());
		 
		 System.out.println("Request path ::::"+ request.getContextPath()+"/resources/upload");
		 
		 
		   project.setStartDate(CommonUtility.getDate(project.getStartDate()));
		   project.setNextUpdateDate(CommonUtility.getDate(project.getNextUpdateDate()));
		   project.setDeliveryDate(CommonUtility.getDate(project.getDeliveryDate()));
		 
		   if (!file.isEmpty()) {
			   project.setSopPath(CommonUtility.fileUpload(file));
		   }
		   
		 	/*String path = "";
			String orignalFileName = file.getName();

			if (!file.isEmpty()) {
				try {
					orignalFileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					path = rootPath + File.separator + "tmpFiles";
					String rootPath = request.getContextPath()+"/resources/";
					path = rootPath + File.separator + "upload";
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
			}*/
		 
		 
		 project.setCreatedBy((Integer) session.getAttribute("userId"));
		 
		 System.out.println("Project Id ::"+project.getProjectId());
		 
		 if(project.getProjectId()>0) {
			 projectDao.updateProject(project);
			 
			  
			 boolean status = false;
			 if(!feedBack.getFeedbackLog().equalsIgnoreCase("")) {
				 /* Add new feedback */
				 feedBack.setProjectId(project.getProjectId());
				 feedBack.setCreatedBy((Integer) session.getAttribute("userId"));
				 projectDao.insertFeedback(feedBack);	 
				 status = true;
			 }
			 
			 if(!emailConversion.getEmailLog().equalsIgnoreCase("")) {
				 emailConversion.setProjectId(project.getProjectId());
				 emailConversion.setCreatedBy((Integer) session.getAttribute("userId"));
				 projectDao.insertEmailConv(emailConversion);
				 status = true;
			 }
			 
			 if(status) {
				 return "redirect:updateProject/"+feedBack.getProjectId();
			 }
			 
			 
		 }else {
			 projectDao.insertProject(project); 
		 }
		 
		  return "redirect:dashboard";
		 
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
			 
			model.addAttribute("feedbackList",projectDao.getProjectFeedback(Integer.parseInt(projectId)));
			
			model.addAttribute("emailList",projectDao.getProjectEmailConv(Integer.parseInt(projectId)));
			
		return "front/update_project";
		}
		
	
	@RequestMapping(value = "/personContact")
	public String personContact(Model model) {
		
		
		/*model.addAttribute("personList",personContactDao.getPersonContact("all"));*/
		model.addAttribute("userTypeList",personContactDao.getUserType("active"));
		
		
		
	return "front/person_to_contact";
	}
	

	@RequestMapping(value = "/feedback")
	public String feedback(Model model) {
		model.addAttribute("companyList",companyDao.getCompany("all"));		
	return "front/feedback";
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
	
	@RequestMapping(value = "/getProjectList")
	@ResponseBody public List<ProjectView> getProjectList(ProjectView projectView) {
		
		System.out.println(projectView.getCompanyId() +"-------------------"+projectView.getDepartmentId());
		
		return projectDao.getProjects(Integer.parseInt(projectView.getCompanyId()), Integer.parseInt(projectView.getDepartmentId()));
	}
	
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
	public void getFile(HttpServletRequest request, @PathVariable("file_name") String fileName, HttpServletResponse response) {
	   
		
		//Authorized user will download the file
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		
	}
	
}
