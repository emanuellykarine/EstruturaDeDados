package Lista.ListaComArray;

public class ListaArray implements Lista{
    private int capacidade;
    private Object[] array;
    private int size;

    public ListaArray(int capacidade){
        size = 0;
        this.capacidade = capacidade;
        array = new Object[capacidade];
    }

    public void insertFirst(Object o){
        if (size == capacidade){ //Se chegar no limite da lista
            capacidade = capacidade * 2;

            Object[] novoArray = new Object[capacidade];
            for (int i = 0; i < size(); i++) {
                novoArray[i] = array[i];
            }
            array = novoArray;
        }

        if (size > 0){
            for (int i = size; i > 0; i++){
                array[i] = array[i-1];
            }

            array[0] = o;
            size++;
        } else {
            array[0] = o;
            size++;
        }
    }

    public void insertLast(Object o){
        if (size == capacidade) {
            capacidade = capacidade * 2;
            Object[] novoArray = new Object[capacidade];

            for (int i = 0; i < size(); i ++) {
                novoArray[i] = array[i];
            }

            array = novoArray;
        }

        array[size] = o;
        size++;
    }

    public void insertAfter(int n, Object o) throws ListaVaziaExcecao, ListaExcecao{
        if (size == 0){
            throw new ListaVaziaExcecao("A lista não possui elementos.");
        }

        if (n > size){
            throw new ListaExcecao("Posição maior que o tamanho da lista.");
        }
        
        if (size == capacidade) {
            capacidade = capacidade * 2;
            Object[] novoArray = new Object[capacidade];

            for (int i = 0; i < size(); i++) {
                novoArray[i] = array[i];
            }

            array = novoArray;
        }

        if (n == size) {
            array[++size] = o;
        } else {
            for (int i = size; i > n + 1; i--) {
                array[i] = array[i-1];
            }

            array[n+1] = o;
            size++;
        }
    }

    public void insertBefore(int n, Object o) throws ListaVaziaExcecao, ListaExcecao{
        if (size == 0) {
            throw new ListaVaziaExcecao("A lista está vazia.");
        }

        if (n > size) {
            throw new ListaExcecao("Posição maior que o tamanho da lista.");
        }

        if (size() == capacidade) {
            capacidade = capacidade * 2;

            Object[] novoArray = new Object[capacidade];
            for (int i = 0; i < size(); i++) {
                novoArray[i] = array[i];
            }

            array = novoArray;
        }

        for (int i = size(); i > n - 1; i--) {
            array[i] = array[i-1];
        }

        array[n - 1] = o;
        size++;
    }

    public void swapElements(int n, int q) throws ListaVaziaExcecao, ListaExcecao{
        if (n > size() || q > size()) {
            throw new ListaExcecao("A posição ultrapassa o tamanho da lista.");
        }

        if (size() == 0) {
            throw new ListaVaziaExcecao("A lista está vazia.");
        }

        Object temp = array[n];
        array[n] = array[q];
        array[q] = temp;
    }

    public Object replaceElement(int n, Object o) throws ListaVaziaExcecao, ListaExcecao{
        if (size() == 0) {
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        if (n > size()) {
            throw new ListaExcecao("Posiçaõ ultrapassa tamanho da lista.");
        }

        Object temp = array[n];
        array[n] = o;
        return temp;
    }

    public Object after(int n) throws ListaVaziaExcecao, ListaExcecao{
        if (size() == 0){
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        if (n > size() || n == 0) {
            throw new ListaExcecao("Posição inválida.");
        }

        if (n == size()) {
            throw new ListaExcecao("Ultimo elemento da lista não possui proximo.");
        }

        return array[n+1];
    }

    public Object before(int n) throws ListaVaziaExcecao, ListaExcecao{
        if (size() == 0) {
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        if (n > size() || n == 0) {
            throw new ListaExcecao("Posição inválida.");
        }

        if (n == 1) {
            throw new ListaExcecao("Primeira posição não possui elemento anterior.");
        }

        return array[n-1];
    }

    public Object first() throws ListaVaziaExcecao{
        if (size() == 0) {
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        return array[0];
    }

    public Object last() throws ListaVaziaExcecao{
        if (size() == 0) {
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        return array[size - 1];
    }

    public boolean isFirst(Object o) throws ListaVaziaExcecao{
        if (size() == 0) {
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        return array[0] == o;
    }

    public boolean isLast(Object o) throws ListaVaziaExcecao{
        if (size() == 0) {
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        return array[size - 1] == o;
    }

    public Object remove(int n) {
        if (size() == 0){
            throw new ListaVaziaExcecao("Lista vazia.");
        }

        Object temp = array[n];
        for (int i = n; i < size - 1; i++){
            array[i] = array[i+1];       
        }

        size--;
        return temp;

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(array[i] + " ");
        }
    }


}
