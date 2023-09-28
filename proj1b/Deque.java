public interface Deque<T> {
    public void addFirst(T a);
    public void addLast(T a);
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public boolean isEmpty();
    public int size();
    public T get(int index);
    public T getRecursive(int index);
}
