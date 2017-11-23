package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.BellyButton;
import org.academiadecodigo.hackathon.jesusfindrserver.model.Profile;
import org.academiadecodigo.hackathon.jesusfindrserver.model.User;
import org.academiadecodigo.hackathon.jesusfindrserver.services.user.UserService;

import java.util.LinkedList;
import java.util.List;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class MockMatchmakerService {

    private UserService userService;
    private List<Profile> profilesList;

    public MockMatchmakerService(UserService userService) {
        this.profilesList = new LinkedList<>();
        this.userService = userService;

        fillProfilesList();
    }

    private void fillProfilesList() {
        for (User user : userService.getUserList()) {
            Profile profile = new Profile(user);
            profile.setBackHair(false);
            profile.setBellyButton(BellyButton.values());
            profilesList.add()
        }
    }

    public  User findMatchForUser(User user) {
        int profilesSize = profilesList.size();
        int roll = (int) (Math.random() * profilesSize);

        while (profilesList.get(roll).getUser().equals(user)) {
            roll = (int) (Math.random() * profilesSize);
        }

        return profilesList.get(roll).getUser();
    }

    public

}
