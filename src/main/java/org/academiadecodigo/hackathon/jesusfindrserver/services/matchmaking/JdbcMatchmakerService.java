package org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking;

import org.academiadecodigo.hackathon.jesusfindrserver.model.*;
import org.academiadecodigo.hackathon.jesusfindrserver.persistence.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class JdbcMatchmakerService implements MatchmakerService {

    private Connection dbConnection;
    private final String profilesTableName = "users_profiles";
    private final String matchesTableName = "matches";

    public JdbcMatchmakerService() {
        this.dbConnection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public String findMatchForUser(User user) {
        reopenConnectionIfNeeded();

        Map<String, String> matchesMade = getMatchesMade();
        List<String> possibleMatches = new LinkedList<>();

        try {
            String query = "SELECT username FROM " + profilesTableName + " WHERE username IS NOT ?";
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setString(1, user.getUsername());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String possibleMatch = resultSet.getString("username");
                if (matchesMade.containsKey(possibleMatch) ||
                        matchesMade.containsValue(possibleMatch)) {
                    continue;
                }
                possibleMatches.add(resultSet.getString("username"));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (possibleMatches.size() == 0) {
            return null;
        }

        int matchId = (int) (Math.random() * possibleMatches.size());

        return possibleMatches.get(matchId);
    }

    @Override
    public Profile getProfileFromUser(User user) {
        reopenConnectionIfNeeded();

        Profile profile = null;

        try {
            String query = "SELECT * FROM " + profilesTableName + " WHERE username IS ?";
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setString(1, user.getUsername());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profile = new Profile(new User(user.getUsername()));
                profile.setAge(resultSet.getInt("age"));
                profile.setImage(resultSet.getInt("image"));
                profile.setSexType(SexType.values()[resultSet.getInt("sex")]);
                profile.setShoeSize(ShoeSize.values()[resultSet.getInt("shoe_size")]);
                profile.setBellyButton(BellyButton.values()[resultSet.getInt("bellybutton")]);
                profile.setSpiritAnimal(resultSet.getString("spirit_animal"));
                profile.setBrowsType(BrowsType.values()[resultSet.getInt("brows_type")]);
                profile.setBackHair(resultSet.getBoolean("back_hair"));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profile;
    }

    @Override
    public void addProfile(Profile profile) {
        reopenConnectionIfNeeded();

        try {
            String query = "INSERT INTO " + profilesTableName +
                    " (username, age, image, sex, shoe_size, bellybutton," +
                    " spirit_animal, brows_type, back_hair) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setString(1, profile.getUser().getUsername());
            statement.setInt(2, profile.getAge());
            statement.setInt(3, profile.getImage());
            statement.setInt(4, profile.getShoeSize().ordinal());
            statement.setInt(5, profile.getBellyButton().ordinal());
            statement.setString(6, profile.getSpiritAnimal());
            statement.setInt(7, profile.getBrowsType().ordinal());
            statement.setBoolean(8, profile.getBackHair());


            if (!statement.execute()) {
                System.out.println("User adding failed");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> getMatchesMade() {
        reopenConnectionIfNeeded();

        Map<String, String> matches = new HashMap<>();

        try {
            String query = "SELECT user1, user2 FROM " + matchesTableName;
            PreparedStatement statement = dbConnection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String user1 = resultSet.getString("user1");
                String user2 = resultSet.getString("user2");
                matches.put(user1, user2);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matches;
    }

    private void reopenConnectionIfNeeded() {
        try {
            if (dbConnection == null || dbConnection.isClosed()) {
                dbConnection = DatabaseConnector.getInstance().getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
