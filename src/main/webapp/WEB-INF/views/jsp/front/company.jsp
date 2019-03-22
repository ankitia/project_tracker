<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Company | infoAnalytica</title>

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
        	<p class="text-right mt-6" >     
         		<div id="login-alert" style="display: none;margin-left: 15px;" class="alert alert-danger col-sm-9 float-left mt-6">No company available.</div>   
         	</p>  
         	
         	 
      <main role="main" class="container">
	  <!-- Trigger the modal with a button -->  
	    
	  <button type="button" class="btn btn-primary btn-sm float-right" data-toggle="modal" onclick="companyReset()" data-target="#myModal">Add Company</button> 
	  <br /><br /> 
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
	    <tbody id="companyList">
	    	<c:if test="${fn:length(companyList) == 0 }">
    			<tr>  
    				<td colspan="5">
    					<div id="login-alert" class="alert alert-danger col-sm-12">No company available.</div>
    				</td>
    			</tr> 
	    	</c:if>
	    	<c:forEach items="${companyList }" var="companyList" varStatus="status">
		    	  <tr>
			        <td >${status.count }</td>
			        <td >${companyList.companyName }</td>
			        <td>${companyList.companyDesc }</td> 
			        <td align="center">
			        	<c:choose>
			        		<c:when test="${companyList.status eq 'Active' }">
			        			<input type="checkbox" name="companyStatus" id="companyStatus_${companyList.companyId}" onclick="updatecompanyStatus('${companyList.companyId}')" checked="checked">
			        		</c:when>
			        		<c:otherwise>
			        			<input type="checkbox" name="companyStatus"  onclick="updatecompanyStatus('${companyList.companyId}')" id="companyStatus_${companyList.companyId}" >
			        		</c:otherwise>			        	
			        	</c:choose>
			        </td>     
			        <td align="center"><a href="#" onclick="deleteCompnay(${companyList.companyId})" ><i class="fa fa-times" aria-hidden="true"></i></a></td> 
			      </tr>
	    	</c:forEach>
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
        <h4 class="modal-title">Add company</h4>
      </div>
      <div class="modal-body">
        
           <div class="form-group">
               <label for="company">company Name</label>
               <div class="input-group pb-modalreglog-input-group">
                   <input type="text" class="form-control" id="companyName"  name="companyName" placeholder="company Name" required>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
               </div>
           </div>
           <div class="form-group">
               <label for="password">Description</label>
               <div class="input-group pb-modalreglog-input-group">
                   <input type="text" class="form-control" id="companyDesc" name="companyDesc" placeholder="desc">                   
               </div>
           </div>
       
      </div>
      <div class="modal-footer">
      	<button type="submit" class="btn btn-primary" onclick="manageCompany('insert')" >Save</button>
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

function manageCompany(action){
	
	$.ajax({
		type : "POST",
		url  : "<%=request.getContextPath()%>/manageCompany",
		data : {
			companyName : $("#companyName").val(),
			companyDesc : $("#companyDesc").val(),
			action : action
		},
		success : function(data){
			//alert(data);
			getCompanyList();
			hideMessage();
			$("#login-alert").show(); 
			$("#login-alert").html("company added successfully.");	
			$('#myModal').modal('hide');   

		},
		error : function(e){
			console.log("Error manageCompany -->"+e);
		}
	});
}

function updatecompanyStatus(companyId){
	var companyStatus = "0";
	if($("#companyStatus_"+companyId).is(":checked")){
			companyStatus = "Active";	
	}else{
		    companyStatus = "InActive";	
	}
	$.ajax({
		type : "GET",
		url  : "<%=request.getContextPath()%>/manageCompany",
		data : { 
			status : companyStatus,
			companyId : companyId,
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
			console.log("Error manageCompany -->"+e);
		}
	});
}

function hideMessage(){ 
	setTimeout(function(){ $("#login-alert").hide(); }, 5000);
}

function deleteCompnay(companyId){
  	
	if(confirm("Are you sure you want to delete?")){
		$.ajax({    
			type : "POST", 
			url  : "<%=request.getContextPath()%>/deleteData",
			data : {  
				companyId : companyId,
				action : 'company'
			}, 
			success : function(data){
				if(data){
					$("#login-alert").show();  
					$("#login-alert").html("Company deleted successfully.");	
					hideMessage();
					getCompanyList();
				}
			},
			error : function(e){
				console.log("Error manageCompany -->"+e);
			}
		});  
	}
	
	
}

function getCompanyList(){
	$.ajax({
		type : "GET",
		url  : "<%=request.getContextPath()%>/getCompanyList",
		data : { 
			 
		},
		success : function(data){
			$("#companyList").empty();			
			for (var i = 0; i < data.length; i++) { 
				var companyStatus = "";
				if(data[i].status=="Active")  
					companyStatus = "<input type='checkbox' name='companyStatus' id='companyStatus_"+data[i].companyId+"' onclick=\"updatecompanyStatus('"+data[i].companyId+"')\" checked='checked'>";	
				else	  
					companyStatus = "<input type='checkbox' name='companyStatus' id='companyStatus_"+data[i].companyId+"' onclick=\"updatecompanyStatus('"+data[i].companyId+"')\">";
			 	
				      
				$("#companyList").append("<tr> <td>"+ (i+1) +"</td><td>"+data[i].companyName+"</td><td>"+data[i].companyDesc+"</td><td style='text-align: center;'>"+companyStatus+"</td><td style='text-align: center;'><a href='#' onclick=\"deleteCompnay("+data[i].companyId+")\" ><i class='fa fa-times' aria-hidden='true'></i></a></td></tr>"); 
			} 
		},
		error : function(e){
			console.log("Error manageCompany -->"+e);
		}
	});
}

</script>    

  </body>

</html>
