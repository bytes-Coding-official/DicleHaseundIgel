package logic;

import org.junit.Test;

public class LogicTest {

    @Test
    public void simpleTest() {
        Logic logic = new Logic();
        assertEquals(1, logic.getOne());
    }
}
