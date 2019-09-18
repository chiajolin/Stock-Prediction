<!-- 
/**
 * written by: HAIYING LIU
 */ -->
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Dancing+Script">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="css/home.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    
    <script src="https://code.highcharts.com/stock/highstock.js"></script>
    <script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
    <script src="js/historical_chart.js"></script>
    <script type="text/javascript">
	    function ShowChart(chart_id) {
	    	var chartObjs = new Array();
	    	chartObjs.push(document.getElementById("historical_chart_container"));
	    	chartObjs.push(document.getElementById("realtime_chart_container"));
	    	chartObjs.push(document.getElementById("indicator_chart_container"));
	    	chartObjs.push(document.getElementById("sentiment_chart_container"));
	    	
	    	for (var i = 0; i < chartObjs.length; i++) {
	    		chartObjs[i].style.display = "none";	// make everyone invisible
	    	}
	    	chartObjs[chart_id].style.display = "block"; // show the target chart
	    }
	</script>
  </head>
    
    
  <body>
    <div class="container">
    <header>
      <div class="menu-toggle" data-js="menu-toggle">
        <span class="menu-toggle-grippy">Toggle</span>
        <span class="menu-toggle-label">Menu</span>
      </div>
    </header>
    
    <!-- <div class="main-chart">    
    	<container>  
    	<div id="chart1" class="chart-area" >
    		<div class="aspect-ratio" ></div>
    		<div class="chart-wrapper" >
        		<div class="chart" ></div>
    		</div>
		</div>
	</div> -->
	
	<div class="banner">
		<br /> <br />
		<div id="chart_navigation_bar">
			<a href="#" onclick="javascript:ShowChart(0);">Historical Data</a>
			&nbsp;&nbsp;
			<a href="#" onclick="javascript:ShowChart(1);">Real-time Data</a>
			&nbsp;&nbsp;
			<a href="#" onclick="javascript:ShowChart(2);">Prediction</a>
			&nbsp;&nbsp;
			<a href="#" onclick="javascript:ShowChart(3);">Sentiment Analysis</a>
		</div>
		<div id="historical_chart_container" style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: block;"></div>
		<div id="realtime_chart_container" style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;">realtime_chart_container TBD</div>
		<div id="indicator_chart_container" style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;">indicator_chart_container TBD</div>
		<div id="sentiment_chart_container" style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;"></div>
	</div>
  
    <div class="hidden-panel">      
      <span class="hidden-panel-close" data-js="hidden-panel-close">Close</span>
      
      <div class="hidden-panel-content">       
        <nav class="hidden-panel-nav">
          <h2>Quick Look</h2>
          <ul>
            <li><a rel="nofollow" rel="noreferrer"href="#" title="Home">Home</a></li>
            <li><a rel="nofollow" rel="noreferrer"href="#" title="About">About</a></li>
            <li><a rel="nofollow" rel="noreferrer"href="#" title="Blog">Blog</a></li>
            <li><a rel="nofollow" rel="noreferrer"href="#" title="Work">Work</a></li>
            <li><a rel="nofollow" rel="noreferrer"href="#" title="Contact">Contact</a></li>
          </ul>
        </nav>
        
        <div class="hidden-panel-text">
          <p>This is an experimental CodePen which utilises an absolutely positioned hidden panel which can be triggered by clicking a toggle.</p>
        </div>
        
        <div class="hidden-panel-credits">
          <span>Coded by <a rel="nofollow" rel="noreferrer"href="http://twitter.com/darrenhuskie" title="Darren Huskie">Darren Huskie</a>.</span>
          <span>Powered by <a rel="nofollow" rel="noreferrer"href="#" title="some framework">some framework</a>.</span>
          <span>Hosted by a <a rel="nofollow" rel="noreferrer"href="#" title="web host">web host</a>.</span>
        </div>
     
      </div>
    </div>
    
    </div>
	<script src="js/home.js"></script>
	<!-- <script type="js/historical_chart.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    -->
  </body>
</html>