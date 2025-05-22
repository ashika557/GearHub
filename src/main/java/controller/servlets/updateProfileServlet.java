package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import controller.Database_controller;
import model.PasswordEncryptionAes;
import model.ProductModel;
import model.userModel;
import util.stringUtils;


/**
 * Servlet implementation class updateProfileServlet
 */
@WebServlet("/updateProfileServlet")
public class updateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String AES_KEY = "E2C24C4957DE9E75F0A53ECD1079A9F8"; // Change this to your secret key


    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");
		String username = (String) request.getSession().getAttribute("username");

		Database_controller db = new Database_controller();

		List<userModel> users = null;

		try {
			users = db.getUserDetialsByName(username);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(users);
	     request.setAttribute("user", users);
	     request.getRequestDispatcher("Pages/updateProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("2");
		Database_controller db = new Database_controller();

		int user_id =  Integer.parseInt(request.getParameter("userId"));

	 	String userName = request.getParameter("name");
	    String email = request.getParameter("email");
	    String address = request.getParameter("address");
	    String password = request.getParameter("pass");

	    String encryptedPassword = null;
		try {
			encryptedPassword = PasswordEncryptionAes.encryptPassword(password, AES_KEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    userModel users  = new userModel(userName, address, encryptedPassword, email);
	    int result = db.updateProfile(users, user_id);
	    System.out.println(result);

	   if (result == 1) {
		   System.out.println("user Detail updated  sucessfully");
           response.sendRedirect("userViewServlet");


	   }else if(result == -1) {
		   System.out.println("error Occured");
           response.sendRedirect("updateProfileServlet");

	   }

	   else if (result == -2) {
		   System.out.println("Error occured ");
	   }

	}

}
