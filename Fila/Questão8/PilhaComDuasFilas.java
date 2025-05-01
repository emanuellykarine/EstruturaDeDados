package Fila.Questão8;

import Questão2.PilhaVaziaExcecao;

public class PilhaComDuasFilas implements Pilha{
    private int N;
    private int incremento;
    private FilaArray filaTemp;
    private FilaArray filaPrincipal;


    public PilhaComDuasFilas(int N, int incremento){
        this.N = N;
        this.incremento = incremento;
        this.filaTemp = new FilaArray(N, incremento);
        this.filaPrincipal = new FilaArray(N, incremento);
    }

    public void push(Object o){
        filaTemp.enqueue(o);

        while (!filaPrincipal.isEmpty()){
            filaTemp.enqueue(filaPrincipal.dequeue());
        }

        FilaArray temp = filaPrincipal;
        filaPrincipal = filaTemp;
        filaTemp = temp;
    }

    public Object pop() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("Pilha vazia");
        }

        return filaPrincipal.dequeue();
    }

    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("Pilha vazia");
        }

        return filaPrincipal.first();
    }

    public int size() {
        return filaPrincipal.size();
    }

    public boolean isEmpty(){
        return filaPrincipal.isEmpty();
    }

}

