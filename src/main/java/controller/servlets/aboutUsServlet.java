package controller.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;
import model.feedbackModel;
import util.stringUtils;

/**
 * Servlet implementation class aboutUsServlet
 */
@WebServlet("/aboutUsServlet")
public class aboutUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aboutUsServlet() {
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
		// TODO Auto-generated method stub
//		doGet(request, response);
		Database_controller db = new Database_controller();
		
	    String name = request.getParameter(stringUtils.USERNAME);
        String email = request.getParameter(stringUtils.USER_EMAIL);
        String feedback = request.getParameter(stringUtils.FEEDBACK);
      
        System.out.println(name);
        System.out.println(email);
        System.out.println(feedback);
        
        feedbackModel feed = new feedbackModel(name, email, feedback);
        
        int result = db.recordFeedback(feed);
        
        if (result == 1) {
        	System.out.println(result);
        	System.out.println("Feedback recorded suessfully");
        }
        else {
        	System.out.println(result);
        }
        
      
	      
	      
	      response.sendRedirect("Pages/aboutUs.jsp");
	}

}
