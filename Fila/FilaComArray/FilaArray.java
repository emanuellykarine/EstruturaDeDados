package Fila.FilaComArray;

public class FilaArray implements Fila {
    private int N; //Tamanho do array
    private int i; //Indice do inicio
    private int f; //Indice do proximo
    private Object[] array;

    public FilaArray(int N) {
        this.N = N;
        array = new Object[N];
    }

    public void enqueue(Object o){
        if (size() == N - 1){ //Se tamanho atual for igual a tamanho total
            int novoTamanho; 
            novoTamanho = N * 2; //Duplica o tamanho do array
            
            Object[] novoArray = new Object[novoTamanho]; //Cria o array com o novo tamanho
            int novoI = i; //novo I recebe primeira posição da fila

            for (int novoF = 0; novoF < size(); novoF++) { //Enquanto novoF for menor do que o tamanho atual
                novoArray[novoF] = array[novoI]; //novoArray desde a posição 0 recebe valores do array antigo a partir da primeira posição da fila passada
                novoI = (novoI + 1) % N; //Atualiza valor do novoI, esse calculo serve pro caso da configuração da lista ser diferente
            }

            f = size(); //Final da fila
            i = 0; //Inicio da fila
            N=novoTamanho; //Novo tamanho
            array = novoArray; //Referencia

        } else {
            array[f] = o; //Final da fila recebe elemento novo
            f = (f+1) % N; //Atualiza valor do final da fila
        }
    }

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
        return i == f;
    }


}
