package logic.cards;

public enum CardType {


    NO_COSTS("Dein letzter Zug kostet nichts!"),
    GIVE_OR_TAKE_CARROTS("Du darfst 10 Karotten nehmen oder 10 Karotten abgeben!"),
    SKIP_TURN("Du musst einmal aussetzen!"),
    GO_BACK("Du musst sofort um eine Position zurückfallen!"),
    EAT_SALAD("Friss sofort einen Salat!"),
    GO_FORWARD("Rücke sofort um eine Position vor!"),
    DRAW_AGAIN("Ziehe gleich noch einmal!"),
    MOVE_TILL_NEXT_CARROT_FIELD("Ziehe sofort vorwärts zum nächsten Karotten-Feld!"),
    GO_BACK_TO_LAST_CARROT_FIELD("Ziehe sofort zurück zum letzten Karotten-Feld!"),


    ;
    private final String text;


    CardType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
