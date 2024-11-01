package com.fawry.productcatalogmanagement.product;

import com.fawry.productcatalogmanagement.dao.ProductDao;
import com.fawry.productcatalogmanagement.dao.ProductDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/catalog/products")
public class AddProductServlet extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init(ServletConfig config) {
        productDao = ProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("currentView", "allProducts");
            req.setAttribute("productList",
                    productDao.getAllProducts());
            req.getRequestDispatcher("/product-home.jsp").forward(req, resp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ProductDto productDto = ProductDto.builder()
                    .name(req.getParameter("name"))
                    .price(Double.parseDouble(req.getParameter("price")))
                    .build();
            productDao.saveProduct(productDto, req.getSession().getAttribute("user").toString());

            resp.sendRedirect(req.getContextPath().concat("/catalog/products"));
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + "=>" + e.getMessage());
        }
    }
}