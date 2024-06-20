package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Choice;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.Move;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.util.List;

public interface RestController {

    List<Player> getPlayers(int gameID) throws Exception;

    void deletePlayer(int gameID, int playerID) throws Exception;

    List<Game> getGames() throws Exception;

    Game getGame(int gameID) throws Exception;

    int postGame(Game game) throws Exception;

    void putGame(Game game) throws Exception;

    void deleteGame(int gameID) throws Exception;

    int postPlayer(String playerName, int gameID) throws Exception;

    void putPlayer(Player player) throws Exception;

    void deletePlayers(int gameID) throws Exception;

    void postMove(Move move) throws Exception;

    Choice getChoice(int gameID, int playerID, int turnID) throws Exception;

    Move[] getMoves(int gameID, int turnID) throws Exception;

    void postChoice(Choice choice) throws Exception;
}
