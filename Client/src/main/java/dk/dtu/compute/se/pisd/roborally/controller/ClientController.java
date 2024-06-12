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

    //TODO change return type and return something
    public void postPlayer(String gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        //Returns playerID
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void putPlayer(Player player) throws Exception {
        String playerJson = gson.toJson(player);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + player.getGameID() + "/players/" + player.getPlayerID()))
                .PUT(HttpRequest.BodyPublishers.ofString(playerJson))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO change return type and return something
    public void getPlayers(String gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


    public void postMove(Move move) throws Exception {
        String moveJson = gson.toJson(move);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + move.getGameID() + "/players" + move.getPlayerID() + "/moves"))
                .POST(HttpRequest.BodyPublishers.ofString(moveJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO change return type and return something
    public void getMoves(String gameID, String playerID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players" + playerID + "/moves"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void postChoice(Move move) throws Exception {
        String moveJson = gson.toJson(move);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + move.getGameID() + "/players" + move.getPlayerID() + "/moves" + "/choices"))
                .POST(HttpRequest.BodyPublishers.ofString(moveJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    //TODO change return type and return something
    public void getChoice(String gameID, String playerID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players" + playerID + "/moves" + "/choices"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
