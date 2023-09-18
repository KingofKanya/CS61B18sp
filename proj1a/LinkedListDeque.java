public class LinkedListDeque<T> {
    public class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Node first;
    private Node last;

    public LinkedListDeque() {
        size = 0;
        first = new Node(null, null, null);
        last = new Node(first, null, first);
        first.prev = last;
        first.next = last;
    }

    public void addFirst(T a) {
        Node m = new Node(first, a, first.next);
        first.next.prev = m;
        first.next = m;
        size++;
    }

    public void addLast(T a) {
        Node m = new Node(last.prev, a, last);
        last.prev.next = m;
        last.prev = m;
        size++;
    }

    public void printDeque() {
        Node p = first.next;
        while (p.item != null) {
            System.out.print(p.item);
            p = p.next;
        }
    }

    public void removeFirst() {
        first.next.next.prev = first;
        first.next = first.next.next;
        size--;
    }

    public void removeLast() {
        last.prev.prev.next = last;
        last.prev = last.prev.prev;
        size++;
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
        Node n = first;
        while (x < index) {
            x++;
            n = n.next;
        }
        return n.item;
    }

    public T getRecursiveHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(first.next, index);
    }
}
