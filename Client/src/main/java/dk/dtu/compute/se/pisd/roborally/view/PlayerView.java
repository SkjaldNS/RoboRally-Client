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
import dk.dtu.compute.se.pisd.roborally.controller.ClientController;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import dk.dtu.compute.se.pisd.roborally.model.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jetbrains.annotations.NotNull;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class PlayerView extends Pane implements ViewObserver {

    private Player player;

    private VBox top;

    private Label playerLabel;
    private Label programLabel;
    private GridPane programPane;
    private Label cardsLabel;
    private GridPane cardsPane;
    private ImageView robotImage;

    private CardFieldView[] programCardViews;
    private CardFieldView[] cardViews;

    private VBox buttonPanel;

    private Button finishButton;
    private Button executeButton;
    private Button stepButton;

    private VBox playerInteractionPanel;

    private GameController gameController;

    private RestController restController;

    public PlayerView(@NotNull GameController gameController, @NotNull Player player) {
        //this.setStyle("-fx-text-base-color: " + player.getRobotId() + ";");
        this.restController = new ClientController();
        HBox robotBox = new HBox();
        robotImage = new ImageView();
        robotImage.setImage(new Image("robots/r" + player.getRobotId() + ".png"));
        robotImage.setFitHeight(100);
        robotImage.setFitWidth(100);
        robotBox.alignmentProperty().set(Pos.BOTTOM_RIGHT);
        top = new VBox();
        robotBox.getChildren().add(robotImage);
        robotBox.setMinWidth(this.getMinWidth());
        robotBox.setMinHeight(this.getMinHeight());
        this.getChildren().add(top);


        this.gameController = gameController;
        this.player = player;

        playerLabel = new Label(player.getName());
        playerLabel.setAlignment(Pos.BOTTOM_RIGHT);
        playerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        programLabel = new Label("Program");

        programPane = new GridPane();
        programPane.setVgap(2.0);
        programPane.setHgap(2.0);
        programCardViews = new CardFieldView[Player.NO_REGISTERS];
        for(int j = 0; j < Player.NO_CARDS; j++) {
            for (int i = 0; i < Player.NO_REGISTERS; i++) {
                CommandCardField cardField = player.getProgramField(i);
                if (cardField != null) {
                    programCardViews[i] = new CardFieldView(gameController, cardField);
                    programPane.add(programCardViews[i], i, 0);
                }
            }
        }
        programPane.add(playerLabel, 31, 6);

        // XXX  the following buttons should actually not be on the tabs of the individual
        //      players, but on the PlayersView (view for all players). This should be
        //      refactored.

        finishButton = new Button("Finish Programming");
        finishButton.setOnAction( e -> gameController.finishProgrammingPhase());

        executeButton = new Button("Execute Program");
        executeButton.setOnAction( e-> gameController.executePrograms());

        stepButton = new Button("Execute Current Register");
        stepButton.setOnAction( e-> gameController.executeStep());

        buttonPanel = new VBox(finishButton, executeButton, stepButton);
        buttonPanel.setAlignment(Pos.CENTER_LEFT);
        buttonPanel.setSpacing(3.0);
        // programPane.add(buttonPanel, Player.NO_REGISTERS, 0); done in update now

        playerInteractionPanel = new VBox();
        playerInteractionPanel.setAlignment(Pos.CENTER_LEFT);
        playerInteractionPanel.setSpacing(3.0);

        cardsLabel = new Label("Command Cards");
        cardsPane = new GridPane();
        cardsPane.setVgap(2.0);
        cardsPane.setHgap(2.0);
        cardViews = new CardFieldView[Player.NO_CARDS];
        for (int i = 0; i < Player.NO_CARDS; i++) {
            CommandCardField cardField = player.getCardField(i);
            if (cardField != null) {
                cardViews[i] = new CardFieldView(gameController, cardField);
                cardsPane.add(cardViews[i], i, 0);
            }
        }

        cardsPane.add(robotImage, Player.NO_REGISTERS + 6, 0);

        top.getChildren().add(programLabel);
        top.getChildren().add(programPane);
        top.getChildren().add(cardsLabel);
        top.getChildren().add(cardsPane);

        if (player.board != null) {
            player.board.attach(this);
            update(player.board);
        }

    }

    @Override
    public void updateView(Subject subject) {
        if (subject == player.board) {
            for (int i = 0; i < Player.NO_REGISTERS; i++) {
                CardFieldView cardFieldView = programCardViews[i];
                if (cardFieldView != null) {
                    if (player.board.getPhase() == Phase.PROGRAMMING ) {
                        cardFieldView.setBackground(CardFieldView.BG_DEFAULT);
                    } else {
                        if (i < player.board.getStep()) {
                            cardFieldView.setBackground(CardFieldView.BG_DONE);
                        } else if (i == player.board.getStep()) {
                            if (player.board.getCurrentPlayer() == player) {
                                cardFieldView.setBackground(CardFieldView.BG_ACTIVE);
                            } else if (player.board.getPlayerNumber(player.board.getCurrentPlayer()) > player.board.getPlayerNumber(player)) {
                                cardFieldView.setBackground(CardFieldView.BG_DONE);
                            } else {
                                cardFieldView.setBackground(CardFieldView.BG_DEFAULT);
                            }
                        } else {
                            cardFieldView.setBackground(CardFieldView.BG_DEFAULT);
                        }
                    }
                }
            }

            if (player.board.getPhase() != Phase.PLAYER_INTERACTION) {
                if (!programPane.getChildren().contains(buttonPanel)) {
                    programPane.getChildren().remove(playerInteractionPanel);
                    programPane.add(buttonPanel, Player.NO_REGISTERS, 0);
                }
                switch (player.board.getPhase()) {
                    case INITIALISATION:
                        finishButton.setDisable(true);
                        // XXX just to make sure that there is a way for the player to get
                        //     from the initialization phase to the programming phase somehow!
                        executeButton.setDisable(false);
                        stepButton.setDisable(true);
                        break;

                    case PROGRAMMING:
                        finishButton.setDisable(false);
                        executeButton.setDisable(true);
                        stepButton.setDisable(true);
                        break;

                    case ACTIVATION:
                        finishButton.setDisable(true);
                        executeButton.setDisable(false);
                        stepButton.setDisable(false);
                        break;

                    default:
                        finishButton.setDisable(true);
                        executeButton.setDisable(true);
                        stepButton.setDisable(true);
                }


            } else {
                if (!programPane.getChildren().contains(playerInteractionPanel)) {
                    programPane.getChildren().remove(buttonPanel);
                    programPane.add(playerInteractionPanel, Player.NO_REGISTERS, 0);
                }
                playerInteractionPanel.getChildren().clear();

                if (player.board.getCurrentPlayer().isLocalPlayer()) {
                    if(player.getCurrentCommand() == Command.OPTION_LEFT_RIGHT) {
                        // TODO Assignment A3: these buttons should be shown only when there is
                        //      an interactive command card, and the buttons should represent
                        //      the player's choices of the interactive command card. The
                        //      following is just a mockup showing two options
                        Button optionButton = new Button("Right");
                        optionButton.setOnAction(e -> {
                            try {
                                Game game = restController.getGame(player.board.getGameId());
                                Choice choice = new Choice(Choice.ChoiceType.RIGHT,
                                        game.getTurnId(),
                                        (int) player.getPlayerID(),
                                        player.board.getGameId());
                                restController.postChoice(choice);
                                gameController.executeCommandOptionAndContinue(Command.RIGHT);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        optionButton.setDisable(false);
                        playerInteractionPanel.getChildren().add(optionButton);

                        optionButton = new Button("Left");
                        optionButton.setOnAction(e -> {
                            try {
                                Game game = restController.getGame(player.board.getGameId());
                                Choice choice = new Choice(Choice.ChoiceType.LEFT,
                                        game.getTurnId(),
                                        (int) player.getPlayerID(),
                                        player.board.getGameId());
                                restController.postChoice(choice);
                                gameController.executeCommandOptionAndContinue(Command.LEFT);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        optionButton.setDisable(false);
                        playerInteractionPanel.getChildren().add(optionButton);
                    }
                } else if (player.board.getCurrentPlayer().getCurrentCommand() == Command.OPTION_LEFT_RIGHT) {
                    try {
                        int playerId = (int) player.board.getCurrentPlayer().getPlayerID();
                        Game game = restController.getGame(player.board.getGameId());
                        DataUpdater.getInstance().startChoicePolling(() -> {
                            Choice choice = null;
                            try {
                                choice = restController.getChoice(game.getGameID(), playerId, game.getTurnId());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            if (choice != null) {
                                gameController.executeCommandOptionAndContinue(choice.getChoiceType() == Choice.ChoiceType.LEFT ? Command.LEFT : Command.RIGHT);
                                DataUpdater.getInstance().stopChoicePolling();
                            }
                        });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

}
