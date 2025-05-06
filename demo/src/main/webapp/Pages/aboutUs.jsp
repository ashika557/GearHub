<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/feedback.css" type="text/css">
    <a href="updateProfile.jsp//updateProfileServlet"></a>
</head>

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
                <li><a href="${pageContext.request.contextPath}/userViewServlet">Products</a></li>
                <li><a href="#">About Us</a></li>
                <%if (session.getAttribute("username") != null ){ %>
                <li> <a href="${pageContext.request.contextPath}/Pages/updateProfile.jsp" class="dropbtn">Profile</a></li>
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
    <section class="contact">
        <div class="contact-wrapper">
            <div class="contact-form">
                <div class="box">
                    <h3>Send Us Your Feedback</h3>
                    <form action="${pageContext.request.contextPath}/aboutUsServlet" method="post">
                        <div class="form-group">
                            <input class="custom-input" type="text" name="senderName" placeholder="Full Name">
                        </div>
                        <div class="form-group">
                            <input class="custom-input" type="email" name="senderEmail" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <textarea class="custom-textarea" name="feedback" placeholder="Feedback or any Queries"></textarea>
                        </div>
                        <button type="submit">Send Message</button>
                    </form>
                </div>
            </div>
            <div class="contact-info">
                <div class="box">
                    <h2>Contact Information</h2>
                    <p><i class="bi bi-telephone-fill"></i> 9804050870</p>
                    <p><i class="bi bi-envelope-fill"></i> gearhub@gmail.com</p>
                    <p><i class="bi bi-geo-alt-fill"></i> Itahari, Sunsari</p>

                    <h4>Follow Us On</h4>
                    <div class="social-icons">
                        <a href="https://www.facebook.com/"><i class="bi bi-facebook" style="margin-right: 20px;"></i></a>
                        <a href="https://www.instagram.com"><i class="bi bi-instagram" style="margin-right: 20px;"></i></a>
                        <a href="https://www.twitter.com"><i class="bi bi-twitter" style="margin-right: 20px;"></i></a>
                        <a href="https://www.whatsapp.com/"><i class="bi bi-whatsapp" style="margin-right: 20px;"></i></a>
                        <a href="https://www.pinterest.com/"><i class="bi bi-pinterest" style="margin-right: 20px;"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>

</html>
