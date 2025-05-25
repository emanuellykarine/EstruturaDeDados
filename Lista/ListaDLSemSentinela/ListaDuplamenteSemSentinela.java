package Lista.ListaDLSemSentinela;

public class ListaDuplamenteSemSentinela implements Lista{
    private int size;
    private No inicio;
    private No fim;

    //Lista duplamente encadeada sem nó sentinela
    //Guarda elementos a partir do inicio
    public ListaDuplamenteSemSentinela(){
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFirst(No n) throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        return n == inicio;
    }

    public boolean isLast(No n) throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        return n == fim;
    }

    public No first() throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        return inicio;
    }

    public No last() throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        return fim;
    }

    public No after(No n) throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        return n.getProximo();
    }

    public No before(No n) throws ListaExcecao{
        if(isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        return n.getAnterior();
    }

    public No replaceElement(No n, Object o) throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        No temp = n;
        n.setElemento(o);
        return temp;
    }

    public void swapElements(No n, No q) throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        Object temp = n.getElemento();
        n.setElemento(q.getElemento());
        q.setElemento(temp);
    }

    public No insertAfter(No n, Object o) throws ListaExcecao{
        if (isEmpty()) {
            throw new ListaExcecao("Lista vazia.");
        }

        No novoNo = new No(o);
        novoNo.setAnterior(n);
        novoNo.setProximo(n.getProximo());
        n.getProximo().setAnterior(novoNo);
        n.setProximo(novoNo);

        size++;
        return novoNo;
    }

    public No insertBefore(No n, Object o) throws ListaExcecao{
        if (isEmpty()) {
            throw new ListaExcecao("Lista vazia.");
        }

        No novoNo = new No(o);
        novoNo.setProximo(n);
        novoNo.setAnterior(n.getAnterior());
        n.getAnterior().setProximo(novoNo);
        n.setAnterior(novoNo);

        size++;
        return novoNo;

    }

    public void insertFirst(Object o){
        No novoNo = new No(o);

        if (isEmpty()){ //Lista vazia
            inicio = novoNo;
            novoNo.setProximo(null);
            fim = novoNo;
        } else {
            novoNo.setProximo(inicio);
            inicio.setAnterior(novoNo);
            inicio = novoNo;
        }

        size++;
        
    }

    public void insertLast(Object o){
        No novoNo = new No(o);

        if (isEmpty()){ //Lista vazia
            fim = novoNo;
            novoNo.setAnterior(null);
            inicio = novoNo;
        } else {
            novoNo.setAnterior(fim);
            fim.setProximo(novoNo);
            fim = novoNo;
        }

        size++;
    }

    public Object remove(No n) throws ListaExcecao{
        if (isEmpty()){
            throw new ListaExcecao("Lista vazia.");
        }

        Object temp = n.getElemento();

        if (n == inicio){ //Se o nó a remover for o primeiro
            inicio = n.getProximo(); //Agora o primeiro é o próximo
            if (inicio != null){ //Se o novo inicio não for vazia ele pode ainda apontar para o inicio antigo como sendo seu anterior
                inicio.setAnterior(null); //Então o anterior do novo inicio precisa ser null pra nao apontar
            } else {
                fim = null; //Lista vazia
            }
        } else if (n == fim) {
            fim = n.getAnterior();
            if (fim != null) {
                fim.setProximo(null);
            } else {
                inicio = null; //Lista vazia
            }
        } else {
            n.getAnterior().setProximo(n.getProximo());
            n.getProximo().setAnterior(n.getAnterior());
        }

        size--;
        return temp;
    }

    public void print(){
        No atual =  inicio;
        for (int i = 0; i < size(); i++) {
            System.out.print(atual.getElemento() + " ");
            atual = atual.getProximo();
        }

        System.out.println();
    }

}
