<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Dashboard</title> 
    <link rel="icon" href="<c:url value="resources/image/favicone.jpg"></c:url>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value="resources/bootstrap/style.css"></c:url>"> 
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css"></c:url>">     
    <link rel="stylesheet" href="<c:url value="resources/bootstrap/css/bootstrap-responsive.css"></c:url>">
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
 	
	
	 
	<script type="text/javascript">  
	
	$(document).ready(function(){
		  $('ul li a').click(function(){
		    $('li a').removeClass("active");
		    $(this).addClass("active");
		});
		}); 
	
	
	function sendRequest(){
		
		
		/* var invocation = new XMLHttpRequest();
		var url = 'http://63.142.251.156:5000/enrich';
		var body = '<?xml version="1.0"?><person><name>Arun</name></person>';
		    
		function callOtherDomain(){
		  if(invocation)
		    {
		      invocation.open('POST', url, true);
		      invocation.setRequestHeader('X-PINGOTHER', 'pingpong');
		      invocation.setRequestHeader('Content-Type', 'application/xml');
		      invocation.onreadystatechange = handler;
		      invocation.send(body); 
		    }
		} */
		
		
		/*  $.ajax({
	            url: '/test/PersonSubmit',
	            type: 'post',
	            dataType: 'json',
	            success: function (data) {
	                $('#target').html(data.msg);
	            },
	            data: person
	        }); */
		 
 	var urlList = '{"url_set_id":"1", 	"data":["infoanalytica.com","2night.com"]}';
		
 	var tdata = JSON.stringify( { "url_set_id": "1", "data": '["infoanalytica.com","2night.com"]' } );
		 
		alert("tdata--->"+tdata);
		
		
		$.ajax({
		    url: "http://63.142.251.156:5000/enrich",
		    type: "GET",  
		    //dataType: "text",     
		    data: {
		    		"task_id": "Task_1", 
		    		"scrape_field" : 'Address'
		    	},
		    success: function(data){
		        alert(data);
		    },
		    error: function(error){
		         console.log("Error:");
		         console.log(error);
		    }
		});
		 
		 
		
		
	}

	function getEnrich(){
		
		$.ajax({
			type: "GET",     
	        url: "http://63.142.251.156:5000/home",
	        //contentType: "application/json",
	        //dataType: "json",
	        data : {
	        	 name : "['test.com','111.com']",
	        	 task_id : "task_1"
	        },
			success : function(data){
				alert(data)
			},
			error : function(e){
				
			}
			
			
		});
		
	}
	
	</script>
    
  </head>

  <body>

    <div id="wrap">
      <!-- Fixed navbar Start-->
      <%@include file="include/header.jsp" %>
      <!-- Fixed navbar End -->
       

      <!-- Begin page content -->
      <div class="container">
        <div class="page-header">
          <h3 class="header_mg">Upload dataset</h3>  
        </div>
         
		  <form action="insertDataset" method="post" enctype="multipart/form-data" >
		  		<div class="form-row">
				  <div class="form-group col-md-6">
				    <label for="inputDataset">Dataset Name</label>
				    <input type="text" class="form-control" id="dataSetName"  name="dataSetName" required placeholder="Enter dataset name">
				  </div>
			      
				  <div class="form-group">
				    <label for="exampleInputFile">Upload Dataset</label>
				    <input type="file" class="form-control-file" id="exampleInputFile" name="exampleInputFile" aria-describedby="fileHelp">
				  </div>
			 
				  <fieldset class="form-group">
				    <legend>Select option you want to scrap</legend>
				    <div class="form-check" al>
				      <label class="form-check-label">
				        <input type="radio"  class="form-check-input radio_button" name="optionsRadios" id="optionsAddress" value="Address" checked>
				        Address &nbsp;&nbsp; 
				         <input type="radio" class="form-check-input radio_button" name="optionsRadios" id="optionstechInstall" value="techInstall">
				        Tech Install
				      </label>
				    </div>  	      
				  </fieldset> 
		  		 <button type="button" class="btn btn-primary" onclick="sendRequest()">Submit</button>
		  		 <button type="button" class="btn btn-primary" onclick="getEnrich()">Enrich</button>
		  		 
		  	  </div> 
		</form>


      </div>

      <div id="push"></div>
    </div>

    <!-- <div id="footer">
      <div class="container">
        <p class="muted credit">Example courtesy <a href="http://martinbean.co.uk">Martin Bean</a> and <a href="http://ryanfait.com/sticky-footer/">Ryan Fait</a>.</p>
      </div>
    </div> -->
  


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="<c:url value="resources/bootstrap/js/bootstrap-dropdown.js"></c:url>"></script>
    <!-- <script src="../assets/js/jquery.js"></script>   
    <script src="../assets/js/bootstrap-transition.js"></script>
    <script src="../assets/js/bootstrap-alert.js"></script>
    <script src="../assets/js/bootstrap-modal.js"></script>
    <script src="../assets/js/bootstrap-dropdown.js"></script>
    <script src="../assets/js/bootstrap-scrollspy.js"></script>
    <script src="../assets/js/bootstrap-tab.js"></script>
    <script src="../assets/js/bootstrap-tooltip.js"></script>
    <script src="../assets/js/bootstrap-popover.js"></script>
    <script src="../assets/js/bootstrap-button.js"></script>
    <script src="../assets/js/bootstrap-collapse.js"></script>
    <script src="../assets/js/bootstrap-carousel.js"></script>
    <script src="../assets/js/bootstrap-typeahead.js"></script> --> 

  </body>
</html>