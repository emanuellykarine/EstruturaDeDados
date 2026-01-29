import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GrafoClasse {
    private Vector<Vertice> listaVertices;

    public GrafoClasse(){
        this.listaVertices = new Vector<>();
    }

    public void inserirVertice(Object valor){
        Vertice v = new Vertice(valor);
        this.listaVertices.add(v);
    }

    public void inserirAresta(int origem, int destino, Object valor){
        Vertice verticeOrigem = this.listaVertices.get(origem);
        Vertice verticeDestino = this.listaVertices.get(destino);

        Aresta a = new Aresta(verticeOrigem, verticeDestino, valor);
        verticeOrigem.inserirAresta(a);
        verticeDestino.inserirAresta(a);
    }

    public Vertice[] finalVertices(Aresta a){
        return new Vertice[]{a.getVerticeOrigem(), a.getVerticeDestino()};
    }

    public Vertice oposto(Vertice v, Aresta a){
        if (a.getVerticeDestino().equals(v)){
            return a.getVerticeOrigem();
        } else if (a.getVerticeOrigem().equals(v)){
            return a.getVerticeDestino();
        } else {
            return null;
        }
    }

    public boolean saoAdjacentes(Vertice v, Vertice w){
        for (Aresta a: v.getverticesAdjacentes()){
            if (a.getVerticeDestino().equals(w) || a.getVerticeOrigem().equals(w)){
                return true;
            }
        }

        return false;
    }

    public void substituirVertice(Vertice v, Object valor) {
        v.setValor(valor);
    }

    public void substituirAresta(Aresta a, Object valor) {
        a.setValor(valor);
    }


    public Object removerVertice(Vertice v) {
        Object valor = v.getValor()/

        for (Aresta a: v.getArestasAdjacentes()) {
            Vertice oposto = this.oposto(v, a);
            if (oposto != null) {
                oposto.removerAresta(a);
            }
        }

        this.listaVertices.remove(v);
        return valor;
    }

    public Object removerAresta(Aresta a){
        Object valor = a.getValor();
        Vertice origem = a.getVerticeOrigem();
        Vertice destino = a.getVerticeDestino();

        origem.removerAresta(a);
        destino.removerAresta(a);

        return valor;
    }

    public List<Vertices> vertices(){
        return new ArrayList<>(this.listaVertices);
    }

    public List<Arestas> arestas(){
        List<Arestas> arestass = new ArrayList<>();
        for (Vertice v: this.listaVertices){
            for (Aresta a: v.getArestasAdjacentes()){
                if (!arestass.contains(a)){
                    arestass.add(a);
                }
            }
        }

        return arestass;
    }

    public int grau(Vertice v){ //quantidade de arestas adjacentes
        return v.getArestasAdjacentes().size();
    }

    public int ordem(){ // quantidade de vértices
        return this.listaVertices.size();
    }

    // public ArrayList<Aresta> arestasIncidentes(Vertice v) {
    //     ArrayList<Aresta> incidentes = new ArrayList<>();

    //     int idx = vertices.indexOf(v);
    //     if (idx == -1) {
    //         return incidentes;
    //     }

    //     // linha
    //     for (int j = 0; j < matrizAdj.length; j++) {
    //         if (matrizAdj[idx][j] != null) {
    //             incidentes.add(matrizAdj[idx][j]);
    //         }
    //     }

    //     // coluna
    //     for (int i = 0; i < matrizAdj.length; i++) {
    //         if (matrizAdj[i][idx] != null && !incidentes.contains(matrizAdj[i][idx])) {
    //             incidentes.add(matrizAdj[i][idx]);
    //         }
    //     }

    //     return incidentes;
    // }

    public List<Object> dfs(){ //método de busca depth (profundidade) usa pilha
        List<Object> visitados = new ArrayList<>(); //visitados pra retornar
        if (listaVertices.isEmpty()) {
            return visitados;
        }

        Vertice inicio = listaVertices.get(0); //começa sempre do inicio
        PilhaArray pilha = new PilhaArray(); //cria uma pilha

        pilha.push(inicio);
        inicio.visitado(true);

        while (!pilha.isEmpty()){
            Vertice atual = pilha.pop();
            visitados.add(atual.getValor());

            for (Aresta a : atual.getArestasAdjacentes()){
                Vertice vizinho = oposto(atual, a);
                if (vizinho != null && !vizinho.visitado()){
                    pilha.push(vizinho);
                    vizinho.visitado(true);
                }
            }
        }

        return visitados; 
    }

    public List<Object> bfs(){ //método de busca breadth (largura) usa fila
        List<Object> visitados = new ArrayList<>();
        if (listaVertices.isEmpty()) {
            return visitados;
        }

        Vertice inicio = listaVertices.get(0);
        FilaArray fila = new FilaArray();

        fila.enqueue(inicio);
        inicio.visitado(true);

        while (!fila.isEmpty()){
            Vertice atual = fila.dequeue();
            visitados.add(atual.getValor());

            for (Aresta a: atual.getArestasAdjacentes()){
                Vertice vizinho = oposto(atual, a);
                if (vizinho != null && !vizinho.visitado()) {
                    fila.enqueue(vizinho);
                    vizinho.visitado(true);
                }
            }
        }
        return visitados; 
    }
}