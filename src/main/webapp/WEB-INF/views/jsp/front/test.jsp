<!DOCTYPE html>
<%@page import="com.ia.config.CommonUtility"%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="<%=request.getContextPath() %>/resources/image/favicon.ico" type="image/gif" sizes="16x16">
    <title>Test | infoAnalytica</title>

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


<!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript" src="resources/grid/js/jquery.min.js"></script>
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="resources/grid/js/grid.locale-en.js"></script>
    <!-- This is the Javascript file of jqGrid --> 
    <script type="text/ecmascript" src="resources/grid/js/jqGrid.min.js"></script>
    <!-- A link to a Boostrap  and jqGrid Bootstrap CSS siles-->
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> -->
    <link rel="stylesheet" type="text/css" media="screen" href="resources/grid/css/jqgrid-bootstrap.css" />
	<script>  
		$.jgrid.defaults.width = 780;
		$.jgrid.defaults.responsive = true;
		$.jgrid.defaults.styleUI = 'Bootstrap';
	</script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/ecmascript" src="resources/grid/js/bootstrap-datepicker.js"></script>
    <script type="text/ecmascript" src="resources/grid/js/bootstrap3-typeahead.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="resources/grid/css/bootstrap-datepicker.css" />

 
  </head>

  <body>
  
    <%@include file="include/header.jsp" %> 
    <!-- Navigation End-->

    <!-- Page Content -->
    <div class="container mb-50">
      <div class="row"> 
        <div class="col-lg-12"> 
        	 
          <div style="margin-left:20px;margin-top: 20px;">
			    <table id="jqGrid"></table>
			    <div id="jqGridPager"></div>
			</div>	 
		
		
		
					 
			
        </div>
      </div>
    </div>


    
    <!-- Footer -->
    <%@include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <%-- <script src="<c:url value="/resources/front2/js/jquery/jquery.min.js"></c:url>"></script>
    <script src="<c:url value="/resources/front2/js/bootstrap/bootstrap.bundle.min.js"></c:url>"></script> --%>

	<script type="text/javascript">

        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'sampleData',
                mtype: "GET",
                datatype: "json",
                page: 1,
                colModel: [
                    {   label : "Project ID",
						//sorttype: 'integer',
						name: 'projectID',
						key: true,
						width: 150
					},
                    {
						label: "Project Name",
                        name: 'projectName',
                        width: 150,
                        // stype defines the search type control - in this case HTML select (dropdownlist)
                        //stype: "select",
                        // searchoptions value - name values pairs for the dropdown - they will appear as options
                        //searchoptions: { value: ":[All];ALFKI:ALFKI;ANATR:ANATR;ANTON:ANTON;AROUT:AROUT;BERGS:BERGS;BLAUS:BLAUS;BLONP:BLONP;BOLID:BOLID;BONAP:BONAP;BOTTM:BOTTM;BSBEV:BSBEV;CACTU:CACTU;CENTC:CENTC;CHOPS:CHOPS;COMMI:COMMI;CONSH:CONSH;DRACD:DRACD;DUMON:DUMON;EASTC:EASTC;ERNSH:ERNSH;FAMIA:FAMIA;FOLIG:FOLIG;FOLKO:FOLKO;FRANK:FRANK;FRANR:FRANR;FRANS:FRANS;FURIB:FURIB;GALED:GALED;GODOS:GODOS;GOURL:GOURL;GREAL:GREAL;GROSR:GROSR;HANAR:HANAR;HILAA:HILAA;HUNGC:HUNGC;HUNGO:HUNGO;ISLAT:ISLAT;KOENE:KOENE;LACOR:LACOR;LAMAI:LAMAI;LAUGB:LAUGB;LAZYK:LAZYK;LEHMS:LEHMS;LETSS:LETSS;LILAS:LILAS;LINOD:LINOD;LONEP:LONEP;MAGAA:MAGAA;MAISD:MAISD;MEREP:MEREP;MORGK:MORGK;NORTS:NORTS;OCEAN:OCEAN;OLDWO:OLDWO;OTTIK:OTTIK;PERIC:PERIC;PICCO:PICCO;PRINI:PRINI;QUEDE:QUEDE;QUEEN:QUEEN;QUICK:QUICK;RANCH:RANCH;RATTC:RATTC;REGGC:REGGC;RICAR:RICAR;RICSU:RICSU;ROMEY:ROMEY;SANTG:SANTG;SAVEA:SAVEA;SEVES:SEVES;SIMOB:SIMOB;SPECD:SPECD;SPLIR:SPLIR;SUPRD:SUPRD;THEBI:THEBI;THECR:THECR;TOMSP:TOMSP;TORTU:TORTU;TRADH:TRADH;TRAIH:TRAIH;VAFFE:VAFFE;VICTE:VICTE;VINET:VINET;WANDK:WANDK;WARTH:WARTH;WELLI:WELLI;WHITC:WHITC;WILMK:WILMK;WOLZA:WOLZA" }
                    },{
						label: "Project Code",
                        name: 'projectCode',
                        width: 150,
                    },{
						label: "projectDesc",
                        name: 'projectDesc',
                        width: 150,
                    },{
						label: "projectOwner",
                        name: 'projectOwner',
                        width: 150,
                    },{
						label: "Start Date",
                        name: 'startDate',
                        width: 150,
						sorttype:'date',
						formatter: 'date',
						srcformat: 'Y-m-d',
						newformat: 'n/j/Y',
                        searchoptions: {
                            // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                            // use it to place a third party control to customize the toolbar
                            dataInit: function (element) {
                               $(element).datepicker({
									autoclose: true,
									format: 'd/m/yyyy',
									orientation : 'bottom'
                                });
                            }
                        }
                    },{
						label: "projectEstimate",
                        name: 'projectEstimate',
                        width: 150,
                    },{
						label: "deliverySchedule",
                        name: 'deliverySchedule',
                        width: 150,
                    },
                     
                   /*  {
						label : "Ship Name",
                        name: 'ShipName',
                        width: 150,
                        searchoptions: {
                            // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                            // use it to place a third party control to customize the toolbar
                            dataInit: function (element) {
							   $(element).attr("autocomplete","off").typeahead({
								   appendTo : "body",
									source: function(query, proxy) {
										$.ajax({
											url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/autocompletepbs.php?callback=?&acelem=ShipName',
											dataType: "jsonp",
											data: {term: query},
											success : proxy
										});
									}
							   });
                            },
							sopt : ['cn']
                        }
                    }, */
                    /* {
						label: "Freight",
						sorttype: 'number',   
						name: 'Freight', width: 150 }, */
                ],
				loadonce: true,
				viewrecords: true,
                width: 780,
                height: 250,
                rowNum: 10,
                pager: "#jqGridPager"
            });
			// activate the toolbar searching
            $('#jqGrid').jqGrid('filterToolbar');
			$('#jqGrid').jqGrid('navGrid',"#jqGridPager", {
                search: false, // show search button on the toolbar
                add: false,
                edit: false,
                del: false,
                refresh: true
            });

        });

    </script>
 

  </body>

</html>
