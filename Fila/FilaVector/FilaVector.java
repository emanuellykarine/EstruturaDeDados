package Fila.FilaVector;

import java.util.Vector;

public class FilaVector implements Fila{
    Vector vetor = new Vector<>();

    public FilaVector(){};

    public void enqueue(Object o){
        vetor.add(o);
    }

    public Object dequeue() throws FilaVaziaExcecao{
        if (isEmpty()) {
            throw new FilaVaziaExcecao("Fila está vazia.");
        }

        Object remover = vetor.firstElement();
        vetor.remove(vetor.firstElement());
        return remover;
    }

    public Object first() throws FilaVaziaExcecao{
        if (isEmpty()) {
            throw new FilaVaziaExcecao("Fila está vazia.");
        }

        return vetor.firstElement();
    }

    public int size(){
        return vetor.size();
    }

    public boolean isEmpty(){
        return vetor.isEmpty();
    }
}
