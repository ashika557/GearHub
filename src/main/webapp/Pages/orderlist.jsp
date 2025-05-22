<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.orderModel" %>

<%
    List<orderModel> orders = (List<orderModel>) request.getAttribute("orders");
    String errorMessage = (String) request.getAttribute("errorMessage");
    String message = (String) session.getAttribute("message");
    session.removeAttribute("message"); // Clear the message after displaying
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order List - Admin Dashboard</title>
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/admin.css">
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <div class="logo">
            <h3>Hello, Admin</h3>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/Pages/admin.jsp"><i class="fas fa-box"></i> Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/Pages/addProducts.jsp"><i class="fas fa-chart-line"></i> Products</a></li>
            <li><a href="#" class="active"><i class="fas fa-shopping-cart"></i> Orders</a></li>
            <li><a href="${pageContext.request.contextPath}/Pages/viewFeedback.jsp"><i class="fas fa-users"></i> Feedback</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="container">
        <h2>Order List</h2>
        
        <% if (message != null && !message.isEmpty()) { %>
            <div class="success-message"><%= message %></div>
        <% } %>
        
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <% if (orders == null || orders.isEmpty()) { %>
            <div class="no-orders">No orders found.</div>
        <% } else { %>
            <table class="order-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Product</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (orderModel order : orders) { %>
                        <tr>
                            <td><%= order.getId() %></td>
                            <td><%= order.getUserName() %></td>
                            <td><%= order.getProductName() %></td>
                            <td>
                                <img src="${pageContext.request.contextPath}/images/<%= order.getProductImage() %>" 
                                     alt="<%= order.getProductName() %>" 
                                     width="50" height="50">
                            </td>
                            <td><%= order.getQuantity() %></td>
                            <td>$<%= order.getTotalPrice() %></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/deleteOrderServlet" method="post" 
                                      onsubmit="return confirm('Are you sure you want to delete this order?');">
                                    <input type="hidden" name="orderId" value="<%= order.getId() %>">
                                    <button type="submit" class="delete-btn">
                                        <i class="fas fa-trash"></i> Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    </div>

    <style>
        .container {
            margin-left: 250px;
            padding: 20px;
        }
        
        .order-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            box-shadow: 0 1px 3px rgba(0,0,0,0.2);
        }
        
        .order-table th, .order-table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        .order-table th {
            background-color: #f8f9fa;
            font-weight: bold;
        }
        
        .order-table tr:hover {
            background-color: #f5f5f5;
        }
        
        .error-message {
            color: #dc3545;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #dc3545;
            border-radius: 4px;
            background-color: #f8d7da;
        }
        
        .success-message {
            color: #28a745;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #28a745;
            border-radius: 4px;
            background-color: #d4edda;
        }
        
        .no-orders {
            text-align: center;
            padding: 20px;
            font-size: 1.2em;
            color: #6c757d;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .delete-btn:hover {
            background-color: #c82333;
        }

        .delete-btn i {
            font-size: 14px;
        }
    </style>
</body>
</html> 