package HeapNo;

public class HeapNo implements Heap{
    private int size;
    private No raiz;
    public No ultimoNo;

    public HeapNo(Object valor){
        // Criando raiz, instanciando o heap inicial
        raiz = new No(null, valor);
        ultimoNo = raiz;
        size++;
    }

    public void insert(Object o){
        No novoNo = new No(null, o);

        if (raiz == null){
            raiz = novoNo;
            ultimoNo = novoNo;
            size = 1;
            return;
        }

        No pai = pai();
        novoNo.setPai(pai);
        if (pai.getEsquerdo() == null) {
            pai.setEsquerdo(novoNo);
        } else {
            pai.setDireito(novoNo);
        }
        size++;
        ultimoNo = novoNo;
        upHeap(novoNo);
    }

    public No pai(){
        if (ultimoNo == raiz){
            return raiz;
        }
        if (ultimoNo.getPai().getDireito() == null){
            return ultimoNo.getPai();
        }

        No atual = ultimoNo;

        while (!isRoot(atual) && atual == atual.getPai().getDireito()){
            atual = atual.getPai();
        }

        if (!isRoot(atual)){
            atual = atual.getPai().getDireito();
        } else {
            atual = raiz;
        }

        while (!isExternal(atual)) {
            atual = atual.getEsquerdo();
        }

        return atual;
    }

    public Object removeMin(){
        if (raiz == null) return null;

        Object remover = raiz.getValor();

        if (size == 1) {
            raiz = null;
            ultimoNo = null;
            size = 0;
            return remover;
        }

        raiz.setValor(ultimoNo.getValor());

        No pai = ultimoNo.getPai();
        if (pai.getDireito() == ultimoNo) {
            ultimoNo = pai.getEsquerdo();
            pai.setDireito(null);
        } else {
            ultimoNo = ultimo();
            pai.setEsquerdo(null);
        }

        size--;
        downHeap(root());

        return remover;
    }

    public No ultimo(){
        No atual = ultimoNo;
        
        if (isRoot(atual)){
            return atual;
        }

        while (!isRoot(atual)) {
            atual = atual.getPai();
        }

        if (atual.getDireito() != null && isExternal(atual.getDireito())){
            return atual.getDireito();
        }

        atual = atual.getEsquerdo();

        while (atual.getDireito() != null) {
            atual = atual.getDireito();
        }
        
        return atual;
    }

    public void downHeap(No n) {
        No filhoE = n.getEsquerdo();
        No filhoD = n.getDireito();

        if (filhoE == null && filhoD == null) {
            return;
        }

        No menor = null;

        if (filhoE != null && filhoD != null){
            menor = ((int) filhoE.getValor() < (int) filhoD.getValor() ? filhoE : filhoD);
        } else if (filhoE != null) {
            menor = filhoE;
        } else {
            menor = filhoD;
        }

        if ((int) n.getValor() <= (int) menor.getValor()){
            return;
        }

        swapElements(n, menor);
        downHeap(menor);
    }

    public void upHeap(No n) {
        while (!isRoot(n) && (int) n.getValor() < (int) n.getPai().getValor()) {
            swapElements(n, n.getPai());
            n = n.getPai();
        }
    }

    public void swapElements(No n, No q){
        Object provisorio = n.getValor();
        n.setValor(q.getValor());
        q.setValor(provisorio);
    }

    public Object min(){
        return raiz.getValor();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int filhoDireito(int i){
        return 2 * i + 1;
    }

    public int filhoEsquerdo(int i){
        return 2 * i;
    }

    public No root() {
        return raiz;
    }

    private boolean isRoot(No n){
        return n.equals(raiz);
    }

    private No leftChild(No n) {
        return n.getEsquerdo();
    }

    private No rightChild(No n){
        return n.getDireito();
    }

    private boolean hasLeft(No n){
        return leftChild(n) != null;
    }

    private boolean hasRight(No n){
        return rightChild(n) != null;
    }

    private boolean isExternal(No n){
        return !hasLeft(n) && !hasRight(n);
    }
}
