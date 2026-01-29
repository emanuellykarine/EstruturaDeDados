import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main{

    static int inicioX, inicioY; //ponto de inicio do labirinto 

    public static int[][] leituraDAT(String nomeArquivo){
        Path filePath = Paths.get(nomeArquivo);

        try{
            List<String> linhas = Files.readAllLines(filePath);
            int linhasQtd = linhas.size(); //quantidade de linhas da matriz
            int colunasQtd = linhas.get(0).trim().split("\\s+").length; //pega a primeira linha, retira todos os espaços e separa em um array de substrings pra ver quantas colunas tem

            int[][] matriz = new int[linhasQtd][colunasQtd]; //inicia a matriz 

            for (int i = 0; i < linhasQtd; i++){ //pra cada linha 
                String[] valores = linhas.get(i).trim().split("\\s+"); //pega os valores daquela linha

                for (int j = 0; j < colunasQtd; j++){ //passa por todas as colunas da matriz e coloca o valor daquela coluna do arquivo na coluna correspondente na matriz
                    matriz[i][j] = Integer.parseInt(valores[j]);

                    if (matriz[i][j] == 2){ //se o elemento naquela posição for igual a 2 é o inicio do labirinto
                        inicioX = i; //guarda as coordenadas
                        inicioY = j;
                    }
                }
            }

            return matriz;

        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }

    }

    public static String Dijsktra(int[][] labirinto, int inicioX, int inicioY){

    }

    public static String AStar(int[][] labirinto, int inicioX, int inicioY){

    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o nome do arquivo dat (deve estar na pasta LabirintoA*): ");
        String nomeArquivo = scanner.nextLine();

        int[][] labirinto = leituraDAT(nomeArquivo);

        if (labirinto != null) {
            System.out.println("Labirinto carregado com sucesso!");
            System.out.println("Ponto inicial em: (" + inicioX + ", " + inicioY + ")");
        }

        scanner.close();
    
        System.out.println(Dijsktra(labirinto, inicioX, inicioY));
        System.out.println(AStar(labirinto, inicioX, inicioY));
    }
}