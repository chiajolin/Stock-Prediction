<!-- 
/**
 * written by: CHIAJO LIN
 */ -->
<!DOCTYPE html>
<%@page import="models.ManageUserBean"%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Stock Prediction</title>
    <link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Dancing+Script">
	<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto">
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'> 
    <!-- <link rel="stylesheet" href="css/normalize.css"> -->
    <!--<link rel="stylesheet" href="css/style.css"> -->
    <link rel="stylesheet" href="css/home.css">  
    <link rel="stylesheet" href="css/dashboards.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="js/dashboards.js"></script>
	<%ManageUserBean user = (ManageUserBean)session.getAttribute("user");
	String userName = "User";%>
  </head>

  <body>
  	<div class="container">
		<header>
			<div class="menu-toggle" data-js="menu-toggle">
				<span class="menu-toggle-grippy">Toggle</span> 
				<span class="menu-toggle-label">Menu</span>
			</div>
		</header>

		<section class="banner" >
				<% if (user != null) {
					userName = user.getUserName();
				}
				%>
				<h2>Welcome <%= userName%></h2>

			<!--  <div id="chart_navigation_bar">
				<a href="index.jsp?priceMode=realtime">Real-time Price</a>
				&nbsp;&nbsp; <a href="index.jsp?priceMode=highest">Highest Price</a> 
				&nbsp;&nbsp; <a href="index.jsp?priceMode=average" >Average Price</a>
				&nbsp;&nbsp; <a href="index.jsp?priceMode=lowest">Lowest Price</a>
			</div>-->
			<div class="stock_container" id = "stock_container_div">
				<!-- content here -->
			</div>
		</section>


		<div class="hidden-panel">
			<!--  <span class="hidden-panel-close" data-js="hidden-panel-close">Close</span>-->

			<div class="hidden-panel-content">
				<nav class="hidden-panel-nav">
					<h2>Quick Look</h2>
					<jsp:include page="navigation_pannel.jsp" />
				</nav>

				<div class="hidden-panel-text">
					<p>This is a stock prediction website implemented by group 5 for Rutgers class cs568.</p>
				</div>
				<br/>
				<div class="hidden-panel-credits">
					<span>Group 5</span>
					<span><a >Weizhong Kong (wk153)</a></span>
					<span><a >Chiajo Lin (cl1074)</a></span> 
					<span><a >Haiying Liu (hl689)</a></span> 
					<span><a >Mengyao Xu (mx62)</a></span> 					 
					<span><a >Wentao Zhu (wz220)</a></span>  
					<span><a >Ce Zhang (cz243)</a></span>  
				</div>

			</div>
		</div>
	</div>
	
	<!-- JavaScript library for DOM operations -->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <!-- my script -->
	
	<script src="js/home.js"></script>

    <% String status =(String) request.getAttribute("status");
    if(status == null){}
    else{
    	//success
    	if(status.equals("success")){
    	%>
    	<script type="text/javascript">
    	window.alert("User Registered");
    	</script> 	
    	<%
    	request.setAttribute("status", null);
    	}
    	
    	//user exists
    	else if(status.equals("exists")){
    	%>
    	<script type="text/javascript">
    	window.alert("Username already exists");
    	</script>
    	<%
    	request.setAttribute("status", null);
    	}
    	
    	//login failure
    	else if(status.equals("login failure")){
    	%>
    	<script type="text/javascript">
    	window.alert("Username or password doesn't exist");
    	</script>
    	<%
    	request.setAttribute("status", null);
    	}
    }%>   
  </body>
</html>
