package client.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import client.Main;

/**
 * Created by ilmir on 2016-11-03.
 */
public class Message implements Serializable {
    private String text;
    private final Integer id;
    private final String date;
    private final Integer authorId;
    private transient String author;
    private transient String formatDate;

    public Message(Integer id, String text, Integer authorId, String date) {
        this.text = text;

        if (id == -1) {
            this.id = Main.getInstance().boardController.getAmount() + 1;
        } else {
            this.id = id;
        }

        this.authorId = authorId;

        if (date.equals("-1")) {
            this.date = String.valueOf(System.currentTimeMillis() / 1000L);
        } else {
            this.date = date;
        }
    }

    public void setupAuthor() {
        Optional userById = Main.getInstance().usersController.getUserById(authorId);

        if (userById.isPresent() && userById.get() instanceof User) {
            User userAuthor = (User)userById.get();

            author = userAuthor.getFirstName() + " " + userAuthor.getLastName();
        } else {
            author = "None";
        }

        this.formatDate = getDateFormat();
    }

    public void setText(String newText) {
        text = newText;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public Long getDate() {
        return Long.valueOf(date);
    }

    public String getDateString() {
        return date;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateFormat() {
        Date time = new java.util.Date((long)this.getDate()*1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return dateFormat.format(time);
    }
}
