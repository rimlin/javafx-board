
package client;

import client.model.Message;
import client.model.Transporter;
import client.model.User;

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

    public void getMessagesFromServer() {
        Transporter transporter = new Transporter("fetch", "messages");

        try {
            outputStream.writeObject(transporter);

            Object objects = inputStream.readObject();

            Message[] theMessages = (Message[])objects;

            Main.getInstance().boardModel.uploadMessages(theMessages);
        } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
        } catch (IOException e) {System.out.println ("readline:"+e.getMessage());
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    public void getUsersFromServer() {
        Transporter transporter = new Transporter("fetch", "users");

        try {
            outputStream.writeObject(transporter);

            Object objects = inputStream.readObject();

            User[] theUsers = (User[])objects;

            Main.getInstance().usersController.uploadUsers(theUsers);
        } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
        } catch (IOException e) {System.out.println ("readline:"+e.getMessage());
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    public void sendMessage(Message messageModel) {
        try {
            outputStream.writeObject(messageModel);
        } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
        } catch (IOException e) {System.out.println ("readline:"+e.getMessage());}
    }

    public void createUser(User userModel) {
        try {
            outputStream.writeObject(userModel);
        } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
        } catch (IOException e) {System.out.println ("readline:"+e.getMessage());}
    }
}
