/**
 * written by: CHIA-JO LIN
 */
package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Registration;

/**
 * Servlet implementation class RegServlet
 */

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public RegServlet() {

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // TODO Auto-generated method stub
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Registration reg = new Registration();
		String userName = request.getParameter("uname");
		String password = request.getParameter("pwd");
		
		String status = reg.storeUserInformation(userName, password); 				
		
		request.setAttribute("status", status);		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);		
	}
}
