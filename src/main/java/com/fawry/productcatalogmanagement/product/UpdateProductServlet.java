package com.fawry.productcatalogmanagement.product;

import com.fawry.productcatalogmanagement.dao.ProductDao;
import com.fawry.productcatalogmanagement.dao.ProductDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/catalog/update-product")
public class UpdateProductServlet extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init(ServletConfig config) {
        productDao = ProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ProductDto productDto = productDao.getProductById(Integer.parseInt(req.getParameter("id")),
                    req.getSession().getAttribute("user").toString());

            req.setAttribute("product", productDto);
            req.getRequestDispatcher("/update-product.jsp").forward(req, resp);
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            ProductDto productDto = ProductDto.builder()
                    .id(Integer.parseInt(req.getParameter("id")))
                    .name(req.getParameter("name"))
                    .price(Double.parseDouble(req.getParameter("price")))
                    .build();

            productDao.updateProduct(productDto);
            resp.sendRedirect(req.getContextPath().concat("/catalog/products"));
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }
}