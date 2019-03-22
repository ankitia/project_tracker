<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Department | infoAnalytica</title>

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
	    
	  <button type="button" class="btn btn-primary btn-sm float-right mg-tp-10" data-toggle="modal" onclick="DepartmentReset()" data-target="#myModal">Add Department</button>
	     
	   <div class="col-sm-4 pd-lf-0">
		   <select name="comapny" id="comapny" class="browser-default custom-select" onchange="getDepartment()">
		   		<option value="">Select Company</option>
		   	<c:forEach var="companyList" items="${companyList }" >
		   		<option value="${companyList.companyId }">${companyList.companyName }</option>
		   	</c:forEach> 
		   </select> 
	   </div>  
	   <br />
	   
       <table class="table table-hover table-bordered">
	    <thead>
	      <tr>
	        <th width="3%">#</th>
	        <th width="17%">Name</th>
	        <th width="50%">Description</th>
	        <th width="15%" style="text-align: center;">Active/DeActive</th>
	        <th width="15%" style="text-align: center;">Delete</th>
	      </tr>
	    </thead>
	    <tbody id="DepartmentList">
	    	<c:if test="${fn:length(departmentList) == 0 }">
    			<tr> 
    				<td colspan="5">
    					<div id="login-alert" class="alert alert-danger col-sm-12">No Department available.</div>
    				</td>
    			</tr> 
	    	</c:if>
	    	
	    </tbody>
  	  </table>
  	  
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
      	<button type="button" class="btn btn-primary" onclick="manageDepartment('insert')" >Save</button>
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
	$("#comapnyId").val($("#comapny").val()); 
	$("#DepartmentList").empty(); 
	$.ajax({
		type : "POST",
		url  : "<%=request.getContextPath()%>/getDepartment",
		data : { 
			action : "all",
			companyId : $("#comapny").val(),
		},
		success : function(data){
				 
				 
			if(data.length==0){   
				$("#DepartmentList").append("<tr> <td colspan='5'><div id='login-alert' class='alert alert-danger col-sm-12'>No Department available.</div></td></tr>");
			}else{
				for (var i = 0; i < data.length; i++) { 
					var DepartmentStatus = "";
					
					if(data[i].status=="Active") 
						DepartmentStatus = "<input type='checkbox' name='DepartmentStatus' id='DepartmentStatus_"+data[i].departmentId+"' onclick='updateDepartmentStatus("+data[i].departmentId+")' checked='checked'>";	
					else	
						DepartmentStatus = "<input type='checkbox' name='DepartmentStatus' id='DepartmentStatus_"+data[i].departmentId+"' onclick='updateDepartmentStatus("+data[i].departmentId+")'>";
					 
					$("#DepartmentList").append("<tr> <td>"+ (i+1) +"</td><td>"+data[i].departmentName+"</td><td>"+data[i].departmentDesc+"</td><td style='text-align: center;'>"+DepartmentStatus+"</td><td style='text-align: center;'><a href='#' onclick=\"deleteDepartment("+data[i].departmentId+")\" ><i class='fa fa-times' aria-hidden='true'></i></a></td> </tr>"); 
				}
			}	
		},
		error : function(e){
			console.log("Error getDepartment -->"+e);
		}
});
	
}

function manageDepartment(action){
	
	$.ajax({
		type : "POST",
		url  : "<%=request.getContextPath()%>/manageDepartment",
		data : {
			departmentName : $("#DepartmentName").val(),
			departmentDesc : $("#DepartmentDesc").val(),
			companyId : $("#comapnyId").val(),
			action : action
		},
		success : function(data){
			//alert(data);
			    
			getDepartment(); 
			$("#login-alert").show(); 
			$("#login-alert").html("Department added successfully.");	
			hideMessage();
			$('#myModal').modal('hide');

		},
		error : function(e){
			console.log("Error manageDepartment -->"+e);
		}
	});
}

function updateDepartmentStatus(DepartmentId){
	
	var DepartmentStatus = "Active";
	if($("#DepartmentStatus_"+DepartmentId).is(":checked")){
			DepartmentStatus = "Active";	
	}else{
		    DepartmentStatus = "InActive";	
	}
	
	$.ajax({
		type : "GET",
		url  : "<%=request.getContextPath()%>/manageDepartment",
		data : { 
			status : DepartmentStatus,
			departmentId : DepartmentId,
			action : 'update'
		},
		success : function(data){
			if(data){
				$("#login-alert").show(); 
				$("#login-alert").html("Status changed successfully.");	
				hideMessage(); 
			}
		},
		error : function(e){
			console.log("Error manageDepartment -->"+e);
		}
	});
}

function hideMessage(){ 
	setTimeout(function(){ $("#login-alert").hide(); }, 5000);
}

function deleteDepartment(departmentId){
	if(confirm("Are you sure you want to delete?")){
		$.ajax({    
			type : "POST", 
			url  : "<%=request.getContextPath()%>/deleteData",
			data : {  
				departmentId : departmentId,
				action : 'department'
			}, 
			success : function(data){ 
				if(data){
					$("#login-alert").show();  
					$("#login-alert").html("Department deleted successfully.");	
					hideMessage();
					getDepartment();
				}
			},
			error : function(e){
				console.log("Error manageCompany -->"+e);
			}
		});  
	}
}

</script>    

  </body>

</html>
