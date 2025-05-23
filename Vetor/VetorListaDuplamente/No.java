package Vetor.VetorListaDuplamente;

public class No{
    private Object elemento;
    private No proximo;
    private No anterior;


    public No (Object o){
        elemento = o;
    }
    
    public void setElemento(Object o){
        elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setProximo(No o){
        proximo = o;
    }

    public No getProximo(){
        return proximo;
    }

    public void setAnterior(No o){
        anterior = o;
    }

    public No getAnterior(){
        return anterior;
    }
}