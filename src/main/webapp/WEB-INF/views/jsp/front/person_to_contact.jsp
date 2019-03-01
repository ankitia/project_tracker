<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Person to Contact | infoAnalytica</title>

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
         		<div id="login-alert" style="display: none;" class="alert alert-danger col-sm-9 float-left mt-6">No Person available.</div>   
         	</p>  
         	
         	 
      <main role="main" class="container">
	  <!-- Trigger the modal with a button -->  
	    
	  <button type="button" class="btn btn-primary btn-sm float-right" data-toggle="modal" onclick="personReset()" data-target="#myModal">Add Person</button> 
	  
	  <div class="col-sm-4 pd-lf-0">   
		   <select name="userType" id="userType" class="browser-default custom-select" onchange="getPersonContact()">
		   		<option value="">Select Type</option>
		   	<c:forEach var="userTypeList" items="${userTypeList }" >
		   		<option value="${userTypeList.userTypeId }">${userTypeList.name }</option>
		   	</c:forEach> 
		   </select> 
	   </div>
	  <br />  
       <table class="table table-hover table-bordered">
	    <thead>
	      <tr> 
	        <th width="3%">#</th>
	        <th width="82%">Name</th>
	        <th width="15%" style="text-align: center;">Active/DeActive</th> 
	      </tr>
	    </thead>
	    <tbody id="personList"> 
	    	<c:if test="${fn:length(personList) == 0 }">
    			<tr>  
    				<td colspan="4">
    					<div id="login-alert" class="alert alert-danger col-sm-12">No Data available.</div>
    				</td>
    			</tr> 
	    	</c:if>
	    	<c:forEach items="${personList }" var="personList" varStatus="status">
		    	  <tr>
			        <td >${status.count }</td>
			        <td >${personList.name }</td>
			        <td align="center">
			        	<c:choose>
			        		<c:when test="${personList.status eq 'Active' }">
			        			<input type="checkbox" name="companyStatus" id="companyStatus_${personList.personContactId}" onclick="updatecompanyStatus('${personList.personContactId}')" checked="checked">
			        		</c:when>
			        		<c:otherwise>
			        			<input type="checkbox" name="companyStatus"  onclick="updatecompanyStatus('${personList.personContactId}')" id="companyStatus_${personList.personContactId}" >
			        		</c:otherwise>			        	
			        	</c:choose>
			        </td>
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
        <h4 class="modal-title">Add Person</h4>
      </div>
      <div class="modal-body">
      
	      <div class="form-group">
	           <label for="selectType">Select Type</label>
			   <select name="selectType" id="selectType" class="browser-default custom-select">
			   		<option value="">Select Type</option>
			   	<c:forEach var="userTypeList" items="${userTypeList }" >
			   		<option value="${userTypeList.userTypeId }">${userTypeList.name }</option>
			   	</c:forEach> 
			   </select> 
		   </div>
        
           <div class="form-group">
               <label for="company">Person Name</label>
               <div class="input-group pb-modalreglog-input-group">
                   <input type="text" class="form-control" id="personName"  name="personName" placeholder="Person Name" required>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
               </div>
           </div>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-primary" onclick="managePerson('insert')" >Save</button>
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

function getPersonContact(){
	$.ajax({
		type : "POST",
		url  : "<%=request.getContextPath()%>/getPersonContact",
		data : {
			typeId : $("#userType").val(), 
		},
		success : function(data){
			//alert(data);
			 $("#selectType").val($("#userType").val());    
			$("#personList").empty();
			
			if(data.length==0){     
				$("#personList").append("<tr> <td colspan='4'><div id='login-alert' class='alert alert-danger col-sm-12'>No Data available.</div></td></tr>");
			}else{
			
			for (var i = 0; i < data.length; i++) { 
				var companyStatus = "";
				if(data[i].status=="Active")  
					companyStatus = "<input type='checkbox' name='companyStatus' id='companyStatus_"+data[i].personContactId+"' onclick='updatecompanyStatus("+data[i].personContactId+")' checked='checked'>";	
				else	
					companyStatus = "<input type='checkbox' name='companyStatus' id='companyStatus_"+data[i].personContactId+"' onclick='updatecompanyStatus("+data[i].personContactId+")' >";
			 	
				 
				$("#personList").append("<tr> <td width='3%'>"+ (i+1) +"</td><td width='82%'>"+data[i].name+"</td><td width='15%' style='text-align: center;'>"+companyStatus+"</td> </tr>"); 
			}
		  }	
			
		},
		error : function(e){
			console.log("Error getPersonContact -->"+e);
		}
	});
}

function managePerson(action){
	
	$.ajax({
		type : "POST",
		url  : "<%=request.getContextPath()%>/managePerson",
		data : {
			name : $("#personName").val(), 
			action : action,
			typeId : $("#selectType").val(),
		},
		success : function(data){
			//alert(data);
			     
			/* $("#personList").empty();
			
			for (var i = 0; i < data.length; i++) { 
				var companyStatus = "";
				if(data[i].status=="Active")
					companyStatus = "<input type='checkbox' name='companyStatus' id='companyStatus_"+data[i].personContactId+"' onclick='updatecompanyStatus('"+data[i].personContactId+"')'>";	
				else	
					companyStatus = "<input type='checkbox' name='companyStatus' id='companyStatus_"+data[i].personContactId+"' onclick='updatecompanyStatus('"+data[i].personContactId+"')' checked='checked'>";
			 	
				 
				$("#personList").append("<tr> <td width='3%'>"+ (i+1) +"</td><td width='82%'>"+data[i].name+"</td><td width='15%' style='text-align: center;'>"+companyStatus+"</td> </tr>"); 
			} */   
			$("#userType").val($("#selectType").val());
			getPersonContact();   
			$("#login-alert").show(); 
			$("#login-alert").html("Data added successfully.");	
			hideMessage();
			$('#myModal').modal('hide');

		},
		error : function(e){
			console.log("Error managePerson -->"+e);
		}
	});
}

function updatecompanyStatus(personContactId){
	var companyStatus = "0";
	if($("#companyStatus_"+personContactId).is(":checked")){
			companyStatus = "Active";	
	}else{
		    companyStatus = "InActive";	
	}
	$.ajax({
		type : "GET",  
		url  : "<%=request.getContextPath()%>/managePerson",
		data : { 
			status : companyStatus,
			personContactId : personContactId,
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
			console.log("Error managePerson -->"+e);
		}
	});
}

function hideMessage(){ 
	setTimeout(function(){ $("#login-alert").hide(); }, 5000);
}


</script>    

  </body>

</html>
