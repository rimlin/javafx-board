package client.model;

import java.io.Serializable;
import java.time.LocalDate;

import client.util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by ilmir on 2016-11-03.
 */
public class Message implements Serializable {
    private final String text;
    private final Integer id;
    private final transient LocalDate date;
    private final String dateFormat;
    private final String authorId;

    public Message(String text, String authorId) {
        this.text = text;
        this.id = 1;
        this.authorId = authorId;
        this.date = LocalDate.of(1999, 2, 21);
        this.dateFormat = DateUtil.format(this.getDate());
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
