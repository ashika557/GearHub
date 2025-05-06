<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductModel" %>
<%@ page import="util.stringUtils" %>

<%
    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Product</title>
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/addproduct.css">
</head>
<body>
<style>
    .button {
        padding: 15px 20px;
        width: 150px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin: 15px 60px;
    }

    .button:hover {
        background-color: #0056b3;
    }

    .error-message {
        color: red;
        margin-bottom: 15px;
    }
</style>
<div class="sidebar">
    <div class="logo">
        <h3>Hello, Admin</h3>
    </div>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/Pages/admin.jsp"><i class="fas fa-box"></i> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/Pages/addProducts.jsp" class="active"><i class="fas fa-chart-line"></i> Products</a></li>
        <li><a href="${pageContext.request.contextPath}/Pages/orderlist.jsp"><i class="fas fa-shopping-cart"></i> Orders</a></li>
        <li><a href="${pageContext.request.contextPath}/Pages/viewFeedback.jsp"><i class="fas fa-users"></i> Feedback</a></li>
    </ul>
</div>

<div class="container">
    <h2>Update Product</h2>
    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
    <div class="error-message"><%= errorMessage %></div>
    <% } %>

    <% if (products == null || products.isEmpty()) { %>
    <div class="error-message">No product found to update.</div>
    <% } else { %>
    <form action="${pageContext.request.contextPath}/EditProductServlet" method="post">
        <% for (ProductModel product : products) { %>
        <div class="group">
            <div class="form-group">
                <label for="productName">Product Name</label>
                <input type="text" id="productName" name="<%= stringUtils.PRODUCT_NAME %>" value="<%= product.getProductName() %>" required>
            </div>
            <div class="form-group">
                <label for="brand">Brand</label>
                <input type="text" id="brand" name="<%= stringUtils.BRAND %>" value="<%= product.getBrand() %>" required>
            </div>
        </div>

        <div class="group">
            <div class="form-group">
                <label for="model">Model</label>
                <input type="text" id="model" name="<%= stringUtils.MODEL %>" value="<%= product.getModel() %>" required>
            </div>
            <div class="form-group">
                <label for="screenSize">Screen size</label>
                <input type="text" id="ss" name="ss" value="<%= product.getScreen_size() %>" required>
            </div>
        </div>

        <div class="group">
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" id="price" name="<%= stringUtils.PRICE %>" min="0" step="0.01" value="<%= product.getPrice() %>" required>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" name="<%= stringUtils.QUANTITY %>" min="1" value="<%= product.getQuantity() %>" required>
            </div>
        </div>

        <!-- Hidden input field to store product ID -->
        <input type="hidden" name="productId" value="<%= product.getProductId() %>">
        <% } %>
        <button type="submit" class="button">Update Product</button>
    </form>
    <% } %>
</div>

</body>
</html>