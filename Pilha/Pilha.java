// Conjunto de métodos abstratos que definem o comportamento de uma classe
// O TAD Pilha possui esses métodos

public interface Pilha {    
    public int size(); //Tamanho da pilha    
    public boolean isEmpty(); //Se a pilha está vazia
    public Object top() throws PilhaVaziaExcecao; //Retorna o ultimo elemento inserido sem remover
    public void push(Object o); //Insere um novo elemento
    public Object pop() throws PilhaVaziaExcecao; //Remove o ultimo elemento inserido e retorna
    // public void empty(); //Esvaziar pilha
}
