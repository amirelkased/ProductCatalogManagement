package com.fawry.productcatalogmanagement.auth;

import com.fawry.productcatalogmanagement.dao.AuthDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginSerlvet extends HttpServlet {
    private AuthDao authDao;

    @Override
    public void init(ServletConfig config) {
        authDao = AuthDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();

        try {
            if (authDao.authenticate(loginRequestDto)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", loginRequestDto.getUsername());
                resp.sendRedirect(req.getContextPath() + "/catalog/products");
            } else {
                req.setAttribute("cred-error", "credentails error");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
    }
}
