package org.academiadecodigo.hackathon.jesusfindrserver.services.user;

import org.academiadecodigo.hackathon.jesusfindrserver.model.User;
import org.academiadecodigo.hackathon.jesusfindrserver.persistence.DatabaseConnector;

import java.sql.Connection;
import java.util.Collection;

public class JdbcUserService implements UserService {

    private Connection dbConnection;

    public JdbcUserService() {
        dbConnection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User findByName(String username) {
        return null;
    }

    @Override
    public Collection<User> getUserList() {
        return null;
    }
}
