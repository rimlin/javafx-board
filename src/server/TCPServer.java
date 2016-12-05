package server;
import java.net.*;
import java.io.*;

/**
 * Created by ilmir on 2016-11-14.
 */

public class TCPServer {
    private ServerSocket listenSocket;
    private Socket clientSocket;

    public static void main (String args[]) {
        TCPServer tcpServer = new TCPServer();
        tcpServer.makeConnect();
    }

    public void makeConnect() {
        try {
            System.out.println("Сервер запущен");
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket (serverPort); // new server port generated

            while(true) {
                Socket clientSocket = listenSocket.accept(); // listen for new connection
                System.out.println("Client accepted");
                Connection connection = new Connection(clientSocket, this); // launch new thread
                connection.start();
            }
        } catch(IOException e) { System.out.println("Listen socket:"+e.getMessage());
        }
    }

    public void tryReConnect() {
        try {
            listenSocket.close();
            clientSocket = null;

            int serverPort = 7896; // the server port
            listenSocket = new ServerSocket (serverPort); // new server port generated

            while(true) {
                Socket clientSocket = listenSocket.accept(); // listen for new connection
                System.out.println("Client accepted");
                Connection connection = new Connection(clientSocket, this); // launch new thread
                connection.start();
            }
        } catch(IOException e) {
            System.out.println("Reconnection not successfull, try again...");
            tryReConnect();
        }
    }

}
