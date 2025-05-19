package Vetor.Pratica1;

public class VetorArray implements Vetor{
    private int capacidade;
    private Object[] array; 
    private int t; //indice 
    public VetorArray(int capacidade){
        this.capacidade = capacidade;
        t = -1;
        array = new Object[capacidade];
    }

    public void insertAtRank(int r, Object o){
        if (r >= capacidade - 1){
            capacidade = capacidade * 2;
        
            Object novoArray[] = new Object[capacidade];
            for (int i = 0; i < array.length; i++){
                novoArray[i] = array[i];  
            }
            array = novoArray;
        }

        if (r > t/2){

        }
        array[r] = o;
        //se a posição nao existir joga excecao
    }
    // public Object elemAtRank(int r) throws ColocacaoErradaExcecao{

    // }
    
    
    // public boolean isEmpty(){
        
    // }
}
