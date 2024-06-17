package dk.dtu.compute.se.pisd.roborally.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.dtu.compute.se.pisd.roborally.model.Choice;
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

    public int postGame(Game game) throws Exception {
        String gameJson = gson.toJson(game);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .POST(HttpRequest.BodyPublishers.ofString(gameJson))
                .header("Content-Type", "application/json")
                .build();

        //Returns gameID
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return Integer.parseInt(response.body());
    }

    public void putGame(Game game) throws Exception {
        String gameJson = gson.toJson(game);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + game.getGameID()))
                .PUT(HttpRequest.BodyPublishers.ofString(gameJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public Game getGames() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(),Game.class);
    }

    public int postPlayer(String playerName, String gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .POST(HttpRequest.BodyPublishers.ofString(playerName))
                .header("Content-Type", "application/json")
                .build();

        //Returns playerID
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return Integer.parseInt(response.body());
    }

    public void putPlayer(Player player) throws Exception {
        String playerJson = gson.toJson(player);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + player.getGameID() + "/players/" + player.getPlayerID()))
                .PUT(HttpRequest.BodyPublishers.ofString(playerJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public Player[] getPlayers(String gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(),Player[].class);
    }


    public void postMove(Move move) throws Exception {
        String moveJson = gson.toJson(move);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + move.getGameID() + "/moves"))
                .POST(HttpRequest.BodyPublishers.ofString(moveJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public Move[] getMoves(String gameID, String turnID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players" + "/moves" + turnID))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(), Move[].class);
    }

    public void postChoice(Move move) throws Exception {
        String moveJson = gson.toJson(move);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + move.getGameID() + "/choices"))
                .POST(HttpRequest.BodyPublishers.ofString(moveJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public Choice getChoice(String gameID, String playerID, String turnID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/choices" + turnID + playerID))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(),Choice.class);
    }

}
