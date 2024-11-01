<%--
  Created by IntelliJ IDEA.
  User: amire
  Date: 29-Aug-24
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/login.css">
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
        <h2>Sign in</h2>
        <% String errorMessage = (String) session.getAttribute("cred-error");
            if (errorMessage != null) {
        %>
        <p class="error-message" style="color: red; font-size: 14px;margin-top: 5px;"><%= errorMessage %>
        </p>
        <% session.setAttribute("notify", null);
        } %>
        <input type="text" placeholder="Username" name="username" required>
        <input type="password" placeholder="Password" name="password" required>
        <button type="submit">Login</button>
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/register.jsp"
                                     class="register-link">Register</a></p>
    </form>
</div>
</body>
</html>
