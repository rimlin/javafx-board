package client;

import client.controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import client.model.Message;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public BoardView boardViewController;
    public Board boardController;

    public TCPClient clientService;

    public Users usersController;

    private static Main instance;

    public MenuBar menuBar;
    public Menu menuFile;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStageValue) throws Exception{
        primaryStage = primaryStageValue;
        primaryStage.setTitle("Board");

        clientService = new TCPClient();

        usersController = new Users();

        boardViewController = new BoardView();
        boardController = new Board();

        clientService.getUsersFromServer();
        clientService.getMessagesFromServer();

        initRootLayout();
        showAuthPage();
    }

    private void attachMenu() {
        menuBar = new MenuBar();
        menuFile = new Menu();

        MenuItem menuBoard = new MenuItem("Доска сообщений");
        MenuItem menuAddMessage = new MenuItem("Добавить сообщение");
        MenuItem menuExit = new MenuItem("Выход");

        menuBoard.setOnAction(t -> showBoardPage());
        menuAddMessage.setOnAction(t -> showMessageForm("create"));
        menuExit.setOnAction(t -> usersController.doExit());

        menuFile.getItems().addAll(menuBoard, menuAddMessage, menuExit);

        menuBar.getMenus().addAll(menuFile);
        rootLayout.setTop(menuBar);

        menuBar.setVisible(false);
    }

    public void initRootLayout() {
        try {
            // Загружаем корневой макет
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));

            rootLayout = loader.load();

            // Отображаем эту сцену
            Scene scene =  new Scene(rootLayout);
            primaryStage.setScene(scene);

            String style = getClass().getResource("view/theme.css").toExternalForm();

            primaryStage.getScene().getStylesheets().addAll(style);

            primaryStage.show();

            attachMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAuthPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Auth.fxml"));

            GridPane authPage = (GridPane) loader.load();

            rootLayout.setCenter(authPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showRegPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Registration.fxml"));

            GridPane regPage = (GridPane) loader.load();

            rootLayout.setCenter(regPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBoardPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Board.fxml"));
            loader.setController(boardViewController);

            GridPane boardPage = (GridPane) loader.load();

            rootLayout.setCenter(boardPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessageView(Message message) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MessageView.fxml"));
            MessageView messageView = new MessageView(message);
            loader.setController(messageView);

            GridPane messagePage = (GridPane) loader.load();

            rootLayout.setCenter(messagePage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessageForm(String type, Message message) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MessageForm.fxml"));
            MessageForm messageForm = new MessageForm(type, message);
            loader.setController(messageForm);

            GridPane messagePage = (GridPane) loader.load();

            rootLayout.setCenter(messagePage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessageForm(String type) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MessageForm.fxml"));
            MessageForm messageForm = new MessageForm(type);
            loader.setController(messageForm);

            GridPane messagePage = (GridPane) loader.load();

            rootLayout.setCenter(messagePage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
