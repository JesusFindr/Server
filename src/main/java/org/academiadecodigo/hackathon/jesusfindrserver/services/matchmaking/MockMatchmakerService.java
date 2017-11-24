package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.*;
import org.academiadecodigo.hackathon.jesusfindrserver.services.user.UserService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class MockMatchmakerService {

    private UserService userService;
    private Map<String, Profile> profilesList;
    private List<String> matchesMade;

    public MockMatchmakerService(UserService userService) {
        this.profilesList = new HashMap<>();
        this.userService = userService;
        matchesMade = new LinkedList<>();

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
            profilesList.put(user.getUsername(), profile);
            image++;
        }
    }

    public User findMatchForUser(User user) {
        List<String> possibleMatches = new LinkedList<>();
        // add all filled profiles
        possibleMatches.addAll(profilesList.keySet());
        // remove already matched usernames
        possibleMatches.removeAll(matchesMade);
        // remove own username
        possibleMatches.remove(user.getUsername());

        int matchId = (int) (Math.random() * possibleMatches.size());
        String matchedUsername = possibleMatches.get(matchId);

        return profilesList.get(matchedUsername).getUser();
    }

    public Profile getProfileFromUser(User user) {
        return profilesList.get(user.getUsername());
    }

    public void addProfile(Profile profile) {
        profilesList.put(profile.getUser().getUsername(), profile);
    }
}
