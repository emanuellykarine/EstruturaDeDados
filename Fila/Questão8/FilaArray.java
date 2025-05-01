package Fila.Questão8;

public class FilaArray implements Fila{
    private int N; 
    private int incremento;
    private int i;
    private int f;
    private Object[] array;

    public FilaArray(int N, int incremento){
        this.N = N;
        this.incremento = incremento;

        array = new Object[N];
    }

    public void enqueue(Object o){
        if (size() == N - 1){
            int novoTamanho;
            if (incremento == 0){
                novoTamanho = N * 2;
            } else {
                novoTamanho = N + incremento;
            }

            Object[] novoArray = new Object[novoTamanho];
            int novoI = i;

            for (int novoF = 0; novoF < size(); novoF++) {
                novoArray[novoF] = array[novoI];
                novoI = (novoI + 1) % N;
            }

            f = size();
            i = 0;
            N = novoTamanho;
            array = novoArray;
        } else {
            array[f] = o;
            f = (f+1) % N;
        }
    }

    public Object dequeue() throws FilaVaziaExcecao{
        if (isEmpty()){
            throw new FilaVaziaExcecao("Fila Vazia.");
        }

        Object remover = array[i];
        i = (i + 1) % N;
        return remover;
    }

    public Object first() throws FilaVaziaExcecao{
        if (isEmpty()){
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
