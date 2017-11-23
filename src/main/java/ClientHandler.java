import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by codecadet on 23/11/17.
 */
public class ClientHandler implements Runnable {

    private Server server;

    private Socket clientSocket;
    private String matchUser;

    public ClientHandler(Socket clientSocket, Server server){

        this.server = server;

        this.clientSocket = clientSocket;
    }


    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (clientSocket.isConnected()){

                String string = bufferedReader.readLine();

                System.out.println(string);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message){

        server.directMessage(this, matchUser, message);
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setMatch(String matchUser) {
        this.matchUser = matchUser;
    }
}
