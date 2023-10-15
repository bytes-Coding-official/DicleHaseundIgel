package logic;

import logic.fields.Field;
import logic.fields.HedgehogField;
import logic.fields.RabbitField;
import logic.fields.SaladField;

public class Logic {

    public static boolean isReachable(Player player, Field destinationfield) {
        var currentfield = player.getFigure().getField();
        System.out.println(currentfield.getID());
        //check fi the index is higher than destination and the destinationField is a HedgehogField
        if (currentfield.getID() >= destinationfield.getID())
            return destinationfield instanceof HedgehogField;
        else if (destinationfield instanceof HedgehogField)
            return false;


        var distance = destinationfield.getID() - currentfield.getID();

        //gausische summe
        var sum = (distance * (distance + 1)) / 2;

        return sum <= player.getCarrotCards();
    }

    public static boolean canMoveToField(Player player, Field destinationField) {

        if (!isReachable(player, destinationField)) {
            return false;
        }

        if (destinationField.getPlayer() != null && destinationField instanceof RabbitField rabbitField && !rabbitField.isEndField()) {
            return false;
        }


        //carrotcheck: if player is first in endField only 10 or less carrots allowed if 2nd than 20 if third than 30 etc.
        if (destinationField instanceof RabbitField rabbitField && rabbitField.isEndField() && player.getCarrotCards() > 10 * (Game.getInstance().getPlayers().stream().filter(p -> p.getFigure().getField() == destinationField).count() + 1))
            return false;

        if (destinationField instanceof RabbitField rabbitField && rabbitField.isEndField() && player.getSaladCards() > 0)
            return false;

        if (destinationField instanceof SaladField && player.getSaladCards() <= 0) return false;
        //get max field id

        var maxFieldID = Game.getInstance().getFields().stream().mapToInt(Field::getID).max().orElse(0);

        //check if destinationID =  maxID and if so is the amount of SaladCards > 0 than return false
        return destinationField.getID() != maxFieldID || player.getSaladCards() <= 0;
    }

    public static Field moveToNextHedgehogField(Player player) {
        //go through all Fields in the game where the ID is lower than the players current field
        //and the field is a HedgehogField and the field is not occupied by another player return that field
        //if it is return field with ID 0
        var hedgehogField = Game.getInstance().getFields().stream().filter(field -> field.getID() < player.getFigure().getField().getID() && field instanceof HedgehogField && field.getPlayer() == null).findFirst().orElse(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get());

        return hedgehogField;
    }
}
