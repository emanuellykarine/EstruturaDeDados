package HeapNo;

public class HeapNo implements Heap{
    private int size;
    private No raiz;
    public No ultimoNo;

    public HeapNo(Object item){
        // Criando raiz, instanciando o heap inicial
        raiz = new No(null, item);
        ultimoNo = raiz;
        size++;
    }

    public void insert(Object o){
        
    }

    // public Object removeMin(){
    //     downHeap();
    //     return remover;
    // }

    // public void downHeap() {
    //     No n = raiz;
    //     No nAnterior = raiz;
    // }

    public void upHeap(No n) {
        while (!isRoot(n) && (int) n.getChave() < (int) n.getPai().getChave()) {
            swapElements(n, n.getPai());
            n = n.getPai();
        }
    }

    public void swapElements(No n, No q){
        Object provisorio = n.getChave();
        n.setChave(q.getChave());
        q.setChave(provisorio);
    }

    public Object min(){
        return raiz.getChave();
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
}
