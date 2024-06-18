package dk.dtu.compute.se.pisd.roborally.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dk.dtu.compute.se.pisd.roborally.model.*;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ClientController implements RestController {

    private static final String BASE_URL = "http://localhost:8080";

    private Gson gson = new GsonBuilder().create();

    private HttpClient httpClient;
    public ClientController() {
        httpClient = HttpClient.newHttpClient();
    }

    @Override
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

    @Override
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

    @Override
    public List<Game> getGames() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        Type listType = new TypeToken<List<Game>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }

    @Override
    public Game getGame(int gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(), Game.class);
    }

    @Override
    public int postPlayer(String playerName, int gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .POST(HttpRequest.BodyPublishers.ofString(playerName))
                .header("Content-Type", "text/plain")
                .build();
        //Returns playerID
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return Integer.parseInt(response.body());
    }

    @Override
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

    @Override
    public List<Player> getPlayers(int gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        Type listType = new TypeToken<List<Player>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }

    @Override
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

    @Override
    public Move[] getMoves(String gameID, String turnID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players" + "/moves" + turnID))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(), Move[].class);
    }

    @Override
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

    @Override
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
