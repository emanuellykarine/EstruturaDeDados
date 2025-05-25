package Lista.ListaDLSemSentinela;

public class No {
    private Object elemento;
    private No anterior;
    private No proximo;

    public No (Object o){
        elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setElemento(Object o){
        elemento = o;
    }

    public No getProximo(){
        return proximo;
    }

    public void setProximo(No n){
        proximo = n;
    }

    public No getAnterior(){
        return anterior;
    }

    public void setAnterior(No n){
        anterior = n;
    }
}
