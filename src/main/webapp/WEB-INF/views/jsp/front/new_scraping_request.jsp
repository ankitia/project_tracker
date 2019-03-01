<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>infoAnalytica</title>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <!-- Bootstrap core CSS -->
    <link href='<c:url value="resources/front2/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    
    <link href="<c:url value="resources/front2/css/custom_style.css"></c:url>" rel="stylesheet">
    <link href="<c:url value="resources/front2/css/bootstrap-select.min.css"></c:url>" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="<c:url value="resources/front2/fonts/font-awesome/css/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css">
    
    <script type="text/javascript">
    function checkValidtion(){
    	
    	if($("#project_id").val()==""){
    		alert("Please select project");
    		return false;
    	}else if($("#exampleInputFile").val()==""){
    		alert("Please select input file");
    		return false;
    	}else if($("#productService").is(":checked")==false && $("#all").is(":checked")==false ){
    		alert("Please select scrap method");
    		return false;
    	}
    }
    
    </script>
    

  </head>

  <body>

    <!-- Navigation Start-->
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
    <div class="container">
     
		<h4 class="mt-4 mb-4">
			Start The New Scraping Session (Max request - <font color="red"> ${userDetails.limit - (userDetails.usageLimit + userDetails.pendingRequest) }</font>)
		</h4> 
		
	<form action="insertDataset" method="post" enctype="multipart/form-data" >	
      <div class="row">
        <div class="col-lg-6">     
        
        	<div class="form-group">
				<!-- <label for="exampleInputFile">Select Project</label> -->
				<label class="custom-file">
				  	<select name="project_id"  class="form-control" id="project_id">
        					<option value="">Select Project </option>	
				  		<c:forEach items="${projectList }" var="projectList">
				  			<option value="${projectList.projectId }">${projectList.name }</option>
				  		</c:forEach> 
			      </select>
				  
				</label>
		  	</div>
            	
			<div class="form-group">
				<label for="exampleInputFile">Upload the File</label>
				<label class="custom-file">
				  <input type="file" id="exampleInputFile" name="exampleInputFile" class="custom-file-input">
				  <span class="custom-file-control"></span>
				</label>
		  	</div>
		  	
		  	<h5 class="mt-4 mb-4">
				What do you want to Scrap
			</h5>
			<div class="table-responsive mb-2">
         	
         		<table class="table table-bordered ">
				  <thead class="thead-light">
					<tr>
					  <th scope="col">Tick the Right One</th>
					  <th scope="col">Info</th>
					</tr>
				  </thead>
				  <tbody>
				   <tr>
					  <td> 
						<label class="custom-control custom-checkbox">
							<input id="productService" type="checkbox" name="productService" class="custom-control-input">
							<span class="custom-control-indicator"></span>
					  	</label>
						</td>
					  <td>Product Service</td>
				  	</tr>
					<!-- <tr>
					  <td> 
						<label class="custom-control custom-checkbox">
							<input type="checkbox" name="address" class="custom-control-input">
							<span class="custom-control-indicator"></span>
					  	</label>
						</td>
					  <td>Address</td>
				  	</tr>
					<tr>
					  <td> 
						<label class="custom-control custom-checkbox">
							<input type="checkbox" name="keyContacts" class="custom-control-input">
							<span class="custom-control-indicator"></span>
					  	</label>
						</td>
					  <td>Key Contacts</td>
				  	</tr>
					<tr>
					  <td>  
						<label class="custom-control custom-checkbox">
							<input type="checkbox" name="foundation" class="custom-control-input">
							<span class="custom-control-indicator"></span>
					  	</label>
						</td>
					  <td>Foundation</td>
				  	</tr> -->
					<tr>
					  <td> 
						<label class="custom-control custom-checkbox">
							<input type="checkbox" id="all" name="all" class="custom-control-input">
							<span class="custom-control-indicator"></span>
					  	</label>
						</td>
					  <td>All</td>
				  	</tr>
				  </tbody>
				</table>
                    
			</div>
			<c:if test="${fn:length(message) > 0 }">   
				<div class="alert alert-danger">
				    ${message }
				</div>
			</c:if>
	  	</div>
	  	
		   
			
		 
       
        <div class="col-lg-12 mb-4">
        	<button type="submit" class="btn btn-primary" onclick="return checkValidtion()">Submit</button>
        	<a href="<%=request.getContextPath() %>/company/1" class="btn btn-danger" >Back</a>
        </div>
      </div>
     </form>
    </div>
    
    
   <!-- Footer -->
    <%@include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
     <script src="<c:url value="resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
     <script src="<c:url value="resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script>
     <script src="<c:url value="resources/front2/js/bootstrap/bootstrap-select.min.js"></c:url>"></script>
  </body>  
</html>
