<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductModel" %>

<%
    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
    String cartMessage = (String) session.getAttribute("cartMessage");
    session.removeAttribute("cartMessage"); // Clear the message after getting it
%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/user_dashboard.css" type="text/css">

</head>

<body>
    <!-- Notification Popup -->


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
                    <li><a href="#">Products</a></li>
                    <li><a href="${pageContext.request.contextPath}/Pages/aboutUs.jsp">About Us</a></li>
                    <% if (session.getAttribute("username") != null) { %>
                    <li><a href="${pageContext.request.contextPath}/updateProfileServlet" class="dropbtn">Profile</a></li>
                    <% } %>
                </ul>
            </div>
            <div class="navbar-right">
                <% if (session.getAttribute("username") != null) { %>
                <form action="${pageContext.request.contextPath}/logoutServlet" method="post">
                    <button type="submit" class="login">Logout</button>
                    <input type="hidden" name="action" value="logout">
                </form>
                <% } else { %>
                <a href="${pageContext.request.contextPath}/Pages/login.jsp"><button class="login">Login</button></a>
                <% } %>

                <form action="${pageContext.request.contextPath}/checkLoginServlet" method="post">
                    <button type="submit" class="cart">
                        <i class="fas fa-shopping-cart"></i>
                    </button>
                </form>
            </div>
        </nav>
    </header>

    <main>
        <div class="hero">
            <div class="hero-text">
                <h1>NEW ARRIVAL</h1>
                <h2>SMART WATCH</h2>
                <p>GEAR UP YOUR STYLE: TIMEPIECES UNLEASHED AT GEARHUB!</p>
                <p>THE WATCH YOU WEAR EVERY DAY JUST GOT SMARTER</p>
                <button class="cta">SHOP NOW</button>
            </div>
            <div class="hero-image">
                <img src="${pageContext.request.contextPath}/images/3810675.jpg" alt="Smart Watch">
            </div>
        </div>

        <h6>NEW PRODUCTS</h6>
        <section class="menu-containers" style="display:flex"; >
            <% if (products != null && !products.isEmpty()) {
                for (ProductModel product : products) { %>
            <div class="menu-items">
                <div class="menu-item">
                    <div class="image-container">
                        <a href="${pageContext.request.contextPath}/productDetailsServlet?id=<%= product.getProductId() %>">
                            <img src="${pageContext.request.contextPath}/images/<%= product.getImageUrlFromPart() %>" alt="<%= product.getProductName() %>">
                        </a>
                    </div>
                    <hr>
                    <h5>Name: <%= product.getProductName() %></h5>
                    <p>Price: <%= product.getPrice() %> NPR</p>

                    <form action="${pageContext.request.contextPath}/addCartServlet" method="post">
                        <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                        <input type="hidden" name="quantity" value="1"> <!-- default quantity -->
                        <input type="hidden" name="price" value="<%= product.getPrice() %>">
                        <button type="submit" style="background-color: black; color: white; padding: 10px 20px; border: none; cursor: pointer;">
                            Add to Cart
                        </button>
                    </form>
                </div>
            </div>
            <% }
            } else { %>
            <p style="text-align: center; font-weight: bold;">No products available.</p>
            <% } %>
        </section>
    </main>

    <footer class="footer" style="background-color: black; color: white; display: flex;">
        <div class="footer-container" style="color: white;">
            <div class="footer-logo">

                <p style="color: white;">GearHub - Your one-stop shop for smart watches and accessories.</p>
            </div>
            <div class="footer-links">
                <h4 style="color: white;">Quick Links</h4>
                <ul style="color: white;">
                    <li><a href="${pageContext.request.contextPath}/Pages/aboutUs.jsp" style="color: white;">About Us</a></li>
                    <li><a href="" style="color: white;">Products</a></li>
                    <li><a href="aboutUs.jsp" style="color: white;">About Us</a></li>
                    <li><a href="#" style="color: white;">FAQs</a></li>
                </ul>
            </div>
            <div class="footer-contact">
                <h4 style="color: white;">Contact Us</h4>
                <p style="color: white;">Email: support@gearhub.com</p>
                <p style="color: white;">Phone: +977-123-456-7890</p>
                <p style="color: white;">Address: Kathmandu, Nepal</p>
            </div>
            <div class="footer-social">
                <h4 style="color: white;">Follow Us</h4>
                <div class="social-icons">
                    <a href="#" style="color: white;"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" style="color: white;"><i class="fab fa-twitter"></i></a>
                    <a href="#" style="color: white;"><i class="fab fa-instagram"></i></a>
                    <a href="#" style="color: white;"><i class="fab fa-linkedin-in"></i></a>
                </div>
            </div>
        </div>
        <div class="footer-bottom" style="color: white;">
            <p style="color: white;">© 2025 GearHub. All Rights Reserved.</p>
        </div>
    </footer>


    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
</body>
</html>