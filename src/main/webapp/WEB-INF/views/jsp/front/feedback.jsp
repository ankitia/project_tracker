<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Feedback | infoAnalytica</title>

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

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>        


 
  </head>

  <body>
  
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
    <div class="container mb-50">
      <div class="row"> 
        <div class="col-lg-12">
        	<p class="text-right mt-6">       
         		<div id="login-alert" style="display: none;" class="alert alert-danger col-sm-9 float-left mt-6">No Department available.</div>   
         	</p>
         </div>
         	 
         	 
      <main role="main" class="container">
	  <!-- Trigger the modal with a button -->  
	      
	  <!-- <button type="button" class="btn btn-primary btn-sm float-right mg-tp-10" data-toggle="modal" onclick="DepartmentReset()" data-target="#myModal">Add Department</button> -->
	     
	   <div class="row">    
      	<div class="col-sm-3">
      		<div class="form-group">
	        	<label for="selectCompany">Select Company</label>
	            <div class="input-group pb-modalreglog-input-group" onchange="getDepartment()">
	        	<select name="companyId" id="companyId" class="browser-default custom-select" required>
			   		<option value="">Select Company</option>
			   	<c:forEach var="companyList" items="${companyList }" >
			   		<c:choose>
			   			<c:when test="${project.companyId  == companyList.companyId}">
			   				<option value="${companyList.companyId }" selected="selected">${companyList.companyName }</option>	
			   			</c:when>
			   			<c:otherwise>
			   				<option value="${companyList.companyId }">${companyList.companyName }</option>
			   			</c:otherwise>
			   		</c:choose>
			   		
			   	</c:forEach> 
			   </select>   
			</div>
			</div>      		
      	</div>
      
      	<div class="col-sm-3 offset-md-1">
      		<div class="form-group">  
				<label for="selectDepartment">Select Department</label>
	            <div class="input-group pb-modalreglog-input-group">  
		        	<select name="departmentId" id="DepartmentList" class="browser-default custom-select" required onchange="getProjectList()">
				   		<option value="">Select Department</option>
					   		<c:forEach var="departmentList" items="${departmentList }">
					   			<c:choose> 
					   				<c:when test="${departmentList.departmentId == project.departmentId }">
					   					<option selected="selected" value="${departmentList.departmentId }">${departmentList.departmentName }</option>	
					   				</c:when>
					   				<c:otherwise>
					   					<option value="${departmentList.departmentId }">${departmentList.departmentName }</option>
					   				</c:otherwise>
					   			</c:choose>					   			
					   		</c:forEach>			   		
				   </select> 
				</div>
			</div>
      	</div>
      	
      	<div class="col-sm-3 offset-md-1">
      		<div class="form-group">  
				<label for="selectProject">Select Project</label>
	            <div class="input-group pb-modalreglog-input-group">
		        	<select name="projectList" id="projectId" class="browser-default custom-select" required>
				   		<option value="">Select Project</option>
					   		<c:forEach var="projectList" items="${projectList }">
					   			<c:choose> 
					   				<c:when test="${departmentList.departmentId == project.departmentId }">
					   					<option selected="selected" value="${departmentList.departmentId }">${departmentList.departmentName }</option>	
					   				</c:when>
					   				<c:otherwise>
					   					<option value="${departmentList.departmentId }">${departmentList.departmentName }</option>
					   				</c:otherwise>
					   			</c:choose>					   			
					   		</c:forEach>			   		
				   </select> 
				</div>
			</div>
      	</div>
      </div>  
      	
    </main>
					 
			
        </div>
      </div>
    </div>


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
	<form action="#">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header mod_hed">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Department</h4>
      </div>
      <div class="modal-body">
        
        <div class="form-group">
            <div class="input-group pb-modalreglog-input-group">
        	<select name="comapny" id="comapnyId" class="browser-default custom-select">
		   		<option value="">Select Company</option>
		   	<c:forEach var="companyList" items="${companyList }" >
		   		<option value="${companyList.companyId }">${companyList.companyName }</option>
		   	</c:forEach> 
		   </select> 
		</div>
		</div>
        
           <div class="form-group">
               <div class="input-group pb-modalreglog-input-group">
                   <input type="text" class="form-control" id="DepartmentName"  name="DepartmentName" placeholder="Department Name" required>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
               </div>
           </div>
           <div class="form-group">
               <div class="input-group pb-modalreglog-input-group"> 
                   <input type="text" class="form-control" id="DepartmentDesc" name="DepartmentDesc" placeholder="Description">                   
               </div>
           </div>
       
      </div>
      <div class="modal-footer">
      	<button type="submit" class="btn btn-primary" onclick="manageDepartment('insert')" >Save</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>
    </form>

  </div>
</div>
	  
    
    <!-- Footer -->
    <%@include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
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

 function getProjectList(){
	 $("#projectId").empty(); 
		$.ajax({
			type : "POST",
			url  : "<%=request.getContextPath()%>/getProjectList",
			data : { 
				action : "all",
				companyId : $("#companyId").val(),
				departmentId : $("#DepartmentList").val(),
			},
			success : function(data){
				if(data.length==0){  
					$("#projectId").empty(); 
				}else{   
					$("#projectId").append('<option value="">Select Project</option>');
					for (var i = 0; i < data.length; i++) {  
						$("#projectId").append('<option value='+data[i].projectId+'>'+data[i].name+' ('+data[i].projectCode+')</option>');

					}
				}	
			}, 
			error : function(e){
				console.log("Error getProjectList -->"+e);
			}
	});
	 
 }

</script>    

  </body>

</html>
