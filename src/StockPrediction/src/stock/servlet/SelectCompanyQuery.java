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
 * Servlet implementation class SelectQuery
 */
@WebServlet("/SelectCompanyQuery")
public class SelectCompanyQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCompanyQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String output = "";
		try {  
			JDBCUtil connection = new JDBCUtil();
			Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();  
            
            String sql = "SELECT Id, Name FROM Stock";
            
            //if (maxStocks > 0)
            //	sql += "LIMIT " + maxStocks;
    
            //String sql = "CREATE TABLE person(uid varchar(32),name char(32))";  
  
            // 创建数据库中的表，  
            ResultSet result = stmt.executeQuery(sql);  
            int stockCount = 0;
            while(result.next())
            {
            	String id = result.getString("Id");
            	String name = result.getString("Name");
            	//String price = result.getString("Price");
            
            	output += id + "#" + name + "#";
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
