import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class TestArrayDequeGold {
    @Test
    public void task1(){
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        assertEquals(ads.isEmpty(), sad.isEmpty());
        assertEquals(ads.size(), sad.size());

        ads.addFirst(1);
        sad.addFirst(1);

        ads.addFirst(2);
        sad.addFirst(2);

        ads.addLast(3);
        sad.addLast(3);

        assertEquals(ads.isEmpty(), sad.isEmpty());
        assertEquals(ads.size(), sad.size());

        assertEquals(ads.removeLast(), sad.removeLast());
        assertEquals(ads.removeFirst(), sad.removeFirst());
        assertEquals(ads.removeFirst(), sad.removeFirst());

        for (int i = 0; i < 100; i++) {
            int n = StdRandom.uniform(1, 100);
            int t = StdRandom.uniform(1,3);
            if(t == 1){
                ads.addFirst(n);
                sad.addFirst(n);
            }else{
                ads.addLast(n);
                sad.addLast(n);
            }
            assertEquals(ads.isEmpty(), sad.isEmpty());
            assertEquals(ads.size(), sad.size());
        }

        for (int i = 0; i < 100; i++) {
            int t = StdRandom.uniform(1,3);
            if(t == 1){
                assertEquals(ads.removeFirst(), sad.removeFirst());
            }else{
                int correct = ads.removeLast();
                int student = sad.removeLast();
                assertEquals("RemoveLast(), student was " + student + ", correct was " + correct
                        ,correct, student);
            }
            assertEquals(ads.isEmpty(), sad.isEmpty());
            assertEquals(ads.size(), sad.size());
        }
    }
}
