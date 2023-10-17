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

        StringBuilder log = new StringBuilder();

        for (int i = 0; i < 150; i++) {
            int n = StdRandom.uniform(1, 100);
            int t = StdRandom.uniform(2);
            if(t == 1){
                log.append("addFirst(").append(n).append(")\n");
                ads.addFirst(n);
                sad.addFirst(n);
            }else{
                log.append("addLast(").append(n).append(")\n");
                ads.addLast(n);
                sad.addLast(n);
            }
            assertEquals(ads.isEmpty(), sad.isEmpty());
            assertEquals(ads.size(), sad.size());
        }

        int correct;
        int student;
        for (int i = 0; i < 100; i++) {
            int t = StdRandom.uniform(2);
            if(t == 1){
                correct = ads.removeLast();
                student = sad.removeLast();
                log.append("removeFirst()\n");
            }else{
                correct = ads.removeLast();
                student = sad.removeLast();
                log.append("removeLast()\n");
            }
            assertEquals(log.toString(), correct, student);
        }
    }
}
