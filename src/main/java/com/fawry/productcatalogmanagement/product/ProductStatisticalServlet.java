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
import java.util.Map;

@WebServlet("/catalog/stats")
public class ProductStatisticalServlet extends HttpServlet {
        private ProductDao productDao;

    @Override
    public void init(ServletConfig config) {
        productDao = ProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Map<String, Integer> statistics = productDao.getProductsStatistics();
            req.setAttribute("statistics", statistics);
            req.getRequestDispatcher("/statistics.jsp").forward(req, resp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
