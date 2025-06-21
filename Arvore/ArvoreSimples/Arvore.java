package Arvore.ArvoreSimples;
import java.util.Iterator;
import java.util.ArrayList;

public class Arvore implements ArvoreSimples{
    No raiz;
    int tamanho;

    public Arvore (Object o) {
        raiz = new No(null, o);
        tamanho = 1;
    }

    public int size(){
        return tamanho;
    }

    public int height(No n){
        if (isExternal(n)){
            return 0;
        } else {
            int a = 0;
            for (No filho : n.getFilhos()){
                a = Math.max(a, this.height(filho));
            }
            return a + 1;
        }
    }

    public boolean isEmpty(){
        return false;
    }

    public Iterator children(No n){
        return n.getFilhos().iterator();
    }

    public Iterator<Object> elements() {
        ArrayList<Object> elems = new ArrayList<>();
        element(root(), elems);
        return elems.iterator(); 
    }

    public void element(No n, ArrayList<Object> e){
        e.add(n.getElemento());
        Iterator<No> eIterator = children(n);
        while(eIterator.hasNext()){
            element(eIterator.next(), e);
        }
    }

    public Iterator<No> nos() {
        ArrayList<No> nos = new ArrayList<>();
        no(root(), nos);
        return nos.iterator();
    }

    public void no(No n, ArrayList<No> nos){
        nos.add(n);

        Iterator<No> noIterator = children(n);
        while(noIterator.hasNext()){
            no(noIterator.next(), nos);
        }
    }

    public No root(){
        return raiz;
    }

    public No parent(No n){
        return n.getPai();
    }

    public boolean isInternal(No n){
        return n.childrenNumber() > 0;
    }

    public boolean isExternal(No n){
        return n.childrenNumber() == 0;
    }

    public boolean isRoot(No n){
        return n == raiz;
    }

    public int depth(No n){
        if (isRoot(n)){
            return 0;
        } else {
            return 1 + this.depth(n.getPai());
        }
    }

    // public int depth(No n){ forma iterativa
    //     int cont = 0;
    //     while (!isRoot(n)){
    //         cont++;
    //         n = n.getPai();
    //     }
    //     return cont;
    // }

    public Object replace(No n, Object o) {
        Object temp = n.getElemento();
        n.setElemento(o);
        return temp;
    }

    public void swapElements(No n, No q) {
        Object temp = n.getElemento();
        n.setElemento(q.getElemento());
        q.setElemento(temp);
    }

    public void addChild(No n, Object o) {
        No novoNo = new No(n, o);
        n.setFilho(novoNo);
        tamanho++;
    }

    public Object remove(No n) throws NoInvalidoExecao{
        No pai = n.getPai();
        if (pai != null || isExternal(n)){
            pai.removeChild(n);
        } else {
            throw new NoInvalidoExecao("Nó inválido");
        }

        Object o = n.getElemento();
        tamanho--;
        return o;
    }

    public void preOrder(No n){
        System.out.println(n);
        for (No filho: n.getFilhos()){
            this.preOrder(filho);
        }
    }

    public void postOrder(No n){
        for (No filho: n.getFilhos()){
            this.postOrder(filho);
        }

        System.out.println(n);
    }

}
