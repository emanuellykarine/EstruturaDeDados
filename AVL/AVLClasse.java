import Arvore.ArvoreBinariaPesquisa.ArvoreBP;

public class AVLClasse implements AVLInterface{
    NoAVL raiz;
    int tamanho;

    public AVLClasse (Object o) {
        raiz = new No(null, o, 0);
        tamanho = 1;
    }

    public void addChild(Object chave){
        No noPai = treeSearch(raiz, chave);
        No novoNo = new No(noPai, chave);

        if((int) noPai.getChave() > (int) chave){
            noPai.setEsquerdo(novoNo);
            novoNo.setPai(noPai);
            while (noPai.getFB() != 0){
                noPai.setFB(getFB() + 1);
                noPai = noPai.getPai();
                if (noPai.getFB() >= 2 || noPai.getFB() <= -2){
                    rotation(noPai.getFB());
                }
            }
        } else {
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
            while (noPai.getFB() != 0){
                noPai.setFB(getFB() - 1);
                noPai = noPai.getPai();
                if (noPai.getFB() >= 2 || noPai.getFB() <= -2){
                    rotation(noPai.getFB());
                }
            }
        }

        tamanho++;
    }
}
