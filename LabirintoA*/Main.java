import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class Main{

    static int inicioX, inicioY; //ponto de inicio do labirinto 
    static int saidaX, saidaY;

    static class Node implements Comparable<Node> {
        int x, y, g, h; //posição x e y, g = distancia, h = heuristica
        List<int[]> caminho;

        Node(int x, int y, int g, int h, List<int[]> cam) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.caminho = new ArrayList<>(cam);
            this.caminho.add(new int[]{x, y});
        }

        @Override
        public int compareTo(Node outro){ //usado no A* pra comparar o valor dos nós pra ordenar pelo g+h
            return Integer.compare(this.g + this.h, outro.g + outro.h); //compara os dois caminhos pra escolher o menor 
        }
    }

    static int heuristica(int x, int y) {
        return Math.abs(x - saidaX) + Math.abs(y - saidaY); //heuristica de manhahattan
    }

    static boolean valido(int x, int y, int[][] lab, boolean[][] vis) { //verifica se está dentro dos limites da matriz, se não foi visitado e se não é uma parede
        return x >= 0 && x < lab.length && y >= 0 && y < lab[0].length &&
               lab[x][y] != 1 && !vis[x][y];
    }


    public static int[][] leituraDAT(String nomeArquivo){
        Path filePath = Paths.get(nomeArquivo);

        try{
            List<String> linhas = Files.readAllLines(filePath);
            int linhasQtd = linhas.size(); //quantidade de linhas da matriz
            int colunasQtd = linhas.get(0).trim().length(); //pega a primeira linha e ver o tamanho dela 

            int[][] matriz = new int[linhasQtd][colunasQtd]; //inicia a matriz 

            for (int i = 0; i < linhasQtd; i++){ //pra cada linha 
                String[] valores = linhas.get(i).trim().split(""); //pega os valores daquela linha

                for (int j = 0; j < colunasQtd; j++){ //passa por todas as colunas da matriz e coloca o valor daquela coluna do arquivo na coluna correspondente na matriz
                    matriz[i][j] = Integer.parseInt(valores[j]);

                    if (matriz[i][j] == 2){ //se o elemento naquela posição for igual a 2 é o inicio do labirinto
                        inicioX = i; //guarda as coordenadas
                        inicioY = j;
                    } else if (matriz[i][j] == 3){
                        saidaX = i;
                        saidaY = j;
                    }
                }
            }

            return matriz;

        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }

    }

    public static String Dijkstra(int[][] labirinto) {
        long tempoInicio = System.currentTimeMillis();
        
        PriorityQueue<Node> fila = new PriorityQueue<>(Comparator.comparingInt(n -> n.g)); //compara pelo valor de g ou seja menor caminho pra ordenar na fila 
        boolean[][] visitado = new boolean[labirinto.length][labirinto[0].length];
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int nodosExplorados = 0;

        fila.offer(new Node(inicioX, inicioY, 0, 0, new ArrayList<>()));

        while (!fila.isEmpty()) {
            Node atual = fila.poll(); //retorna menor caminho 
            nodosExplorados++;

            if (visitado[atual.x][atual.y]) continue;
            visitado[atual.x][atual.y] = true;

            if (labirinto[atual.x][atual.y] == 3) {
                long tempo = System.currentTimeMillis() - tempoInicio;
                return formatarResultado("DIJKSTRA", atual.caminho, atual.g, tempo, nodosExplorados);
            }

            for (int[] dir : direcoes) {
                int nx = atual.x + dir[0];
                int ny = atual.y + dir[1];

                if (valido(nx, ny, labirinto, visitado)) {
                    fila.offer(new Node(nx, ny, atual.g + 1, 0, atual.caminho));
                }
            }
        }

        return "DIJKSTRA: Nenhuma saída encontrada!";
    }

    public static String AStar(int[][] labirinto) {
        long tempoInicio = System.currentTimeMillis();
        
        PriorityQueue<Node> fila = new PriorityQueue<>(); //usar fila de prioridade pq vai pegar o menor valor sempre 
        boolean[][] visitado = new boolean[labirinto.length][labirinto[0].length];// cria uma matriz de boolean marcando se aquele elemento foi visitado ou não
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //pega os 4 caminhos possíveis a partir da posição atual (esquerda, direita, cima, baixo)
        int nodosExplorados = 0; //quantidade de posições exploradas 

        int heur = heuristica(inicioX, inicioY); //calcula heuristica daquele ponto 
        //inicia a fila inserindo o nó inicial e criando o caminho 
        fila.offer(new Node(inicioX, inicioY, 0, heur, new ArrayList<>())); //insere na fila a nova posição 

        while (!fila.isEmpty()) { //funciona um pouco como o bfs, enquanto a fila não estiver vazia
            Node atual = fila.poll(); //remove a posição de menor valor da fila 
            nodosExplorados++; //soma nos nós explorados

            if (visitado[atual.x][atual.y]) continue; //se o nó já tinha sido visitado não marca
            visitado[atual.x][atual.y] = true; // se não tiver sido visitado marca como visitado

            if (labirinto[atual.x][atual.y] == 3) { //se a posição atual for de saída então terminou 
                long tempo = System.currentTimeMillis() - tempoInicio;
                return formatarResultado("A*", atual.caminho, atual.g, tempo, nodosExplorados);
            }

            for (int[] dir : direcoes) { //se não for a saída, pra cada direção calcula as coordenadas do vizinho 
                int nx = atual.x + dir[0]; //soma de x 
                int ny = atual.y + dir[1]; //soma de y

                if (valido(nx, ny, labirinto, visitado)) { //se a nova posição for valida ou seja não tiver sido visitado, não for parede e estiver dentro da matriz
                    int novaHeur = heuristica(nx, ny); //calcula a heuristica desse ponto 
                    fila.offer(new Node(nx, ny, atual.g + 1, novaHeur, atual.caminho)); //e insere a nova posição na fila 
                }
            }
        }

        return "A*: Nenhuma saída encontrada!";
    }

    static String formatarResultado(String algoritmo, List<int[]> caminho, int distancia, 
                                     long tempo, int nodosExplorados) {
        StringBuilder sb = new StringBuilder("\n═══════════════════════════════════\n");
        sb.append("Algoritmo: ").append(algoritmo).append("\n");
        sb.append("Distância: ").append(distancia).append(" passos\n");
        sb.append("Nós explorados: ").append(nodosExplorados).append("\n");
        sb.append("Tempo: ").append(tempo).append("ms\n");
        sb.append("Caminho: ");

        for (int[] pos : caminho) {
            sb.append("(").append(pos[0]).append(",").append(pos[1]).append(") ");
        }

        sb.append("\n═══════════════════════════════════\n");
        return sb.toString();
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o nome do arquivo dat (deve estar na pasta LabirintoA*): ");
        String nomeArquivo = scanner.nextLine();

        int[][] labirinto = leituraDAT(nomeArquivo);

        if (labirinto != null) {
            System.out.println("Labirinto carregado com sucesso!");
            System.out.println("Ponto inicial em: (" + inicioX + ", " + inicioY + ")");

            System.out.println(Dijkstra(labirinto));
            System.out.println(AStar(labirinto));

        } else {
            System.out.println("Falha ao carregar o labirinto.");
        }

        scanner.close();
    
        
    }
}