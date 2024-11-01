package com.fawry.productcatalogmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDao {
    private static volatile ProductDao productDao = null;
    private final DBManager dbManager = DBManager.getInstance();
    private final AuthDao authDao = AuthDao.getInstance();

    private ProductDao() {

    }

    public static ProductDao getInstance() {
        if (productDao == null) {
            synchronized (ProductDao.class) {
                if (productDao == null) {
                    productDao = new ProductDao();
                }
            }
        }
        return productDao;
    }

    public List<ProductDto> getAllProducts(String username) throws SQLException {
        final String sql = "SELECT * FROM product_manager.product WHERE user_id = ?";
        List<ProductDto> result;

        int id = authDao.getUserByUsername(username);

        try (Connection connection = dbManager.getConnection()) {
            if (id == -1) {
                throw new Exception("Username not found or null");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            result = toProduct(resultSet);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return result;
    }

    public List<ProductDto> getAllProducts() throws SQLException {
        final String sql = "SELECT * FROM product_manager.product";
        List<ProductDto> result;

        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            result = toProduct(resultSet);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return result;
    }

    private List<ProductDto> toProduct(ResultSet resultSet) throws SQLException {
        List<ProductDto> productDtoList = new ArrayList<>();
        while (resultSet.next()) {
            productDtoList.add(
                    ProductDto.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getDouble("price"))
                            .build()
            );
        }
        return productDtoList;
    }

    public ProductDto getProductById(int id, String username) {
        final String sql = "SELECT * FROM product_manager.product WHERE user_id = ? && id=?";
        ProductDto result = new ProductDto();

        int userId = authDao.getUserByUsername(username);

        try (Connection connection = dbManager.getConnection()) {
            if (userId == -1) {
                throw new Exception("Username not found or null");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = ProductDto.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getDouble("price"))
                        .build();

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return result;
    }

    public void saveProduct(ProductDto productDto, String username) {
        try (Connection connection = dbManager.getConnection()) {
            String sql = "INSERT INTO product_manager.product VALUES (id, ?,?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productDto.getName());
            preparedStatement.setDouble(2, productDto.getPrice());
            preparedStatement.setInt(3, authDao.getUserByUsername(username));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateProduct(ProductDto productDto) {
        try (Connection connection = dbManager.getConnection()) {

            String sql = "UPDATE product_manager.product SET name=?, price=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productDto.getName());
            preparedStatement.setDouble(2, productDto.getPrice());
            preparedStatement.setDouble(3, productDto.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteTodo(int productId) {
        try (Connection connection = dbManager.getConnection()) {
            String sql = "DELETE FROM product WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Map<String, Integer> getProductsStatistics() throws SQLException {
        final String sql = "SELECT count(*) as cnt, SUM(price) as summation FROM product";

        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int totalProducts = resultSet.getInt("cnt");
                int totalPrice = resultSet.getInt("summation");
                return Map.of("totalProducts", totalProducts, "totalPrice", totalPrice);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return Map.of();
        }
        return Map.of();
    }
}
