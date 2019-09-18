/**
 * written by: HAIYING LIU
 */
package stock.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;  
import java.sql.Statement;
import java.sql.Timestamp;  


import models.JDBCUtil;



/**
 * Servlet implementation class StockHistoricalChart
 */
@WebServlet("/StockHistoricalChart")
public class StockHistoricalChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockHistoricalChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {  
			
			JDBCUtil connection = new JDBCUtil();
			Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();  
            
            String stockId = request.getParameter("stockId").trim();
            String sql = "SELECT Time, Open, High, Low, Close, Volume FROM Historical WHERE Stock_Id = " + stockId + " ORDER BY Time ASC;";
    
            //String sql = "CREATE TABLE person(uid varchar(32),name char(32))";  
  
            // 创建数据库中的表，  
            ResultSet result = stmt.executeQuery(sql);  
            int stockCount = 0;
            //JSONArray []resultJson = new JSONArray[400];
            
            String output = "[";
            boolean firstRow = true;
            while(result.next())
            {
            	Date date = result.getDate("Time");
            	Timestamp ts = new Timestamp(date.getTime());
            	String open = result.getString("Open");
            	String high = result.getString("High");
            	String low = result.getString("Low");
            	String close = result.getString("Close");
            	Integer volume = result.getInt("Volume");
            	
            	String [] item = new String[6];
            	item[0] = "" + ts.getTime();
            	item[1] = open;
            	item[2] = high;
            	item[3] = low;
            	item[4] = close;
            	item[5] = volume.toString();
            	//JSONArray arrayJson = new JSONArray(output); 
            	//resultJson[i++] = arrayJson;
            	
            	//Time time = result.getTime("Time");
            	//output += "<div class=\"stock\" id=\"stock-" + stockCount + "\">";
            	//output += "<a class=\"stock_link\" href=\"historical_chart.jsp?name=" + name + "\">";
            	//output += "<span class=\"symbol\">" + name +  "</span> <span class=\"change\">" + price + "</span></a></div>";
            	//stockCount++;
            	if (firstRow == true){
            		firstRow = false;
            	}
            	else {
            		output += ",\n";
            	}
            	output += "[";
            	output += item[0];
            	for (int i = 1; i < 6; i++){
            		output += "," + item[i];
            	}
            	output += "]";
           }
            conn.close();  
            output += "]";
            
            response.getWriter().append(output);
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
