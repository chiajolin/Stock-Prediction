<!-- 
/**
 * written by: CHIAJO LIN
 */ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="models.ManageUserBean"%>


<ul id="nav">
	<li><a href="index.jsp" title="Home">Home</a></li>
	<li><a href="query.jsp" title="Query">Query</a></li>
	<li><a href="account.jsp" title="Login" id="loginID">Login</a></li>
	
</ul>
<h3><a id="logout" href='LogOutServlet' >Log Out</a></h3>

<script type="text/javascript">
	$("#nav li a").click(function() {
		$("#stock_container_div").load($(this).attr('href'));
		return false;
	});
</script>
    
    <%ManageUserBean user = (ManageUserBean)session.getAttribute("user");
	String userName = "User";%>
	<% if (user != null) {%>
	<script>$("#loginID").hide();</script>
	<% } %>
	
	<% if (user == null) {%>
	<script>$("#logout").hide();</script>
	<% } %>


	
