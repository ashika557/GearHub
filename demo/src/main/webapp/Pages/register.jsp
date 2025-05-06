<%@page import="util.stringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/register.css">
    <title>Register</title>
</head>
<body>
    <div class="container">
        <div class="forms-container">
            <div class="signin-signup">
                <form action="${pageContext.request.contextPath}/registerServlet" method="post" class="sign-up-form">
                    <h2 class="title">Create Accout</h2>
                    <div class="input-field">
                        <input type="text" class="form-control" id="userName" name="userName" required placeholder="Username">
                    </div>
                    <div class="input-field">
                        <input type="email" class="form-control" id="email" name="email" required placeholder="Email">
                    </div>
                    <div class="input-field">
                        <input type="text" class="form-control" id="address" name="address" required placeholder="Address">
                    </div>
                    <div class="input-field">
                        <input type="password" class="form-control" id="password" name="password" required placeholder="Password">
                    </div>
                    <div class="input-field">
                        <input type="password" class="form-control" id="retypePassword" name="retypePassword" required placeholder="Retype Password">
                    </div>
                    <button type="submit" class="btn">Sign up</button>
                    <p class="social-text">Or follow us on social platforms</p>
                    <div class="social-media">
                        <a href="#" class="social-icon">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-google"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                    </div>
                </form>
                <div class="login-link">
                    <p>Already have an account? <a href="${pageContext.request.contextPath}/Pages/login.jsp">Login</a></p>
                </div>
                  <% String errorMessage = (String) request.getAttribute(stringUtils.ERROR_MESSAGE); %>
                <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                <div class="alert alert-danger mt-2" role="alert"><%= errorMessage %></div>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
