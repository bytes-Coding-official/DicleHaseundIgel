package logic.cards;

public enum CardType {
    ;


    private final String text;
    private final int effect;

    CardType(String text, int effect) {
        this.text = text;
        this.effect = effect;
    }

    public String getText() {
        return text;
    }

    public int getEffect() {
        return effect;
    }
}
