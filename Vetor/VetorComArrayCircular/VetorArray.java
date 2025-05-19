package Vetor.VetorComArrayCircular;

public class VetorArray implements Vetor{
    private int i; 
    private int f;
    private int capacidade;
    private Object[] array;

    public VetorArray(int capacidade) {
        this.capacidade = capacidade;
        i = 0;
        f = 0;
        array = new Object[capacidade];
    }

    public void insertAtRank(int r, Object o) {
        if (r > size()){
            throw new ColocacaoErradaExcecao("Não é possível inserir nessa colocação pois ela ultrapassa o tamanho do vetor.");
        }

        if (size() == capacidade - 1) {
            int novoTamanho;
            novoTamanho = capacidade * 2;
            
            Object[] novoArray = new Object[novoTamanho];
            int novoI = i;

            for (int novoF = 0; novoF < size(); novoF++) {
                novoArray[novoF] = array[novoI];
                novoI = (novoI + 1) % capacidade;
            }

            f = size();
            i = 0;
            capacidade = novoTamanho;
            array = novoArray;
        } else {
            
        }

    }
}
