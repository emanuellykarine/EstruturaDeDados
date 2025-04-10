public class PilhaArray implements Pilha{ //Classe pilha array receber os métodos definidos na interface pilha
    private int capacidade;
    private Object[] array;
    private int t;
    private int FC;

    public PilhaArray(int capacidade, int crescimento) {
        this.capacidade = capacidade; //Capacidade do array recebe capacidade informada
        t =-1; //Indice
        FC = crescimento; //Fator de crescimento pra ver se vai ser incremento ou duplicação
        if (crescimento <= 0){ //Se o cresimento for menor ou igual a zero, vai ser utilizado duplicação
            FC = 0;
        }

        array = new Object[capacidade];
    }

    public void push(Object o){
        if (t >= capacidade - 1) { //Se o indice do array atingir a capacidade
            if (FC == 0) { //Veja se o fator de crescimento é 0
                capacidade = capacidade * 2; //Se for 0 usa o método da duplicação
            } else {
                capacidade = capacidade + FC; //Se não for zero então usa o método do incremento utilizando o fator de crescimento
            }
            Object novoArray[] = new Object[capacidade]; //Cria um novo array com a nova capacidade
            for (int i = 0; i < array.length; i++){ //Passa todos os elementos do array antigo pro array novo
                novoArray[i] = array[i];
            }
            array = novoArray; //Array antigo agora recebe array novo (referência)
        }
        array[++t] = o; //Posição livre recebe o novo elemento (Primeiro aumenta a posição do t e depois atribui o valor)
    }

    public Object pop()throws PilhaVaziaExcecao{
        if (isEmpty()){ //Checa se a pilha está vazia
            throw new PilhaVaziaExcecao("A pilha está vazia");
        }
        Object remover = array[t--]; //Remove o ultimo elemento (Primeiro atribui o valor de array[t] pra remover e depois diminui o valor de t)
        return remover; //Retorna o elemento que foi removido
    }

    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()){
            throw new PilhaVaziaExcecao("A pilha está vazia.");
        }
        return array[t]; //Retorna o ultimo elemento 
    }

    public boolean isEmpty(){
        return t == -1; //Retorna se t é igual a -1
    }

    public int size(){
        return t+1;
    }

    public void empty(){
        Object arrayVazio[] = new Object[capacidade]; 
        array = arrayVazio;
    }
}
