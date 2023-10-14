package logic;

import logic.cards.HasenCards;
import logic.fields.Field;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private static Game instance;
    private final Set<HasenCards> hasenCards = new HashSet<>();
    private final Set<Field> fields = new HashSet<>();
    private Set<Player> players = new HashSet<>();
    private Player currentPlayer;
    private int carrotCards;


    private Game() {
        generateFields();
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    private void generateFields() {
    }

    private void distributeCarrotCards() {
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public int getCarrotCards() {
        return carrotCards;
    }

    public void setCarrotCards(int carrotCards) {
        this.carrotCards = carrotCards;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Set<HasenCards> getHasenCards() {
        return hasenCards;
    }

    public Set<Field> getFields() {
        return fields;
    }

}
