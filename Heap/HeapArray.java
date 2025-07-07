public class HeapArray implements Heap{
    private int capacidade;
    private Object[] array; 
    private int size;

    public HeapArray(int capacidade){
        this.capacidade = capacidade;
        array = new Object[capacidade];
        size = 0;
    }

    public void insert(Object o){
        if (size == capacidade){
            capacidade = capacidade * 2;
        
            Object novoArray[] = new Object[capacidade];
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

    public void downHeap(){
        int index = 1;
        int indexFilhoD = filhoDireito(index);
        int indexFilhoE = filhoEsquerdo(index);
        while ((int)array[index] > (int)array[indexFilhoE] || (int)array[index] > (int)array[indexFilhoD]){
            if (array[indexFilhoE] == null && array[indexFilhoD] == null){
                break;
            }

            if (array[indexFilhoE] != null && array[indexFilhoD] != null){
                if (((int)array[index] - (int)array[indexFilhoE]) > ((int)array[index] - (int)array[indexFilhoD])){
                    swapElements(indexFilhoE, index);
                    index = indexFilhoE;
                } else {
                    swapElements(indexFilhoD, index);
                    index = indexFilhoD;
                }
            } else {
                swapElements(indexFilhoE, index);
            }
            indexFilhoD = filhoDireito(index);
            indexFilhoE = filhoEsquerdo(index);
        }
    }

    public void upHeap(){
        int index = size;
        int indexPai = index / 2;

        while ((int)array[index] < (int)array[indexPai] && index != 1){
            swapElements(index, indexPai);
            index = indexPai;
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
