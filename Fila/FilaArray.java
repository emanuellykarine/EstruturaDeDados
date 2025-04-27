package Fila;

public class FilaArray implements Fila {
    private int N; //Tamanho do array
    private int i; //Indice do inicio
    private int f; //Indice do proximo
    private Object[] array;
    private int capacidade;

    public FilaArray(int capacidade) {
        this.capacidade = capacidade;

        array = new Object[capacidade];
    }

    // public void enqueue(Object o){
    //     if (size() = N - 1){

    //     } else {
    //         array[f] = o;
    //         f = (f+1) % N;
    //     }
    // }

    public Object dequeue() throws FilaVaziaExcecao{
        if (isEmpty()){
            throw new FilaVaziaExcecao("Fila vazia.");
        }

        Object remover = array[i];
        i = (i + 1) % N;
        return remover;
    }

    public Object first() throws FilaVaziaExcecao{
        if(isEmpty()){
            throw new FilaVaziaExcecao("Fila vazia.");
        }

        return array[i];
    }

    public int size(){
        return (N - i + f) % N;
    }

    public boolean isEmpty(){
        return (i == f);
    }


}
