package dk.dtu.compute.se.pisd.roborally.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.Move;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientController {

    private static final String BASE_URL = "http://localhost:8080";

    private Gson gson = new GsonBuilder().create();

    private HttpClient httpClient;
    public ClientController() {
        httpClient = HttpClient.newHttpClient();
    }

    //TODO change return type and return something
    public void postPlayer() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/players"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        //Returns playerID
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void putPlayer(Player player) throws Exception {
        String playerJson = gson.toJson(player);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/players/" + player.getPlayerID()))
                .PUT(HttpRequest.BodyPublishers.ofString(playerJson))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO change return type and return something
    public void getPlayers() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/players"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


    //TODO change return type and return something
    public void postGame() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        //Returns gameID
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void putGame(Game game) throws Exception {
        String gameJson = gson.toJson(game);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + game.getGameID()))
                .PUT(HttpRequest.BodyPublishers.ofString(gameJson))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO change return type and return something
    public void getGames() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


    public void postMove(Command command) throws Exception {
        String commandJson = gson.toJson(command);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/moves"))
                .POST(HttpRequest.BodyPublishers.ofString(commandJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO How is a move identified? Check if this is correct
    public void putMove(Move move) throws Exception {
        String gameJson = gson.toJson(move);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/moves/" + move.getGameID() + move.getPlayerID() + move.getTurnID()))
                .PUT(HttpRequest.BodyPublishers.ofString(gameJson))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO change return type and return something
    public void getMoves() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/moves"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
