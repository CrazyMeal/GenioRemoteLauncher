package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KVN on 28/05/2016.
 */
public class GenioInterfaceTest {

    @Test
    public void constructorTest(){
        GenioInterface gi1 = new GenioInterface();
        assertEquals(-1, gi1.getId());
        assertEquals("Default", gi1.getName());
        assertEquals("G0R0C0", gi1.getVersion());

        GenioInterface gi2 = new GenioInterface(80 , "Interface Gescom Rime" , "G5R0C1");
        assertEquals(80, gi2.getId());
        assertEquals("Interface Gescom Rime", gi2.getName());
        assertEquals("G5R0C1", gi2.getVersion());
    }
}
