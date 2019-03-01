<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>infoAnalytica</title>
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- Bootstrap core CSS -->
    <link href='<c:url value="resources/front2/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    
    <link href="<c:url value="resources/front2/css/custom_style.css"></c:url>" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="<c:url value="resources/front2/fonts/font-awesome/css/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css">

  

  </head>

  <body>

    <!-- Navigation Start-->
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
    <div class="container">
     
		<h4 class="mt-4 mb-4">
			Search The Database
		</h4>
      <div class="row">
        <div class="col-lg-6">
         	
         	<div class="form-group">
    			<label for="Location">Location</label>
				<input type="text" class="form-control" id="Location" aria-describedby="emailHelp" placeholder="Enter Location">
				
			  </div>
	  	</div>
        <div class="col-lg-6">
			  <div class="form-group">
				<label for="Industry">Industry</label>
				<input type="text" class="form-control" id="Industry" placeholder="Enter Industry">
			  </div>        
        </div>
        <div class="col-lg-6">
         	
         	<div class="form-group">
    			<label for="EmployeeSize">Employee Size</label>
				<input type="text" class="form-control" id="EmployeeSize" aria-describedby="emailHelp" placeholder="Enter Employee Size">
				
			  </div>
	  	</div>
        <div class="col-lg-6">
			  <div class="form-group">
				<label for="RevenueSize">Revenue Size</label>
				<input type="text" class="form-control" id="RevenueSize" placeholder="Enter Revenue Size">
			  </div>        
        </div>
        <div class="col-lg-6">
         	
         	<div class="form-group">
    			<label for="TechInstalled">Tech Installed</label>
				<input type="text" class="form-control" id="TechInstalled" aria-describedby="emailHelp" placeholder="Enter Tech Installed">
				
			  </div>
	  	</div>
        <div class="col-lg-6">
			  <div class="form-group">
				<label for="Persona">Persona</label>
				<input type="text" class="form-control" id="Persona" placeholder="Enter Persona">
			  </div>        
        </div>
        <div class="col-lg-12 text-right mb-4">
        	<button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </div>
    </div>
    
      
    <!-- Footer -->
    <%@include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
    <script src="<c:url value="resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script>
     

  </body>

</html>
