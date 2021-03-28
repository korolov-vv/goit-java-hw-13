package ua.goit.hw13;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;

public class HTTPClientUtil {
    private static final Gson gson = new Gson();

    public static HttpRequest prepareCreateRequest(User user) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint()))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
    }

    public static HttpRequest prepareUpdateRequest(String id, User user) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint() + "/" + id))
                .method("PUT", HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
    }

    public static HttpRequest prepareDeleteRequest(String id, User user) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint() + "/" + id))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
    }

    public static HttpRequest prepareGetRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint()))
                .GET()
                .build();
    }

    public static HttpRequest prepareGetUserById(String id) {
        return HttpRequest.newBuilder()
                .uri(URI.create(JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint() + "/" + id))
                .GET()
                .build();
    }

    public static HttpRequest prepareGetUserByName(String param, String value) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(String.format("%s%s?%s=%s",
                        JsonPlaceHolderApi.getURL(),
                        JsonPlaceHolderApi.getUsersEndPoint(),
                        param,
                        value.replace(" ", "%20"))))
                .build();
    }

    public static HttpRequest prepareGetPostsOfTheUser(int id) {
        return HttpRequest.newBuilder()
                .uri(URI.create(String.format(
                        JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint() + "/" + id + "/" + "posts")))
                .GET()
                .build();
    }

    public static HttpRequest prepareGetComments(Post post) {
        return HttpRequest.newBuilder()
                .uri(URI.create(
                        JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getPostsEndPoint() + "/" + post.getId() + JsonPlaceHolderApi.getCommentsEndPoint()
                ))
                .GET()
                .build();
    }

    public static HttpRequest prepareGetTasks(int id) {

        return HttpRequest.newBuilder()
                .uri(URI.create(
                        JsonPlaceHolderApi.getURL() + JsonPlaceHolderApi.getUsersEndPoint() + "/" + id + JsonPlaceHolderApi.getTasksEndPoint()
                ))
                .GET()
                .build();
    }
}
