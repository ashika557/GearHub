package controller.servlets;

import java.io.File;
import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


import model.ProductModel;
import util.stringUtils;
import controller.Database_controller;
/**
 * Servlet implementation class addProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB)
public class addProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Database_controller dbController = new Database_controller();
//
//		// Retrieve values from the form fields
//	    String productName = request.getParameter(stringUtils.PRODUCT_NAME);
//	    System.out.println(productName);
//	    String brand = request.getParameter(stringUtils.BRAND);
//	    String model = request.getParameter(stringUtils.MODEL);
//	    String screenSize = request.getParameter("ss");
//	    double price = Double.parseDouble(request.getParameter(stringUtils.PRICE));
//	    int quantity = Integer.parseInt(request.getParameter(stringUtils.QUANTITY));
//	    Part imagePart = request.getPart(stringUtils.IMAGE);
//
//	    System.out.println(screenSize);
//
//
//	    ProductModel products = new ProductModel(productName, imagePart, brand, model, screenSize, price, quantity);
//	    String savePath = stringUtils.IMAGE_DIR_PATH;
//	    String filename = products.getImageUrlFromPart();
//
//	    System.out.println(filename+"works");
//
//	    if (!filename.isEmpty() && filename != null) {
//	    	imagePart.write(savePath + filename);
//	    }
//
//	    int result = dbController.addProduct(products);
//
//	    System.out.println(result);
//
//	 	if (result == 1){
//	 	    System.out.print("Product added sucessfully");
//	 	   response.sendRedirect("Pages/admin.jsp");
//	 	}
//	 	else if (result == -2){
//	 		 System.out.print("Product addition failed");
//	 		 response.sendRedirect("Pages/addProducts.jsp");
//	    }
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database_controller dbController = new Database_controller();

		// Retrieve values from the form fields
		String productName = request.getParameter(stringUtils.PRODUCT_NAME);
		String brand = request.getParameter(stringUtils.BRAND);
		String model = request.getParameter(stringUtils.MODEL);
		String screenSize = request.getParameter("ss");
		double price = Double.parseDouble(request.getParameter(stringUtils.PRICE));
		int quantity = Integer.parseInt(request.getParameter(stringUtils.QUANTITY));
		Part imagePart = request.getPart(stringUtils.IMAGE);

		ProductModel products = new ProductModel(productName, imagePart, brand, model, screenSize, price, quantity);
		String saveDir = stringUtils.IMAGE_DIR_PATH;
		String filename = products.getImageUrlFromPart();
		System.out.println(products.getImageUrlFromPart());
		// Ensure filename is not null or empty
		if (filename != null && !filename.isEmpty()) {
			File saveDirectory = new File(saveDir);

			// Create directory if it does not exist
			if (!saveDirectory.exists()) {
				saveDirectory.mkdirs();
			}

			// Ensure correct path separator
			String fullPath = saveDir + File.separator + filename;

			// Write the image file
			imagePart.write(fullPath);
			System.out.println("Image saved at: " + fullPath);
		}

		int result = dbController.addProduct(products);
		System.out.println("Product added successfully: " + result);

		if (result == 1) {
			System.out.println("Product added successfully");
			response.sendRedirect("Pages/admin.jsp");
		} else if (result == -2) {
			System.out.println("Product addition failed");
			response.sendRedirect("Pages/addProducts.jsp");
		}
	}


}
