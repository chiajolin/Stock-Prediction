<!-- 
/**
 * written by: HAIYING LIU & Mengyao Xu
 */ -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Dancing+Script">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="css/home.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>

<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
<script src="js/historical_chart2.js"></script>
<script type="text/javascript">
	    function ShowChart(chart_id) {
	    	var chartObjs = new Array();
	    	chartObjs.push(document.getElementById("historical_chart_container"));
	    	chartObjs.push(document.getElementById("realtime_chart_container"));
	    	chartObjs.push(document.getElementById("indicator_chart_container"));
	    	chartObjs.push(document.getElementById("sentiment_chart_container"));
	    	chartObjs.push(document.getElementById("prediction_container"));
	    	
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
				<span class="menu-toggle-grippy">Toggle</span> <span
					class="menu-toggle-label">Menu</span>
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
				<a href="#" onclick="javascript:ShowChart(2);">Indicator</a>
				&nbsp;&nbsp; 
				<a href="#" onclick="javascript:ShowChart(3);">Sentiment Analysis</a>
				&nbsp;&nbsp; 
				<a href="#" onclick="javascript:ShowChart(4);">Prediction</a>
			</div>
			<div id="historical_chart_container"
				style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: block;"></div>
			<div id="realtime_chart_container"
				style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;">realtime_chart_container
				TBD</div>
			<div id="indicator_chart_container"
				style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;">indicator_chart_container
				TBD</div>
			<div id="sentiment_chart_container"
				style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;"></div>
			
			<div id="prediction_container"
				style="height: 400px; width: 100%; margin: 0 auto; min-width: 640px; display: none;">
				<table style="width: 60%; margin: 0 auto; background: #eeffee">
					<tr>
						<td style="width:60%">Prediction: Naive Bayes</td>
						<td><span id="naive_bayes_predict_value"></span></td>
						<td><span id="naive_bayes_predict_change"></span></td>
					</tr>
					<tr>
						<td style="width:60%">Prediction: SVM</td>
						<td><span id="svm_predict_value"></span></td>
					</tr>
					<tr>
						<td style="width:60%">Prediction: ANN</td>
						<td><span id="ann_predict_value"></span></td>
						<td><span id="ann_predict_change"></span></td>
					</tr>
				</table>
				</div>
		</div>

		<div class="hidden-panel">
			<span class="hidden-panel-close" data-js="hidden-panel-close">Close</span>

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
	<script src="js/home.js"></script>
	<!-- <script type="js/historical_chart.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    -->
</body>
</html>