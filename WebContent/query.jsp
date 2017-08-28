<!-- 
/**
 * written by: CHIAJO LIN
 */ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="models.ManageUserBean"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Page</title>
<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/home.css">
	<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="js/query.js"></script>
	<link rel="stylesheet" href="css/queryPage.css">


</head>

<body>
<div class="form">
<div class="cont_login">
<h1>Query</h1>
<!-- <p class="forgot"><a id="logout" href='LogOutServlet' onclick="invalidateSession();">Log Out</a></p>
<p class="forgot1"><a href='change_password.jsp'>Change Password</a></p>-->
				<form> 
						<h3>Please select one query type.</h3>
						<span class= "custom-dropdown">
						<select id="querySelectType" style="min-width:400px; height: 110px;" size="100" required>
						<option id ="real-time-price">Real-time Price</option>
						<option id ="highest-price">Highest Price</option>
						<option id ="average-price">Average Price</option>
						<option id ="lowest-price">Lowest Price</option>
						<option id ="query-fifth">Query Fifth</option>
    					</select>
						</span>

						<h3>Please select at least one company.</h3>
						<span class="custom-dropdown">
    					<select id="queryCompanySelect" multiple="multiple" style="min-width:400px; height: 220px;" size="100" required>
    					</select>
    					</span>  
						
						<table id="table_query_result" class="gridtable" style="width: 80%; margin: 10 auto;">
							
						</table>					

						<button type="button" class="button button-block" id="btn_query" >Start Query</button>
				</form>
</div>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  </body>
</html>
