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

            array = novoArray;
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

    // public void adicionaPilha(Pilha p){
    //     int tamanhoP = p.size();
    //     int capacidade = array.length + tamanhoP;
    //     Object novoArray[] = new Object[capacidade];

    //     for (int i = 0; i <= t; i++){
    //         novoArray[i] = array[i];
    //     }

    //     for (int i = 1; i <= tamanhoP; i++){
    //         novoArray[capacidade - i] = p.pop();
    //     }

    //     for (int i = capacidade - tamanhoP; i <= capacidade - 1; i++){
    //         p.push(novoArray[i]);
    //     }

    //     array = novoArray;
    //     t = capacidade - 1; //Funciona o topo mas não o tamanho de P1
    // }

    public void adicionaPilha(Pilha p){
        int tamanhoP = p.size();
        int novoTamanho = this.size() + p.size();

        // public void addPilha(Pilha p2) {
        //     PilhaArray pilhaAux = new PilhaArray(p2.size(), -1); // pilha vazia para poder inverter a ordem
        //     while(!p2.isEmpty()){ // vai inverter a ordem do array para quando for botar em p1, ele não esteja invertido
        //         try{
        //             pilhaAux.push(p2.pop());
        //         }catch(PilhaVaziaExcecao e) {}
        //     }
    
        //     while(!pilhaAux.isEmpty()){
        //         try{
        //             Object p = pilhaAux.pop();
        //             this.push(p);
        //             p2.push(p); // recolocando na pilha p2 dnv
        //         }catch(PilhaVaziaExcecao e){}
        //     }
        // }
        for (int i = 1; i <= tamanhoP; i++){
            this.push(p.pop());
        }

        for (int i = novoTamanho - tamanhoP; i <= novoTamanho - 1; i++){
            p.push(array[i]);
        }
    }
}
