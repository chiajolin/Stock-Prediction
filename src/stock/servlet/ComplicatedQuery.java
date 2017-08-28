/**
 * written by: HAIYING LIU
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
@WebServlet("/ComplicatedQuery")
public class ComplicatedQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplicatedQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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

            String sql = "SELECT Close FROM Historical WHERE Stock_Id IN (" + IdSet + ") ORDER BY Close ASC LIMIT 1";
        	ResultSet result = stmt.executeQuery(sql);
        	Float fLowestPrice = 0.0f;
           
            while(result.next())
            {
            	fLowestPrice = result.getFloat("Close");
            }
            
            sql = "SELECT Stock_Id, Price, Name FROM"
            		+ "(SELECT V.Stock_Id, AVG(V.Close) AS Price, Name FROM"
            			+ "(SELECT H.Stock_Id, H.Close, S.Name FROM Historical H, Stock S WHERE S.Id = H.Stock_Id AND H.Stock_Id NOT IN (" + IdSet + ")) V"
            		+ " GROUP BY V.Stock_Id) AVGRESULT WHERE Price < " + fLowestPrice;
            ResultSet result_final = stmt.executeQuery(sql);
            boolean emptyResult = true;
            String output = "<tr><td>Stock ID</td><td>Stock Symbol</td><td>Average Price</td></tr>";
            while(result_final.next())
            {
            	Float fFinalPrice = result_final.getFloat("Price");
            	String FinalPrice = String.format("%.02f", fFinalPrice);
            	String FinalName = result_final.getString("Name");
            	String FinalId = result_final.getString("Stock_Id");
            	emptyResult = false;
            	output += "<tr>"
            			+ "<td>" + FinalId + "</td>"
            			+ "<td>" + FinalName + "</td>"
            			+ "<td>" + FinalPrice + "</td>"
            			+ "</tr>";
            }
            if (emptyResult)
            {
            	output = "<tr><td>No Result Under Lowest Price: " + fLowestPrice + "!</td></tr>";
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
