<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="<c:url value="resources/bootstrap/css/bootstrap-responsive.css"></c:url>">
  </head>

  <body>
  
  	<c:if test="${userName != null }"> 
      <!-- Fixed navbar -->
      <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Athena web</a>
            <div class="nav-collapse collapse">    
              <ul class="nav"> 
                <li><a href="<%=request.getContextPath() %>/dashboard">Dashboard</a></li>
                <li><a href="<%=request.getContextPath() %>/dataSet/1">Data set</a></li>
               
                <!-- <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li> -->
              </ul>  
              <ul class="nav navbar-nav navbar-right">
			      <li><a href="<%=request.getContextPath() %>/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			    </ul> 
            </div><!--/.nav-collapse -->
             
                 
          </div>
        </div>
      </div>
      
      </c:if>
      <c:if test="${userName == null }">
      		<c:redirect url="/"></c:redirect>
      </c:if>
      
  </body>
</html>