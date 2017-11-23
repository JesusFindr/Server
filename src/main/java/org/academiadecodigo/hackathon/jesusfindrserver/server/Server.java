package org.academiadecodigo.hackathon.jesusfindrserver.server;

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

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class Server {

    private int portNumber;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private Map<String, ClientHandler> clientMap;

    private Map<String, String> onHoldMessages;

    public Server() {
        portNumber = 9090;
        executorService = Executors.newFixedThreadPool(10);
        clientMap = new HashMap<>();
        onHoldMessages = new HashMap<>();

    }

    public void start() {

        try {
            serverSocket = new ServerSocket(portNumber);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String string = bufferedReader.readLine();

                for (String s : onHoldMessages.keySet()){

                    directMessage(s, onHoldMessages.get(s));
                }

                ClientHandler clientHandler = new ClientHandler(clientSocket, this, string);

                clientMap.put(string, clientHandler);

                executorService.submit(clientHandler);

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
            }
            else {

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


}

