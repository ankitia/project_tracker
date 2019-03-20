<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Dashboard | infoAnalytica</title>

	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
    


    <!-- Bootstrap core CSS -->
    <link href='<c:url value="/resources/front2/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    
    <link href="<c:url value="/resources/front2/css/custom_style.css"></c:url>" rel="stylesheet">
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> --> 
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
    <div class=" mb-50" style="margin: 20px;"> 
      <div class="row">       
        <div class="offset-md-1" style="width: 1100px; overflow-x: scroll;"> 
 	  <main role="main" class="container">
       <table class="table table-hover table-bordered">
	    <thead>
	      <tr>
	        <th width="20">#</th>
	        <th width="50">Project Code</th>
	        <th width="150">Client</th> 
	        <th width="10%">Project Name</th>
	        <th width="10%">Brief Description</th>
	        <th width="10%">Project Owner</th>
	        <th width="30%">Start date</th>
	        <th width="10%">ORDER (No. of records)</th>
	        <th width="27%" style="text-align: center;">Delivery Schedule</th>
	        <th width="10%">Delivered Till Now</th>
	        <th width="10%">Pending</th>
	        <th width="50%">Next Update Date</th>
	        <th width="10%">Next Delivery (Estimate)</th>
	        <th width="10%">Resources</th>
	        <th width="10%">Notes</th>
	         
	      </tr>   
	    </thead>
	    <tbody id="projectList">
	    	<c:if test="${fn:length(projectList) == 0 }">
    			<tr>    
    				<td colspan="9">
    					<div id="login-alert" class="alert alert-danger col-sm-12">No project available.</div>
    				</td>
    			</tr> 
	    	</c:if>
	    	<c:forEach items="${projectList }" var="projectList" varStatus="status">
		    	  <tr>
			        <td >${status.count }</td> 
			        <td> <a href="updateProject/${projectList.projectId }">${projectList.projectCode }</a></td>   
			        <td >${projectList.name }</td>
			        <td >${projectList.name }</td> 
			        <td style="text-align: center;"> 	    
			        	    <a href="#" title="${projectList.projDesc }" ><font size="5"><i class="fa fa-info-circle" aria-hidden="true"></i></font></a>  
			       	</td>
			        <td >${projectList.projectOwner }</td>
			        <td >  
			        	<fmt:parseDate pattern="yyyy-MM-dd" value="${projectList.startDate }" var="parsedDate" />
						<fmt:formatDate value="${parsedDate}" var="parsedDate" pattern="dd-MM-yyyy" />
				        ${parsedDate }
				    </td> 
			        <td >${projectList.estimateTarget }</td>
			        <td >${projectList.deliverySchedule }</td>
			        <td >
			        	 ${projectList.deliveredTillNow }
			        </td> 
			        <td > 
			        		<c:out value="${projectList.estimateTarget - projectList.deliveredTillNow}"></c:out>  
			        </td>
			        <td > 
	    				<fmt:parseDate pattern="yyyy-MM-dd" value="${projectList.nextUpdateDate}" var="parsedDate" />
						<fmt:formatDate value="${parsedDate}" var="parsedDate" pattern="dd-MM-yyyy" />
				        ${parsedDate }  
			        </td>
			        <td >${projectList.delivery }</td> 
			        <td >${projectList.resources }</td>
			        <td style="text-align: center;">  
			        		<a href="#" title="${projectList.notes }" ><font size="5"><i class="fa fa-info-circle" aria-hidden="true"></i></font></a>
			        </td>
			      </tr>
	    	</c:forEach>
	    </tbody>
  	  </table>
	</main>		
        </div>
      </div>
    </div>


    
    <!-- Footer -->
    <%@include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
    <script src="<c:url value="/resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script>

 

  </body>

</html>
