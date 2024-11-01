package com.fawry.productcatalogmanagement.product;

import com.fawry.productcatalogmanagement.dao.ProductDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/catalog/my-products")
public class OwnedProductServlet extends HttpServlet {
        private ProductDao productDao;

    @Override
    public void init(ServletConfig config) {
        productDao = ProductDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("currentView", "myProducts");
            req.setAttribute("productList",
                    productDao.getAllProducts(req.getSession().getAttribute("user").toString()));
            req.getRequestDispatcher("/product-home.jsp").forward(req, resp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
