package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Choice;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.Move;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.util.List;

public interface RestController {

    List<Player> getPlayers(int gameID) ;

    void deletePlayer(int gameID, int playerID);

    List<Game> getGames() throws Exception;

    Game getGame(int gameID);

    int postGame(Game game);

    void putGame(Game game);

    void deleteGame(int gameID);

    int postPlayer(String playerName, int gameID);

    void putPlayer(Player player);

    void deletePlayers(int gameID);

    void postMove(Move move);

    Choice getChoice(int gameID, int playerID, int turnID);

    Move[] getMoves(int gameID, int turnID);

    void postChoice(Choice choice);
}
