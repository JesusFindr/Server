package org.academiadecodigo.hackathon.jesusfindrserver.server;

import org.academiadecodigo.hackathon.jesusfindrserver.model.Profile;
import org.academiadecodigo.hackathon.jesusfindrserver.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by codecadet on 23/11/17.
 */
public class ClientHandler implements Runnable {

    private Server server;
    private String username;

    private Socket clientSocket;
    private String matchUser;

    public ClientHandler(Socket clientSocket, Server server, String username) {

        this.server = server;
        this.username = username;
        this.clientSocket = clientSocket;
    }


    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (clientSocket.isConnected()) {

                String string = bufferedReader.readLine();

                messageHandler(string);
            }

        } catch (IOException e) {

            server.clientMapUpdate(username);
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {

        String string = username + ": " + message;

        server.directMessage(matchUser, string);
    }

    public void messageHandler(String string) {

        if (!string.contains("#€")){
            sendMessage(string);
        }

        String[] strings = string.split("#€");

        if (strings[0].equals("match")) {
            setMatch(server.getMatchmakerService().findMatchForUser(new User(username)));
            if (matchUser != null) {
                sendProfile(matchUser);
            } else {
                sendMessage("match#€notfound");
            }
        }

        if (strings[0].equals("isMatchOnline")){

            server.isOnline(matchUser);
        }

        if (strings[0].equals("profile")) {
            sendProfile(strings[1]);
        }

    }

    private void sendProfile(String matchUser) {
        Profile match = server.getMatchmakerService().getProfileFromUser(new User(matchUser));
        server.directMessage(username, String.format("profile#€%s#€%d#€%s#€%d#€%s#€%s#€%s#€%s#€%s",
                match.getUser().getUsername(), match.getAge(),
                match.getSexType().getType(), match.getImage(),
                match.getShoeSize().getType(), match.getBellyButton().getType(),
                match.getSpiritAnimal(), match.getBrowsType().getType(),
                Boolean.toString(match.getBackHair())));
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setMatch(String matchUser) {
        this.matchUser = matchUser;
    }
}

