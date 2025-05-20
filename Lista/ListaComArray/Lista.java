package Lista.ListaSimples;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(Object o);
    public boolean isLast(Object o);
    public Object first();
    public Object last();
    public Object before(Object o);
    public Object after(Object o);
    public void replaceElement(int n, Object o);
    public void swapElements(int n, int q);
    public int insertBefore(int n, Object o);
    public int insertAfter(int n, Object o);
    public void insertFirst(Object o);
    public void inserLast(Object o);
    public Object remove(int n);
}
