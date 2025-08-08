package MetodosOrdenacao;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class mergeSort{
    //Função que mescla os subarrays ordenados
    static void merge(int a[], int i1, int j1, int i2, int j2){ //a[] - array principal | i1 e j1 - indices do primeiro array | i2 e j2 - indices do segundo array
        int arrayTemp[] = new int[((j1 - i1) + (j2 - i2) + 2)]; //Array temporário que vai conter os elementos dos dois subarrays, o calculo é feito dessa forma pois 
        //é um array só que foi dividido em dois pedaços ou seja os indice partem desses pedaços
        int i, j, k;
        i = i1; //Variável i recebe começo do primeiro subarray
        j = i2; //Variável j recebe começo do segundo subarray
        k = 0; //Indice do array temporario

        while (i <= j1 && j <= j2){ // Enquanto não chegar no fim de nenhum dos subarrays
            if (a[i] < a[j]){ // Se elemento do primeiro subarray for menor do que o primeiro elemento do segundo subarray
                arrayTemp[k++] = a[i++]; //Guarda o menor valor
            } else {
                arrayTemp[k++] = a[j++]; //Guarda o menor valor
            }
        }

        //Chegou no fim do segundo subarray e guardou todos os elementos no array temporário
        while (i <= j1){ //Enquanto não chegar no fim do primeiro subarray
            arrayTemp[k++] = a[i++]; //Guarda o restante dos valores do primeiro subarray
        }

        //Chegou no fim do primeiro subarray e guardou todos os elementos no array temporário
        while (j <= j2){ //Enquanto não chegar no fim do segundo subarray
            arrayTemp[k++] = a[j++]; //Guarda o restante dos valores do segundo subarray
        }

        //j2 é o final do array
        for (i = i1, j = 0; i <= j2; i++, j++){ //Reinicia os indices, o array temporário agora tem o mesmo tamanho do array inicial
            a[i] = arrayTemp[j]; //Copia todos os elementos ordenados do array temporário para o array inicial
        }

    }

    //Função recursiva que divide o array ao meio até não poder ser mais dividido, ou seja, até ter apenas um elemento
    static void sort(int a[], int primeiro, int ultimo){
        int meio;
        if (primeiro < ultimo) { //Se o primeiro indice for menor que o ultimo indice, ou seja, enquanto não tiver apenas um elemento
            meio = (primeiro + ultimo) / 2; //Meio recebe o valor da metade do array
            sort(a, primeiro, meio); //Faz a separação da primeira metade
            sort(a, meio+1, ultimo); // Faz a separação da segunda metade
            merge(a, primeiro, meio, meio+1, ultimo); //Ordena e junta as duas metades ordenadas
        }
    } 

    public static void main(String[] args){
        List<Integer> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){ //leitura do arquivo
            String line = br.readLine(); //linha por linha
            
            while (line != null){
                lista.add(Integer.parseInt(line)); //guarda linha na lista transformando para inteiro
                line = br.readLine();     
            }

            int[] array = lista.stream().mapToInt(i -> i).toArray();

            long tempoInicial = System.currentTimeMillis(); //tempo inicial antes da execução do código
            sort(array, 0, array.length - 1); //Primeira chamada do merge Sort

            System.out.print("tempo de execução:" + (System.currentTimeMillis() - tempoInicial) + " milisegundos \n"); 
            for (int i = 0; i < array.length; i++){
                System.out.println(array[i]);
            }
        } catch (IOException e){
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }        
} 