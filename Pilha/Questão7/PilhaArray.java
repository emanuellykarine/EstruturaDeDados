package Questão7;

public class PilhaArray implements Pilha{
    private int capacidade;    
    private Object[] array;
    private int t;
    private int FC;

    public PilhaArray(int capacidade, int crescimento) {
        this.capacidade = capacidade;
        t = -1;
        FC = crescimento;
        if (crescimento <= 0) {
            FC = 0;
        }

        array = new Object[capacidade];
    }

    public void push(Object o){
        if (t >= capacidade - 1){
            if (FC == 0) {
                capacidade = capacidade * 2;
            } else {
                capacidade = capacidade + FC;
            }

            Object novoArray[] = new Object[capacidade];
            for (int i = 0; i < array.length; i++){
                novoArray[i] = array[i];
            }

            novoArray = array;
        }

        array[++t] = o;
    }

    public Object pop() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("Pilha vazia");
        }

        Object remover = array[t--];
        return remover;
    }

    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("Pilha vazia");
        }

        return array[t];
    }

    public boolean isEmpty(){
        return t == -1;
    }

    public int size(){
        return t+1;
    }

    public void adicionaPilha(Pilha p){
        int tamanhoP = p.size();
        int capacidade = array.length + tamanhoP;
        Object novoArray[] = new Object[capacidade];

        for (int i = 0; i <= t; i++){
            novoArray[i] = array[i];
        }

        for (int i = 1; i <= tamanhoP; i++){
            novoArray[capacidade - i] = p.pop();
        }

        for (int i = capacidade - tamanhoP; i <= capacidade - 1; i++){
            p.push(novoArray[i]);
        }

        array = novoArray;
        t = capacidade - 1; //Funciona o topo mas não o tamanho de P1
    }
}
