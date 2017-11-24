package org.academiadecodigo.hackathon.jesusfindrserver.server;

import org.academiadecodigo.hackathon.jesusfindrserver.model.*;
import org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking.MatchmakerService;
import org.academiadecodigo.hackathon.jesusfindrserver.services.matchmaking.MockMatchmakerService;
import org.academiadecodigo.hackathon.jesusfindrserver.services.user.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int portNumber;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private UserService userService;

    private MatchmakerService matchmakerService;

    private Map<String, ClientHandler> clientMap;

    private Map<String, String> onHoldMessages;

    public Server() {
        portNumber = 9090;
        executorService = Executors.newFixedThreadPool(10);
        clientMap = new HashMap<>();
        onHoldMessages = new HashMap<>();
        matchmakerService = new MockMatchmakerService();

    }

    public void start() {

        try {
            serverSocket = new ServerSocket(portNumber);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String string = bufferedReader.readLine();

                handleFirstMessage(string, clientSocket);

                for (String s : onHoldMessages.keySet()) {

                    directMessage(s, onHoldMessages.get(s));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void directMessage(String destination, String message) {

        try {
            if (clientMap.get(destination).getClientSocket().isConnected()) {

                PrintWriter out = new PrintWriter(clientMap.get(destination).getClientSocket().getOutputStream());
                out.write(message);
                out.flush();
            } else {

                String string = "message!!" + message;
                onHoldMessages.put(destination, string);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline(String matchUser) {

        return clientMap.get(matchUser).getClientSocket().isConnected();
    }

    public void clientMapUpdate(String s) {

        clientMap.remove(s);
    }

    public void handleFirstMessage(String string, Socket clientSocket) {

        String[] strings = string.split("#€");

        if (strings[0].equals("login") && userService.authenticate(strings[1], strings[2])) {

            ClientHandler clientHandler = new ClientHandler(clientSocket, this, string);

            clientMap.put(strings[1], clientHandler);

            executorService.submit(clientHandler);

            directMessage(strings[1], "login#€success");

            return;
        }

        if (strings[0].equals("register") && userService.findByName(strings[1]) == null) {

            User user = new User(strings[1], strings[2]);

            userService.addUser(user);

            ClientHandler clientHandler = new ClientHandler(clientSocket, this, string);

            clientMap.put(strings[1], clientHandler);

            executorService.submit(clientHandler);

            directMessage(strings[1], "register#€success");

            handleProfile(string, user);

            return;

        } else {

            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

                if (strings[0].equals("login")) {
                    out.write("login#€fail");
                    out.flush();
                }
                if (strings[0].equals("register")) {
                    out.write("register#€fail");
                    out.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public void handleProfile(String string, User user){

        Profile profile = new Profile(user);

        String[] strings = string.split("#€");

        profile.setAge(Integer.parseInt(strings[3]));

        profile.setSexType(SexType.valueOf(strings[4]));

        profile.setShoeSize(ShoeSize.valueOf(strings[5]));

        profile.setBellyButton(BellyButton.valueOf(strings[6]));

        profile.setSpiritAnimal(strings[7]);

        profile.setBrowsType(BrowsType.valueOf(strings[8]));

    }

}

