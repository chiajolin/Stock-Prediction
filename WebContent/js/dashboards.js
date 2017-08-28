/**
 * written by: HAIYING LIU
 */
$(document).ready(function() {
	var url = window.location.href + "";
	var urlItems = url.split('?');
	var price_mode = "realtime";
	var maxStock = 0;
	
	if (urlItems.length > 1) {
		var params = urlItems[1].split('&');
		if (params.length > 1) {
			price_mode = params[0].split('=')[1];
			
			if (params.length > 2) {
				maxStock = params[1].split('=')[1];
			}
		}
		else {
			price_mode = urlItems[1].split('=')[1];
			
		}
	}
	
	$.get('InitializeDashboardServlet', {
		priceMode : price_mode,
		maxStocks : maxStock
	}, function(responseText) {
		$('#stock_container_div').html(responseText);
	});
});