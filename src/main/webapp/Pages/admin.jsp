<%@page import="util.stringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductModel" %>
<%@ page import="controller.Database_controller" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<%
    Database_controller dbController = new Database_controller();
    List<ProductModel> products = null;

    try {
        products = dbController.getAllProducts();
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/admin.css" type="text/css">
</head>
<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <div class="logo">
            <h3>Hello, Admin</h3>
        </div>
        <ul class="nav-links">
            <li><a href="#" class="active"><i class="fas fa-chart-line"></i> Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/Pages/addProducts.jsp"><i class="fas fa-box"></i> Products</a></li>
            <li><a href="${pageContext.request.contextPath}/orderListServlet"><i class="fas fa-shopping-cart"></i> Orders</a></li>
            <li><a href="${pageContext.request.contextPath}/viewFeedbackServlet"><i class="fas fa-users"></i> Feedback</a></li>
        </ul>
        <form action="${pageContext.request.contextPath}/logoutServlet" method="">
    			<button type="submit" class="logout">Logout</button>
		</form>
    </div>

    <!-- Main Content Area -->
    <div class="content">
        <h1>Admin Dashboard</h1>
        <div class="container">
            <div class="cart-header">
                <h1 class="cart-heading">Added Items</h1>
            </div>
            <div class="conditional-content">
                <table class="shadow p-3 mb-5 bg-body-tertiary rounded">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Screen Size</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Iterate over the list of products -->
                        <% for (ProductModel product : products) { %>
                            <tr>
                                <td><img src="${pageContext.request.contextPath}/images/<%=product.getImageUrlFromPart() %>" width="80px" height="80px"></td>
                                <td><%= product.getProductName() %></td>
                                <td>$<%= product.getPrice() %></td>
                                <td><%= product.getQuantity() %></td>
                                <td><%= product.getBrand() %></td>
                                <td><%= product.getModel() %></td>
                                <td><%= product.getScreen_size() %></td>
                                <td id= "buttons">
                                	 <form action="${pageContext.request.contextPath}/EditProductServlet" method="get">
       									<input type="hidden" name="productId" value="<%= product.getProductId() %>">
       									<button type="submit" class="buy">Edit</button>
  									</form>
                                	
   								    <form action="${pageContext.request.contextPath}/deleteProductServlet" method="post">
       									<input type="hidden" name="productId" value="<%= product.getProductId() %>">
       									<button type="submit" class="remove">Delete</button>
  									</form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>    
        </div>  
    </div>

    <!-- JavaScript to hide messages after 20 seconds -->
    <script>
        setTimeout(function(){
            const messages = document.querySelectorAll(".message");
            messages.forEach(function(message) {
                message.style.display = "none";
            });
        }, 20000);
    </script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
