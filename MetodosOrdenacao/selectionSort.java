package MetodosOrdenacao;

public class selectionSort{
    public static void main(String[] args){
        int[] array = {45, 2, 60, 14, 37, 8, 29, 51,
            0, 33, 25, 42, 17, 55, 6, 48,
            22, 3, 58, 10, 41, 19, 27, 13,
            5, 36, 59, 1, 50, 12, 34, 7,
            24, 53, 4, 39, 30, 61, 11, 46,
            21, 16, 49, 26, 32, 9, 28, 15,
            38, 20, 57, 23, 31, 54, 47, 18,
            35, 43, 56, 44, 52, 40, 62, 63};

        long tempoInicial = System.currentTimeMillis(); //tempo inicial antes da execução do código
        for (int i = 0; i < array.length; i++){ //Percorre todo o array
            int indiceDoMenor = i; // Recebe o índice do valor atual 
            for (int j = i + 1; j < array.length; j++){ // Começa do numero da frente e vai ate o final do array
                if (array[j] < array[indiceDoMenor]){ //Se valor no índice j for menor que o anterior
                    indiceDoMenor = j; //Indice do menor recebe indice do atual, pois o menor é o atual
                }
            }
            //Depois que acha o menor de todos, traz ele pra frente
            int guarda = array[i]; //Variável guarda recebe valor de comparação inicial que estava no índice i 
            array[i] = array[indiceDoMenor]; //Guarda o menor valor encontrado no indice inicial
            array[indiceDoMenor] = guarda; //Guarda o valor de comparação inicial na posição antiga do menor valor encontrado, os trocando de posição  
        }
        System.out.print("tempo de execução:" + (System.currentTimeMillis() - tempoInicial) + " milisegundos \n"); 
    }
}        
