package logic;

import logic.fields.Field;

public class Figure {

    private final Color color;
    private final Player player;
    private Field field;

    public Figure(Field field, Color color, Player player) {
        this.field = field;
        this.color = color;
        this.player = player;
        player.setFigure(this);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Color getColor() {
        return color;
    }

    public Player getPlayer() {
        return player;
    }
}
