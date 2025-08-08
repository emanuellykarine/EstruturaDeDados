package MetodosOrdenacao;

public class TimSort {

    public static int MinRun (int tamanho) { //Comprimento mínimo de uma RUN (subvetor ordenado que compôe o vetor de entrada)
        // Pega os 6 bits mais significativos do tamanho e soma 1 se os bits menos significativos restantes contiverem pelo menos um bit com valor 1
        // Bit menos significativo é o mais à direita e o mais significativo é o mais à esquerda
        int r = 0; 
        while (tamanho >= 64) {
            r = (r | tamanho) & 1;
            tamanho++;
        }
        return tamanho + r;
    }

    public static void InsertionSort(int[] array, int esquerda, int direita) { //Recebe o array, o inicio (esquerda) e o final desse array (direita)
        for (int i = esquerda + 1; i <= direita; i++) {
            int chave = array[i];
            int j = i - 1;

            while (j >= esquerda && array[j] > chave) {
                array[j+1] = array[j];
                j--;
            }

            array[j+1] = chave;
        }
    }

    public static void MergeSort(int[] array, int esquerda, int meio, int meio2, int direita) {
         int arrayTemp[] = new int[((meio - esquerda) + (direita - meio2) + 2)]; //Array temporário que vai conter os elementos dos dois subarrays, o calculo é feito dessa forma pois 
        //é um array só que foi dividido em dois pedaços ou seja os indice partem desses pedaços
        int i, j, k;
        i = esquerda; //Variável i recebe começo do primeiro subarray
        j = meio2; //Variável j recebe começo do segundo subarray
        k = 0; //Indice do array temporario

        while (i <= meio && j <= direita){ // Enquanto não chegar no fim de nenhum dos subarrays
            if (array[i] < array[j]){ // Se elemento do primeiro subarray for menor do que o primeiro elemento do segundo subarray
                arrayTemp[k++] = array[i++]; //Guarda o menor valor
            } else {
                arrayTemp[k++] = array[j++]; //Guarda o menor valor
            }
        }

        //Chegou no fim do segundo subarray e guardou todos os elementos no array temporário
        while (i <= meio){ //Enquanto não chegar no fim do primeiro subarray
            arrayTemp[k++] = array[i++]; //Guarda o restante dos valores do primeiro subarray
        }

        //Chegou no fim do primeiro subarray e guardou todos os elementos no array temporário
        while (j <= direita){ //Enquanto não chegar no fim do segundo subarray
            arrayTemp[k++] = array[j++]; //Guarda o restante dos valores do segundo subarray
        }

        //direita é o final do array
        for (i = esquerda, j = 0; i <= direita; i++, j++){ //Reinicia os indices, o array temporário agora tem o mesmo tamanho do array inicial
            array[i] = arrayTemp[j]; //Copia todos os elementos ordenados do array temporário para o array inicial
        }
    }

    public static void TimSort(int array[], int n) {

    }

    public static void main(String[] args) {
        int[] array = { -2, 7,  15,  -14, 0, 15,  0, 7,
                      -7, -4, -13, 5,   8, -14, 12 };
        
        int tamanho = array.length;
        for (int i = 0; i < tamanho; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");

        TimSort(array, tamanho);

        for (int i = 0; i < tamanho; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }
}
