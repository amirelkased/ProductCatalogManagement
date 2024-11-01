package com.fawry.productcatalogmanagement.dao;

import com.fawry.productcatalogmanagement.auth.LoginRequestDto;
import com.fawry.productcatalogmanagement.auth.RegisterRequestDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDao {
    private final DBManager dbManager = DBManager.getInstance();
    private static volatile AuthDao authDao = null;

    private AuthDao() {

    }

    public static AuthDao getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public boolean authenticate(LoginRequestDto request) {
        final String sql = "SELECT * FROM product_manager.user WHERE username=?";

        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getUsername());
            ResultSet result = preparedStatement.executeQuery();

            if (result.next() && !result.getString("password").equals(request.getPassword())) {
                return false;
            }
        } catch (Exception exception) {
            System.out.println("Authenticated process was failed!");
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean register(RegisterRequestDto request) {
        final String sql = "INSERT INTO product_manager.user(firstname, lastname, username, password) VALUES(?,?,?,?)";

        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getFirstname());
            preparedStatement.setString(2, request.getLastname());
            preparedStatement.setString(3, request.getUsername());
            preparedStatement.setString(4, request.getPassword());
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Register process was failed!");
            return false;
        }
        return true;
    }

    public int getUserByUsername(String username) {
        final String sql = "SELECT * FROM product_manager.user WHERE username=?";

        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt("id");
            }
        } catch (Exception exception) {
            System.out.println("Authenticated process was failed!");
            System.out.println(exception.getMessage());
            return -1;
        }
        return -1;
    }

    private static class SingletonHelper {
        public static final AuthDao INSTANCE = new AuthDao();
    }
}
