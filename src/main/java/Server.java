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

    public Server() {
        portNumber = 9090;
        executorService = Executors.newFixedThreadPool(10);
        clientMap = new HashMap<String, ClientHandler>();

    }

    public void start() {

        try {
            serverSocket = new ServerSocket(portNumber);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String string = bufferedReader.readLine();

                clientMap.put(string, clientHandler);

                executorService.submit(clientHandler);

                System.out.println(string);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void directMessage(ClientHandler source, String destination, String message){

        try {
            PrintWriter out = new PrintWriter(clientMap.get(destination).getClientSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMatch(String user1, String user2){

        clientMap.get(user1).setMatch(user2);
        clientMap.get(user2).setMatch(user1);
    }

}
