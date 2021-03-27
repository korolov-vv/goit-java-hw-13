package ua.goit.hw13;

public class Post {
    private int userId;
    private String id;
    private String title;
    private String body;

    public String getId() {
        return id;
    }

    public Post(int userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
