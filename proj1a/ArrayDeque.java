import java.lang.reflect.Array;

/**
 * Array based list.
 *
 * @author Josh Hug
 */

public class ArrayDeque<T> {
    /**
     * size of the deque
     */
    private int size;
    /**
     * size of the array
     */
    private int length;
    private int front;
    private int last;
    private T[] array;

    public int getLength() {
        return length;
    }

    /**
     * create an empty array deque
     */
    public ArrayDeque() {
        size = 0;
        length = 8;
        front = 4;
        last = 4;
        array = (T[]) new Object[8];
    }

    /**
     * get the previous index
     */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    /**
     * get the next index
     */
    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private void shrink() {
        //System.out.println("####");
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        front = length / 4;
        last = ptr2;
        array = newArray;
        length /= 2;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        front = length;
        last = ptr2;
        array = newArray;
        length *= 2;
    }

    /**
     * add one item at the front of the deque
     *
     * @param x the item we wanted to add
     */
    public void addFirst(T x) {
        if (size == length - 1) {
            grow();
        }
        front = minusOne(front);
        array[front] = x;
        size++;
    }

    /**
     * add one item at the last of the deque
     *
     * @param x the item we wanted to add
     */
    public void addLast(T x) {
        if (size == length - 1) {
            grow();
        }
        array[last] = x;
        last = plusOne(last, length);
        size++;
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public T get(int index) {
        if (index > size) {
            return null;
        }
        int ptr = front + index;
        if (ptr > length) {
            ptr -= length;
        }
        ptr--;
        return array[ptr];
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Deletes item from the end of the deque and
     * returns deleted item.
     */
    public T removeLast() {
        //System.out.println("size:" + size + ",length:" + length);
        if (size >= 16 && length / size >= 4) {
            //System.out.println("###");
            shrink();
        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size--;
        return array[last];
    }

    /**
     * Deletes item from the front of the deque and
     * returns deleted item.
     */
    public T removeFirst() {
        if (size >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T res = array[front];
        front = plusOne(front, length);
        size--;
        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * print the entire deque form the front to the end
     */
    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
} 