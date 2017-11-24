package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.Profile;
import org.academiadecodigo.hackathon.jesusfindrserver.model.User;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public interface MatchmakerService {
    User findMatchForUser(User user);

    Profile getProfileFromUser(User user);

    void addProfile(Profile profile);
}
