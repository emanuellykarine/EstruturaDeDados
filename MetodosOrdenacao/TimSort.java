package MetodosOrdenacao;
public class TimSort {

    public static int MinRun (int tamanho) { //Comprimento mínimo de uma RUN (subvetor ordenado que compôe o vetor de entrada)
        // Pega os 6 bits mais significativos do tamanho e soma 1 se os bits menos significativos restantes contiverem pelo menos um bit com valor 1
        // Bit menos significativo é o mais à direita e o mais significativo é o mais à esquerda
        int r = 0;
        while (tamanho >= 64) {
            r = (r | tamanho) & 1;
            tamanho >>= 1; // dividi por 2 e deslocando os bits
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
        int minrun = MinRun(n); // vai calcular o tamanho da minimo de cada run
        int inicio = 0;
        int i = 0;
        int fim = 0;

        // Insertion das Runs
        while(inicio < n) { // vai  ordenar com o insertion enquanto tiver elementos para ordenar
            fim = Math.min(inicio+minrun - 1, n-1); // vai evitar que passe o tamanho do array, ou seja min(run ideial, ultimo indice do array)
            InsertionSort(array, inicio, fim); // ordenação da run
            inicio += minrun; // o inicio vai ser incrementado pelo tamanho da minrun a cada passo
        }

        // Merge das Runs
        int tamanho = minrun; // tamanho das primeiras runs que serao mescladas
        int meio;
        while(tamanho < n) {
            inicio = 0;
            while(inicio < n) {
                meio = inicio + tamanho -1;
                fim = Math.min(inicio + (2 * tamanho) - 1, n-1); // vai evitar que passe o tamanho do array, ou seja min(run ideial, ultimo indice do array)
                if (meio < fim) // garante que o intervalo  seja valido e que exista runs para mesclar
                    MergeSort(array, inicio, meio, meio+1, fim);
                inicio += 2 * tamanho;
            }
            tamanho*=2; // tamanho atual das minruns que serão mescladas
        }
    }

    public static void main(String[] args) {
        int[] array = { 45, 2, 60, 14, 37, 8, 29, 51,
                        0, 33, 25, 42, 17, 55, 6, 48,
                        22, 3, 58, 10, 41, 19, 27, 13,
                        5, 36, 59, 1, 50, 12, 34, 7,
                        24, 53, 4, 39, 30, 61, 11, 46,
                        21, 16, 49, 26, 32, 9, 28, 15,
                        38, 20, 57, 23, 31, 54, 47, 18,
                        35, 43, 56, 44, 52, 40, 62, 63,
                        527, 831, 12, 478, 299, 945, 63, 234, 784, 391,
                        105, 860, 712, 9, 456, 387, 619, 274, 558, 101,
                        527, 831, 12, 478, 299, 945, 63, 234, 784, 391,
                        105, 860, 712, 9, 456, 387, 619, 274, 558, 101,
                        334, 701, 43, 865, 220, 17, 900, 533, 678, 288,
                        47, 759, 311, 169, 842, 276, 127, 638, 560, 987,
                        8, 450, 689, 301, 982, 176, 523, 615, 270, 47,
                        139, 764, 377, 209, 945, 532, 189, 874, 420, 68
                };

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

/* Exemplo 1 com 30 elementos
{ 17, 3, 29, 0, 8, 22, 15, 6, 1, 27,
  10, 14, 19, 5, 30, 13, 21, 4, 25, 9,
  11, 7, 2, 20, 18, 23, 16, 28, 12, 24
};

Exemplo 2 com 64 elementos
    45, 2, 60, 14, 37, 8, 29, 51,
    0, 33, 25, 42, 17, 55, 6, 48,
    22, 3, 58, 10, 41, 19, 27, 13,
    5, 36, 59, 1, 50, 12, 34, 7,
    24, 53, 4, 39, 30, 61, 11, 46,
    21, 16, 49, 26, 32, 9, 28, 15,
    38, 20, 57, 23, 31, 54, 47, 18,
    35, 43, 56, 44, 52, 40, 62, 63

Exemplo 3 com 15 elementos
     -2, 7,  15,  -14, 0, 15,  0, 7,
     -7, -4, -13, 5,   8, -14, 12
*/