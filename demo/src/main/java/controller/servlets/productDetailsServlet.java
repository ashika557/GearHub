package controller.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;
import model.ProductModel;

/**
 * Servlet implementation class productDetailsServlet
 */
@WebServlet("/productDetailsServlet")
public class productDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database_controller db = new Database_controller();
		
		int editProductId = Integer.parseInt(request.getParameter("id"));
		
		List<ProductModel> products = null;
		
		try {
			products = db.getProductByID(editProductId);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	     request.setAttribute("products", products);
	     request.getRequestDispatcher("Pages/productDetials.jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
