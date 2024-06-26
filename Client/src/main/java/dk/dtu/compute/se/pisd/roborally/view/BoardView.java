/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Phase;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

/**
 * The view of the board of the game. The board is displayed as a grid of spaces.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class BoardView extends VBox implements ViewObserver {

    private Board board;

    private GridPane mainBoardPane;
    private SpaceView[][] spaces;

    private PlayerView playerView;

    private Label statusLabel;

    /**
     * The constructor of the board view. It creates the view of the board and the player view.
     */
    public BoardView(@NotNull GameController gameController) {
        board = gameController.board;

        mainBoardPane = new GridPane();
        Player player = null;
        for (int i = 0; i < board.getPlayers().length; i++) {
            Player player1 = board.getPlayers()[i];
            if (player1.isLocalPlayer()) {
                player = player1;
                break;
            }
        }
        if(player == null) {
            throw new IllegalArgumentException("No local player found");
        }
        playerView = new PlayerView(gameController, player);
        statusLabel = new Label("<no status>");

        this.getChildren().add(mainBoardPane);
        this.getChildren().add(playerView);
        this.getChildren().add(statusLabel);

        spaces = new SpaceView[board.width][board.height];

        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                Space space = board.getSpace(x, y);
                SpaceView spaceView = new SpaceView(space);
                spaces[x][y] = spaceView;
                mainBoardPane.add(spaceView, x, y);
            }
        }

        board.attach(this);
        update(board);
    }

    /**
     * Update the view of the board. This includes updating the view of the spaces and the player
     * view.
     *
     * @param subject the subject that has changed.
     */
    @Override
    public void updateView(Subject subject) {
        if (subject == board) {
            Phase phase = board.getPhase();
            statusLabel.setText(board.getStatusMessage());
        }
    }

}
