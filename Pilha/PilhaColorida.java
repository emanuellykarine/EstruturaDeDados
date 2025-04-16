public interface PilhaColorida {
    public int sizeVermelha(); //Tamanho da pilha    
    public boolean isEmptyVermelha(); //Se a pilha está vazia
    public Object topVermelha() throws PilhaVaziaExcecao; //Retorna o ultimo elemento inserido sem remover
    public void pushVermelha(Object o); //Insere um novo elemento
    public Object popVermelha() throws PilhaVaziaExcecao; //Remove o ultimo elemento inserido e retorna
    public int sizePreta(); //Tamanho da pilha    
    public boolean isEmptyPreta(); //Se a pilha está vazia
    public Object topPreta() throws PilhaVaziaExcecao; //Retorna o ultimo elemento inserido sem remover
    public void pushPreta(Object o); //Insere um novo elemento
    public Object popPreta() throws PilhaVaziaExcecao; //Remove o ultimo elemento inserido e retorna
}
