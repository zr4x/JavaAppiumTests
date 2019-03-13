import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        assertEquals(14, MainClass.getLocalNumber(), "Local number return wrong number ");
    }
}
