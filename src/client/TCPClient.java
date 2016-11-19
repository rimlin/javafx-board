
package client;

import client.model.Message;

import java.net.*;
import java.io.*;


public class TCPClient {

    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public TCPClient() throws IOException {
        Socket s = null;
        System.out.println("Клиент запущен");
        int serverPort = 7896;
        String serverHost = "127.0.0.1";

        s = new Socket(serverHost, serverPort);

        inputStream = new ObjectInputStream(s.getInputStream());
        outputStream = new ObjectOutputStream(s.getOutputStream());
    }


    public void sendMessage(Message messageModel) {
        try {
            outputStream.writeObject(messageModel);
            //outputStream.close();
        } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
        } catch (IOException e) {System.out.println ("readline:"+e.getMessage());}
    }
}
