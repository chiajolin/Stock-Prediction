/**
 * written by: CHIA-JO LIN & HAIYING LIU
 */
package stock.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.JDBCUtil;

/**
 * Servlet implementation class ComplicatedQuery
 */
@WebServlet("/RealtimePriceServlet")
public class RealtimePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealtimePriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  paramString = request.getParameter("paramString").trim();
		String[] items = paramString.split("#"); 
		try {  
			JDBCUtil connection = new JDBCUtil();
			Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();  
            
            String IdSet = items[0];
            for(int i = 1; i < items.length; i++) {
            	IdSet += "," + items[i];
            }

        	String sql = "SELECT r5.Stock_Id, s.Name, r5.Price, r5.Time FROM Stock s INNER JOIN" +
                	"(SELECT r4.Price, r4.Stock_Id, r4.Time FROM Realtime r4 INNER JOIN" +
                	"(SELECT r1.Stock_Id, r1.Time, MIN(r1.Id) min_id FROM Realtime r1 INNER JOIN" +
                	"(SELECT  Stock_Id, MAX(Time) max_time FROM Realtime GROUP BY Stock_Id" +
                	") r2 ON  r1.Stock_Id = r2.Stock_Id AND r1.Time = r2.max_time GROUP BY r1.Stock_Id" +
                	") r3 ON r3.min_id = r4.Id"+
                	") r5 ON r5.Stock_Id = s.Id and S.Id IN (" + IdSet + ")"; 

            ResultSet result = stmt.executeQuery(sql);
            String output = "<tr><td>Stock ID</td><td>Stock Symbol</td><td>Highest Price</td></tr>";
            while(result.next())
            {
            	String id = result.getString("Stock_Id");
            	String name = result.getString("Name");
            	Float fprice = result.getFloat("Price");
            	String price = String.format("%.02f", fprice);

            	output += "<tr>"
            			+ "<td>" + id + "</td>"
            			+ "<td>" + name + "</td>"
            			+ "<td>" + price + "</td>"
            			+ "</tr>";
            }
            response.getWriter().append(output);
            conn.close();  
              
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
