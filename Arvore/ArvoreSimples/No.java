package Arvore.ArvoreSimples;

import java.util.ArrayList;

public class No {
    private Object elemento;
    private No pai;
    private ArrayList<No> filhos;

    public No (No p, Object o){
        elemento = o;
        pai = p;
        filhos = new ArrayList<>();
    }

    public void setElemento(Object o){
        elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setPai(No n){
        pai = n;
    }

    public No getPai(){
        return pai;
    }

    public void setFilho(No n){
        filhos.add(n);
    }

    public ArrayList<No> getFilhos() {
        return filhos;
    }

    public int childrenNumber(){
        return filhos.size();
    }

    public void removeChild(No n){
        filhos.remove(n);
    }
}
