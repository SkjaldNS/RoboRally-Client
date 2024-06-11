package dk.dtu.compute.se.pisd.roborally.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.dtu.compute.se.pisd.roborally.model.Command;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientController {

    private static final String BASE_URL = "http://localhost:8080";

    private HttpClient httpClient;
    public ClientController() {
        httpClient = HttpClient.newHttpClient();
    }

    public void postGame() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void postMove(Command command) throws Exception {
        Gson gson = new GsonBuilder()
                .create();
        String commandJson = gson.toJson(command);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/moves"))
                .POST(HttpRequest.BodyPublishers.ofString(commandJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
