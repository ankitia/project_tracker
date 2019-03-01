<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">   
<head>
<title>Login</title>
  
	
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
<link rel="stylesheet" href="<c:url value="resources/css/style.css"></c:url>">
<link rel="stylesheet" href="<c:url value="resources/bootstrap/css/bootstrap.min.css"></c:url>">
<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.min.css"></c:url>">
<link rel="stylesheet" href="<c:url value="resources/css/font-awesome.min.css"></c:url>">
 
<script src="<c:url value="resources/js/jquery.min.js"></c:url>"></script>
<script src="<c:url value="resources/js/jquery.validate.min.js"></c:url>"></script>
<script src="<c:url value="resources/bootstrap/js/bootstrap.min.js"></c:url>"></script>
<script src="<c:url value="resources/bootstrap/js/ValidationFormScript.js"></c:url>"></script>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<style type="text/css">
   
   .bg-dark{
	   background-color: #2957a4!important;
}
 
.text-center{
text-align: center !important;

}

.navbar-brand {
    float: none; 
    height: 50px;
    padding: 15px 15px;
    font-size: 18px;
    line-height: 20px;
    color: #FFF !important; 
    margin-left: -58px !important;
    font-size: 25px  !important; 
}
</style>

</head>
<body class="">


<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <!-- <a class="navbar-brand" href="#">infoAnalytica</a> -->
      <div class="container">
        <a class="navbar-brand" href="#">infoAnalytica</a> 
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse text-center" id="navbarResponsive">
        	<a class="navbar-brand nav-link ">Project Tracker</a>
          <ul class="navbar-nav ml-auto mr-6">
            <%-- <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/front">Dashboard
                <span class="sr-only">(current)</span>
              </a> 
            </li>  --%>
            <li></li>
            
          </ul>
          
        </div>
      </div>
    </nav>

 <!-- id="form1" -->
 <form action="checkUser" method="post" class="form-horizontal" >
<div class="form-signin ">
  
	  <fieldset>	    
	    <!-- Form Name -->    
	    <h3 class="brand-home text-center mg0"><strong> infoAnalytica </strong></h3>
	    <hr>
	    
	    <!-- Text input-->
	    <div class="form-group">
	      <label class="col-md-3 control-label" for="Email">E-Mail</label>
	      <div class="col-md-9">
	        <div class="input-group"> <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
	          <input id="Email" required name="Email" type="text" placeholder="Enter Your Email" class="form-control input-md">
	        </div>
	      </div>
	    </div>
	    
	    <!-- Password input-->
	    <div class="form-group">
	      <label class="col-md-3 control-label" for="Password">Password</label>
	      <div class="col-md-9">
 	        <div class="input-group"> <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
	          <input id="password" required name="password" type="password" placeholder="Enter Your Password" class="form-control input-md">
	        </div>
	      </div>
	    </div>
	   
	   <c:if test="${fn:length(message) > 0 }">
		    <div class="form-group">
		      <div class="col-md-12 text-center">   
		        <font color="red" size="3" >${message}</font> <br>
		       </div>
		    </div>
	    </c:if>   
	    <!-- Button -->
	    <div class="form-group">
	      <label class="col-md-4 control-label" ></label>
	      <div class="col-md-8">      
	        <!-- <button id="Submit"  type="submit">Login</button> -->
	          
	        <input type="submit" name="login" value="Login" class="btn btn-info btn-md">
	        <!-- <a href="front" class="btn btn-info btn-md">New Design</a>  -->
	      </div>
	    </div>
	  </fieldset>

</div>
</form> 
<!-- <script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script> --> 

  
 
</body>
</html>