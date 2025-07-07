public class HeapArray implements Heap{
    private int capacidade;
    private Object[] array; 
    private int size;

    public HeapArray(int capacidade){
        this.capacidade = capacidade;
        array = new Object[capacidade + 1];
        size = 0;
    }

    public void insert(Object o){
        if (size == capacidade){
            capacidade = capacidade * 2;
        
            Object novoArray[] = new Object[capacidade + 1];
            for (int i = 0; i < array.length; i++){
                novoArray[i] = array[i];  
            }
            array = novoArray;
        }
        
        array[++size] = o;
        upHeap();
    }

    public Object removeMin(){
        Object remover = array[1];
        array[1] = array[size--];

        downHeap();

        return remover;
    }

    public void downHeap() {
    int index = 1;

    while (true) {
        int indexFilhoE = filhoEsquerdo(index);
        int indexFilhoD = filhoDireito(index);

        if (indexFilhoE > size && indexFilhoD > size) {
            break;
        }

        int menorFilho = -1;

        if (indexFilhoE <= size && indexFilhoD <= size) {
            // Ambos os filhos existem
            menorFilho = ((int) array[indexFilhoE] < (int) array[indexFilhoD]) ? indexFilhoE : indexFilhoD;
        } else if (indexFilhoE <= size) {
            menorFilho = indexFilhoE;
        } else {
            menorFilho = indexFilhoD;
        }

        if ((int) array[index] <= (int) array[menorFilho]) {
            break;
        }

        swapElements(index, menorFilho);
        index = menorFilho;
    }
}


    public void upHeap() {
    int index = size;
    while (index > 1) {
        int indexPai = index / 2;

        if ((int)array[index] < (int)array[indexPai]) {
            swapElements(index, indexPai);
            index = indexPai;
        } else {
            break;
        }
    }
}


    public void swapElements(int n, int p){
        Object provisorio = array[n];
        array[n] = array[p];
        array[p] = provisorio;
    }

    public Object min(){
        return array[1];
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
}
