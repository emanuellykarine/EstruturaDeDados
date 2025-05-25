package Lista.ListaDLSemSentinela;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst() throws ListaExcecao;
    public boolean isLast() throws ListaExcecao;
    public No first() throws ListaExcecao;
    public No last() throws ListaExcecao;
    public No after(No n) throws ListaExcecao;
    public No before(No n) throws ListaExcecao;
    public No replaceElement(No n, Object o) throws ListaExcecao;
    public No insertBefore(No n, Object o) throws ListaExcecao;
    public No insertAfter(No n, Object o) throws ListaExcecao;
    public void swapElements(No n, No q) throws ListaExcecao;
    public void insertLast(Object o);
    public void insertFirst(Object o);
    public Object remove(No n) throws ListaExcecao;
    public void print();
}
