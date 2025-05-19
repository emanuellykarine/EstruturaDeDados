package Vetor.VetorComArray;

public interface Vetor {
    public Object elemAtRank(int r) throws ColocacaoErradaExcecao; //Retorna o elementro na colocação r
    public void insertAtRank(int r, Object o) throws ColocacaoErradaExcecao; //Insere um novo elemento na colocação r
    public Object replaceAtRank(int r, Object o) throws ColocacaoErradaExcecao; // Substitui o elemento na colocação r por o e retorna o antigo elemento
    public Object removeAtRank(int r) throws ColocacaoErradaExcecao; //Remove e retorna o elemento na colocação r
    public int size();
    public boolean isEmpty();
    public void print();
}