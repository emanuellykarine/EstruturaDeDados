package Lista.ListaDuplamente;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(No n) throws ListaExcecao;
    public boolean isLast(No n) throws ListaExcecao;
    public No first() throws ListaExcecao;
    public No last() throws ListaExcecao;
    public No before(No n) throws ListaExcecao;
    public No after(No n) throws ListaExcecao;
    public No replaceElement(No n, Object o) throws ListaExcecao;
    public No insertAfter(No n, Object o) throws ListaExcecao;
    public No insertBefore(No on, Object o) throws ListaExcecao;
    public void swapElements(No n, No q) throws ListaExcecao;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void print();
    public Object remove(No n) throws ListaExcecao;
}
