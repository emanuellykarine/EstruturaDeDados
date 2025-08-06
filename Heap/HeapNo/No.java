package HeapNo;

public class No {
    private No pai;
    private No esquerdo;
    private No direito;
    private Object valor;

    public No (No p, Object o){
        pai = p;
        valor = o;
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

    public void setValor(Object o){
        valor = o;
    }

    public Object getValor(){
        return valor;
    }


}
