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
        	 
          	 
      <main role="main" class="container">
	  <!-- Trigger the modal with a button -->  
	    
	   
	  <br /><br /> 
       <table class="table table-hover table-bordered">
	    <thead>
	      <tr>
	        <th width="3%">#</th>
	        <th width="10%">Project Code</th>
	        <th width="10%">Client</th>
	        <th width="10%">Project Name</th>
	        <th width="10%">Brief Description</th>
	        <th width="10%">Project Owner</th>
	        <th width="10%">Start date</th>
	        <th width="10%">ORDER (No. of records)</th>
	        <th width="27%" style="text-align: center;">Delivery Schedule</th>
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
			        <td >${projectList.projDesc }</td>
			        <td >${projectList.personToContact }</td>
			        <td >${projectList.startDate }</td>
			        <td >${projectList.estimateTarget }</td>
			        <td >${projectList.deliverySchedule }</td>
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
