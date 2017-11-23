package org.academiadecodigo.hackathon.jesusfindrserver.services.user;

import org.academiadecodigo.hackathon.jesusfindrserver.model.User;

import java.util.*;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class MockUserService implements UserService {

    Map<String, User> userList;

    public MockUserService() {
        this.userList = new HashMap<>();

        fillUserList();
    }

    private void fillUserList() {

        String[] mockUsers = new String[] {
                "sara", "ricardo", "pedro", "fábio",
                "luís", "romeu"
        };

        for (String name : mockUsers) {
            User newUser = new User(name,
                    // 12345 hashed to SHA
                    "8cb2237d0679ca88db6464eac60da96345513964");
            userList.put(name, newUser);
        }

    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = findByName(username);

        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        userList.put(user.getUsername(), user);
    }

    @Override
    public User findByName(String username) {
        return userList.get(username);
    }

    public Collection<User> getUserList() {
        return userList.values();
    }
}
