package Sequencia;

public interface Sequencia{

    //Métodos genéricos
    public boolean isEmpty();
    public int size();
    public void print();

    //Métodos de vetor
    public void insertAtRank(int n, Object o) throws SequenciaExcecao;
    public Object removeAtRank(int n) throws SequenciaExcecao;
    public Object elemAtRank(int n) throws SequenciaExcecao;
    public Object replaceAtRank(int n, Object o) throws SequenciaExcecao;

    //Métodos de fila
    public boolean isFirst(No n) throws SequenciaExcecao;
    public boolean isLast(No n) throws SequenciaExcecao;
    public No first() throws SequenciaExcecao;
    public No last() throws SequenciaExcecao;
    public No before() throws SequenciaExcecao;
    public No after() throws SequenciaExcecao;
    public No replaceElement(No n, Object o) throws SequenciaExcecao;
    public void insertAfter(No n, Object o) throws SequenciaExcecao;
    public void insertBefore(No n, Object o) throws SequenciaExcecao;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void swapElements(No n, No q) throws SequenciaExcecao;
    public Object remove(No n) throws SequenciaExcecao;

    //Métodos de sequência
    public No atRank(int n) throws SequenciaExcecao;
    public int rankOf(No n) throws SequenciaExcecao;
}