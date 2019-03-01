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
    <link href='<c:url value="/resources/front2/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    
    <link href="<c:url value="/resources/front2/css/custom_style.css"></c:url>" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/front2/fonts/font-awesome/css/font-awesome.min.css"></c:url>" rel="stylesheet" type="text/css">

 
  

  </head>

  <body>

   <!-- Navigation Start-->
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

  
    <!-- Page Content  -->
    <div class="container">
     
		<h4 class="mt-4 mb-4">
			Start The New Integration Session
		</h4>
      <div class="row">
        <div class="col-lg-6">         	
			<div class="form-group">
				<label for="exampleInputFile">Select the Company Task ID </label>
				<select class="form-control" id="exampleSelect1">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				  <option>4</option>
				  <option>5</option>
				</select>
		  	</div>
		  </div>
		</div>
		  	
		  	<h5 class="mt-4 mb-4">
				Select the Web site you want to Integrate
			</h5>
			<ul class="image_select list-unstyled">
				<li>
					<label class="image-checkbox">
					  <span class="text">Yellow Pages</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
						<span class="text">Google Places</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Yelp</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Better Business Bureau</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Bambora</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Linkedin Company</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Linkedin Contact</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Facebook</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">Crunch base</span>
					  <input type="checkbox" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
				<li>
					<label class="image-checkbox">
					  <span class="text">ALL</span>
					  <input type="checkbox"  id="chk-selectAll" name="image[]" value="" />
					  <i class="fa fa-check hidden"></i>
					</label>
				</li>
			</ul>
			


			<div class="mb-4">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
    </div>
    
    
   <!-- Footer -->
    <%@include file="include/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
     <script src="<c:url value="resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
     <script src="<c:url value="resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script>
    
    <script type="text/javascript">
	 // image gallery
		// init the state from the input
		$(".image-checkbox").each(function () {
		  if ($(this).find('input[type="checkbox"]').first().attr("checked")) {
			$(this).addClass('image-checkbox-checked');
		  }
		  else {
			$(this).removeClass('image-checkbox-checked');
		  }
		});

		// sync the state to the input
		$(".image-checkbox").on("click", function (e) {
		  $(this).toggleClass('image-checkbox-checked');
		  var $checkbox = $(this).find('input[type="checkbox"]');
		  $checkbox.prop("checked",!$checkbox.prop("checked"))

		  e.preventDefault();
		}); 
	 </script>
	 
	 <script type="text/javascript">
	 	$('#chk-selectAll').on('click', function(){
			$('.image-checkbox').prop('checked', $(this).prop('checked'));
		}); 
	  </script>

  </body>

</html>
