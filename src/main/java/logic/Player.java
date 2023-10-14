package logic;

public class Player {
    private final String name;
    private int carrotCards;
    private Figure figure;

    private int saladCards;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCarrotCards() {
        return carrotCards;
    }

    public void setCarrotCards(int carrotCards) {
        this.carrotCards = carrotCards;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public int getSaladCards() {
        return saladCards;
    }

    public void setSaladCards(int saladCards) {
        this.saladCards = saladCards;
    }
}
