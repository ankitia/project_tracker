<%@page import="com.ia.config.CommonUtility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Data set</title> 
    <link rel="icon" href="<c:url value="/resources/image/favicone.jpg"></c:url>" />
    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/style.css"></c:url>"> 
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"></c:url>">
    
<style>
.pagination {
    display: inline-block;
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

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>    
  </head>

  <body>

    <div id="wrap">
      <!-- Fixed navbar Start-->
      <%@include file="include/header.jsp" %>
      <!-- Fixed navbar End -->
       

      <!-- Begin page content -->
      <div class="container">
      
      <div class="page-header">
          <h3>Dataset List</h3>
        </div>
         
         <table class="table table-striped text-left">
    <thead>
      <tr> 
      	<th width="3%">Sr.</th>
        <th width="50%">Dataset Name</th>          
        <th width="15%" style="text-align: center;">Start time</th>
        <th width="15%" style="text-align: center;">End time</th>
        <th width="10%" style="text-align: center;">Total record</th> 
        <th width="10%" style="text-align: center;">Unique record</th>
        <th width="7%" style="text-align: center;">Status</th>
      </tr>  
    </thead>  
    <tbody>
       <c:forEach var="dataSetList" items="${dataSetList }" varStatus="status">
       		<tr>
       			<td>
       				<c:choose>
       					<c:when test="${page == 1 }">
       						${ status.count } 
       					</c:when>
       					<c:otherwise> 
	       					<c:set var="noOfrecord" value="<%=CommonUtility.NO_OFF_RECORDS %>" ></c:set>
	       					${ status.count + ((page-1) * noOfrecord )  } 
       					</c:otherwise>
       				</c:choose>
       					
       			</td>
       			<td>${dataSetList.dataSetName }</td>       			 
       			<td  style="text-align: center;">
       				<fmt:parseDate pattern="yyyy-MM-dd HH:mm" value="${dataSetList.startTime }" var="date"></fmt:parseDate>
       				<fmt:formatDate type="date" value="${date }" pattern="dd-MM-yyy HH:mm" var="date" /> 
       				<c:out value="${date }"></c:out> 
       			</td> 
       			<td  style="text-align: center;">
       				<fmt:parseDate pattern="yyyy-MM-dd HH:mm" value="${dataSetList.endTime }" var="date"></fmt:parseDate>
       				<fmt:formatDate type="date" value="${date }" pattern="dd-MM-yyy HH:mm" var="date" /> 
       				<c:out value="${date }"></c:out> 
       			</td>
       			<td style="text-align: center;">
       					<fmt:formatNumber value="${dataSetList.totalRecord}" type="NUMBER"></fmt:formatNumber>
       			</td>
       			<td style="text-align: center;">
       					<fmt:formatNumber value="${dataSetList.totalUniqueRecord}" type="NUMBER"></fmt:formatNumber>
       			</td>
       			<td style="text-align: right;"> 
       				<input type="button" name="button" value="${dataSetList.status }" class="btn btn-primary"> 
       				</td>
       		</tr>
       </c:forEach> 
    </tbody> 
  </table>

  <div class="pagination" style="float: right;"> 
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
	  
  </div>

      </div>

      <div id="push"></div>
    </div>
<!-- 
    <div id="footer">
      <div class="container">
        <p class="muted credit">Example courtesy <a href="http://martinbean.co.uk">Martin Bean</a> and <a href="http://ryanfait.com/sticky-footer/">Ryan Fait</a>.</p>
      </div>
    </div>
 -->


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="resources/bootstrap/js/jquery.js"></c:url>"></script>
    <script src="<c:url value="resources/bootstrap/js/bootstrap-dropdown.js"></c:url>"></script>
    <script src="../assets/js/jquery.js"></script>   
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
    <script src="../assets/js/bootstrap-typeahead.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		  $('div ul li a').click(function(){
			  alert("HEllo"); 
		    $('li a').removeClass("active");
		    $(this).addClass("active");
		});
		}); 
	</script>   
  </body>
</html>