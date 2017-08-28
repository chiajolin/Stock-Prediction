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
@WebServlet("/AveragePriceServlet")
public class AveragePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AveragePriceServlet() {
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

        	String sql = "SELECT D.Stock_Id, D.Name, AVG(D.Close) AS Price "+
        				 "FROM ( "+
        				 "SELECT H.Stock_Id, H.Close, H.Time, S.Name "+
        				"FROM Historical H, Stock S WHERE S.Id = H.Stock_Id and S.Id IN (" + IdSet + ")"+
        				 ") D GROUP BY D.Stock_Id";

            ResultSet result = stmt.executeQuery(sql);
            String output = "<tr><td>Stock ID</td><td>Stock Symbol</td><td>Average Price</td></tr>";
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
