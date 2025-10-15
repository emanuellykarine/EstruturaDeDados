package Sequencia;

public class SequenciaListaDL {
    private int size;
    private No inicio;
    private No fim;

    public SequenciaListaDL(){
        this.size = 0;
        this.inicio = new No(null);
        this.fim = new No(null);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void print(){
        No atual = inicio.getProximo();
        for (int i = 0; i < size(); i++){
            System.out.print(atual.getElemento() + " ");
            atual = atual.getProximo();
        }

        System.out.println();
    }

    public Object elemAtRank(int n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        No atual = inicio.getProximo();
        for (int i = 0; i < n; i++){
            atual = atual.getProximo();
        }

        return atual.getElemento();
    }

    //Insere naquela posição e empurra os outros pra frente
    public void insertAtRank(int n, Object o) throws SequenciaExcecao{
        if (n > size()){
            throw new SequenciaExcecao("Rank inválido.");
        }

        No novoNo = new No(o);
        No atual = inicio;
        for (int i = 0; i <= n; i++){
            atual = atual.getProximo();
        }

        novoNo.setProximo(atual);
        novoNo.setAnterior(atual.getAnterior());
        atual.getAnterior().setProximo(novoNo);
        atual.setAnterior(novoNo);

        size++;
    }

    public Object replaceAtRank(int n, Object o) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        if (n > size()){
            throw new SequenciaExcecao("Rank inválido.");
        }

        No atual = inicio.getProximo();
        for (int i = 0; i < n; i++){
            atual = atual.getProximo();
        }

        Object temp = atual.getElemento();
        atual.setElemento(o);
        return temp;
    }

    public Object removeAtRank(int n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        if (n > size()){
            throw new SequenciaExcecao("Rank inválido.");
        }

        No atual = inicio.getProximo();
        for (int i = 0; i < n; i++){
            atual = atual.getProximo();
        }

        Object temp = atual.getElemento();
        atual.getProximo().setAnterior(atual.getAnterior());
        atual.getAnterior().setProximo(atual.getProximo());

        size--;
        return temp;
    }

    public boolean isFirst(No n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        return n == inicio.getProximo();
    }

    public boolean isLast(No n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        return n == fim.getAnterior();
    }

    public No first() throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }
    
        return inicio.getProximo();
    }

    public No last() throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        return fim.getAnterior();
    }

    public No before(No n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        return n.getAnterior();
    }

    public No after(No n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        return n.getProximo();
    }

    public No replaceElement(No n, Object o) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        n.setElemento(o);
        return n;
    }


    public void insertBefore(No n, Object o) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        No novoNo = new No(o);
        novoNo.setProximo(n);
        novoNo.setAnterior(n.getAnterior());
        n.getAnterior().setProximo(novoNo);
        n.setAnterior(novoNo);

        size++;
    }

    public void insertAfter(No n, Object o) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        No novoNo = new No(o);
        novoNo.setAnterior(n);
        novoNo.setProximo(n.getProximo());
        n.getProximo().setAnterior(novoNo);
        n.setProximo(novoNo);

        size++;
    }

    public void insertFirst(Object o){
        No novoNo = new No(o);

        if (inicio.getProximo() == null) { //Lista vazia
            inicio.setProximo(novoNo);
            fim.setAnterior(novoNo);
            novoNo.setAnterior(inicio);
            novoNo.setProximo(fim);
        } else {
            novoNo.setAnterior(inicio);
            novoNo.setProximo(inicio.getProximo());
            inicio.getProximo().setAnterior(novoNo);
            inicio.setProximo(novoNo);
        }

        size++;
    }

    public void insertLast(Object o){
        No novoNo = new No(o);

        if (fim.getAnterior() == null) { //Lista vazia
            fim.setAnterior(novoNo);
            inicio.setProximo(novoNo);
            novoNo.setAnterior(inicio);
            novoNo.setProximo(fim);
        } else {
           novoNo.setProximo(fim);
            novoNo.setAnterior(fim.getAnterior());
            fim.getAnterior().setProximo(novoNo);
            fim.setAnterior(novoNo); 
        }

        size++;
    }

    public void swapElements(No n, No q) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        Object temp = n.getElemento();
        n.setElemento(q.getElemento());
        q.setElemento(temp);
    }

    public Object remove(No n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        Object temp = n.getElemento();

        n.getAnterior().setProximo(n.getProximo());
        n.getProximo().setAnterior(n.getAnterior());

        n.setAnterior(null);
        n.setProximo(null);

        size--;
        return temp;
    }

    public No atRank(int n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        No atual;
        if (n <= size()/2) {
            atual = inicio.getProximo();
            for (int i = 0; i < n; i++){
                atual = atual.getProximo();
            }
        } else {
            atual = fim.getAnterior();
            for (int i = 0; i < size()-n-1; i++){
                atual = atual.getAnterior();
            }
        }
        return atual;  
    }

    public int rankOf(No n) throws SequenciaExcecao{
        if (isEmpty()){
            throw new SequenciaExcecao("Sequencia vazia.");
        }

        No atual = inicio.getProximo();
        int rank = 0;

        while (atual != n && atual != fim){
            atual = atual.getProximo();
            rank++;
        }

        return rank;
    }
}
