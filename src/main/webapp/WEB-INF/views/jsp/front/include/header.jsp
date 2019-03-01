<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <!-- Navigation -->
  <c:if test="${userDetails.fname != null }"> 
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <!-- <a class="navbar-brand" href="#">infoAnalytica</a> -->
      <div class="container">
        <a class="navbar-brand" href="#">infoAnalytica</a> 
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto mr-6">
            <%-- <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/front">Dashboard
                <span class="sr-only">(current)</span>
              </a> 
            </li>  --%>
            
            <% if(CommonUtility.USER_ROLE_ID==1){
            	%>
		            <%-- <li class="nav-item active">
		              <a class="nav-link" href="<%=request.getContextPath() %>/account">Account  </a>
		            </li> --%>	
            <% } %>
            
            
            <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/dashboard">Dashboard  </a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/company">Company  </a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/department">Department  </a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/personContact">Manage  </a>
            </li>
            <%-- <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/project">Project  </a>
            </li> --%>    
               
            <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/manageProject">manageProject  </a>
            </li>   
            <%-- <li class="nav-item active">
              <a class="nav-link" href="<%=request.getContextPath() %>/company/1">Company  </a>
            </li> --%>
             
            <li>
            <%-- <div class="dropdown">
			  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			     <i class="fa fa-user fa-fw"></i>  ${userDetails.fname } ${userDetails.lname } <!-- (Admin)  -->   
			  </button>
			  <div class="dropdown-menu"> 
			    <a class="dropdown-item" href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
			    <a class="dropdown-item" href="<%=request.getContextPath() %>/userSettings"><i class="fa fa-gear fa-fw"></i> Settings</a>
			    <div class="dropdown-divider"></div>
			    <a  class="dropdown-item" href="<%=request.getContextPath() %>/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
			  </div>
			</div> --%>
			</li> 
            <%-- <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath() %>/integration">Integration </a>
            </li> --%>
          </ul>
          <ul class="navbar-nav">            
            <li class="nav-item"> 
              <a class="btn btn-primary" href="<%=request.getContextPath() %>/logout">Logout</a>
            </li>
          </ul> 
        </div>
      </div>
    </nav>
   </c:if>
	<c:if test="${userDetails.fname == null }">
      		<c:redirect url="/"></c:redirect>
    </c:if>
</html>
