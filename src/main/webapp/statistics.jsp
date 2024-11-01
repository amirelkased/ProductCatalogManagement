<%--
  Created by IntelliJ IDEA.
  User: amire
  Date: 29-Aug-24
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Statistics</title>
    <%--    <link rel="stylesheet" href="styles/statistics.css">--%>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .stats-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 350px;
            text-align: center;
        }

        .stats-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
        }

        .stats-item {
            font-size: 18px;
            margin: 10px 0;
            color: #555;
        }

        .stats-value {
            font-size: 36px;
            font-weight: bold;
            color: #4CAF50;
        }

        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="stats-container">
    <div class="stats-title">Products Statistics</div>
    <div class="stats-item">Total Number of Products:</div>
    <div class="stats-value">${requestScope.get("statistics").get("totalProducts")}</div>
    <div class="stats-item">Total Price of Products:</div>
    <div class="stats-value">${requestScope.get("statistics").get("totalPrice")}</div>

    <a href="${pageContext.request.contextPath}/catalog/products" class="btn">Go to Product List Home</a>
</div>
</body>
</html>
