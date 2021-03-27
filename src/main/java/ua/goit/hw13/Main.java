package ua.goit.hw13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    Gson gson = new Gson();

    HttpClient httpClient = HttpClient.newBuilder().build();
    User user = new User("Vadym", "vados", "test@gmail.com", null, "756 493 029");
    List<User> userList;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.exercise1_1();
            main.exercise1_2();
            main.exercise1_3();
            main.exercise1_4();
            main.exercise1_5();
            main.exercise1_6();
            main.exercise2();
            main.exercise3();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void exercise1_1() throws IOException, InterruptedException {
        System.out.println("Exercise 1");
        HttpResponse<String> responseOfCreate = httpClient.send(HTTPClientUtil.prepareCreateRequest(user),
                HttpResponse.BodyHandlers.ofString());
        System.out.println("1. The User with next parameters was created: \n" + responseOfCreate.body() + "\n");
    }

    public void exercise1_2() throws IOException, InterruptedException {

        user.setName("Ivan");
        user.setUsername("ivanushka");
        HttpResponse<String> responceOfUpdate = httpClient.send(HTTPClientUtil.prepareUpdateRequest("5", user),
                HttpResponse.BodyHandlers.ofString());
        System.out.println("2. The User was updated: \n" + responceOfUpdate.body() + "\n");
    }

    public void exercise1_3() throws IOException, InterruptedException {

        HttpResponse<String> responseOfDelete = httpClient.send(HTTPClientUtil.prepareDeleteRequest("10", user),
                HttpResponse.BodyHandlers.ofString());
        System.out.println("3. The User with next parameters was deleted: \n" + responseOfDelete.statusCode() + "\n");
    }

    public void exercise1_4() throws IOException, InterruptedException {

        HttpResponse<String> responceOfGet = httpClient.send(HTTPClientUtil.prepareGetRequest(),
                HttpResponse.BodyHandlers.ofString());
        System.out.println("4. The list of Users: \n" + responceOfGet.body() + "\n");
    }

    public void exercise1_5() throws IOException, InterruptedException {
        HttpResponse<String> getUserById = httpClient.send(HTTPClientUtil.prepareGetUserById("7"),
                HttpResponse.BodyHandlers.ofString());
        System.out.println("5. The User with an id 7: " + getUserById.body());
    }

    public void exercise1_6() throws IOException, InterruptedException {
        HttpResponse<String> getUserByName = httpClient.send(
                HTTPClientUtil.prepareGetUserByName("name","Nicholas Runolfsdottir V"),
                HttpResponse.BodyHandlers.ofString()
        );
        System.out.println("5. The User with name Nicholas Runolfsdottir V: " + getUserByName.body());
    }

    public void exercise2() throws IOException, InterruptedException {

        HttpResponse<String> getUsersPosts = httpClient.send(HTTPClientUtil.prepareGetPostsOfTheUser(user),
                HttpResponse.BodyHandlers.ofString());
        List<Post> allUserPosts = gson.fromJson(getUsersPosts.body(), new TypeToken<List<Post>>() {
        }.getType());
        Post maxPost = Collections.max(allUserPosts, Comparator.comparingInt((p) -> Integer.parseInt(p.getId())));
        String fileName = String.format("user-%s-post-%s-comments.json.txt", user.getId(), maxPost.getId());
        FileWriter file = new FileWriter("/" + fileName);
        HttpResponse<String> getCommentsToPost = httpClient.send(HTTPClientUtil.prepareGetComments(maxPost),
                HttpResponse.BodyHandlers.ofString());

        file.write(String.valueOf(getCommentsToPost));
    }

    public void exercise3() throws IOException, InterruptedException {

        HttpResponse<String> getUsersTasks = httpClient.send(HTTPClientUtil.prepareGetTasks(user),
                HttpResponse.BodyHandlers.ofString());
        List<Task> allUserTasks = gson.fromJson(getUsersTasks.body(), new TypeToken<List<Task>>() {
        }.getType());
        System.out.println(allUserTasks.stream().filter(task -> !task.isCompleted())
                .collect(Collectors.toList()));
    }
}
