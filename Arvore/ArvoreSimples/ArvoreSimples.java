package Arvore.ArvoreSimples;
import java.util.Iterator;

public interface ArvoreSimples {
    // Métodos genéricos
    public int size();
    public int height();
    public boolean isEmpty();
    public Iterator elements();
    public Iterator nos();

    //Métodos de acesso
    public No root();
    public No parent(No n);
    public Iterator children(No n);

    //Métodos de consulta
    public boolean isInternal(No n);
    public boolean isExternal(No n);
    public boolean isRoot(No n);
    public int depth(No n);

    //Métodos de atualização
    public Object replace(No n, Object o);
    public void swapElements(No n, No q);

    //Métodos de inserir e remover
    public void addChildren(No n, Object o);
    public Object remove(No n); //Só pode remover se for nó externo e que tenha um pai

}
