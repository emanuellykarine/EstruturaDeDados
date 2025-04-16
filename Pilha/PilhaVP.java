public class PilhaVP implements PilhaColorida{
    private int n; //Capacidade total do array
    private Object pilhaVP[];
    private int topoVermelha;
    private int topoPreta;

    public PilhaVP(int capacidadeVermelha, int capacidadePreta){
        this.n = capacidadeVermelha + capacidadePreta + 1;
        topoVermelha = -1; //Vermelha começa do início da pilha
        topoPreta = n - 1; //Preta começa do fim da pilha
        pilhaVP = new Object[n];
    }

    public void pushVermelha(Object o){
        if (topoVermelha + topoPreta >= n/3) { //Se o array estiver com 1/3 de utilização, reduz o tamanho pela metade
            n = n/2;
            Object novoArray[] = new Object[n];
            for (int i = 0; i < topoVermelha; i++){
                novoArray[i] = pilhaVP[i];
            }

            for (int i = n - 1; i > topoPreta; i--){
                novoArray[i] = pilhaVP[i/2];
            }
            pilhaVP = novoArray;
        }

        if (topoVermelha >= topoPreta) {
            n = n * 2;
            Object novoArray[] = new Object[n];
            for (int i = 0; i < topoVermelha; i++){
                novoArray[i] = pilhaVP[i];
            }

            for (int i = n - 1; i > topoPreta; i--){
                novoArray[i] = pilhaVP[i/2];
            }
            pilhaVP = novoArray;
        }
        pilhaVP[++topoVermelha] = o;
    }

    public Object popVermelha()throws PilhaVaziaExcecao{
        if (isEmptyVermelha()) {
            throw new PilhaVaziaExcecao("Pilha vermelha vazia");
        }
        Object remover = pilhaVP[topoVermelha--];
        return remover;
    }

    public Object topVermelha()throws PilhaVaziaExcecao{
        if (isEmptyVermelha()) {
            throw new PilhaVaziaExcecao("A pilha vermelha está vazia.");
        }
        return pilhaVP[topoVermelha];
    }

    public int sizeVermelha(){
        return topoVermelha + 1;
    }

    public boolean isEmptyVermelha(){
        return topoVermelha == -1;
    }

    public void emptyVermelha(){

    }

    public void pushPreta(Object o){
        if (topoVermelha + topoPreta >= n/3) { //Se o array estiver com 1/3 de utilização, reduz o tamanho pela metade
            n = n/2;
            Object novoArray[] = new Object[n];
            for (int i = 0; i < topoVermelha; i++){
                novoArray[i] = pilhaVP[i];
            }

            for (int i = n - 1; i > topoPreta; i--){
                novoArray[i] = pilhaVP[i/2];
            }
            pilhaVP = novoArray;
        }
        
        if (topoPreta <= topoVermelha) {
            n = n * 2;
            Object novoArray[] = new Object[n];
            for (int i = 0; i < topoVermelha; i++){
                novoArray[i] = pilhaVP[i];
            }

            for (int i = n - 1; i > topoPreta; i--){
                novoArray[i] = pilhaVP[i/2];
            }
            pilhaVP = novoArray;
        }
        pilhaVP[--topoPreta] = o;
    }

    public Object popPreta()throws PilhaVaziaExcecao{
        if (isEmptyPreta()) {
            throw new PilhaVaziaExcecao("Pilha preta vazia");
        }
        Object remover = pilhaVP[topoPreta++];
        return remover;
    }

    public Object topPreta()throws PilhaVaziaExcecao{
        if (isEmptyPreta()) {
            throw new PilhaVaziaExcecao("A pilha preta está vazia.");
        }
        return pilhaVP[topoPreta];
    }

    public int sizePreta(){
        return n - topoPreta - 1;
    }

    public boolean isEmptyPreta(){
        return topoPreta == n - 1;
    }

    public void emptyPreta(){

    }

}