import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffBy1(){
        CharacterComparator c1 = new OffByN(1);
        assertTrue(c1.equalChars('a', 'b'));
        assertTrue(c1.equalChars('b', 'c'));
        assertFalse(c1.equalChars('x', 'x'));
    }

    @Test
    public void testOffBy2(){
        CharacterComparator c2 = new OffByN(2);
        assertTrue(c2.equalChars('a', 'c'));
        assertTrue(c2.equalChars('b', 'd'));
        assertFalse(c2.equalChars('x', 'y'));
    }
}
