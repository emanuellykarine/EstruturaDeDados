package Arvore.ArvoreBinariaPesquisa;

public class No {
    protected Object chave;
    protected No pai;
    protected No esquerdo;
    protected No direito;
    protected int nFilhos;

    public No (No p, Object o){
        chave = o;
        pai = p;
        nFilhos = 0;
    }

    public void setNFilhos(int n){
        nFilhos = n;
    }

    public int getNFilhos(){
        return nFilhos;
    }

    public void setChave (Object o){
        chave = o;
    }

    public Object getChave(){
        return chave;
    }

    public void setPai(No n){
        pai = n;
    }

    public No getPai(){
        return pai;
    }

    public void setDireito(No n) {
        direito = n;
    }

    public No getDireito(){
        return direito;
    }

    public void setEsquerdo(No n){
        esquerdo = n;
    }

    public No getEsquerdo(){
        return esquerdo;
    }


}
