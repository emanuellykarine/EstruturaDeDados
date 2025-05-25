package Deque;

public interface Deque {
    public int size();
    public boolean isEmpty();
    public void insertLast(Object o);
    public void insertFirst(Object o);
    public Object removeFirst() throws DequeExcecao;
    public Object removeLast() throws DequeExcecao;
    public Object first() throws DequeExcecao;
    public Object last() throws DequeExcecao; 
    public void print();
}
