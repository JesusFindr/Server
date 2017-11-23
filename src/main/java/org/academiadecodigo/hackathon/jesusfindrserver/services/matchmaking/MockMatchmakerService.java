package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.*;
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
        int image = 0;
        for (User user : userService.getUserList()) {
            Profile profile = new Profile(user);
            profile.setBackHair(false);
            profile.setBellyButton(BellyButton.values()[(int) (Math.random() * 3)]);
            profile.setAge((int) (Math.random() * 100));
            profile.setBrowsType(BrowsType.values()[(int) (Math.random() * 2)]);
            profile.setSexType(SexType.VIRGIN);
            profile.setShoeSize(ShoeSize.values()[(int) (Math.random() * 3)]);
            profile.setImage(image);
            profile.setRealName(user.getUsername());
            profilesList.add(profile);
            image++;
        }
    }

    public User findMatchForUser(User user) {
        int profilesSize = profilesList.size();
        int roll = (int) (Math.random() * profilesSize);

        while (profilesList.get(roll).getUser().equals(user)) {
            roll = (int) (Math.random() * profilesSize);
        }

        return profilesList.get(roll).getUser();
    }

    public
}
