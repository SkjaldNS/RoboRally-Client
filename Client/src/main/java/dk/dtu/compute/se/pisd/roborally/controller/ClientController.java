package dk.dtu.compute.se.pisd.roborally.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dk.dtu.compute.se.pisd.roborally.model.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * The ClientController class is responsible for handling the client-side operations of the game.
 * Provides methods for interacting with the server.
 * This includes methods for creating, updating, retrieving, and deleting games and players, as well as posting moves and choices.
 */
public class ClientController implements RestController {

    private static final String BASE_URL = "http://localhost:8080";

    private Gson gson = new GsonBuilder().create();

    private HttpClient httpClient;


    /**
     * Constructor for the ClientController class.
     * Initializes the HttpClient.
     */
    public ClientController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }  

    /**
     * Posts a new game to the server.
     * @param game The game to be posted.
     * @return The ID of the posted game.
     * @throws Exception if an error occurs while posting the game.
     */
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


    /**
     * Updates an existing game on the server.
     * @param game The game to be updated.
     * @throws Exception if an error occurs while updating the game.
     */
    @Override
    public void putGame(Game game) throws Exception {
        String gameJson = gson.toJson(game);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games"))
                .PUT(HttpRequest.BodyPublishers.ofString(gameJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * Retrieves a list of all games from the server.
     * @return A list of all games.
     * @throws Exception if an error occurs while retrieving the games.
     */
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

    /**
     * Retrieves a specific game from the server.
     * @param gameID The ID of the game to be retrieved.
     * @return The retrieved game.
     * @throws Exception if an error occurs while retrieving the game.
     */
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

    /**
     * Deletes a specific game from the server.
     * @param gameID The ID of the game to be deleted.
     * @throws Exception if an error occurs while deleting the game.
     */
    @Override
    public void deleteGame(int gameID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * Posts a new player to a specific game on the server.
     * @param playerName The name of the player to be posted.
     * @param gameID The ID of the game to which the player will be posted.
     * @return The ID of the posted player.
     * @throws Exception if an error occurs while posting the player.
     */
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

    /**
     * Updates an existing player in a specific game on the server.
     * @param player The player to be updated.
     * @throws Exception if an error occurs while updating the player.
     */
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

    /**
     * Retrieves a list of all players in a specific game from the server.
     * @param gameID The ID of the game from which the players will be retrieved.
     * @return A list of all players in the game.
     * @throws Exception if an error occurs while retrieving the players.
     */
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

    /**
     * Deletes a specific player from a specific game on the server.
     * @param gameID The ID of the game from which the player will be deleted.
     * @param playerID The ID of the player to be deleted.
     * @throws Exception if an error occurs while deleting the player.
     */
    @Override
    public void deletePlayer(int gameID, int playerID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players/" + playerID))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * Deletes all players from a specific game on the server.
     * @param gameID The ID of the game from which the players will be deleted.
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the operation is interrupted.
     * @throws URISyntaxException if the URI syntax is incorrect.
     */
    @Override
    public void deletePlayers(int gameID) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/players"))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * Posts a new move to a specific game on the server.
     * @param move The move to be posted.
     * @throws Exception if an error occurs while posting the move.
     */
    @Override
    public void postMove(Move move) throws Exception {
        String moveJson = gson.toJson(move);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + move.getGameId() + "/moves"))
                .POST(HttpRequest.BodyPublishers.ofString(moveJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * Retrieves all moves from a specific turn in a specific game from the server.
     * @param gameID The ID of the game from which the moves will be retrieved.
     * @param turnID The ID of the turn from which the moves will be retrieved.
     * @return An array of all moves from the turn.
     * @throws Exception if an error occurs while retrieving the moves.
     */
    @Override
    public Move[] getMoves(int gameID, int turnID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameID + "/moves/" + turnID))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(), Move[].class);
    }

    /**
     * Posts a new choice to a specific game on the server.
     * @param choice The choice to be posted.
     * @throws Exception if an error occurs while posting the choice.
     */
    @Override
    public void postChoice(Choice choice) throws Exception {
        String moveJson = gson.toJson(choice);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + choice.getGameId() + "/choices"))
                .POST(HttpRequest.BodyPublishers.ofString(moveJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * Retrieves a specific choice from a specific turn and player in a specific game from the server.
     * @param gameId The ID of the game from which the choice will be retrieved.
     * @param playerId The ID of the player from which the choice will be retrieved.
     * @param turnId The ID of the turn from which the choice will be retrieved.
     * @return The retrieved choice.
     * @throws Exception if an error occurs while retrieving the choice.
     */
    @Override
    public Choice getChoice(int gameId, int playerId, int turnId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/games/" + gameId + "/choices" + "/" + turnId + "/" +  playerId))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(),Choice.class);
    }

}
