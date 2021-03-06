package org.academiadecodigo.hackathon.jesusfindrserver.services.user;

import org.academiadecodigo.hackathon.jesusfindrserver.model.User;

import java.util.Collection;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public interface UserService {

    boolean authenticate(String username, String password);

    void addUser(User user);

    User findByName(String username);

    Collection<User> getUserList();
}
