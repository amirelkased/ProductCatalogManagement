<%--
  Created by IntelliJ IDEA.
  User: amirelkased
  Date: 15-Aug-23
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--    <link rel="stylesheet" href="styles/update-product.css">--%>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #ddd;
        }

        .btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        form {
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        textarea,
        select,
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        textarea {
            resize: vertical;
        }

        select {
            height: 38px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
    <title>Product Editor</title>
</head>
<body>
<div class="container">
    <h1 class="header">Product Editor</h1>
    <form action="${pageContext.request.contextPath}/catalog/update-product" method="post">
        <input hidden="hidden" name="id" value="${requestScope.get("product").getId()}">
        <div class="form-group">
            <label for="title">Name</label>
            <input type="text" id="title" name="name" value="${requestScope.get("product").getName()}" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" id="price" name="price" value="${requestScope.get("product").getPrice()}"
                   required>
        </div>
        <button class="btn" type="submit">Update</button>
    </form>
</div>
</body>
</html>