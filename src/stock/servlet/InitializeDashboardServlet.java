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

import models.JDBCUtil;

import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.Statement;

/**
 * Servlet implementation class InitializeDashboardServlet
 */
@WebServlet("/InitializeDashboardServlet")
public class InitializeDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String priceMode = "realtime";
		if (request.getParameter("priceMode") != null)
			priceMode = request.getParameter("priceMode").trim();
		String output = "";
		try {  
			JDBCUtil connection = new JDBCUtil();
			Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();  
            
            String sql = "";
            if (priceMode.equals("realtime")) {
            	//sql = "SELECT R.Stock_Id, S.Name, R.Price, R.Time FROM Stock S, Realtime R WHERE S.Id = R.Stock_Id";
            	sql = "SELECT r5.Stock_Id, s.Name, r5.Price, r5.Time FROM Stock s INNER JOIN" +
            	"(SELECT r4.Price, r4.Stock_Id, r4.Time FROM Realtime r4 INNER JOIN" +
            	"(SELECT r1.Stock_Id, r1.Time, MIN(r1.Id) min_id FROM Realtime r1 INNER JOIN" +
            	"(SELECT  Stock_Id, MAX(Time) max_time FROM Realtime GROUP BY Stock_Id" +
            	") r2 ON  r1.Stock_Id = r2.Stock_Id AND r1.Time = r2.max_time GROUP BY r1.Stock_Id" +
            	") r3 ON r3.min_id = r4.Id"+
            	") r5 ON r5.Stock_Id = s.Id";    	
            }
            else if (priceMode.equals("highest")) {
            	//sql = "SELECT S.Id, S.Name, MAX(H.Close) FROM Stock S, Historical H WHERE S.Id = H.Stock_Id GROUP BY S.Name ORDER BY H.Time";
            	sql = "SELECT D.Stock_Id, D.Name, Max(D.Close) AS Price FROM (SELECT H.Stock_Id, H.Close, H.Time, S.Name FROM Historical H, Stock S WHERE S.Id = H.Stock_Id ORDER BY H.Time DESC LIMIT 10) D GROUP BY D.Stock_Id";
            }
            else if(priceMode.equals("average")) {
            	
            	sql = "SELECT D.Stock_Id, D.Name, AVG(D.Close) AS Price FROM (SELECT H.Stock_Id, H.Close, H.Time, S.Name FROM Historical H, Stock S WHERE S.Id = H.Stock_Id) D GROUP BY D.Stock_Id";
            }
            else if(priceMode.equals("lowest")) {
            	sql = "SELECT D.Stock_Id, D.Name, MIN(D.Close) AS Price FROM (SELECT H.Stock_Id, H.Close, H.Time, S.Name FROM Historical H, Stock S WHERE S.Id = H.Stock_Id) D GROUP BY D.Stock_Id";
            }
            //if (maxStocks > 0)
            //	sql += "LIMIT " + maxStocks;
    
            //String sql = "CREATE TABLE person(uid varchar(32),name char(32))";  
  
            // 创建数据库中的表，  
            ResultSet result = stmt.executeQuery(sql);  
            int stockCount = 0;
            while(result.next())
            {
            	String id = result.getString("Stock_Id");
            	String name = result.getString("Name");
            	//String price = result.getString("Price");
            	Float fprice = result.getFloat("Price");
            	String price = String.format("%.02f", fprice);
            	//Time time = result.getTime("Time");
            	output += "<div class=\"stock\" id=\"stock-" + stockCount + "\">";
            	output += "<a class=\"stock_link\" href=\"historical_chart2.jsp?Id=" + id + "&name=" + name + "\">";
            	output += "<span class=\"symbol\">" + name +  "</span> <span class=\"change\">" + price + "</span></a></div>";
            	stockCount++;
            }
            conn.close();  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
