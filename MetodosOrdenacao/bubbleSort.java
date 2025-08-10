package MetodosOrdenacao;

public class bubbleSort{
    public static void main(String[] args){
    
    int[] lista = {45, 2, 60, 14, 37, 8, 29, 51,
    0, 33, 25, 42, 17, 55, 6, 48,
    22, 3, 58, 10, 41, 19, 27, 13,
    5, 36, 59, 1, 50, 12, 34, 7,
    24, 53, 4, 39, 30, 61, 11, 46,
    21, 16, 49, 26, 32, 9, 28, 15,
    38, 20, 57, 23, 31, 54, 47, 18,
    35, 43, 56, 44, 52, 40, 62, 63};

    long tempoInicial = System.currentTimeMillis(); //tempo inicial antes da execução do código
    for (int i = 0; i < lista.length - 1; i++){ //Vai até o antepenultimo
        boolean troca = false; //Não teve trocas
        for (int j = 0; j < lista.length - i - 1; j++){ // Vai ate o tamanho menos i menos 1, por que depois que ja houve as trocas, os maiores ja vao ser empurrados pro final
            if (lista[j] > lista[j+1]){ // Se elemento no índice j for maior do que o elemento no índice j+1, ou seja do que o elemento da frente 
                int guarda = lista[j]; // Recebe maior valor
                lista[j] = lista[j+1]; // Guarda o menor no lugar do maior
                lista[j+1] = guarda; // Guarda o maior no lugar do menor, ou seja na frente do menor
                troca = true; // Troca para true pra informar que houve uma troca
            }
        }

        if (!troca){
            break;
        }
    }
    System.out.print("tempo de execucao:" + (System.currentTimeMillis() - tempoInicial) + " milisegundos \n"); 

}
}

// https://www.geeksforgeeks.org/bubble-sort-algorithm/