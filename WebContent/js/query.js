/**
 * written by: CHIAJO LIN & HAIYING LIU
 */

function getSelectValues(select) {
  var result = [];
  var options = select && select.options;
  var opt;

  for (var i=0, iLen=options.length; i<iLen; i++) {
    opt = options[i];

    if (opt.selected) {
      result.push(opt.value);
    }
  }
  return result;
}

$(document).ready(function() {
	$.get('SelectCompanyQuery', {
		
	}, function(responseText) {
		var selectCompanyObj = document.getElementById("queryCompanySelect");
		var items = responseText.split("#");
		for (var i = 0; i < items.length - 1; i += 2) {	// the last item is empty 2#sdfa#3#sdaf#
			var option = document.createElement("option");
		    option.text = items[i + 1];
		    option.value = items[i];
		    selectCompanyObj.add(option);
		}
	});

	$("#btn_query").click(function(){
		var selectQueryTpye = document.getElementById("querySelectType");
		var selectedQueryType = getSelectValues(selectQueryTpye);
		
		var selectCompanyObj = document.getElementById("queryCompanySelect");
		var selectedCompany = getSelectValues(selectCompanyObj);
		if (selectedCompany.length < 1)
			return;
		var paramStr = "" + selectedCompany[0];
		for (var i = 1; i < selectedCompany.length; i++) {
			paramStr = paramStr + "#" + selectedCompany[i];
		}
		
		if(selectedQueryType == "Query Fifth"){
			$.get('ComplicatedQuery', {
				paramString : paramStr
			}, function(responseText) {
				$("#table_query_result").html(responseText);
			});
		}
		else if(selectedQueryType == "Lowest Price"){
			$.get('LowestPriceServlet', {
				paramString : paramStr
			}, function(responseText) {
				$("#table_query_result").html(responseText);
			});
		}		
		else if(selectedQueryType == "Average Price"){
			$.get('AveragePriceServlet', {
				paramString : paramStr
			}, function(responseText) {
				$("#table_query_result").html(responseText);
			});
		}		
		else if(selectedQueryType == "Highest Price"){
			$.get('HighestPriceServlet', {
				paramString : paramStr
			}, function(responseText) {
				$("#table_query_result").html(responseText);
			});
		}
		
		else if(selectedQueryType == "Real-time Price"){
			$.get('RealtimePriceServlet', {
				paramString : paramStr
			}, function(responseText) {
				$("#table_query_result").html(responseText);
			});
		}
	});
});