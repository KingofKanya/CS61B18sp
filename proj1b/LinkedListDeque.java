//public class LinkedListDeque<T> {
//    public class Node {
//        private T item;
//        private Node prev;
//        private Node next;
//
//        public Node(Node p, T i, Node n) {
//            prev = p;
//            item = i;
//            next = n;
//        }
//    }
//
//    private int size;
//    private Node first;
//    private Node last;
//
//    public LinkedListDeque() {
//        size = 0;
//        first = new Node(null, null, null);
//        last = new Node(first, null, first);
//        first.prev = last;
//        first.next = last;
//    }
//
//    public void addFirst(T a) {
//        Node m = new Node(first, a, first.next);
//        first.next.prev = m;
//        first.next = m;
//        size++;
//    }
//
//    public void addLast(T a) {
//        Node m = new Node(last.prev, a, last);
//        last.prev.next = m;
//        last.prev = m;
//        size++;
//    }
//
//    public void printDeque() {
//        Node p = first.next;
//        while (p.item != null) {
//            System.out.print(p.item);
//            p = p.next;
//        }
//    }
//
//    public void removeFirst() {
//        first.next.next.prev = first;
//        first.next = first.next.next;
//        size--;
//    }
//
//    public void removeLast() {
//        last.prev.prev.next = last;
//        last.prev = last.prev.prev;
//        size++;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public T get(int index) {
//        if (index < 0 || index >= size) {
//            return null;
//        }
//        int x = -1;
//        Node n = first;
//        while (x < index) {
//            x++;
//            n = n.next;
//        }
//        return n.item;
//    }
//
//    public T getRecursiveHelper(Node n, int index) {
//        if (index == 0) {
//            return n.item;
//        }
//        return getRecursiveHelper(n.next, index - 1);
//    }
//
//    public T getRecursive(int index) {
//        if (index < 0 || index >= size) {
//            return null;
//        }
//        return getRecursiveHelper(first.next, index);
//    }
//}

public class LinkedListDeque<T> implements Deque<T>{
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }

        /* constructor for Node.(especially for sentinel node). */
        public Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T a) {
        Node temp = new Node(sentinel, a, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size++;
    }

    public void addLast(T a) {
        Node temp = new Node(sentinel.prev, a, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item);
            p = p.next;
        }
    }

    /**
     * @return the first item that be moved.
     */
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return res;
    }

    /**
     * @return return the last item be moved
     */
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int x = -1;
        Node n = sentinel;
        while (x < index) {
            x++;
            n = n.next;
        }
        return n.item;
    }

    private T getRecursiveHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}
