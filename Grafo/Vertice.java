import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Object valor;
    private List<Aresta> arestasAdjacentes; //arestas que saem deste vertice

    public Vertice(Object valor){
        this.valor = valor;
        this.arestasAdjacentes = new ArrayList<>();
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void inserirAresta(Aresta a) { 
        if (!arestasAdjacentes.contains(a)){
            this.arestasAdjacentes.add(a);
        }
    }

    public void removerAresta(Aresta a) {
        this.arestasAdjacentes.remove(a);
    }

    public List<Aresta> getArestasAdjacentes() {
        return arestasAdjacentes;
    }
}
