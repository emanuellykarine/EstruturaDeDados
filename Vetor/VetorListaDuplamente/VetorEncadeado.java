package Vetor.VetorListaDuplamente;

public class VetorEncadeado implements Vetor{
    private No inicio;
    private No fim;
    private int size;
    
    public VetorEncadeado (){
        inicio = new No(null);
        fim = new No(null);
        inicio.setProximo(fim);
        fim.setAnterior(inicio);
        size = 0;
    }

    public void insertAtRank(int r, Object o){
        if (r > size()){
            throw new ColocacaoErradaExcecao("Rank não maior que o tamanho do vetor.");
        }

        int cont = 0;
        No atual;
        No novoNo = new No(o);

        if (r == size){
            atual = fim;
        } else {
            atual = inicio.getProximo();
            while (cont != r){
                atual = atual.getProximo();
                cont++;
            }
        }
        novoNo.setProximo(atual);
        novoNo.setAnterior(atual.getAnterior());
        atual.getAnterior().setProximo(novoNo);
        atual.setAnterior(novoNo);
        size++;

    }

    public Object elemAtRank(int r){
        if (r > size()){
            throw new ColocacaoErradaExcecao("Rank maior que o tamanho do vetor.");
        }

        int cont = 0;
        No atual = inicio.getProximo();

        while (cont != r){
            atual = atual.getProximo();
            cont++;
        }

        return atual.getElemento();
    }

    public Object removeAtRank(int r){
        if (r > size()){
            throw new ColocacaoErradaExcecao("Rank maior que o tamanho do vetor.");
        }

        int cont = 0;
        No atual;

        if (r == size - 1){
            atual = fim.getAnterior();
        } else {
            atual = inicio.getProximo();
            while (r != cont){
                atual = atual.getProximo();
            }
        }

        Object remover = atual.getElemento();
        atual.getAnterior().setProximo(atual.getProximo());
        atual.getProximo().setAnterior(atual.getAnterior());

        size--;

        return remover;
    }

    public Object replaceAtRank(int r, Object o){
        if (r > size()){
            throw new ColocacaoErradaExcecao("Rank maior que o tamanho do vetor.");
        }

        No atual = inicio.getProximo();
        int cont = 0;
        
        while (cont != r){
            atual = atual.getProximo();
        }

        Object antigo = atual.getElemento();
        atual.setElemento(o);
        return antigo;

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void print(){
        No atual = inicio.getProximo();
        while (atual != null) {
            System.out.print(atual.getElemento());
            if (atual.getProximo() != null) {
                System.out.print(" -> ");
            }
            atual = atual.getProximo();
        }
        System.out.println(); // Pula linha ao final
    }
}