package HeapNo;

public interface Heap {
    public void insert(Object o);
    public Object removeMin();
    public int size();
    public boolean isEmpty();
    public Object min();
    public int filhoDireito(int i);
    public int filhoEsquerdo(int i);
}

