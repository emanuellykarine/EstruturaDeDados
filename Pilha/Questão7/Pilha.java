package Questão7;

public interface Pilha {
    public void push(Object o);
    public int size();
    public Object top() throws PilhaVaziaExcecao;
    public Object pop() throws PilhaVaziaExcecao;
    public boolean isEmpty();
    public void adicionaPilha(Pilha p);
}
