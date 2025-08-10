package MetodosOrdenacao;

public class insertionSort {
    public static void main (String[] args) {
       int[] array = { -2, 7,  15,  -14, 0, 15,  0, 7,
                      -7, -4, -13, 5,   8, -14, 12 };

        int tamanho = array.length;

        for (int i = 1; i < tamanho; i++){
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = chave;
        } 
    }
    
}
