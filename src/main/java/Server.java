import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

                ClientHandler clientHandler = new ClientHandler(clientSocket);

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
}
