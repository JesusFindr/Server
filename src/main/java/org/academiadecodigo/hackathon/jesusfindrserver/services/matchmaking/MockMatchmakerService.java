package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.Profile;
import org.academiadecodigo.hackathon.jesusfindrserver.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class MockMatchmakerService implements MatchmakerService{

    private Map<User, Profile> profilesList;

    public MockMatchmakerService(){
        profilesList = new HashMap<>();
    }

    public void addProfile(Profile profile) {


    }


}
