<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Project infoAnalytica</title>

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



<script type="text/javascript">

var enrichURL = "<%=CommonUtility.ENRICH_URL%>";

 

</script>
  </head>

  <body>
  
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
    <div class="container mb-50">
      <div class="row"> 
        <div class="col-lg-12"> 
        	<p class="text-right mt-6" >     
         		<div id="login-alert" style="display: none;" class="alert alert-danger col-sm-9 float-left mt-6">No project available.</div>   
         	</p> 
         	
         	 
      <main role="main" class="container">
	  <!-- Trigger the modal with a button -->  
	     
	  <!-- <button type="button" class="btn btn-primary btn-sm float-right" data-toggle="modal" onclick="projectReset()" data-target="#myModal">Add Project</button> -->
	  
       <%-- <table class="table table-striped">
	    <thead>
	      <tr>
	        <th width="3%">#</th>
	        <th width="17%">Name</th>
	        <th width="65%">Description</th>
	        <th width="15%" style="text-align: center;">Active/DeActive</th>
	      </tr>
	    </thead>
	    <tbody id="projectList">
	    	<c:if test="${fn:length(projectList) == 0 }">
    			<tr>
    				<td colspan="3">
    					<div id="login-alert" class="alert alert-danger col-sm-12">No project available.</div>
    				</td>
    			</tr> 
	    	</c:if>
	    	<c:forEach items="${projectList }" var="projectList" varStatus="status">
		    	  <tr>
			        <td >${status.count }</td>
			        <td >${projectList.name }</td>
			        <td>${projectList.desc }</td> 
			        <td align="center">
			        	<c:choose>
			        		<c:when test="${projectList.status eq '1' }">
			        			<input type="checkbox" name="projectStatus" id="projectStatus_${projectList.projectId}" onclick="updateProjectStatus('${projectList.projectId}')" checked="checked">
			        		</c:when>
			        		<c:otherwise>
			        			<input type="checkbox" name="projectStatus"  onclick="updateProjectStatus('${projectList.projectId}')" id="projectStatus_${projectList.projectId}" >
			        		</c:otherwise>			        	
			        	</c:choose>
			        </td>
			      </tr>
	    	</c:forEach>
	    </tbody>
  	  </table> --%>
  	  
  	  <br /><br />
  	  <ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Project Information</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Specifications</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Project Plan</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">IA Process</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">IA Process QC</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Feedback</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Delivery Count</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Master File</a>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">...</div>
  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">Tabe 2</div>
  <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">Tabe 3</div>
</div>
  	  
  	  
    </main>
			
			<%-- <div class="pagination" style="float: right;"> 
			  <c:choose>
			  	<c:when test="${page ==  1}">
					 <a href="${page }" class="active">&laquo;</a>
			  	</c:when>
			  	<c:otherwise>
			  		<a href="${page - 1 }">&laquo;</a>
			  	</c:otherwise>
			  		
			  </c:choose>
			  
			  <c:forEach begin="1" end="${totalPage }" var="i" step="1">
			  		<c:choose>  
			  			<c:when test="${i == page }">
			  				<a class="active" href="#">${i }</a>	
			  			</c:when>
			  			<c:otherwise> 
			  				<a href="${i}">${i }</a>	
			  			</c:otherwise>
			  		</c:choose> 
			  </c:forEach>
			  <c:choose>
			  	<c:when test="${page ==  totalPage}">
					 <a href="${page }" class="active">&raquo;</a>
			  	</c:when>
			  	<c:otherwise>
			  		<a href="${page + 1 }">&raquo;</a>
			  	</c:otherwise>
			  		
			  </c:choose>
			  
		  </div> --%>
			
        </div>
      </div>
    </div>


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header mod_hed">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Project</h4>
      </div>
      <div class="modal-body">
        <form action="test">
           <div class="form-group">
               <label for="project">Project Name</label>
               <div class="input-group pb-modalreglog-input-group">
                   <input type="text" class="form-control" id="projectName"  name="projectName" placeholder="Project Name" required>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
               </div>
           </div>
           <div class="form-group">
               <label for="password">Description</label>
               <div class="input-group pb-modalreglog-input-group">
                   <input type="text" class="form-control" id="desc" name="desc" placeholder="desc">                   
               </div>
           </div>
       </form>
      </div>
      <div class="modal-footer">
      	<button type="submit" class="btn btn-primary" onclick="insertProject()" >Save</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	  
    
    <!-- Footer -->
    <%@include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
    <script src="<c:url value="/resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script>

<script type="text/javascript">

function insertProject(){
	
	$.ajax({
		type : "GET",
		url  : "<%=request.getContextPath()%>/insertProject",
		data : {
			name : $("#projectName").val(),
			desc : $("#desc").val(),
		},
		success : function(data){
			alert(data);
			    
			$("#projectList").empty();
			
			for (var i = 0; i < data.length; i++) { 
				var projectStatus = "";
				if(data[i].status==0)
					projectStatus = "<input type='checkbox' name='projectStatus' id='projectStatus_"+data[i].projectId+"' onclick='updateProjectStatus('"+data[i].projectId+"')'>";	
				else	
					projectStatus = "<input type='checkbox' name='projectStatus' id='projectStatus_"+data[i].projectId+"' onclick='updateProjectStatus('"+data[i].projectId+"')' checked='checked'>";
				
				 
				$("#projectList").append("<tr> <td width='3%'>"+ (i+1) +"</td><td width='17%'>"+data[i].name+"</td><td width='65%'>"+data[i].desc+"</td><td width='15%' style='text-align: center;'>"+projectStatus+"</td> </tr>"); 
			} 
			$("#login-alert").show(); 
			$("#login-alert").html("Project added successfully.");	
			hideMessage();
			$('#myModal').modal('hide');

		},
		error : function(e){
			console.log("Error insertProject -->"+e);
		}
	});
}

function updateProjectStatus(projectId){
	var projectStatus = "0";
	if($("#projectStatus_"+projectId).is(":checked")){
			projectStatus = "1";	
	}else{
		    projectStatus = "0";	
	}
	$.ajax({
		type : "GET",
		url  : "<%=request.getContextPath()%>/updateProjectStatus",
		data : { 
			status : projectStatus,
			projectId : projectId,
		},
		success : function(data){
			if(data){
				$("#login-alert").show(); 
				$("#login-alert").html("Status changed successfully.");	
				hideMessage(); 
			}
		},
		error : function(e){
			console.log("Error insertProject -->"+e);
		}
	});
}

function hideMessage(){ 
	setTimeout(function(){ $("#login-alert").hide(); }, 3000);
}


</script>    

  </body>

</html>
