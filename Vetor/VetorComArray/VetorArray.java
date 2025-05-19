package Vetor.VetorComArray;

public class VetorArray implements Vetor{
    private int capacidade;
    private Object[] array; 
    private int size;

    public VetorArray(int capacidade){
        this.capacidade = capacidade;
        size = 0;
        array = new Object[capacidade];
    }

    public void insertAtRank(int r, Object o) throws ColocacaoErradaExcecao{
        if (r > size) {
            throw new ColocacaoErradaExcecao("Não é possível inserir nessa colocação pois ela ultrapassa o tamanho do vetor.");
        }
        
        if (size == capacidade){
            capacidade = capacidade * 2;
        
            Object novoArray[] = new Object[capacidade];
            for (int i = 0; i < array.length; i++){
                novoArray[i] = array[i];  
            }
            array = novoArray;
        }

        for (int i = size; i > r; i--) {
            array[i] = array[i-1];
        }
        
        array[r] = o;
        size++;
    }

    public Object replaceAtRank(int r, Object o) throws ColocacaoErradaExcecao{
        if (r > size) {
            throw new ColocacaoErradaExcecao("Não tem como substituir uma colocação que não existe.");
        }

        Object antigo = array[r];
        array[r] = o;
        return antigo;
    }

    public Object elemAtRank(int r) throws ColocacaoErradaExcecao{
        return array[r];
    }

    public Object removeAtRank(int r) throws ColocacaoErradaExcecao{
        if (r > size) {
            throw new ColocacaoErradaExcecao("Não é possível remover um objeto dessa colocação pois ela não existe.");
        }

        Object remover = array[r];
        for (int i = r; i <= size - r; i++){
            array[i] = array[i+1];
        }
        size--;
        return remover;
    }
    
    
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void print(){
        for (int i = 0; i < size; i++){
            System.out.print(this.array[i] + " ");
        }
    }
}
