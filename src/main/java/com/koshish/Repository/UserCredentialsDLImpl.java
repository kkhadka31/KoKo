package com.koshish.Repository;

import com.koshish.Security.PasswordService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCredentialsDLImpl {
    DB db = new DB();

    public void storePasswordInDatabase(String username, String hashedPassword, byte[] salt) {

        String insertQuery = "INSERT INTO USER_CREDENTIALS (username, password, salt) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = db.connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setBytes(3, salt);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean verifyUserCredentials(String userName, String inputPassword) {
        boolean userVerification = false;
        String selectQuery = "SELECT password, salt FROM USER_CREDENTIALS WHERE username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedHashPassword = resultSet.getString("password");
                byte[] salt = resultSet.getBytes("salt");

                PasswordService passwordService = new PasswordService();
                String hashedPassword = passwordService.hashPassword(inputPassword, salt);
                if (hashedPassword.equals(storedHashPassword)) {
                    userVerification = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return userVerification;
    }

    public boolean userExists(String userName) {
        boolean userExists = false;
        String selectQuery = "SELECT userName FROM PERSON WHERE userName = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String usernameFromDB = resultSet.getString("username");
                if (userName.equals(usernameFromDB)) {
                    userExists = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userExists;
    }


    public long getUserId(String userName) {
        long id = 0;
        String selectQuery = "SELECT userId FROM USER_CREDENTIALS WHERE userName = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("userId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return id;
    }
    public void createUser(String firstName, String secondName, String fullName, String password, String email, String gender, String phoneNumber, String userName, String role) {
//        long id = getUserId(userName);
        String insertQuery = "INSERT INTO PERSON (firstName, secondName, fullName, password, email, gender, phoneNumber, userName, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = db.connection.prepareStatement(insertQuery);
//            preparedStatement.setLong(1, id);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setString(3, fullName);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, userName);
            preparedStatement.setString(9, role);
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, secondName);
//            preparedStatement.setString(3, fullName);
//            preparedStatement.setString(4, email);
//            preparedStatement.setString(5, gender);
//            preparedStatement.setString(6, phoneNumber);
//            preparedStatement.setString(7, userName);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
