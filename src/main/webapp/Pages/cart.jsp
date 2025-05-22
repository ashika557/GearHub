<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.cartModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/cart.css" type="text/css">
</head>
<header class="header">
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
                <li><a href="${pageContext.request.contextPath}/userViewServlet">Products</a></li>
                <li><a href="${pageContext.request.contextPath}/Pages/aboutUs.jsp">About Us</a></li>
                <%if (session.getAttribute("username") != null ){ %>
                <li> <a href="${pageContext.request.contextPath}/updateProfileServlet" class="dropbtn">Profile</a></li>
                <li><a href="C:\Users\Admin\Documents\java\html\user_orderlist.html">Orders</a></li>
                <%} %>
            </ul>
        </div>
        <div class="navbar-right">
        <%if (session.getAttribute("username")!= null){ %>
        	<form action="${pageContext.request.contextPath}/logoutServlet" method="">
    			<button type="submit" class="login">Logout</button>
    			<input type="hidden" name="action" value="logout">
			</form>
          <%}else{%>
          <a href="${pageContext.request.contextPath}/Pages/login.jsp"><button class="login">Login</button></a>
          <%}%>
            <form action="${pageContext.request.contextPath}/checkLoginServlet" method="">
            <button type="submit" class="cart">
            	
                <i class="fas fa-shopping-cart"></i>
            </button>
        </form>
        </div>
    </nav>
</header>
<body>
    <div class="container">
        <div class="cart-header">
            <h1 class="cart-heading">Your Cart</h1>
        </div>
    
        <div class="conditional-content">
            <% List<cartModel> cartItems = (List<cartModel>) request.getAttribute("cartItems"); %>
            <table class="shadow p-3 mb-5 bg-body-tertiary rounded">
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Total Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% double totalPrice = 0; %>
                    <% for (cartModel cartItem : cartItems) { %>
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/images/<%= cartItem.getProductImage() %>" alt="<%= cartItem.getProductName() %>" width="80px" height="80px"></td>
                            <td><%= cartItem.getProductName() %></td>
                            <td>$<%= cartItem.getTotalPrice() %></td>
                            <td><%= cartItem.getQuantity() %></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/removeCartItemServlet" method="post">
                                    <input type="hidden" name="cartId" value="<%= cartItem.getCartId() %>">
                                    <button type="submit" class="remove">Remove</button>
                                </form>
                            </td>
                        </tr>
                        <% totalPrice += cartItem.getTotalPrice(); %>
                    <% } %>
                </tbody>
            </table>
            <p class="total-price">Total Price: $<%= totalPrice %></p>
        </div>    
    </div>
    <script>
        setTimeout(function(){
            const messages = document.querySelectorAll(".message");
            messages.forEach(function(message) {
                message.style.display = "none";
            });
        }, 20000);
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
