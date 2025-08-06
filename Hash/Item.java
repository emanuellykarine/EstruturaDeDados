package Hash;

// Hashtable armazena pares chave/valor na tabela hash.
// Em Hashtable, especificamos um objeto que é usado como chave e o valor que queremos associar
// a essa chave. A chave é então hash, e o código hash resultante é usado como o índice 
// no qual o valor é armazenado na tabela.
public class Item {
    private int chave;
    private Object valor;

    public static final Item AVAILABLE = new Item(-1, null); // marcador de espaço removido


    public Item(int chave, Object valor)  {
        this.chave = chave;
        this.valor = valor;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return this.chave;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Object getValor() {
        return this.valor;
    }
}
