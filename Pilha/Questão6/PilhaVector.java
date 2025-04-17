package Questão6;
import java.util.Vector;

import Questão2.Pilha;
import Questão2.PilhaVaziaExcecao;

public class PilhaVector implements Pilha {
    Vector vetor = new Vector();
    
    public PilhaVector(){}

    public void push(Object o){
        vetor.add(o);
    }

    public Object pop() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("A pilha está vazia");
        }

        Object remover = vetor.lastElement();
        vetor.remove(vetor.size() - 1);
        return remover; 
    }

    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("A pilha está vazia");
        }

        return vetor.lastElement();
    }

    public boolean isEmpty() {
        return vetor.isEmpty();
    }

    public int size(){
        return vetor.size();
    }

    public void empty(){

    }
}
