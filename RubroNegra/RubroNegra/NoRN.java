import Arvore.ArvoreBinariaPesquisa.No;

public class NoRN extends No {
    private boolean cor; // true para vermelho, false para preto

    public NoRN(No p, Object o, boolean c){
        super(p, o);
        cor = c;
    }

    public void setCor(boolean c){
        cor = c;
    }

    public boolean getCor(){
        return cor;
    }
}
