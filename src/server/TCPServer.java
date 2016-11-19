package server;
import java.net.*;
import java.io.*;

/**
 * Created by ilmir on 2016-11-14.
 */

public class TCPServer {
    public static void main (String args[]) {
        try {
            System.out.println("Сервер запущен");
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket (serverPort); // new server port generated

            while(true) {
                Socket clientSocket = listenSocket.accept(); // listen for new connection
                System.out.println("Client accepted");
                Connection connection = new Connection(clientSocket); // launch new thread
                connection.start();
            }
        } catch(IOException e) { System.out.println("Listen socket:"+e.getMessage());
        }
    }

}
