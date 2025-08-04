package Hash;

public class Item {
    private int chave;
    private Object valor;

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
