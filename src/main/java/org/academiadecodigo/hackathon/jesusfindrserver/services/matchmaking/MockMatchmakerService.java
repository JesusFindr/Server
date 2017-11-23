package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.Profile;
import org.academiadecodigo.hackathon.jesusfindrserver.model.User;

import java.util.LinkedList;
import java.util.List;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class MockMatchmakerService {

    private List<Profile> profilesList;

    public MockMatchmakerService() {
        this.profilesList = new LinkedList<>();
    }

    private User findMatchForUser(User user) {
        int profilesSize = profilesList.size();
        int roll = (int) (Math.random() * profilesSize);

        while (profilesList.get(roll).getUser().equals(user)) {
            roll = (int) (Math.random() * profilesSize);
        }

        return profilesList.get(roll).getUser();
    }

}
