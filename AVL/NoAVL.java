import Arvore.ArvoreBinariaPesquisa.No;

public class NoAVL extends No {
    private int FB;

    public NoAVL (No p, Object o, int f){
        chave = o;
        pai = p;
        nFilhos = 0;
        FB = f;
    }

    public void setFB(int f){
        FB = f;
    }

    public int getFB(){
        return FB;
    }
}