package client.controller;

import client.Main;
import client.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by ilmir on 2016-11-29.
 */
public class Users {
    private ArrayList<User> userList = new ArrayList<User>();

    private Boolean isAuth = false;
    private User currentUser;

    public Users() {

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User userModel) {
        isAuth = true;
        currentUser = userModel;
        Main.getInstance().menuBar.setVisible(true);
        Main.getInstance().menuFile.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
    }

    public void uploadUsers(User[] list) {
        userList.addAll(Arrays.asList(list));
    }

    public Optional getUserById(Integer id) {
        return userList
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public Optional getUserByLoginPassword(String login, String password) {
        return userList
                .stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    public Integer getAmount() {
        return userList.size();
    }

    public void regUser(String login, String password, String name, String surname) {
        User newUser = new User(-1, login, password, name, surname);
        userList.add(newUser);

        this.setCurrentUser(newUser);

        Main.getInstance().clientService.createUser(newUser);
    }

    public void doExit() {
        isAuth = false;

        Main.getInstance().showAuthPage();
        Main.getInstance().menuBar.setVisible(false);
    }
}
