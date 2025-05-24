package FilaComDuasPilhas;

public class PilhaArray implements Pilha{
    private int capacidade;
    private int t;
    private int FC;
    private Object[] array;

    public PilhaArray(int capacidade, int crescimento){
        this.capacidade = capacidade;
        t = -1;
        FC = crescimento;
        if (crescimento <= 0){
            FC = 0;
        }

        array = new Object[capacidade];
    }

    public void push (Object o){
        if (t >= capacidade - 1){
            if (FC == 0){
                capacidade = capacidade * 2;
            } else {
                capacidade = capacidade + FC;
            }

            Object novoArray[] = new Object[capacidade];
            for (int i = 0; i < array.length; i++){
                novoArray[i] = array[i];
            }

            array = novoArray;
        }
        array[++t] = o; 
    }

    public Object pop() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("Pilha vazia.");
        } 
        Object remover = array[t--];
        return remover;
    }

    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("Pilha vazia.");
        }
        return array[t];
    }

    public int size(){
        return t+1;
    }

    public boolean isEmpty(){
        return t == -1;
    }
}
