package com.fawry.productcatalogmanagement.auth;

import com.fawry.productcatalogmanagement.dao.AuthDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AuthDao authDao;

    @Override
    public void init(ServletConfig config) {
        authDao = AuthDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("firstname");
        String lname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        RegisterRequestDto request = RegisterRequestDto.builder()
                .firstname(fname)
                .lastname(lname)
                .username(username)
                .password(password)
                .build();

        if (authDao.register(request)) {
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("notify", "Username is already exists!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}