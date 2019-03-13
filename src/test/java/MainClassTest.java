import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClassTest {

    @Test
    void testGetLocalNumber(){
        assertEquals(14, MainClass.getLocalNumber(), "Local number return wrong number ");
    }

    @Test
    void testGetClassNumber() {
        assertTrue(new MainClass().getClassNumber() > 45, "getClassNumber return number less than 45");
    }
}
