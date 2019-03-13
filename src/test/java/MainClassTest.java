import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClassTest {

    //Home Work #1
    @Test
    void testGetLocalNumber(){
        assertEquals(14, MainClass.getLocalNumber(), "Local number return wrong number ");
    }

    //Home Work #2
    @Test
    void testGetClassNumber() {
        assertTrue(new MainClass().getClassNumber() > 45, "getClassNumber return number less than 45");
    }

    //Home Work #3
    @Test
    void testGetClassString() {
        String text = new MainClass().getClassString();
        assertTrue(text.contains("hello") || text.contains("Hello"), "class_string doesn't contains «hello» or «Hello»");
    }
}
