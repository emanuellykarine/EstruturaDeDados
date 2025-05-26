package Lista.ListaDuplamente;

public class ListaDuplamenteEncadeada implements Lista{
    private int size;
    private No inicio;
    private No fim;

    //Lista duplamente encadeada com nó sentinela
    //Nó sentinela não guarda elementos, é uma referencia para o inicio e fim da lista
    //Só guarda elementos a partir do proximo do inicio
    public ListaDuplamenteEncadeada(){
        this.inicio = new No(null); //Nó sentinela
        this.fim = new No(null); //Nó sentinela
        this.size = 0;
    }

    public No first() throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        return inicio.getProximo(); //Depois do sentinela
    }

    public No last() throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        return fim.getAnterior();//Antes do sentinela
    }

    public No before(No n) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        return n.getAnterior();
    }

    public No after(No n) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        return n.getProximo();
    }

    public Object replaceElement(No n, Object o) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        Object temp = n.getElemento();
        n.setElemento(o);
        return temp;
    }

    public void insertAfter(No n, Object o) throws ListaExcecao{
         if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        No novoNo = new No(o); //Criar novo nó
        novoNo.setAnterior(n); //Anterior do novo nó é o n
        novoNo.setProximo(n.getProximo()); //Proximo do nov nó é o proximo de n
        n.getProximo().setAnterior(novoNo); //Proximo de n que ainda é o antigo nó recebe o novo nó como anterior
        n.setProximo(novoNo);

        size++;
    }

    public void insertBefore(No n, Object o) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        No novoNo = new No(o); //Criar novo nó
        novoNo.setProximo(n); //Proximo do novo é n
        novoNo.setAnterior(n.getAnterior()); //Anterior do novo nó é o anterior de n
        n.getAnterior().setProximo(novoNo); //Proximo do anterior antigo de n aponta para o novo nó
        n.setAnterior(novoNo); //Anterior de n aponta para novo nó

        size++;
    }

    public void swapElements(No n, No q) throws ListaExcecao{
         if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        Object temp = n.getElemento();
        n.setElemento(q.getElemento());
        q.setElemento(temp);
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

    public Object remove(No n) throws ListaExcecao{
         if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        Object temp = n.getElemento();

        n.getAnterior().setProximo(n.getProximo());
        n.getProximo().setAnterior(n.getAnterior());

        n.setAnterior(null);
        n.setProximo(null);

        size--;
        return temp;
    }

    public boolean isLast(No n) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        return n == fim.getAnterior();
    }

    public boolean isFirst(No n) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        return n == inicio.getProximo();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void print() {
        No atual = inicio.getProximo();
        for (int i = 0; i < size(); i++) {
            System.out.print(atual.getElemento() + " ");
            atual = atual.getProximo();
        }
        System.out.println(); // Pula linha ao final
    }
}
