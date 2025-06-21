package Arvore.ArvoreBinariaPesquisa;
import java.util.Iterator;

public interface ArvoreBinariaPesquisa {
    //Métodos genéricos
    public int size();
    public int height(No n);
    public boolean isEmpty();
    public Iterator<Object> elements();
    public Iterator<No> nos();

    //Métodos de acesso
    public No root();
    public No parent(No n);
    public Iterator children(No n);

    //Métodos de consulta
    public boolean isInternal(No n);
    public boolean isExternal(No n);
    public boolean isRoot(No n);
    public int depth(No n);

    //Métodos de inserir e remover
    public void addChild(Object chave);
    public Object remove(Object chave);

    //Métodos de travessia
    public void preOrder(No n);
    public void postOrder(No n);
    public void inOrder(No n);

    //Métodos AB
    public No leftChild(No n);
    public No rightChild(No n);
    public boolean hasLeft(No n);
    public boolean hasRight(No n);
    
    //Método Arvore binaria de pesquisa
    public No treeSearch(No n, Object chave);
}
