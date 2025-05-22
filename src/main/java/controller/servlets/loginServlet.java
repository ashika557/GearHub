package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;
import util.stringUtils;

/**
 * Servlet implementation class loginAndRegisterServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello");
		Database_controller db = new Database_controller();
		
		String username = request.getParameter("userName");
        String password = request.getParameter("password");
        
        
        int login_result = db.getUserLogin(username, password);
        if (login_result == 1) {
            request.getSession().setAttribute("username", username);
            request.getSession().setMaxInactiveInterval(30 * 60); //setting the maximum session timeout to 30 minutes
            response.sendRedirect("Pages/admin.jsp");
//            response.sendRedirect(request.getContextPath() + StringUtils.HOME_PAGE);
        } 
        else if (login_result == 2) {
        	request.getSession().setAttribute("username", username);
            request.getSession().setMaxInactiveInterval(30 * 60);
            response.sendRedirect("userViewServlet");
            
        } 
        else if (login_result == 0) {
            request.setAttribute(stringUtils.ERROR_MESSAGE, stringUtils.LOGIN_DETEAIL_ERROR);
            System.out.println("Username or Password Incorrect");
	        request.getRequestDispatcher("Pages/login.jsp").forward(request, response);
//            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
	        
        } 
        else {
            request.setAttribute(stringUtils.ERROR_MESSAGE, stringUtils.SERVER_ERROR_MESSAGE);
	        request.getRequestDispatcher("Pages/login.jsp").forward(request, response);
//           request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
        }
        
        
	}

}

