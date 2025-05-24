package Lista.ListaDuplamente;

public class ListaDuplamenteEncadeada implements Lista{
    private int size;
    private No inicio;
    private No fim;

    public ListaDuplamenteEncadeada(){
        this.inicio = new No(null);
        this.fim = new No(null);
        this.size = 0;
    }

    public No first() throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        return inicio.getProximo();
    }

    public No last() throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        return fim.getAnterior();
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

    public No replaceElement(No n, Object o) throws ListaExcecao{
        if (size() == 0) {
            throw new ListaExcecao("Lista vazia.");
        }

        if (n == inicio || n == fim) {
            throw new ListaExcecao("Nó inválido.");
        }

        No temp = n;
        n.setElemento(o);
        return temp;
    }

    public No insertAfter(No n, Object o) throws ListaExcecao{
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
        return novoNo;
    }

    public No insertBefore(No n, Object o) throws ListaExcecao{
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
        return novoNo;
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

        novoNo.setAnterior(inicio);
        novoNo.setProximo(inicio.getProximo());
        inicio.getProximo().setAnterior(novoNo);
        inicio.setProximo(novoNo);

        size++;
    }

    public void insertLast(Object o){
        No novoNo = new No(o);

        novoNo.setProximo(fim);
        novoNo.setAnterior(fim.getAnterior());
        fim.getAnterior().setProximo(novoNo);
        fim.setAnterior(novoNo);

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
            System.out.println(atual + " ");
            atual = atual.getProximo();
        }
    }
}