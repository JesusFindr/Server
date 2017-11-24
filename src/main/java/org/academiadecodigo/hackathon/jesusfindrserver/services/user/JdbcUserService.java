package org.academiadecodigo.hackathon.jesusfindrserver.services.user;

import org.academiadecodigo.hackathon.jesusfindrserver.model.User;
import org.academiadecodigo.hackathon.jesusfindrserver.persistence.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

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

        if(user.getUsername() == null){
            return false;
        }

        closeStatement();
        return user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {

        checkConnection();

        if(existingUser(user)){
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

    private boolean existingUser(User user) {
        return findByName(user.getUsername()) != null;
    }

    @Override
    public User findByName(String username) {
        return null;
    }

    @Override
    public Collection<User> getUserList() {
        return null;
    }

    //utils
    private void checkConnection(){
        if(!isConnected()){
            DatabaseConnector.getInstance().getConnection();
        }
    }

    private boolean isConnected(){

        boolean isConnected = false;
        try {
            isConnected = dbConnection != null && !dbConnection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    private void closeStatement(){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
