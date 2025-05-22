<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/addproduct.css">
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <div class="logo">
        <h3>Hello, Admin</h3>
    </div>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/Pages/admin.jsp"><i class="fas fa-box"></i> Dashboard</a></li>
        <li><a href="#" class="active"><i class="fas fa-chart-line"></i> Products</a></li>
        <li><a href="C:\Users\Admin\Documents\java\html\orderlist.jsp"><i class="fas fa-shopping-cart"></i> Orders</a></li>
        <li><a href="${pageContext.request.contextPath}/Pages/viewFeedback.jsp"><i class="fas fa-users"></i> Feedback</a></li>
    </ul>
 
</div>
<div class="container">
    <h2>Add Product</h2>
    <form action="${pageContext.request.contextPath}/addProductServlet" method="post" enctype="multipart/form-data">

        <div class="group">
            <div class="form-group">
                <label for="productName">Product Name</label>
                <input type="text" id="productName" name="productName" required>
            </div>
            <div class="form-group">
                <label for="brand">Brand</label>
                <input type="text" id="brand" name="brand" required>
            </div>
        </div>

        <div class="group">
            <div class="form-group">
                <label for="model">Model</label>
                <input type="text" id="model" name="model" required>
            </div>
            <div class="form-group">
                <label for="screenSize">Screen size</label>
                <input type="text" id="ss" name="ss" required>
            </div>
        </div>

        <div class="group">
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" id="price" name="price" min="0" required>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" name="quantity" min="1" required>
            </div>
        </div>
        <div class="group">
            <div class="form-group">
                <label for="image">Image</label>
                <input type="file" id="image" name="image" accept="image/*" required>
            </div>
            <div class="form-group">
                <button type="submit">Add Product</button>
            </div>
        </div>
    </form>
</div>

<script>
    document.getElementById('image').addEventListener('change', function() {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview').src = e.target.result;
            document.getElementById('preview').style.display = 'block';
        }
        reader.readAsDataURL(this.files[0]);
    });
</script>
</body>
</html>
