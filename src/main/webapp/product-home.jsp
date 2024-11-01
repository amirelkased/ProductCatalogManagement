<%--
  Created by IntelliJ IDEA.
  User: amire
  Date: 29-Aug-24
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--    <link rel="stylesheet" href="styles/product.css">--%>
    <style>
        body, h1, h2, h3, p, ul, li {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .btn {
            display: inline-block;
            padding: 10px 15px;
            font-size: 14px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .actions {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button-container {
            display: flex;
            gap: 5px;
        }

        .actions button {
            margin-right: 5px;
            background-color: transparent;
            border: 1px solid #ddd;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .edit {
            background-color: #28a745;
        }

        .edit:hover {
            background-color: #218838;
        }

        .delete {
            background-color: #dc3545;
        }

        .delete:hover {
            background-color: #c82333;
        }
    </style>
    <title>To-Do List</title>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Product List</h1>
        <a href="${pageContext.request.contextPath}/create-product.jsp">
            <button class="btn">New Product</button>
        </a>

        <c:choose>
            <c:when test="${currentView == 'myProducts'}">
                <!-- If the current view is "My Products", show the "All Products" button -->
                <a href="${pageContext.request.contextPath}/catalog/products">
                    <button class="btn">All Products</button>
                </a>
            </c:when>
            <c:otherwise>
                <!-- If the current view is anything else, show the "My Products" button -->
                <a href="${pageContext.request.contextPath}/catalog/my-products">
                    <button class="btn">My Products</button>
                </a>
            </c:otherwise>
        </c:choose>

        <a href="${pageContext.request.contextPath}/catalog/stats">
            <button class="btn">Product Statistics</button>
        </a>


        <a href="${pageContext.request.contextPath}/logout">
            <button class="btn">Log Out</button>
        </a>
    </div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty requestScope.productList}">
            <c:set var="i" value="1"/>
            <c:forEach items="${requestScope.productList}" var="product">
                <c:set var="productId" value="${product.getId()}"/>
                <c:set var="name" value="${product.getName()}"/>
                <c:set var="price" value="${product.getPrice()}"/>
                <tr>
                    <td>${i}</td>
                    <td>${name}</td>
                    <td>${price}</td>
                    <td class="actions">
                        <div class="button-container">
                            <a href="${pageContext.request.contextPath}/catalog/update-product?id=${productId}">
                                <button class="edit">Edit</button>
                            </a>
                            <a href="${pageContext.request.contextPath}/catalog/delete-product?id=${productId}">
                                <button class="delete">Delete</button>
                            </a>
                        </div>
                    </td>
                </tr>
                <c:set var="i" value="${i + 1}"/>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
