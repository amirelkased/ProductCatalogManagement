package com.fawry.productcatalogmanagement.product;

import com.fawry.productcatalogmanagement.dao.ProductDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/catalog/delete-product")
public class DeleteProductServlet extends HttpServlet {
        private ProductDao productDao;

    @Override
    public void init(ServletConfig config) {
        productDao = ProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productDao.deleteTodo(id);

        resp.sendRedirect(req.getContextPath().concat("/catalog/products"));
    }
}
