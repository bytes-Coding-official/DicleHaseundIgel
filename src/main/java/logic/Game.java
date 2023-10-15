package logic;

import logic.cards.HasenCards;
import logic.fields.*;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private static Game instance;
    private final Set<HasenCards> hasenCards = new HashSet<>();
    private final Set<Field> fields = new HashSet<>();
    private Set<Player> players = new HashSet<>();
    private Player currentPlayer;
    private int carrotCards = 250; //TODO: correct value

    private Field[] lastFields = new Field[6];


    private Game() {
        generateFields();
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public static void setInstance(Game instance) {
        Game.instance = instance;
    }

    private void generateFields() {
        var field = new RabbitField(0, 435, 434);
        field.setStartField(true);
        fields.add(field);
        fields.add(new CarrotField(1, 375, 434));
        fields.add(new RabbitField(2, 345, 494));
        fields.add(new PositionField(3, 345, 554, 3));
        fields.add(new CarrotField(4, 404, 584));
        fields.add(new RabbitField(5, 405, 644));
        fields.add(new SaladField(6, 346, 644));
        fields.add(new HedgehogField(7, 285, 675));
        fields.add(new PositionField(8, 225, 675, 4));
        fields.add(new PositionField(9, 164, 675, 2));
        fields.add(new HedgehogField(10, 104, 645));
        fields.add(new PositionField(11, 75, 585, 3));
        fields.add(new CarrotField(12, 75, 525));
        fields.add(new RabbitField(13, 75, 465));
        fields.add(new HedgehogField(14, 135, 465));
        fields.add(new EffectField(15, 195, 465));
        fields.add(new PositionField(16, 193, 404, 2));
        fields.add(new PositionField(17, 195, 343, 4));
        fields.add(new HedgehogField(18, 135, 343));
        fields.add(new PositionField(19, 75, 313, 3));
        fields.add(new CarrotField(20, 75, 253));
        fields.add(new SaladField(21, 75, 193));
        fields.add(new PositionField(22, 104, 133, 2));
        fields.add(new HedgehogField(23, 134, 72));
        fields.add(new RabbitField(24, 194, 72));
        fields.add(new CarrotField(25, 254, 72));
        fields.add(new PositionField(26, 285, 133, 4));
        fields.add(new PositionField(27, 345, 163, 3));
        fields.add(new PositionField(28, 405, 163, 2));
        fields.add(new HedgehogField(29, 405, 103));
        fields.add(new RabbitField(30, 465, 103));
        fields.add(new EffectField(31, 533, 103));
        fields.add(new CarrotField(32, 593, 103));
        fields.add(new RabbitField(33, 593, 163));
        fields.add(new PositionField(34, 653, 163, 2));
        fields.add(new PositionField(35, 713, 134, 3));
        fields.add(new HedgehogField(36, 743, 74));
        fields.add(new CarrotField(37, 803, 74));
        fields.add(new RabbitField(38, 863, 74));
        fields.add(new CarrotField(39, 895, 135));
        fields.add(new PositionField(40, 924, 196, 2));
        fields.add(new SaladField(41, 924, 256));
        fields.add(new HedgehogField(42, 924, 316));
        fields.add(new PositionField(43, 864, 345, 3));
        fields.add(new PositionField(44, 804, 345, 4));
        fields.add(new RabbitField(45, 804, 405));
        fields.add(new PositionField(46, 804, 465, 2));
        fields.add(new EffectField(47, 864, 465));
        fields.add(new CarrotField(48, 924, 465));
        fields.add(new HedgehogField(49, 923, 525));
        fields.add(new RabbitField(50, 923, 585));
        fields.add(new PositionField(51, 893, 645, 3));
        fields.add(new PositionField(52, 833, 675, 2));
        fields.add(new PositionField(53, 803, 615, 4));
        fields.add(new CarrotField(54, 772, 554));
        fields.add(new HedgehogField(55, 712, 554));
        fields.add(new SaladField(56, 682, 614));
        fields.add(new RabbitField(57, 682, 675));
        fields.add(new CarrotField(58, 622, 675));
        fields.add(new PositionField(59, 562, 644, 2));
        fields.add(new RabbitField(60, 562, 584));
        fields.add(new SaladField(61, 562, 524));
        var end = new RabbitField(62, 562, 464);
        end.setEndField(true);
        fields.add(end);
    }

    private void distributeCarrotCards() {
        if (players.size() >= 5) {
            players.forEach(player -> player.setCarrotCards(98));
        } else {
            players.forEach(player -> player.setCarrotCards(68));
        }
    }

    public void startGame() {
        //TODO: Implement
    }


    private void distributeSaladCards() {
        players.forEach(player -> player.setSaladCards(3));
    }

    public void distributeCards() {
        distributeSaladCards();
        distributeCarrotCards();
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


    public Field[] getLastFields() {
        return lastFields;
    }

    public void setLastFields(Field[] lastFields) {
        this.lastFields = lastFields;
    }
}
