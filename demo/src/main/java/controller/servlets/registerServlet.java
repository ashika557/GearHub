package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.PasswordEncryptionAes;
import model.userModel;
import util.stringUtils;
import controller.Database_controller;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String AES_KEY = "E2C24C4957DE9E75F0A53ECD1079A9F8"; // Change this to your secret key

    public registerServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Database_controller db = new Database_controller();

        String userName = request.getParameter("userName");
        String address = request.getParameter("address");
        String plainPassword = request.getParameter("password"); // Get plain password
        String email = request.getParameter("email");

        if (db.doesUserExist(userName)) {
            // If username exists, forward to an error page
            request.setAttribute(stringUtils.ERROR_MESSAGE, "Username already exists !");
            request.getRequestDispatcher("Pages/register.jsp").forward(request, response);
            return; // Exit the method
        }
            // Encrypt the password
        String encryptedPassword = null;
		try {
			encryptedPassword = PasswordEncryptionAes.encryptPassword(plainPassword, AES_KEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Create user model with encrypted password
        userModel user = new userModel(userName, address, encryptedPassword, email);

        // Attempt to register the user
        int result = db.registerUser(user);

        if (result == 1) {
            // Redirect to a confirmation page or login page
            response.sendRedirect("Pages/login.jsp");
        } else if (result == -2) {
            // Database error (SQLException or ClassNotFoundException)
            // Forward to an error page
            request.setAttribute(stringUtils.ERROR_MESSAGE, "Database error occurred. Please try again later.");
            request.getRequestDispatcher("Pages/register.jsp").forward(request, response);
        } else {
            // Other error occurred
            // Forward to an error page
            request.setAttribute(stringUtils.ERROR_MESSAGE, "An unexpected error occurred. Please try again later.");
            request.getRequestDispatcher("Pages/register.jsp").forward(request, response);
        }
    }
}
