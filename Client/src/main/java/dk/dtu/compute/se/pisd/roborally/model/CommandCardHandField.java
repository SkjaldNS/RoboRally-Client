package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

public class CommandCardHandField extends Subject implements CommandCardInterface{

    public PlayerLocal player;

    @Expose
    private CommandCard card;

    @Expose
    private boolean visible;

    public CommandCardHandField(PlayerLocal player) {
        this.player = player;
        this.card = null;
        this.visible = true;
    }

    public CommandCardHandField(){
    }

    public CommandCard getCard() {
        return card;
    }

    public void setCard(CommandCard card) {
        if (card != this.card) {
            this.card = card;
            notifyChange();
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (visible != this.visible) {
            this.visible = visible;
            notifyChange();
        }
    }

    @Override
    public int getType() {
        return 2;
    }
}
