package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;

import controller.Database_controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteProductServlet
 */
@WebServlet("/deleteProductServlet")
public class deleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database_controller db = new Database_controller();
		
		int id = Integer.parseInt(request.getParameter("productId"));
		
		System.out.print(id);
		int result = 0;
		
		try {
			result = db.deleteProduct(id);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		if(result == 1) {
			System.out.print("delete sucess");
			response.sendRedirect("Pages/admin.jsp");
		}
		else {
			System.out.print("delete error");
			response.sendRedirect("Pages/admin.jsp");
			
		}
	}

}
