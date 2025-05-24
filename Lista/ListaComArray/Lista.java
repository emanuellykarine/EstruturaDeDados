package Lista.ListaComArray;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(Object o) throws ListaVaziaExcecao;
    public boolean isLast(Object o) throws ListaVaziaExcecao;
    public Object first() throws ListaVaziaExcecao;
    public Object last() throws ListaVaziaExcecao;
    public Object before(int n) throws ListaVaziaExcecao, ListaExcecao;
    public Object after(int n) throws ListaVaziaExcecao, ListaExcecao;
    public Object replaceElement(int n, Object o) throws ListaVaziaExcecao, ListaExcecao;
    public void swapElements(int n, int q) throws ListaVaziaExcecao, ListaExcecao;
    public void insertBefore(int n, Object o) throws ListaVaziaExcecao, ListaExcecao;
    public void insertAfter(int n, Object o) throws ListaVaziaExcecao, ListaExcecao;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public Object remove(int n);
    public void print();
}
