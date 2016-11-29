package client.model;

import client.Main;
import client.controller.Users;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by ilmir on 2016-11-03.
 */
public class User implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String login;
    private final String password;

    public User(Integer id, String login, String password, String firstName, String lastName) {
        if (id == -1) {
            this.id = Main.getInstance().usersController.getAmount() + 1;
        } else {
            this.id = id;
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
