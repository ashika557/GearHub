<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.userModel" %>

<% 
	List<userModel> users = (List<userModel>)request.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/updateProfile.css" type="text/css">
    <title>Document</title>
    <style>
    </style>
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
                <li><a href="C:\Users\Admin\Documents\java\html\user_dashboard.html">Products</a></li>
                <li><a href="${pageContext.request.contextPath}/Pages/aboutUs.jsp">About Us</a></li>
                <%if (session.getAttribute("username") != null ){ %>
                <li> <a href="C:\Users\Admin\Documents\java\html\profile.html" class="dropbtn">Profile</a></li>
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
        <p class="edit">Edit profile</p>
        <form action="${pageContext.request.contextPath}/updateProfileServlet" method="post">
        <% for (userModel user : users) { %>
            <div class="group">
                <input type="text" name="name"  value="<%= user.getUserName() %>" required>
                <input type="email" name="email" value="<%= user.getEmail() %>" required>
            </div>
          
            <div class="group">
                <input type="text" name="address" value="<%= user.getAddress() %>" required>
                <input type="text" name="pass" value="<%= user.getPassword() %>" required>
            </div>
            
			<input type="hidden" name="userId" value="<%= user.getUserId() %>">
            
        <%} %>
            <button type="submit" class="save">Save changes</button>
        </form>
    </div>
</body>
</html>