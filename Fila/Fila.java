package Fila;

public interface Fila {
    public int size(); //Tamanho da fila
    public boolean isEmpty(); //Se a fila está vazia
    public void enqueue(Object o); //Insere no fim da fila
    public Object dequeue() throws FilaVaziaExcecao; //Remove e retorna do inicio da fila
    public Object first() throws FilaVaziaExcecao; //Retorna o primeiro da fila
}
