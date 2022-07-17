package org.example;

public class Cats2Json {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final String upvotes;

    //Конструктор без аннотаций:
    public Cats2Json(String id, String text, String type, String user, String upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    //Геттеры:
    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getUpvotes() {
        return upvotes;
    }


    @Override
    public String toString() {
        return "Cats_info = " +
                "\n cats can: " + text +
                "\n say: " + user +
                "\n people vote = " + upvotes;
    }
}