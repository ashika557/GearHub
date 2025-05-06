<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.feedbackModel" %>

<% List<feedbackModel> feeds = (List<feedbackModel>)request.getAttribute("feedbacks"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/viewfeedback.css" type="text/css">
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <div class="logo">
        <h3>Hello, Admin</h3>
    </div>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/Pages/admin.jsp""><i class="fas fa-shopping-cart"></i> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/Pages/addProducts.jsp""><i class="fas fa-box"></i> Products</a></li>
        <li><a href="#"><i class="fas fa-users"></i> Orders</a></li>
        <li><a href="#" class="active"><i class="fas fa-chart-line"></i> Feedback</a></li>
    </ul>
    <form action="${pageContext.request.contextPath}/logoutServlet" method="">
    	<button type="submit" class="logout">Logout</button>
	</form>
</div>
<div class="content">
    <div class="container">
        <div class="cart-header">
            <h1 class="cart-heading">User Feedback's</h1>
           
        </div>
    
        <div class="conditional-content">
            <table class="shadow p-3 mb-5 bg-body-tertiary rounded">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Feedback</th>
                    </tr>
                </thead>
                <% if (feeds != null && !feeds.isEmpty()) { %>
    				<tbody>
        			<% for (feedbackModel data : feeds) { %>
            			<tr>
                			<td><%= data.getUserName()%></td>
                			<td><%= data.getEmail() %></td>
                			<td><%= data.getFeedback() %></td>
            			</tr>
        			<% } %>
    			</tbody>
				<% } else { %>
    			<tbody>
        			<tr>
            			<td colspan="3">No feedback available.</td>
        			</tr>
    			</tbody>
				<% } %>            
			</table>
        </div>    
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
