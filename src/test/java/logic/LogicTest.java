package logic;

import logic.cards.HasenCards;
import logic.fields.Field;
import logic.fields.HedgehogField;
import logic.fields.SaladField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;


class LogicTest {


    @BeforeEach
    void setUp() {
        Game.setInstance(null);
        var players = new ArrayList<Player>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Game.getInstance().getPlayers().addAll(players);
        Game.getInstance().setCurrentPlayer(players.get(0));
        new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.BLUE, players.get(0));


        new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.YELLOW, players.get(1));

        Game.getInstance().distributeCards();
    }

    void generateGameWithData(ArrayList<Player> players, Player currentPlayer, int carrotCards, HashSet<HasenCards> hasenCards, Field[] lastPositions) {
        Game.setInstance(null);
        Game.getInstance().getPlayers().clear();
        Game.getInstance().getPlayers().addAll(players);
        Game.getInstance().setCurrentPlayer(currentPlayer);
        //go through the players and set the fields player to that player
        for (var player : players) {
            player.getFigure().getField().setPlayer(player);
        }
        Game.getInstance().setCarrotCards(carrotCards);
        Game.getInstance().getHasenCards().clear();
        Game.getInstance().getHasenCards().addAll(hasenCards);
        Game.getInstance().setLastFields(lastPositions);
    }


    @Test
    void playerCanMoveOnField() {
        //get the field with id 3 
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field.getID() == 3).findFirst().get();
        Assertions.assertNotNull(destinationField);
        //get the player with the name Player 1
        var player = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();
        //check if the player can move to the field with id 3
        Assertions.assertNotNull(player);
        Assertions.assertTrue(Logic.canMoveToField(player, destinationField));
    }

    @Test
    void notEnoughCarrots() {
        //get the field with id 3 
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field.getID() == 3).findFirst().get();
        Assertions.assertNotNull(destinationField);
        //get the player with the name Player 1
        var player = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();
        //check if the player can move to the field with id 3
        player.setCarrotCards(1);
        Assertions.assertNotNull(player);
        Assertions.assertFalse(Logic.canMoveToField(player, destinationField));
    }

    @Test
    void cantMoveForwardsToHedgehog() {
        var player = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();

        //get the field with id 3 
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field instanceof HedgehogField && field.getID() > player.getFigure().getField().getID()).findFirst().get();
        Assertions.assertNotNull(destinationField);
        //get the player with the name Player 1
        //check if the player can move to the field with id 3
        Assertions.assertNotNull(player);
        Assertions.assertFalse(Logic.canMoveToField(player, destinationField));
    }

    @Test
    void canMoveBackwardsToHedgehog() {
        var player = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();

        //set the playersFied to 50
        player.getFigure().setField(Game.getInstance().getFields().stream().filter(field -> field.getID() == 50).findFirst().get());

        //get the field with id 3 
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field instanceof HedgehogField && field.getID() < player.getFigure().getField().getID()).findFirst().get();
        Assertions.assertNotNull(destinationField);
        //get the player with the name Player 1
        //check if the player can move to the field with id 3
        Assertions.assertNotNull(player);
        Assertions.assertTrue(Logic.canMoveToField(player, destinationField));
    }

    @Test
    void saladFieldWithoutSalad() {
        var player = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();
        //get the field with id 3 
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field instanceof SaladField && field.getID() > player.getFigure().getField().getID()).findFirst().get();
        Assertions.assertNotNull(destinationField);


        player.setSaladCards(0);
        //get the field with id 3 
        Assertions.assertNotNull(destinationField);
        //get the player with the name Player 1
        //check if the player can move to the field with id 3
        Assertions.assertNotNull(player);
        Assertions.assertFalse(Logic.canMoveToField(player, destinationField));
    }

    @Test
    void saladFieldWithSalad() {
        var player = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();

        player.setCarrotCards(50);
        player.setSaladCards(1);
        System.out.println(player.getFigure().getField().getID());
        //get the closest saladField with an ID higher than player1s current id
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field instanceof SaladField && field.getID() > player.getFigure().getField().getID()).min(Comparator.comparingInt(Field::getID)).get();
        Assertions.assertNotNull(destinationField);
        //get the player with the name Player 1
        //check if the player can move to the field with id 3
        Assertions.assertNotNull(player);
        Assertions.assertTrue(Logic.canMoveToField(player, destinationField));

    }

    Field nextCarrotField(Player player) {
        //get the next free carrotField where no player is on
        var carrotField = Game.getInstance().getFields().stream().filter(field -> field.getID() > player.getFigure().getField().getID() && field.getPlayer() == null).findFirst().get();
        return carrotField;
    }

    @Test
    void nextCarrotFullField() {
        var player1 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();
        player1.getFigure().getField().setPlayer(player1);
        //var player 2
        var player2 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 2")).findFirst().get();
        //set the player2 to the next carrotField
        player2.getFigure().setField(Game.getInstance().getFields().stream().filter(field -> field.getID() == 1).findFirst().get());
        player2.getFigure().getField().setPlayer(player2);


        //get the field with id 3 
        var destinationField = nextCarrotField(player1);

        Assertions.assertNotNull(destinationField);
        //get the player1 with the name Player 1
        //check if the player1 can move to the field with id 3
        Assertions.assertNotNull(player1);
        Assertions.assertNotNull(player2);
        Assertions.assertNotEquals(destinationField, player2.getFigure().getField());

    }

    @Test
    void nextCarrotField() {
        var player1 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();
        player1.getFigure().getField().setPlayer(player1);
        //var player 2
        var player2 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 2")).findFirst().get();
        //set the player2 to the next carrotField
        player2.getFigure().setField(Game.getInstance().getFields().stream().filter(field -> field.getID() == 3).findFirst().get());
        player2.getFigure().getField().setPlayer(player2);


        //get the field with id 3 
        var destinationField = nextCarrotField(player1);

        Assertions.assertNotNull(destinationField);
        //get the player1 with the name Player 1
        //check if the player1 can move to the field with id 3
        Assertions.assertNotNull(player1);
        Assertions.assertNotNull(player2);
        Assertions.assertNotEquals(destinationField, player2.getFigure().getField());

    }


    @Test
    void canMoveToFinalPositionFirst() {
        var player1 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();

        //put figure to final field with max ID
        player1.getFigure().setField(Game.getInstance().getFields().stream().filter(field -> field.getID() == Game.getInstance().getFields().stream().mapToInt(Field::getID).max().orElse(1) - 1).findFirst().get());
        player1.getFigure().getField().setPlayer(player1);
        //var player 2
        var player2 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 2")).findFirst().get();
        //set the player2 to the next carrotField
        player2.getFigure().setField(Game.getInstance().getFields().stream().filter(field -> field.getID() == 3).findFirst().get());
        player2.getFigure().getField().setPlayer(player2);


        //get the field with id 62
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field.getID() == Game.getInstance().getFields().stream().mapToInt(Field::getID).max().orElse(1)).findFirst().get();

        Assertions.assertNotNull(destinationField);
        //get the player1 with the name Player 1
        //check if the player1 can move to the field with id 3
        player1.setCarrotCards(3);
        player1.setSaladCards(0);
        Assertions.assertNotNull(player1);
        Assertions.assertNotNull(player2);
        Assertions.assertTrue(Logic.canMoveToField(player1, destinationField));
        player1.setSaladCards(1);
        Assertions.assertFalse(Logic.canMoveToField(player1, destinationField));
        player1.setCarrotCards(13);
        player1.setSaladCards(0);
        Assertions.assertFalse(Logic.canMoveToField(player1, destinationField));

    }

    @Test
    void canMoveToFinalPositionSecond() {
        var player1 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 1")).findFirst().get();
        var destinationField = Game.getInstance().getFields().stream().filter(field -> field.getID() == Game.getInstance().getFields().stream().mapToInt(Field::getID).max().orElse(1)).findFirst().get();

        //put figure to final field with max ID
        player1.getFigure().setField(Game.getInstance().getFields().stream().filter(field -> field.getID() == Game.getInstance().getFields().stream().mapToInt(Field::getID).max().orElse(1) - 1).findFirst().get());
        player1.getFigure().getField().setPlayer(player1);
        //var player 2
        var player2 = Game.getInstance().getPlayers().stream().filter(p -> p.getName().equals("Player 2")).findFirst().get();
        //set the player2 to the next carrotField
        player2.getFigure().setField(destinationField);
        player2.getFigure().getField().setPlayer(player2);


        //get the field with id 62

        Assertions.assertNotNull(destinationField);
        //get the player1 with the name Player 1
        //check if the player1 can move to the field with id 3
        player1.setCarrotCards(3);
        player1.setSaladCards(0);
        Assertions.assertNotNull(player1);
        Assertions.assertNotNull(player2);
        Assertions.assertTrue(Logic.canMoveToField(player1, destinationField));
        player1.setSaladCards(1);
        Assertions.assertFalse(Logic.canMoveToField(player1, destinationField));
        player1.setCarrotCards(13);
        player1.setSaladCards(0);
        Assertions.assertTrue(Logic.canMoveToField(player1, destinationField));
        player1.setCarrotCards(23);
        Assertions.assertFalse(Logic.canMoveToField(player1, destinationField));

    }

}
