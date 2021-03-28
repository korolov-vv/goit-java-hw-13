package ua.goit.hw13;

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public int getId() {
        return id;
    }

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
