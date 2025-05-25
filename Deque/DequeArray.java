package Deque;

public class DequeArray implements Deque{
    private int size; //Tamanho
    private Object[] array;
    private int capacidade;

    public DequeArray(int capacidade){
        this.capacidade = capacidade;
        this.size = 0;

        array = new Object[capacidade];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void insertFirst(Object o){
        if (capacidade == size()) {
            capacidade = capacidade * 2;

            Object novoArray[] = new Object[capacidade];
            for (int i = 0; i < size(); i++){
                novoArray[i] = array[i];
            }

            array = novoArray;
        }
        
        for (int i = size(); i > 0; i--){
            array[i] = array[i - 1];
        }

        array[0] = o;
        size++;
    }

    public void insertLast(Object o){
        if (capacidade == size()) {
            capacidade = capacidade * 2;

            Object novoArray[] = new Object[capacidade];
            for (int i = 0; i < size(); i++){
                novoArray[i] = array[i];
            }

            array = novoArray;
        }

        array[size()] = o;
        size++;
    }

    public Object removeFirst() throws DequeExcecao{
        if (isEmpty()) {
            throw new DequeExcecao("Deque vazio.");
        }

        Object remover = array[0];
        for (int i = 0; i < size() - 1; i++){
            array[i] = array[i+1];
        }

        size--;
        return remover;
    }

    public Object removeLast() throws DequeExcecao{
        if (isEmpty()){
            throw new DequeExcecao("Deque vazio.");
        }

        Object remover = array[size];
        array[size] = null;
        size--;
        return remover;
    }

    public Object first() throws DequeExcecao{
        if (isEmpty()){
            throw new DequeExcecao("Deque vazio.");
        }

        return array[0];
    }

    public Object last() throws DequeExcecao{
        if (isEmpty()){
            throw new DequeExcecao("Deque vazio.");
        }

        return array[size-1];
    }

    public void print(){
        for (int i = 0; i < size(); i++){
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}
