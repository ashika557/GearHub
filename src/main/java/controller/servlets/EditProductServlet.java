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
import util.stringUtils;
//creating class//
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Validate productId parameter
		String productIdStr = request.getParameter("productId");
		if (productIdStr == null || productIdStr.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
			return;
		}

		int editProductId;
		try {
			editProductId = Integer.parseInt(productIdStr);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID format");
			return;
		}

		Database_controller db = new Database_controller();
		List<ProductModel> products = null;

		try {
			products = db.getProductByID(editProductId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
			return;
		}

		if (products == null || products.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
			return;
		}

		request.setAttribute("products", products);
		request.getRequestDispatcher("Pages/updateProduct.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Validate productId parameter
		String productIdStr = request.getParameter("productId");
		if (productIdStr == null || productIdStr.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
			return;
		}

		int edit_id;
		try {
			edit_id = Integer.parseInt(productIdStr);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID format");
			return;
		}

		// Retrieve and validate form parameters
		String productName = request.getParameter(stringUtils.PRODUCT_NAME);
		String brand = request.getParameter(stringUtils.BRAND);
		String model = request.getParameter(stringUtils.MODEL);
		String screenSize = request.getParameter("ss");
		String priceStr = request.getParameter(stringUtils.PRICE);
		String quantityStr = request.getParameter(stringUtils.QUANTITY);

		// Check for null or empty parameters
		if (productName == null || brand == null || model == null || screenSize == null ||
				priceStr == null || quantityStr == null ||
				productName.trim().isEmpty() || brand.trim().isEmpty() || model.trim().isEmpty() ||
				screenSize.trim().isEmpty() || priceStr.trim().isEmpty() || quantityStr.trim().isEmpty()) {
			request.setAttribute("errorMessage", "All fields are required");
			request.getRequestDispatcher("Pages/updateProduct.jsp").forward(request, response);
			return;
		}

		// Parse price and quantity
		double price;
		int quantity;
		try {
			price = Double.parseDouble(priceStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid price or quantity format");
			request.getRequestDispatcher("Pages/updateProduct.jsp").forward(request, response);
			return;
		}

		// Create ProductModel instance (fixed constructor call)
		ProductModel prod = new ProductModel(edit_id, productName, model, brand, screenSize, price, quantity);

		// Update product in database
		Database_controller db = new Database_controller();
		int result = db.updateProduct(prod, edit_id);

		if (result == 1) {
			System.out.println("Product updated successfully");
			response.sendRedirect("Pages/admin.jsp");
		} else {
			System.out.println("Error occurred during product update");
			request.setAttribute("errorMessage", "Failed to update product");
			request.getRequestDispatcher("Pages/updateProduct.jsp").forward(request, response);
		}
	}
}
