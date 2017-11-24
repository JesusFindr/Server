package org.academiadecodigo.hackathon.jesusfindrserver.services.user;

import org.academiadecodigo.hackathon.jesusfindrserver.model.User;
import org.academiadecodigo.hackathon.jesusfindrserver.persistence.DatabaseConnector;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class JdbcUserService implements UserService {

    private Connection dbConnection;
    private Statement statement = null;

    public JdbcUserService() {
        dbConnection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public boolean authenticate(String username, String password) {

        User user = findByName(username);
        checkConnection();

        if (user == null) {
            return false;
        }

        closeStatement();

        return user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {

        checkConnection();

        if (existingUser(user)) {
            return;
        }

        String query = "INSERT INTO users(username, password)VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            int affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        closeStatement();
    }

    @Override
    public User findByName(String username) {

        checkConnection();

        User user = null;

        String query = "SELECT username, password FROM users WHERE username = ?";

        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(name, password);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        closeStatement();
        return user;
    }

    @Override
    public Collection<User> getUserList() {

        checkConnection();

        Collection<User> collection = new LinkedList<>();

        String query = "SELECT * FROM users";

        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(name, password);
                collection.add(user);

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return collection;
    }

    //utils
    private void checkConnection() {
        if (!isConnected()) {
            DatabaseConnector.getInstance().getConnection();
        }
    }

    private boolean isConnected() {

        boolean isConnected = false;
        try {
            isConnected = dbConnection != null && !dbConnection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    private boolean existingUser(User user) {
        return findByName(user.getUsername()) != null;
    }

    private void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
