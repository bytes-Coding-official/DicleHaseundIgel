package logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class LogicTest {


    @Before
    public void setUp() {
        Game.setInstance(null);
        var players = new ArrayList<Player>();
        players.clear();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.BLUE, players.get(0));
        new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.YELLOW, players.get(1));
        Game.getInstance().distributeCards();


    }
    
    @Test
    public void playerCanMoveOnField() {
     
    }

}
