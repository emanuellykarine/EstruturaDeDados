package MetodosOrdenacao;
public class timSort {

    public static int MinRun (int tamanho) { //Comprimento mínimo de uma RUN (subvetor ordenado que compôe o vetor de entrada)
        // Pega os 6 bits mais significativos do tamanho e soma 1 se os bits menos significativos restantes contiverem pelo menos um bit com valor 1
        // Bit menos significativo é o mais à direita e o mais significativo é o mais à esquerda
        int r = 0;
        while (tamanho >= 64) {
            r = (r | tamanho) & 1; //Olha se o ultimo bit é 1
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
        int fim = 0;

        // Insertion das Runs
        while(inicio < n) { // vai  ordenar com o insertion enquanto tiver elementos para ordenar
            fim = Math.min(inicio+minrun - 1, n-1); // vai evitar que passe o tamanho do array, ou seja min(run ideial, ultimo indice do array)
            InsertionSort(array, inicio, fim); // ordenação da run
            inicio += minrun; // o inicio vai ser incrementado pelo tamanho da minrun a cada passo
        }
        
        if (minrun == n) // se for menor do que 64 ele ja vai estar ordenado pelo insertion, pq la no metodo minrun ele só altera o tamanho das run se for n >= 64, caso contrário minrun == n
            return;

        // Merge das Runs
        int tamanho = minrun; // tamanho das primeiras runs que serao mescladas
        int meio;
        while(tamanho < n) {
            inicio = 0; // vai percorrer o array a partir do inicio dele e ao longo dos merges vai atualizando
            while(inicio < n) {
                meio = inicio + tamanho -1; // meio é o fim da primeira run que sera mesclada
                fim = Math.min(inicio + (2 * tamanho) - 1, n-1); // vai evitar que passe o tamanho do array, ou seja min(run ideial, ultimo indice do array)
                if (meio < fim) // garante que o intervalo  seja valido e que exista runs para mesclar
                    MergeSort(array, inicio, meio, meio+1, fim);
                inicio += 2 * tamanho;
            }
            tamanho*=2; // tamanho atual das minruns que serão mescladas
        }
    }

    public static void main(String[] args) {
        int[] array = { -2, 7,  15,  -14, 0, 15,  0, 7,
                        -7, -4, -13, 5,   8, -14, 12
                        };

        int tamanho = array.length;
        for (int i = 0; i < tamanho; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");

        long tempoInicial = System.currentTimeMillis();

        TimSort(array, tamanho);

        long tempoFinal = System.currentTimeMillis();

        for (int i = 0; i < tamanho; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");

        System.out.println("Tempo de execução em segundos: " + (tempoFinal - tempoInicial) / 1000);
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

Exemplo 4 com 100 elementos
{
    95, 50, 46, 89, 29, 76, 62, 7, 97, 53,
    2, 10, 91, 83, 67, 59, 60, 88, 23, 20,
    11, 78, 100, 96, 44, 81, 68, 5, 37, 24,
    31, 48, 69, 74, 64, 70, 84, 55, 86, 34,
    92, 15, 40, 57, 33, 30, 36, 39, 43, 73,
    41, 35, 79, 71, 4, 82, 38, 58, 25, 56,
    51, 65, 13, 28, 45, 42, 72, 49, 3, 19,
    6, 27, 90, 17, 32, 99, 18, 47, 77, 14,
    66, 9, 85, 22, 94, 63, 75, 26, 12, 87,
    1, 93, 80, 21, 54, 61, 8, 52, 98, 16
}
    */
