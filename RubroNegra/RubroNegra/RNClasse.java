import AVL.NoAVL;
import Arvore.ArvoreBinariaPesquisa.ArvoreBP;

public class RNClasse extends ArvoreBP implements RNInterface {
    NoRN raiz;
    int tamanho;

    public RNClasse (Object o) {
        super(o);
        raiz = new NoRN(null, o, false); // raiz é negro
    }

    public void newIntersection(NoRN noFilho){
        
    }

    public void addChild(Object chave){
        NoRN noPai = (NoRN) treeSearch(raiz, chave);
        NoRN novoNo = new NoRN(noPai, chave, true); // quando insere ele é rubro

        if((int) noPai.getChave() > (int) chave){
            noPai.setEsquerdo(novoNo);
            novoNo.setPai(noPai);
            newIntersection(novoNo); //verificar se precisa repintar e fazer rotação
        } else { //inserção do lado direito
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
            newIntersection(novoNo); //verificar se precisa repintar e fazer rotação
        }
        tamanho++;
    }
}