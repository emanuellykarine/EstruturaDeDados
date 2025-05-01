package Fila.Questão8;

public interface Pilha {
    public void push(Object o);
    public Object pop() throws PilhaVaziaExcecao;
    public Object top() throws PilhaVaziaExcecao;
    public int size();
    public boolean isEmpty();
}
