package Hash;
import java.util.Iterator;


public interface Hash {
    public Object findElement(int chave);
    public void insertItem(int chave, Object valor);
    public Object removeElement(int chave);
    public int size();
    public boolean isEmpty();
    public Iterator<Integer> keys();
    public Iterator<Object> elements();
    public void reHash();
    public int hash(int chave);
    //Linear Probing e Hash Duplo
    //Código de dispersão (Hash Code)
    //mapa de compressão

}
