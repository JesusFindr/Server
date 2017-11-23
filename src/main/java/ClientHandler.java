import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by codecadet on 23/11/17.
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket){

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
}
