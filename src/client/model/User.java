package client.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by ilmir on 2016-11-03.
 */
public class User {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty login;

    public User(String firstName, String lastName, String login) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.login = new SimpleStringProperty(login);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getLogin() {
        return login.get();
    }
}
