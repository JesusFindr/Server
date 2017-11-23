package org.academiadecodigo.hackathon.jesusfindrserver.server;

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

        String[] strings = string.split("!!");

        if (strings[0].equals("match")) {

            setMatch(strings[1]);
        }

        if (strings[0].equals("message") && matchUser != null) {

            sendMessage(strings[1]);
        }

        if (strings[0].equals("isMatchOnline")){

            server.isOnline(matchUser);

        }

    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setMatch(String matchUser) {
        this.matchUser = matchUser;
    }
}

