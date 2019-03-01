<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content=""> 

	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">

    <title>Manage Project | infoAnalytica</title>

	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	

    <!-- Bootstrap core CSS -->
    <link href='<c:url value="/resources/front2/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    
    <link href="<c:url value="/resources/front2/css/custom_style.css"></c:url>" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/front2/fonts/font-awesome/css/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css">

<style>
.pagination {
    display: inline-block;
    margin-bottom: 10px;
}

.pagination a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
    border: 1px solid #ddd;
}

.pagination a.active {
    background-color: #4CAF50;
    color: white;
    border: 1px solid #4CAF50;
}

.mod_hed{
display: block;
}

.table td, .table th {
    padding: 0.45rem;
    vertical-align: top;
    border-top: 1px solid #dee2e6;
}
 
 /*.form-control {
    display: block;
    width: 100%;
    padding: .095rem .75rem;
    font-size: 1rem;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
} */  
 
.pagination a:hover:not(.active) {background-color: #ddd;}

.nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active{
    border-color: #dee2e6 #fff #dee2e6 #dee2e6; 
}

.row {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -0px; 
    margin-left: -10px;  
}

.custom-file-input {
    height: 2.0rem;
    }   

</style>        




  </head>

  <body>
  
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
     
      <div class="row"> 
        <div class="col-lg-12"> 
        	<p class="text-right mt-6" >     
         		<div id="login-alert" style="display: none;" class="alert alert-danger col-sm-9 float-left mt-6">No project available.</div>   
         	</p> 
        </div>
      </div> 
      
      <div class="row"> 
        <div class="col-lg-12 text-center">
        		Add Project Detail
        </div>
       </div>
      <br />
      
      <form action="insertProject" method="post" enctype="multipart/form-data" >  
      
      
      
      <div class="row">   
      	<div class="col-sm-2 offset-md-3">
      		<div class="form-group">
	        	<label for="selectCompany">Select Company</label>
	            <div class="input-group pb-modalreglog-input-group" onchange="getDepartment()">
	        	<select name="companyId" id="companyId" class="browser-default custom-select" required>
			   		<option value="">Select Company</option>
			   	<c:forEach var="companyList" items="${companyList }" >
			   		<option value="${companyList.companyId }">${companyList.companyName }</option>
			   	</c:forEach> 
			   </select>   
			</div>
			</div>      		
      	</div>
      	
      	<div class="col-sm-2 offset-md-1">
      		<div class="form-group">  
				<label for="selectDepartment">Select Department</label>
	            <div class="input-group pb-modalreglog-input-group">
		        	<select name="departmentId" id="DepartmentList" class="browser-default custom-select" required>
				   		<option value="">Select Department</option>
				   </select> 
				</div>
			</div>
      	</div>
      </div>

  

<div class="row">   
        <div class="col-lg-3">
			<div class="nav flex-column nav-tabs" id="v-pills-tab" role="tablist" aria-orientation="vertical" >
			  <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">Project Information</a>
			  <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">Project Specifications</a>
			  <!-- <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">Project Plan</a> 
			  <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings</a> -->
			</div>
		</div>        
        <div class="col-lg-8"> <!-- style="border: 1px solid #dee2e6; padding: 25px;  margin-left: -32px;" -->  
		<div class="tab-content" id="v-pills-tabContent" style="border: 1px solid #dee2e6; padding: 25px;  margin-left: -32px;margin-bottom: 100px;" > 
		  <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
		  
		  <div class="row"> 
		  <div class="col-lg-3">
			  <div class="form-group"> 
		          <label for="projectName">Project Name</label>
		          <div class="input-group pb-modalreglog-input-group">
		              <input type="text" class="form-control" id="projectName"  name="name" placeholder="Project Name" required>
		              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		          </div>
		      </div>
		      
		      <div class="form-group">
		          <label for="projectCode">Project Code</label>
		          <div class="input-group pb-modalreglog-input-group">
		              <input type="text" class="form-control" id="projectCode"  name="projectCode" placeholder="Project Code" required>
		              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		          </div>
		      </div>
		      
		      <div class="form-group">  
		          <label for="projDesc">Project Description</label>
		          <div class="input-group pb-modalreglog-input-group">
		              <input type="text" class="form-control" id="projDesc"  name="projDesc" placeholder="Project Description" required>
		              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		          </div>
		      </div>  
		      
		        
		  </div> 
		  <div class="col-lg-3">
		   
		   		<div class="form-group">
		          <label for="projectType">Project Type</label>
		          <div class="input-group pb-modalreglog-input-group" >
		        	<select name="projectType" id="projectType" class="browser-default custom-select" >
				   		<option value="">Select Project Type</option> 
				   	<c:forEach var="projectTypeList" items="${projectTypeList }" >
				   		<option value="${projectTypeList.projectTypeId }">${projectTypeList.name }</option>
				   	</c:forEach> 
				   </select> 
				</div>
		      </div>    
			   <div class="form-group"> 
		          <label for="projectType">Client contact</label>
		          <div class="input-group pb-modalreglog-input-group">
		        	<select name="personToContact" id="personToContactId" class="browser-default custom-select">
				   		<option value="">Select Client contact</option>
				   	<c:forEach var="personContactList" items="${personContactList }" >
				   		<option value="${personContactList.personContactId }">${personContactList.name }</option>
				   	</c:forEach> 
				   </select> 
				</div>
		      </div>    
 		      
		      <div class="form-group">   
		      		<label for="projectOwner">Project Owner</label>
		            <div class="input-group pb-modalreglog-input-group">
		        	<select name="projectOwner" id="projectOwnerId" class="browser-default custom-select">
				   		<option value="">Select Project Owner</option>
				   	<c:forEach var="projectOwnerList" items="${projectOwnerList }" >
				   		<option value="${projectOwnerList.personContactId }">${projectOwnerList.name }</option>
				   	</c:forEach> 
				   </select> 
				</div>
				</div>    
				
		  
		  </div>
		  <div class="col-lg-3">
			  <div class="form-group">
		          <label for="projectType">Start Date</label>
		          <div class="input-group pb-modalreglog-input-group">
		              <input type="date" class="form-control" id="startDate"  name="startDate" placeholder="Start Date" required>
		              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>  
		          </div>
		      </div>
		      
		      <div class="form-group">
		          <label for="projectType">Delivery Date</label>
		          <div class="input-group pb-modalreglog-input-group">
		              <input type="date" class="form-control" id="deliveryDate"  name="deliveryDate" placeholder="Delivery Date" required>
		              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>  
		          </div>
		      </div>
		      
		      <div class="form-group">  
		          <label for="estimateTarget">Estimate Target</label>
		          <div class="input-group pb-modalreglog-input-group">
		              <input type="text" class="form-control" id="estimateTarget"  name="estimateTarget" placeholder="Estimate Target" required>
		              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		          </div>
		      </div>
 		  </div>
		  <div class="col-lg-3">
		  
		  	  <div class="form-group">   
		      		<label for="deliverySchedule">Delivery Schedule</label>
		            <div class="input-group pb-modalreglog-input-group">
		        	<select name="deliverySchedule" id="deliverySchedule" class="browser-default custom-select">
				   		<option value="">Select Delivery</option>
				   		<option value="Daily">Daily</option>
				   		<option value="Weekly">Weekly</option>
				   		<option value="Monthly">Monthly</option>				   	 
				   </select>  
				</div>
			  </div>
			  
			  <div class="form-group">   
		      		<label for="deliverySchedule">Project Status</label>
		            <div class="input-group pb-modalreglog-input-group">
		        	<select name="projectStatus" id="projectStatus"  class="browser-default custom-select">
				   		<option value="">Select Delivery</option>
				   		<option value="Active">Active</option>
				   		<option value="Onhold">Onhold</option>
				   		<option value="Completed">Completed</option>				   	 
				   </select>  
				</div>
			  </div>
		  </div>
		  <div class="col-lg-12">  
		  		<!-- <button class="btn btn-primary" onclick="insertProject();" value="Save">Save</button> -->
		  		<input type="submit" class="btn btn-primary" value="Save">   
		  </div>
		  </div>
		  
		  </div>
		  <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
		  
		     <div class="row"> 
			    <div class="col-lg-3">
				  <div class="form-group"> 
			          <label for="SOP">SOP Description</label>
			          <div class="input-group pb-modalreglog-input-group">
			              <input type="text" class="form-control" id="sopDesc"  name="sopDesc" placeholder="SOP Description" >
			              <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
			          </div>
			      </div>
			     </div>   
			     <div class="col-lg-6">
			      
				     <div class="form-group" style="margin-top: 20px;">
				     	<!-- <label for="exampleFormControlTextarea1">Attachment</label> -->
				     <div class="input-group">
						  <div class="input-group-prepend"> 
						    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
						  </div>
						  <div class="custom-file">
						    <input type="file" class="custom-file-input" id="exampleInputFile" name="exampleInputFile" aria-describedby="inputGroupFileAddon01">
						    <label class="custom-file-label" for="exampleInputFile">Choose file</label>
						  </div>
						</div>		        
			      </div>
			     </div>
			     <div class="col-lg-8">
			     	<div class="form-group"> 
					  <label for="clientInstruction">Client Instructions</label>
					  <textarea class="form-control rounded-0" name="clientInstruction" id="clientInstruction" rows="5"></textarea>
					</div>
			     </div>
			     <div class="col-lg-8">
			     	<div class="form-group">  
					  <label for="briefAssociate">Project Brief For Associate</label>
					  <textarea class="form-control rounded-0" id="briefAssociate" name="briefAssociate" rows="5"></textarea>
					</div>
			     </div> 
			     
			     <div class="col-lg-12"> 
				  		<input type="submit" class="btn btn-primary" value="Save">   
				  </div> 
			</div> 

		  </div>
		  <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">.tab 1 tab 1
		  	tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1tab 1</div>
		  <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">...</div>
	   </div>
</div>
</div>

</form>
	<!-- Footer -->
    <%@include file="include/footer.jsp" %>

 <script src="<c:url value="/resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
    <script src="<c:url value="/resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script>

    
<script type="text/javascript">

function getDepartment(){
	  
	$("#DepartmentList").empty(); 
	$.ajax({
		type : "POST",
		url  : "<%=request.getContextPath()%>/getDepartment",
		data : { 
			action : "all",
			companyId : $("#companyId").val(),
		},
		success : function(data){
			if(data.length==0){  
				$("#DepartmentList").empty(); 
			}else{   
				$("#DepartmentList").append('<option value="">Select Department</option>');
				for (var i = 0; i < data.length; i++) { 
					var DepartmentStatus = "";
					$("#DepartmentList").append('<option value='+data[i].departmentId+'>'+data[i].departmentName+'</option>');

				}
			}	
		},
		error : function(e){
			console.log("Error getDepartment -->"+e);
		}
});
	
} 

function insertProject(){
	
	$.ajax({
		type  :"POST",
		url : "insertProject",
		data : {
			name : $("#projectName").val(),
			projectCode 	:$("#projectCode").val(),
			projectType 	:$("#projectType").val(),
			companyId   	:$("#companyId").val(),
			departmentId 	:$("#DepartmentList").val(),
			personToContact :$("#personToContactId").val(),
			projectOwner	:$("#projectOwnerId").val(),
			startDate		:$("#startDate").val(),
			deliveryDate	:	$("#deliveryDate").val(),
				
		},
		success : function(data){
			alert(data); 
		},
		error : function(e){
			console.log("Error insertProject-->"+e);
		}
	});
	
}


 

</script>
</body> 
</html>
