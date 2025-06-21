package Arvore.ArvoreSimples;
import java.util.Iterator;

public interface ArvoreSimples {
    // Métodos genéricos
    public int size();
    public int height(No n);
    public boolean isEmpty();
    public Iterator<Object> elements(); //retorna um iterador com todos os elementos da árvore
    public Iterator<No> nos(); //retorna um iterador com todos os nós

    //Métodos de acesso
    public No root();
    public No parent(No n);
    public Iterator children(No n); //retorna um iterador com os filhos do nó

    //Métodos de consulta
    public boolean isInternal(No n);
    public boolean isExternal(No n);
    public boolean isRoot(No n);
    public int depth(No n);

    //Métodos de atualização
    public Object replace(No n, Object o);
    public void swapElements(No n, No q);

    //Métodos de inserir e remover
    public void addChild(No n, Object o);
    public Object remove(No n); //Só pode remover se for nó externo e que tenha um pai

    //Métodos de travessia
    public void preOrder(No n);
    public void postOrder(No n);
}
