package PilhaEncadeada;

public interface Pilha {
    public void push(Object o);
    public Object top() throws PilhaVaziaExcecao;
    public Object pop() throws PilhaVaziaExcecao;
    public int size();
    public boolean isEmpty();
}
