import dk.dtu.compute.se.pisd.roborally.controller.ClientController;
import dk.dtu.compute.se.pisd.roborally.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientControllerTest {

    private ClientController clientController;
    private HttpClient httpClient;

    @BeforeEach
    public void setup() {
        httpClient = Mockito.mock(HttpClient.class);
        clientController = new ClientController(httpClient);
    }

    @Test
    public void testPostGame() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Game game = new Game("test"); // Initialize game object as per your requirement
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        int result = clientController.postGame(game);

        assertEquals(1, result);
    }

    @Test
    public void testPutGame() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Game game = new Game("test");
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        clientController.putGame(game);

        Mockito.verify(httpClient).send(any(), any());
    }

    @Test
    public void testGetGames() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("[{\"gameId\":1,\"gameName\":\"test\",\"boardId\":1,\"gameStatus\":0,\"turnId\":0,\"numberOfPlayers\":0}]");

        List<Game> games = clientController.getGames();

        Assertions.assertNotNull(games);
        assertEquals(1, games.size());
        assertEquals(1, games.get(0).getGameID());
        assertEquals("test", games.get(0).getGameName());
    }

    @Test
    public void testGetGamesEmpty() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("[]");

        List<Game> games = clientController.getGames();

        Assertions.assertNotNull(games);
        assertEquals(0, games.size());
    }

    @Test
    public void testGetGame() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("{\"gameId\":1,\"gameName\":\"test\",\"boardId\":1,\"gameStatus\":0,\"turnId\":0,\"numberOfPlayers\":0}");

        Game game = clientController.getGame(1);

        Assertions.assertNotNull(game);
        assertEquals(1, game.getGameID());
        assertEquals("test", game.getGameName());
    }

    @Test
    public void testDeleteGame() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        clientController.deleteGame(1);

        Mockito.verify(httpClient).send(any(), any());
    }

    @Test
    public void testPostPlayer() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        int playerId = clientController.postPlayer("testPlayer", 1);

        assertEquals(1, playerId);
    }

    @Test
    public void testGetPlayers() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("[{\"playerId\":1,\"playerName\":\"testPlayer\",\"gameId\":1}]");

        List<Player> players = clientController.getPlayers(1);

        Assertions.assertNotNull(players);
        assertEquals(1, players.size());
        assertEquals(1, players.get(0).getPlayerID());
        assertEquals("testPlayer", players.get(0).getName());
    }

    @Test
    public void testDeletePlayer() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        clientController.deletePlayer(1, 1);

        Mockito.verify(httpClient).send(any(), any());
    }

    @Test
    public void testDeletePlayers() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        clientController.deletePlayers(1);

        Mockito.verify(httpClient).send(any(), any());
    }

    @Test
    public void testPostMove() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Move move = new Move();
        move.setReg1(Command.FORWARD);
        move.setReg2(Command.OPTION_LEFT_RIGHT);
        move.setReg3(Command.LEFT);
        move.setReg4(Command.RIGHT);
        move.setReg5(Command.U_TURN);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");
        clientController.postMove(move);

        Mockito.verify(httpClient).send(any(), any());
    }

    @Test
    public void testGetMoves() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("[{\"gameId\":1,\"turnId\":1,\"playerId\":1,\"fromSpaceX\":1,\"fromSpaceY\":1,\"toSpaceX\":1,\"toSpaceY\":1}]");

        Move[] moves = clientController.getMoves(1, 1);

        Assertions.assertNotNull(moves);
        assertEquals(1, moves.length);
        assertEquals(1, moves[0].getGameId());
        assertEquals(1, moves[0].getTurnId());
        assertEquals(1, moves[0].getPlayerId());
    }

    @Test
    public void testPostChoice() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Choice choice = new Choice(Choice.ChoiceType.LEFT, 1, 1, 1); // Initialize choice object as per your requirement
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("1");

        clientController.postChoice(choice);

        Mockito.verify(httpClient).send(any(), any());
    }

    @Test
    public void testGetChoice() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn("{\"gameId\":1,\"turnId\":1,\"playerId\":1,\"choiceId\":1}");

        Choice choice = clientController.getChoice(1, 1, 1);

        Assertions.assertNotNull(choice);
        assertEquals(1, choice.getGameId());
        assertEquals(1, choice.getTurnId());
        assertEquals(1, choice.getPlayerId());
    }

}
