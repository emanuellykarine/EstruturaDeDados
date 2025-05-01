package Fila.Questão3.FilaEncadeada;

public class FilaEncadeada implements Fila{
    private No inicio;
    private No fim;
    private int tamanho;
    
    public FilaEncadeada(){
        tamanho = 0;
    }

    public void enqueue(Object o){ //Insere no final
        No novoNo = new No();
        novoNo.setElemento(o); //Seta elemento no novo nó
        
        if (isEmpty()){
            inicio = fim = novoNo; //Se fila tiver vazia o inicio e o fim aponta para o novo nó
        } else {
            fim.proximo = novoNo; // proximo do fim recebe o novo nó, agora o fim é o novo nó
        }

        fim = novoNo; //fim agora é o novo nó
        tamanho++; //aumenta tamanho
    }

    public Object dequeue() throws FilaVaziaExcecao { //Remover do inicio 
        if (isEmpty()){
            throw new FilaVaziaExcecao("Fila está vazia.");
        }

        Object removerInicio = inicio.getElemento(); //pega elemento do inicio que vai remover
        inicio = inicio.proximo; //inicio recebe o proximo do inicio, por que agora o inicio antigo vai ser removido e o novo inicio é o segundo elemento
        tamanho--; //diminui do tamanho
        return removerInicio; //retorna o antigo inicio
    }

    public Object first() throws FilaVaziaExcecao {
        if (isEmpty()){
            throw new FilaVaziaExcecao("A fila está vazia.");
        }

        return inicio.getElemento();
    } 

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return inicio == null;
    }

    public void printFila() throws FilaVaziaExcecao {
        if (isEmpty()) {
            throw new FilaVaziaExcecao("Fila vazia.");
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
