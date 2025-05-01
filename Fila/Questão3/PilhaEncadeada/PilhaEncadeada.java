package Fila.Questão3.PilhaEncadeada;

public class PilhaEncadeada implements Pilha{
    private No inicio;
    private int tamanho;

    public PilhaEncadeada(){
        tamanho = 0;
    }

    public void push(Object o) {
        No novoNo = new No();
        novoNo.setElemento(o);

        novoNo.proximo = inicio; // Proximo do novo nó é antigo topo, ou seja, ele recebe o antigo inicio
        inicio = novoNo; //agora o novo topo ou inicio é o novo nó
        tamanho++;
    }

    public Object pop() throws PilhaVaziaExcecao{
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("Pilha está vazia.");
        }

        Object removerTopo = inicio.getElemento(); //Pega elemento do topo
        inicio = inicio.proximo; //Inicio aponta para o segundo elemento 
        tamanho--; //Diminui o tamanho
        return removerTopo; //Retorna o topo antigo que foi removido
    }

    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("PIlha está vazia");
        }

        return inicio.getElemento();
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return inicio == null;
    }

    public void printPilha() throws PilhaVaziaExcecao {
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("Pilha vazia.");
        }
    
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.getElemento());
            if (atual.proximo != null) {
                System.out.print(" -> ");
            }
            atual = atual.proximo;
        }
        System.out.println(); // Pula linha ao final
    }
}
