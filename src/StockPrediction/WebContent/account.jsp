<!-- 
/**
 * written by: CHIA-JO LIN
 */ -->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'> 
    
    <link rel="stylesheet" href="css/account.css">   
  </head>

  <body>
    <div class="form">    
    <div class="cont_login">
		<h1>Account</h1>
		<ul class="tab-group">
			<li class="tab active"><a href="#login">Log In</a></li>
        	<li class="tab"><a href="#signup">Sign Up</a></li>       	
      	</ul>
      
		<div class="tab-content">		
        	<div id="login">   
				<form action="LoginServlet" method="post">
            	<div class="field-wrap">
            		<label>
						User Name<span class="req">*</span>
            		</label>
            		<input type="text" name="uname" required autocomplete="off"/>
          		</div>        
          		<div class="field-wrap">
            		<label>
              		Password<span class="req">*</span>
            		</label>
            		<input type="password"required name="pwd" autocomplete="off"/>
          		</div>          
          		<!--<p class="forgot"><a href="#">Forgot Password?</a></p>-->         
          		<button class="button button-block">Log In</button>     
          		</form>
        	</div> 
			<div id="signup">             
				<form action="RegServlet" method="post">          
				<div class="field-wrap">
					<label>
                		User Name<span class="req">*</span>
            		</label>
            		<input type="text" name="uname" required autocomplete="off"/>
				</div>
				<div class="field-wrap">
					<label>
              			Enter Password<span class="req">*</span>
            		</label>
            		<input type="password" name="pwd" id="pwd" onchange ="rangeCheck();" required autocomplete="off"/>
				</div>
				<div class="field-wrap">
            		<label>
              			Confirm Password<span class="req">*</span>
            		</label>
            		<input type="password" name="cpwd" id="cpwd" onchange ="passwordCheck();" required autocomplete="off"/>
				</div>      
				<button type="submit" class="button button-block">Get Started</button>        
          		</form>
			</div>
       
		</div> <!-- tab-content -->     
	</div> 
	</div><!-- /form -->
	
	<!-- JavaScript library for DOM operations -->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <!-- my script -->
	<script src="js/account.js"></script>
    <script src="js/password_check.js"></script>
    
    
<!-- 下面目前沒用 要改 -->
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
