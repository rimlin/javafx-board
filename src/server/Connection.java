package server;
import client.model.Message;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import client.model.Transporter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by ilmir on 2016-11-14.
 */

class Connection extends Thread {
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    ObjectOutputStream dbSave;
    Socket clientSocket;

    private String messagesFile = "./messages.json";
    private String boardFile = "./board.json";
    private String usersFile = "./users.json";

    ArrayList<String> tableTypes = new ArrayList<String>();

    public Connection (Socket aClientSocket) {
        tableTypes.add("messages");
        tableTypes.add("board");
        tableTypes.add("users");

        this.checkTableExists();

        try {
            clientSocket = aClientSocket;
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch(IOException e){System.out.println ("Connection:"+e.getMessage());
        }
    }

    private void checkTableExists() {
        for (int i = 0; i < tableTypes.size(); i++) {
            String tableName = tableTypes.get(i);
            File f = new File(getFilePath(tableName));

            // Создаем файл таблицы, если его не существует
            if (!f.exists()) {
                this.resetTable(tableName);
            }
        }
    }

    public void run() { // an echo server
        Object model;

        while (true) {
            try {
                model = inputStream.readObject();

                if (model instanceof Message) {
                    this.saveData(model, "messages");
                }

                if (model instanceof Transporter) {
                    Transporter transporter = (Transporter) model;

                    System.out.printf(transporter.getOperation());

                    //if (transporter.getOperation() == "fetch") {
                        this.fetchData(transporter);
                    //}
                }
            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchData(Transporter transporter) {
        JSONArray list = getData((String)transporter.getModule());

        switch (transporter.getModule()) {
            case "messages":
                ArrayList<Message> theMessagesList = new ArrayList<Message>();

                Iterator i = list.iterator();

                while (i.hasNext()) {
                    JSONObject message = (JSONObject) i.next();
                    theMessagesList.add(new Message((String)message.get("text"), (String)message.get("authorId")));
                }

                Message[] theMessages = theMessagesList.toArray(new Message[theMessagesList.size()]);

                try {
                    outputStream.writeObject(theMessages);
                } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
                } catch (IOException e) {System.out.println ("readline:"+e.getMessage());}

                break;
        }
    }

    private JSONArray getData(String type) {
        JSONParser parser = new JSONParser();
        JSONArray list = new JSONArray();

        try {
            Object obj = parser.parse(new FileReader(getFilePath(type)));

            JSONObject jsonObject = (JSONObject) obj;

            list = (JSONArray) jsonObject.get("list");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private void saveData(Object model, String type) {
        JSONObject table = new JSONObject();
        JSONObject item = new JSONObject();
        JSONArray list = getData(type);

        if (type == "messages") {
            Message message = (Message) model;

            item.put("id", message.getId());
            item.put("text", message.getText());
            item.put("authorId", message.getAuthorId());
            item.put("dateFormat", message.getDateFormat());
        }

        list.add(item);
        table.put("list", list);

        try (FileWriter file = new FileWriter(getFilePath(type))) {
            file.write(table.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetTable(String type) {
        JSONObject obj = new JSONObject();
        JSONArray emptyArray = new JSONArray();
        obj.put("list", emptyArray);

        try (FileWriter file = new FileWriter(getFilePath(type))) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getFilePath(String type) {
        String filePath = "";
        System.out.println("TYPE: " + type);

        //if (type == "messages")
        filePath = messagesFile;
        if (type == "board") filePath = boardFile;
        if (type == "users") filePath = usersFile;

        return filePath;
    }
}
