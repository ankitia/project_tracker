
package com.ia.web.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ia.config.CommonUtility;
import com.ia.web.Dao.CompanyDao;
import com.ia.web.Dao.DepartmentDao;
import com.ia.web.Dao.PersonContactDao;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.CompanyMaster;
import com.ia.web.Modal.DepartmentMaster;
import com.ia.web.Modal.EmailConversion;
import com.ia.web.Modal.FeedBack;
import com.ia.web.Modal.FeedBackAttachment;
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
  
	@RequestMapping(value = "/getCompanyList")
	@ResponseBody public List<CompanyMaster> getCompanyList() {
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
	 public String insertProjects(Project project,HttpSession session,MultipartHttpServletRequest multipartRequest,HttpServletRequest request,FeedBack feedBack){
		
		   project.setStartDate(CommonUtility.getDate(project.getStartDate()));
		   project.setDeliveryDate(CommonUtility.getDate(project.getDeliveryDate()));
		
		   MultipartFile file = multipartRequest.getFile("exampleInputFile") ;
		   String dataDirectory = request.getServletContext().getRealPath("/resources/upload/");
		   if(!file.isEmpty()) {
			   project.setSopPath(CommonUtility.fileUpload(file,dataDirectory));
		   }
		   
		   
		 projectDao.insertProject(project);
		 
		 
		 
		 
		 
		 /*MultipartFile filePath = multipartRequest.getFile("filePath") ;
		    
		 dataDirectory = request.getServletContext().getRealPath("/resources/feedback/");
		 if(!filePath.isEmpty()) {
			 FeedBackAttachment attachment = new FeedBackAttachment();
			 System.out.println(filePath.getOriginalFilename());
			 attachment.setFeedbackId(25);
			 attachment.setFilePath(CommonUtility.fileUpload(filePath,dataDirectory));
			 projectDao.insertFeedbackAttechment(attachment); 
		   }*/
		 
		 
		 
		 return "redirect:dashboard";
	}
	
	 @RequestMapping(value="/insertProject")
	 public String insertProject(MultipartHttpServletRequest multipartRequest,Project project,HttpSession session,HttpServletRequest request){

		   int userId = (Integer) session.getAttribute("userId");
		 
		   project.setStartDate(CommonUtility.getDate(project.getStartDate()));
		   project.setNextUpdateDate(CommonUtility.getDate(project.getNextUpdateDate()));
		   project.setDeliveryDate(CommonUtility.getDate(project.getDeliveryDate()));
		   
		   
		   MultipartFile file = multipartRequest.getFile("exampleInputFile") ;
		   String dataDirectory = request.getServletContext().getRealPath("/resources/upload/");
		   if(!file.isEmpty()) {
			   project.setSopPath(CommonUtility.fileUpload(file,dataDirectory));
		   }
		   
		 
		   
		   
		   
		 project.setCreatedBy(userId);
		 
		 System.out.println("Project Id ::"+project.getProjectId());
		 
		 if(project.getProjectId()>0) {
			 projectDao.updateProject(project);
			 
			  
			 boolean status = false;
			 String feedbackLog = request.getParameter("feedbackLog");
			 if(!feedbackLog.equalsIgnoreCase("")) {
				  
				 FeedBack feedBack = new FeedBack();
				 feedBack.setFeedbackLog(feedbackLog);
				 feedBack.setProjectId(project.getProjectId());
				 feedBack.setCreatedBy(userId);
				 int feedbackId = projectDao.insertFeedback(feedBack);	 
				 
				 //dataDirectory = request.getServletContext().getRealPath("/resources/feedback/"+userId+"/");
				 MultipartFile filePath = multipartRequest.getFile("filePath") ;
				    
				 dataDirectory = request.getServletContext().getRealPath("/resources/feedback/");
				 if(!filePath.isEmpty()) {
					 FeedBackAttachment attachment = new FeedBackAttachment();
					 System.out.println(filePath.getOriginalFilename());
					 attachment.setFeedbackId(feedbackId);
					 attachment.setFilePath(CommonUtility.fileUpload(filePath,dataDirectory));
					 projectDao.insertFeedbackAttechment(attachment); 
				   }
				 
				 status = true;
			 }
			 
			 String emailLog = request.getParameter("emailLog");
			  if(!emailLog.equalsIgnoreCase("")) {
				  EmailConversion emailConversion = new EmailConversion();
				  emailConversion.setEmailLog(emailLog);
				 emailConversion.setProjectId(project.getProjectId());
				 emailConversion.setCreatedBy(userId);
				 int emailConvId = projectDao.insertEmailConv(emailConversion);
				 
				 
				 
				 MultipartFile emailfilePath = multipartRequest.getFile("emailfilePath") ;
				    
				 dataDirectory = request.getServletContext().getRealPath("/resources/feedback/");
				 if(!emailfilePath.isEmpty()) {
					 projectDao.insertEmailConvAttechment(emailConvId, CommonUtility.fileUpload(emailfilePath,dataDirectory)); 
				   }
				 
				 status = true;
			 }
			 
			 if(status) {
				 return "redirect:updateProject/"+project.getProjectId();
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
	
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	@ResponseBody public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
	   
		System.out.println("This is call");
		//Authorized user will download the file
		String fileName =  request.getParameter("fileName"); //"tempo.msg"; 
		String dirName  = request.getParameter("dirName"); 
        String dataDirectory = request.getServletContext().getRealPath("/resources/"+dirName+"/");
        Path file = Paths.get(dataDirectory, fileName);
        
        System.out.println(dataDirectory +"---"+fileName);
        System.out.println(Files.exists(file));
        if (Files.exists(file))
        {
        	System.out.println("True");
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
