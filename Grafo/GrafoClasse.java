import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//estrutura só pra fins de estudo, não foi testada
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
        Object valor = v.getValor()

        for (Aresta a: v.getArestasAdjacentes()) {
            Vertice oposto = this.oposto(v, a);
            if (oposto != null) {
                oposto.removerAresta(a);
            }
        }

        this.listaVertices.remove(v);
        return valor;
    }


    //prova 4 - grafo 
    // public void removerVertice(Vertice v) {

    //     // 1) remover todas as arestas incidentes em v
    //     for (Vertice u : Vertices) {
    //         u.VerticesAdjacentes.remove(v);
    //     }

    //     // 2) remover o próprio vértice do grafo
    //     Vertices.remove(v);
    // }

    // public int grau(Vertice v) {
    //     return v.VerticesAdjacentes.size();
    // }

    //--------------------------------------------------------
    //3a prova 
    // public ArrayList<Aresta> arestasIncidentes(Vertice v) {
    //     ArrayList<Aresta> arestasInc = new ArrayList<>();

    //     int idx = vertices.indexOf(v);
    //     if (idx == -1) return arestasInc;

    //     for (int j = 0; j < matrizAdj[idx].length; j++) {
    //         if (matrizAdj[idx][j] != null) {
    //             arestasInc.add(matrizAdj[idx][j]);
    //         }
    //     }

    //     return arestasInc;
    // }

    // public int grau(Vertice v) {
    //     return arestasIncidentes(v).size();
    // }

    //----------------------------------------

    //recuperção
    // public void inserirVertice(Vertice v) {
    //     vertices.add(v);
    //     qtdVertices++;

    //     // cria nova matriz maior
    //     Vector<Arestas> novaMatriz[][] = new Vector[qtdVertices][qtdVertices];

    //     // copia os dados antigos
    //     if (matrizAdj != null) {
    //         for (int i = 0; i < qtdVertices - 1; i++) {
    //             for (int j = 0; j < qtdVertices - 1; j++) {
    //                 novaMatriz[i][j] = matrizAdj[i][j];
    //             }
    //         }
    //     }

    //     // inicializa novas posições
    //     for (int i = 0; i < qtdVertices; i++) {
    //         for (int j = 0; j < qtdVertices; j++) {
    //             if (novaMatriz[i][j] == null) {
    //                 novaMatriz[i][j] = new Vector<Arestas>();
    //             }
    //         }
    //     }

    //     matrizAdj = novaMatriz;
    // }

    //---------------------------------------

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

   
    public List<Object> dfs() {
        List<Object> visitados = new ArrayList<>();

        for (Vertice v : listaVertices) {
            v.visitado(false);
        }

        if (listaVertices.isEmpty()) return visitados;

        Vertice inicio = listaVertices.get(0);
        PilhaArray pilha = new PilhaArray();

        pilha.push(inicio);
        inicio.visitado(true);

        while (!pilha.isEmpty()) {
            Vertice atual = pilha.pop();
            visitados.add(atual.getValor());

            for (Aresta a : atual.getArestasAdjacentes()) {
                Vertice vizinho = oposto(atual, a);
                if (vizinho != null && !vizinho.visitado()) {
                    pilha.push(vizinho);
                    vizinho.visitado(true);
                }
            }
        }

        return visitados;
    }

    public List<Object> bfs() {
        List<Object> visitados = new ArrayList<>();

        for (Vertice v : listaVertices) {
            v.visitado(false);
        }

        if (listaVertices.isEmpty()) return visitados;

        Vertice inicio = listaVertices.get(0);
        FilaArray fila = new FilaArray();

        fila.enqueue(inicio);
        inicio.visitado(true);

        while (!fila.isEmpty()) {
            Vertice atual = fila.dequeue();
            visitados.add(atual.getValor());

            for (Aresta a : atual.getArestasAdjacentes()) {
                Vertice vizinho = oposto(atual, a);
                if (vizinho != null && !vizinho.visitado()) {
                    fila.enqueue(vizinho);
                    vizinho.visitado(true);
                }
            }
        }

        return visitados;
    }


    //como copiar uma matriz 

}