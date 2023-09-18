import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ArrayDequeTest {
    @Test
    public void testaddsizeempty() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        assertEquals(true, dq.isEmpty());

        dq.addFirst("first");
        assertEquals(1, dq.size());

        dq.addLast("middle");
        assertEquals(2, dq.size());

        dq.addLast("last");
        assertEquals(3, dq.size());

        dq.printDeque();

        String first = dq.removeFirst();
        assertEquals("first", first);

        String last = dq.removeLast();
        assertEquals("last", last);

        assertEquals(1, dq.size());

        dq.removeFirst();
        assertTrue(dq.isEmpty());
    }

    @Test
    public void testgrowshrink() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < 7; i++) {
            dq.addLast(i);
        }
        assertEquals(8, dq.getLength());
        dq.addLast(7);
        assertEquals(16, dq.getLength());
        for (int i = 0; i < 3; i++) {
            dq.addLast(i);
        }
        assertEquals(16, dq.getLength());
        for (int i = 0; i < 15; i++) {
            dq.addLast(i);
        }
        assertEquals(32, dq.getLength());

        for (int i = 0; i < 17; i++) {
            dq.removeFirst();
        }
        assertEquals(32, dq.getLength());
        dq.removeFirst();
        System.out.println("size:" + dq.size() + ",length:" + dq.getLength());
        assertEquals(32, dq.getLength());

        for (int i = 0; i < 64; i++) {
            dq.addLast(i);
        }
        assertEquals(128, dq.getLength());

        for (int i = 0; i < 40; i++) {
            dq.removeLast();
        }
        System.out.println("size:" + dq.size() + ",length:" + dq.getLength() + ",length / size:" + dq.getLength() / dq.size());
        dq.removeLast();
        System.out.println("size:" + dq.size() + ",length:" + dq.getLength() + ",length / size:" + dq.getLength() / dq.size());
        assertEquals(64, dq.getLength());

    }

    @Test
    public void testisEmpty() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertTrue(arrayDeque.isEmpty());
    }
}
