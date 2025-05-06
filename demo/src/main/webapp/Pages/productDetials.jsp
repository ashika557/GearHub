<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductModel" %>

<% List<ProductModel> products = (List<ProductModel>)request.getAttribute("products"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/productDetails.css" type="text/css">
</head>

<body>
<header>
     <nav class="navbar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/aplicationImages/gearhub-high-resolution-logo (1).png" alt="Logo">
        </div>
        <div class="navbar-middle">
            <div class="search-bar">
                <input type="text" placeholder="Search...">
                <button class="search-icon"><i class="fas fa-search"></i></button>
            </div>
            
            <ul class="navbar-links">
                <li><a href="C:${pageContext.request.contextPath}/userViewServlet">Products</a></li>
                <li><a href="C:\Users\Admin\Documents\java\html\feedback.html">About Us</a></li>
                <% if (session.getAttribute("username") != null) { %>
                <li><a href="C:\Users\Admin\Documents\java\html\profile.html" class="dropbtn">Profile</a></li>
                <li><a href="C:\Users\Admin\Documents\java\html\user_orderlist.html">Orders</a></li>
                <% } %>
            </ul>
        </div>
        <div class="navbar-right">
        <% if (session.getAttribute("username") != null) { %>
          <a href="C:\Users\Admin\Documents\java\html\login.html"><button class="login">Logout</button></a>
          <% } else { %>
          <a href="C:\Users\Admin\Documents\java\html\login.html"><button class="login">Login</button></a>
          <% } %>
            <div class="cart-icon">
                <i class="fas fa-shopping-cart"></i>
            </div>
        </div>
    </nav>
</header>

<div class="product-list">
    <% for (ProductModel product : products) { %>
    <div class="product-container">
        <div class="product-image">
            <img src="${pageContext.request.contextPath}/images/<%= product.getImageUrlFromPart() %>" alt="Product Image">
        </div>
        <div class="product-details">
            <h1>Name: <%= product.getProductName() %></h1>
            <p>Brand: <%= product.getBrand() %></p>
            <p>Model: <%= product.getModel() %></p>
            <p>Screensize: <%= product.getScreen_size() %> inch</p>
            <div class="price-container">
                <span class="discounted-price">Price: <%= product.getPrice() %></span>
                <span class="original-price">M.R.P: $59</span>
                <span class="discount">-58%</span>
            </div>
            <form action="${pageContext.request.contextPath}/addCartServlet" method="post">
                <input type="hidden" name="productId" value="<%= product.getProductId()%>">
                <input type="hidden" name="price" value="<%= product.getPrice() %>">
                <div class="quantity-selector">
                    <p>Quantity: </p>
                    <button type="button" class="quantity-btn" id="decrease">-</button>
                    <input type="number" class="quantity-input" name="quantity" value="1" min="1">
                    <button type="button" class="quantity-btn" id="increase">+</button>
                </div>
                <div class="buy-options">
                    <button type="submit" class="add-to-cart">Add to Cart</button>
                </div>
            </form>
            <div class="product-info">
                <div class="info-item">
                    <img src="${pageContext.request.contextPath}/aplicationImages/download (1).png" alt="Returnable Icon">
                    <p>10 days Returnable</p>
                </div>
                <div class="info-item">
                    <img src="${pageContext.request.contextPath}/aplicationImages/delevery.jpg" alt="Delivery Icon">
                    <p>Free Delivery</p>
                </div>
                <div class="info-item">
                    <img src="${pageContext.request.contextPath}/aplicationImages/warrenty.png" alt="Warranty Icon">
                    <p>Warranty Policy</p>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>

<script>
    document.getElementById("increase").addEventListener("click", function() {
        var input = document.querySelector(".quantity-input");
        var value = parseInt(input.value);
        input.value = value + 1;
    });

    document.getElementById("decrease").addEventListener("click", function() {
        var input = document.querySelector(".quantity-input");
        var value = parseInt(input.value);
        if (value > 1) {
            input.value = value - 1;
        }
    });
</script>
</body>
</html>
