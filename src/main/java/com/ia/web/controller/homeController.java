package com.ia.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


import com.ia.config.CommonUtility;
import com.ia.web.Dao.DatasetDao;
import com.ia.web.Dao.HomeDAO;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.Dataset;
import com.ia.web.Modal.ExceptionResponse;
import com.ia.web.Modal.ProjectList;
import com.ia.web.Modal.ProjectView;
import com.ia.web.Modal.Scrap;
import com.ia.web.Modal.TempUrl;
import com.ia.web.Modal.User;

@Controller
public class homeController {

	
	@Autowired
	HomeDAO homeDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	/*@Qualifier("datasetDao")*/
	DatasetDao datasetDao;
	 
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpServletRequest request) {
		 
		
		
		System.out.println("Path---"+request.getContextPath()+"/resources/upload");
		
		return "login";
	}

 
	
	//Check valid user
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST )
	public String checkUser(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		 User user = homeDao.checkUser(request.getParameter("Email"), request.getParameter("password"));		
		 if(user!=null) {		 
			 session.setAttribute("userName", request.getParameter("Email"));
			 session.setAttribute("userId", user.getUserId());
			 CommonUtility.LOGIN_USER_ID = user.getUserId();
			 CommonUtility.USER_ROLE_ID = user.getUserRoleId();
			 
			 User updateUser = homeDao.getUserDetails(user.getUserId());			 
			 context.setAttribute("userDetails",updateUser);
			 
			 //return "redirect:front";
			 return "redirect:dashboard"; 
		 }else {
			 model.addAttribute("message","Please enter valid username and password");
			 return "login";
		 }	
	}
	
	@RequestMapping(value = "/dashboard")
	public ModelAndView dashboard(Model model) {
		
		model.addAttribute("projectList", projectDao.getProjects());
		
		System.out.println(projectDao.getProjects().size());
		
		return new ModelAndView("front/dashboard");
	}
	 
	//@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/getData", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	@ResponseBody public String getData(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("This ia call-------------------------------------");
		
		System.out.println("Get data" + request.getParameter("name"));
		
		Scrap scrap = new Scrap();
		scrap.setName(request.getParameter("name"));
		scrap.setCurrent_org(request.getParameter("current_org"));
		scrap.setCurrent_position(request.getParameter("current_position"));
		scrap.setLocation(request.getParameter("location"));
		scrap.setUrl(request.getParameter("url"));
		
		homeDao.insertScrap(scrap);
		
 
		return "true";
	
	}
  
	@RequestMapping(value="test")
	@ResponseBody String  testData(HttpServletResponse response) {
		 
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	        
	        return "test";
	}
	
	
	@RequestMapping(value ="/dataSet/{page}")  
	public String dataSet(Model model,@PathVariable("page") int page) {
		
		System.out.println("data set");
		
		double totalRecord =   datasetDao.getDatasetList(CommonUtility.LOGIN_USER_ID).size();
		int totalPage = 0;
		if((totalRecord % CommonUtility.NO_OFF_RECORDS) > 0) {
			totalPage =  (int) (totalRecord / CommonUtility.NO_OFF_RECORDS) +1;
		}else
			totalPage =  (int) (totalRecord / CommonUtility.NO_OFF_RECORDS) ;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page",page);
		model.addAttribute("dataSetList",datasetDao.getDatasetList(page));	
		return "dataSet";
	}
	
	
	@RequestMapping(value = "/insertDataset"  )
	public String insertDataset(@RequestParam("exampleInputFile") MultipartFile file,HttpServletRequest request,HttpSession session,Model model) {
		
		String taskId = datasetDao.getTaskId();
		
		/*String address = request.getParameter("address");
		String keyContacts = request.getParameter("keyContacts");
		String foundation = request.getParameter("foundation");*/
		/*String techInstall = "";*/
		String productService = request.getParameter("productService");
		String all = request.getParameter("all");
		
		
		
		System.out.println("--"+all);
		
		String name = request.getParameter("dataSetName");
		String projectId = request.getParameter("project_id");
		String path = "";
		String orignalFileName = file.getName();
		if (!file.isEmpty()) {
			try {
				orignalFileName = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				/*String rootPath = System.getProperty("catalina.home");
				path = rootPath + File.separator + "tmpFiles";*/
				
				String rootPath = request.getContextPath()+"/resources/";
				path = rootPath + File.separator + "upload";
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();

				
				System.out.println("Request path ::::"+path);
				
				// Create the file on server
				path = dir.getAbsolutePath() + File.separator + orignalFileName;
				File serverFile = new File(dir.getAbsolutePath() + File.separator + orignalFileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//System.out.println(ext+"---path-->"+path+"----"+orignalFileName+"--------------"+dir.getAbsolutePath());
				System.out.println("Server File Location="+ serverFile.getAbsolutePath());
				//return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				//return "You failed to upload " + name + " => " + e.getMessage();
				System.out.println("Error ---"+e);
			}
		} else {
			//return "You failed to upload " + name + " because the file was empty.";
			System.out.println("File is empty");
		}
		  
		Dataset dataset = new Dataset();
		dataset.setDataSetName(name);
		dataset.setFileName(path);
		dataset.setProcessName(name);
		dataset.setStatus("Pending");
		
		dataset.setUserId((Integer) session.getAttribute("userId"));
		//dataset.setScrapOption(request.getParameter("optionsRadios"));
		//dataset.setTotalUniqueRecord(unique.size());
		dataset.setTaskId(taskId);
		dataset.setProjectId(Integer.parseInt(projectId));
		//homeDao.insertDataset(dataset);
		
				
		
		/*if(address!=null || all!=null) {
			dataset.setScrapOption("Address");
			homeDao.insertDataset(dataset);
		} 
		if(keyContacts!=null || all!=null) {
			dataset.setScrapOption("Key Contacts");
			homeDao.insertDataset(dataset);
		}
		if(foundation!=null || all!=null) {
			dataset.setScrapOption("Foundation");
			homeDao.insertDataset(dataset);
		}*/
		//model.addAttribute("projectList", projectDao.getProjects("active"));
		System.out.println("Path :::"+path);
		if(CommonUtility.checkUniqueDomain(path)) {
			
			dataset.setTotalRecord(CommonUtility.TOTAL_RECEORD);
			
			User user = (User) context.getAttribute("userDetails");
			
			
			if(CommonUtility.TOTAL_RECEORD <= (user.getLimit()- (user.getUsageLimit()+user.getPendingRequest()))){
				if(productService!=null) {
					
					int totalPendingRequest = (int) (CommonUtility.TOTAL_RECEORD+user.getPendingRequest());
					datasetDao.setPendingRequest(user.getUserId(),totalPendingRequest);
					
					dataset.setScrapOption("productService");
					datasetDao.insertDataset(dataset);
					
					System.out.println("User details :::"+user.getUserId());
					
					user = homeDao.getUserDetails(user.getUserId());
					
					System.out.println("User details :::"+user.getUserId()+"---"+user.getFname());
					
					context.setAttribute("userDetails",user);
				}	
			}else { 
				model.addAttribute("message","Request size exceed");
				return "front/new_scraping_request";
			}
			
			
			
		}else {
			
			model.addAttribute("message","Duplicate record in file please check");
			
			return "front/new_scraping_request";
		}
		
		
		
		//homeDao.loadFile(path, taskId);
		//homeDao.updateURLStatus(taskId,address,keyContacts,foundation,techInstall);
		
		
		/*Thread thread = new InterpreterExample(homeDao.lastInsertedRecord(),path);
		thread.start();*/
		
		return "redirect:company";
	}
	
	@RequestMapping(value = "/logout")
	public RedirectView logout(HttpSession session,HttpServletRequest request) {
		session.setAttribute("userName",null);  
		return new RedirectView(request.getContextPath()+"/");
	}
	
	
	@RequestMapping(value = "/front")
	public String front() {
		
		System.out.println("front end");
		
	return "front/index";
	}
	
	
	
	//insertcompany
	
	@RequestMapping(value = "/newSearch")
	public String newSearch() {
		
		System.out.println("newSearch end");
		
	return "front/new_search";
	}
	
	@RequestMapping(value = "/newScrapingRequest")
	public String newScrapingRequest(Model model) {
		//model.addAttribute("projectList", projectDao.getProjects("active"));
		
	return "front/new_scraping_request";
	}
	
	@RequestMapping(value = "/integration")
	public String integration() {
		
		System.out.println("new_scraping_request end");
		
	return "front/integration";
	}
	
	@RequestMapping(value = "/integrationRequest")
	public String integrationRequest() {
		
		System.out.println("new_integration_request end");
		
	return "front/new_integration_request";
	}
	
	@RequestMapping(value = "/updateDataset", method = RequestMethod.POST)
	@ResponseBody public void updateDataset(HttpServletRequest request) throws NumberFormatException, SQLException {
		datasetDao.updateStatus(Integer.parseInt(request.getParameter("dataSetId")),request.getParameter("status"),Integer.parseInt(request.getParameter("scrapCount")));
	}
	
	@RequestMapping(value = "/updateNLPDataset", method = RequestMethod.POST)
	@ResponseBody public String updateNLPDataset(HttpServletRequest request) throws NumberFormatException, SQLException {
		return datasetDao.updateNlpStatus(Integer.parseInt(request.getParameter("dataSetId")))+"";
	}
	
	/* @RequestMapping(value = "/downloadCSV")
	    public void downloadCSV(HttpServletResponse response,HttpServletRequest request) throws Exception {
	 
		 String scrapField = request.getParameter("scrapField");
		 String taskId = request.getParameter("taskId");
		 
		 Dataset dataset = homeDao.getDatasetDetails(taskId, scrapField);
		 
		 ArrayList<TempUrl> tempUrls = homeDao.getTaskReport(taskId);
		 System.out.println("scrapField--->"+scrapField);
		 response.setContentType("text/csv; charset=UTF-8");
		 response.setCharacterEncoding("UTF-8");
			String reportName = taskId+"_Report.csv";
			response.setHeader("Content-disposition", "attachment;filename="+reportName);
	 
			ArrayList<String> rows = new ArrayList<String>();
			String existingRecord = "0";
			if(dataset.getExistingRecord()!=null)
				existingRecord = dataset.getExistingRecord();	
			
			if(scrapField.equalsIgnoreCase("Address")) {
				rows.add("\t\t  ADDRESS  RESULT \n\n");
				
				
				ArrayList<DataSetHistoryLog> dataSetHistoryLogs = datasetDao.getDataSetLog(dataset.getDataSetId());
				System.out.println(dataset.getDataSetId()+"::: Size :::"+dataSetHistoryLogs.size());
				
				for (int i = 0; i < dataSetHistoryLogs.size(); i++) {
					
					rows.add("Scraping Task ID \t "+ taskId +" \t Processing Date \t"+ dataSetHistoryLogs.get(i).getStartDate() +" \n");
					rows.add("No of Reconrds \t "+ dataSetHistoryLogs.get(i).getTotalRecord() +" \t Processing Time Taken \t"+ dataSetHistoryLogs.get(i).getEndDate() +"  \n"); 
					rows.add("No of Active Records \t "+ dataSetHistoryLogs.get(i).getActiveUrl() +" \t No of Existing Records \t "+  dataSetHistoryLogs.get(i).getExitstingRecord()  +"  \n");
					rows.add("No of Reconrds sent to Scraping \t "+ dataSetHistoryLogs.get(i).getScrapRecordCount() +" \t Unique no of URL having atleast one address \t "+ dataSetHistoryLogs.get(i).getUniqueAddress() +" \n");
					rows.add("Address Completeness % \t \t Total Number of Address Found \t "+ dataSetHistoryLogs.get(i).getTotalAddress() +" \n\n\n\n");
				}
				
				rows.add("Scraping Task ID \t "+ taskId +" \t Processing Date \t"+ dataset.getProcessStartTime() +" \n");
				rows.add("No of Reconrds \t "+ dataset.getTotalRecord() +" \t Processing Time Taken \t"+ dataset.getEndTime() +"  \n"); 
				rows.add("No of Active Records \t "+ dataset.getActiveUrl() +" \t No of Existing Records \t "+  existingRecord  +"  \n");
				rows.add("No of Reconrds sent to Scraping \t "+ dataset.getNoOfRecordScrap() +" \t Unique no of URL having atleast one address \t "+ dataset.getUniqueAddress() +" \n");
				rows.add("Address Completeness % \t \t Total Number of Address Found \t "+ dataset.getTotalAddress() +" \n");
				
			}else if(scrapField.equalsIgnoreCase("Key Contacts")) {
				 
			}else if(scrapField.equalsIgnoreCase("Foundation")) {
				rows.add("\t\t  TECH INSTALLED MAPPING RESULTS \n\n"); 
				rows.add("Scraping Task ID \t "+ taskId +" \t Processing Date \t"+ dataset.getProcessStartTime() +" \n");
				rows.add("No of Reconrds \t "+ dataset.getTotalRecord() +" \t Processing Time Taken \t"+ dataset.getEndTime() +"  \n"); 
				rows.add("No of Active Records \t "+ dataset.getActiveUrl() +" \t No of Existing Records \t "+ existingRecord  +"  \n");
				rows.add("No of URL Tech Installed Found \t "+ homeDao.getCountTechOutput(taskId, "","techCount") +" \t No of URL with Ecommerce/Payment etc \t "+ homeDao.getCountTechOutput(taskId, "E-Commerce","") +" \n");
				rows.add("No of URL with content Management \t "+ homeDao.getCountTechOutput(taskId, "Content Management System","") +" \t No of URL with Email/Server etc \t "+ homeDao.getCountTechOutput(taskId, "Server","") +" \n");
				rows.add("No of URL with Analytics \t "+ homeDao.getCountTechOutput(taskId, "Analytics & Tracking","") +" \t No of URL with other tech installed \t  "+homeDao.getCountTechOutput(taskId, "","others"));
				
			}else if(scrapField.equalsIgnoreCase("Tech Install")) {
				 
			}
			
			rows.add("\n\n\n\n");
			
			rows.add("URL,URL Name,Status,URL Status,"+scrapField);
			rows.add("\n");
	 
			for (int i = 0; i < tempUrls.size(); i++) {
				
				String scrapValue = "";
				if(scrapField.equalsIgnoreCase("Address")) {
					scrapValue = tempUrls.get(i).getAddress();	
				}else if(scrapField.equalsIgnoreCase("Key Contacts")) {
					scrapValue = tempUrls.get(i).getKeyContacts();
				}else if(scrapField.equalsIgnoreCase("Foundation")) {
					scrapValue = tempUrls.get(i).getFouncation();
				}else if(scrapField.equalsIgnoreCase("Tech Install")) {
					scrapValue = tempUrls.get(i).getTechInstall();
				}
				
				rows.add(""+tempUrls.get(i).getUrl()+","+tempUrls.get(i).getUrlName()+","+tempUrls.get(i).getStatus()+","+tempUrls.get(i).getUrlStatus()+","+scrapValue);
				rows.add("\n");
				
			}
			
			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				
				//System.out.println("outputString--->"+outputString);
				try{
					response.getOutputStream().print(outputString);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
	 
			response.getOutputStream().flush();
	    }*/
	 
	 
	 @RequestMapping(value="/userSettings")
	 public String userSettings(Model model) {
		 
		 User updateUser = (User) context.getAttribute("userDetails");
		 User user = homeDao.getUserDetails(updateUser.getUserId());			 
		 context.setAttribute("userDetails",user);
		 
		 if(user.getUserRoleId()==1) {
			 model.addAttribute("userList",homeDao.getUserList());
		 }
		 
		 
		 return "front/user_settings";
	 }
	 
	 @RequestMapping(value="/project")
	 public String project(Model model) {
		 
		 model.addAttribute("projectList", projectDao.getProjects());
		 
		 return "front/project";
	 }
	 
	
	 
	 @RequestMapping(value="/updateProjectStatus")
	 @ResponseBody public String updateProjectStatus(HttpServletRequest request,HttpSession session){
		 return projectDao.updateProjectStatus(request.getParameter("status"),Integer.parseInt(request.getParameter("projectId")))+"";
		 
	 }
		 
	 
	 @RequestMapping(value="/downloadFinalList")
	 @ResponseBody public void downloadFinalList(HttpServletResponse response) {
		 String csvFileName = "Project_summary.csv";
		 
		 User user = (User) context.getAttribute("userDetails");
         String headerKey = "Content-Disposition";
         String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
         response.setHeader(headerKey, headerValue);
	  
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter;
		try {
			csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
			String[] header = { "taskId","projectName","startDate","totalRecord","webStartTime","webEndTime","webStatus","webActive","webInActive","nlpStartTime","nlpEndTime","nlpActive","nlpInActive","nlpStatus"};
	        csvWriter.writeHeader(header);
	        List<ProjectList> tempUrls = homeDao.downloadFinalList(user.getUserId());
	        for (ProjectList aBook : tempUrls) {
	            csvWriter.write(aBook, header);
	        }
	 
	        csvWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 }
	 
	 //Download csv
	 @RequestMapping(value="/downloadCSV")
	 @ResponseBody public void downloadCSV(HttpServletResponse response,HttpServletRequest request) {
		 String csvFileName = "DeActive_URL.csv";
		 
	        response.setContentType("text/csv");
	 
	        String dataSetId = request.getParameter("dataSetId");
	        String action = request.getParameter("action");
	        
	        // creates mock data
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
	        response.setHeader(headerKey, headerValue);
	 
	        // uses the Super CSV API to generate CSV data from the model data
	        ICsvBeanWriter csvWriter;
			try {
				csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
				String[] header = { "URL","urlStatus"};
		        csvWriter.writeHeader(header);
		 
		        List<TempUrl> tempUrls = null;
		        if(action.equalsIgnoreCase("InActiveNLP")) {
		        	tempUrls = homeDao.getUrl(Integer.parseInt(dataSetId), "InActiveNLP");
		        }else if(action.equalsIgnoreCase("InActive")){
		        	tempUrls = homeDao.getUrl(Integer.parseInt(dataSetId), "InActive");
		        }
		        
		        System.out.println("Size--------------------"+ tempUrls.size());
		        
		        for (TempUrl aBook : tempUrls) {
		            csvWriter.write(aBook, header);
		        }
		 
		        csvWriter.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }

	
	 
	 @RequestMapping(value="/account")
	 public String account(Model model) {
		 
		 
		 model.addAttribute("userList", homeDao.getUserList());
		 return "front/account";
	 }
	
	 
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("Validation Error");
	        response.setErrorMessage(ex.getMessage());
	        System.out.println("This is call nullpointer error");
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	    }
	  
	  @RequestMapping(value = "/test", method = RequestMethod.GET)
		public String test() {
			 
			 System.out.println("test ---");
			
			return "front/test";
		}
	  
	  @SuppressWarnings("unchecked")
	@RequestMapping(value = "/sampleData", method = RequestMethod.GET)
	  @ResponseBody public JSONArray sampleData() {
			  
			  JSONArray array = new JSONArray();
			  JSONObject object = new JSONObject();
			   
			  /*object.put("OrderID","10248");
			  object.put("OrderDate","1996-07-04");
			  object.put("CustomerID","WILMK");
			  object.put("ShipName","Vins et alcools Chevalier");
			  object.put("Freight","32.3800");

			  
			  array.add(object);
			  
			  object = new JSONObject();
			  object.put("OrderID","11248");
			  object.put("OrderDate","1996-07-04");
			  object.put("CustomerID","WILMK");
			  object.put("ShipName","Vins et alcools Chevalier");
			  object.put("Freight","30.3800");
			  
			  array.add(object);*/
			  
			  
			  
			  List<ProjectView> projectViews = projectDao.getProjects();
			  for (int i = 0; i < projectViews.size(); i++) {
				
				  object = new JSONObject();
				  object.put("projectID",projectViews.get(i).getProjectId());
				  object.put("projectName",projectViews.get(i).getName());
				  object.put("projectCode",projectViews.get(i).getProjectCode());
				  object.put("projectDesc",projectViews.get(i).getProjDesc());
				  object.put("projectOwner",projectViews.get(i).getProjectOwner());
				  object.put("startDate",projectViews.get(i).getStartDate());
				  object.put("projectEstimate",projectViews.get(i).getEstimateTarget());
				  object.put("deliverySchedule",projectViews.get(i).getDeliverySchedule());
				  
				  array.add(object);
			}
			  
			return array;
		} 
	    

	  @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	  @ResponseBody public boolean deleteData(HttpServletRequest request,HttpSession session)
	  {
		  String action = request.getParameter("action");
		  
		  int userId = Integer.parseInt(session.getAttribute("userId")+"");
		  
		  System.out.println(action +"--"+userId);
		  
		  if(action.equalsIgnoreCase("company")) {
			  int id = Integer.parseInt(request.getParameter("companyId"));
			  return homeDao.deleteData(id, action, userId);
		  }else if(action.equalsIgnoreCase("department")) {
			  int id = Integer.parseInt(request.getParameter("departmentId"));
			  return homeDao.deleteData(id, action, userId);
		  }else if(action.equalsIgnoreCase("type")) {
			  int id = Integer.parseInt(request.getParameter("typeId"));
			  return homeDao.deleteData(id, action, userId);
		  }
		  
		  
		  return false;
	  }
}