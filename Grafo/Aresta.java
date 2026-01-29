package Grafo;

public class Aresta {
    private Vertice verticeOrigem;
    private Vertice verticeDestino;
    private Object valor;

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino, Object valor) {
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
        this.valor = valor;
    }

    public Vertice getVerticeOrigem() {
        return this.verticeOrigem;
    }

    public Vertice getVerticeDestino() {
        return this.verticeDestino;
    }

    public Object getValor() {
        return this.valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}