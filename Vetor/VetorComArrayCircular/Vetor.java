package Vetor.VetorComArrayCircular;

public interface Vetor {
    public void insertAtRank(int r, Object o) throws ColocacaoErradaExcecao;
    public Object removeAtRank(int r) throws ColocacaoErradaExcecao;
    public Object replaceAtRank(int r, Object o) throws ColocacaoErradaExcecao;
    public Object elemAtRank(int r) throws ColocacaoErradaExcecao;
    public int size();
    public boolean isEmpty();
    public void print();
}
