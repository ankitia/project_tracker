<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>User Settings infoAnalytica</title>

	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	

    <!-- Bootstrap core CSS -->
    <link href='<c:url value="/resources/front2/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    
    <link href="<c:url value="/resources/front2/css/custom_style.css"></c:url>" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/front2/fonts/font-awesome/css/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css">

     

  </head>

  <body>
  
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-12" style="min-height: 450px;margin-top: 100px;">
        	<p class="text-right mt-4"> 
         		
         	</p>  
         	
         	<div class="table-responsive mb-4">
         		<table class="table table-bordered ">
				  <thead class="thead-light">
					<tr>
					  <th>User name</th>
					  <th scope="col">Server IP</th>					  
					  <th scope="col">Limit</th>
					  <th scope="col">Usage limit</th>
					  <th scope="col">Pending request</th>
 					</tr>
				  </thead>
				  <tbody>
					  	<tr>
						  <td>${userDetails.fname } ${userDetails.lname }</td>
						  <td>${userDetails.serverIp }</td>						  
						  <td>${userDetails.limit }</td>
						  <td>${userDetails.usageLimit }</td> 
						  <td>${userDetails.pendingRequest }</td>
				  </tbody>
				</table>
                    
			</div> 
			
			 
			
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
