package logic.fields;

import logic.Player;

public abstract class Field {

    private final int x, y;

    private final int ID;
    private Player player;

    public Field(int ID, int x, int y) {
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getID() {
        return ID;
    }

    public boolean isSpace() {
        return player == null;
    }

    public abstract void performAction();
}
