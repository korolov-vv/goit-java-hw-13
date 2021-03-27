package ua.goit.hw13;

public class JsonPlaceHolderApi {
    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS_END_POINT = "/users";
    private static final String POSTS_END_POINT = "/posts";
    private static final String COMMENTS_END_POINT = "/comments";
    private static final String TASKS_END_POINT = "/todos";

    public static String getURL() {
        return URL;
    }
    public static String getUsersEndPoint(){
        return USERS_END_POINT;
    }

    public static String getPostsEndPoint() {
        return POSTS_END_POINT;
    }

    public static String getCommentsEndPoint() {
        return COMMENTS_END_POINT;
    }

    public static String getTasksEndPoint() {
        return TASKS_END_POINT;
    }
}
