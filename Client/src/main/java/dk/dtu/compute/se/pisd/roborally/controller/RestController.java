package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Choice;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.Move;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.util.List;

public interface RestController {

    List<Player> getPlayers(int gameID) throws Exception;

    List<Game> getGames() throws Exception;

    Game getGame(int gameID) throws Exception;

    int postGame(Game game) throws Exception;

    void putGame(Game game) throws Exception;

    int postPlayer(String playerName, int gameID) throws Exception;

    void putPlayer(Player player) throws Exception;

    void postMove(Move move) throws Exception;

    Move[] getMoves(String gameID, String turnID) throws Exception;

    void postChoice(Move move) throws Exception;

    Choice getChoice(String gameID, String playerID, String turnID) throws Exception;
}
