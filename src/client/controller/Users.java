package client.controller;

import client.Main;
import client.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by ilmir on 2016-11-29.
 */
public class Users {
    private ArrayList<User> userList = new ArrayList<User>();

    private User currentUser;

    public Users() {

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User userModel) {
        currentUser = userModel;
    }

    public void uploadUsers(User[] list) {
        userList.addAll(Arrays.asList(list));
    }

    public User getUserById(Integer id) {
        User findedUser = userList
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .get();

        return findedUser;
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
}
