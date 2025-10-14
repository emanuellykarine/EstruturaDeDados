import AVL.NoAVL;
import Arvore.ArvoreBinariaPesquisa.ArvoreBP;

public class RNClasse extends ArvoreBP implements RNInterface {
    NoRN raiz;
    int tamanho;

    public RNClasse (Object o) {
        super(o);
        raiz = new NoRN(null, o, false); // raiz é negro
    }

    public void newIntersection(NoRN noPai){
        NoRN pai = (NoRN) noFilho.getPai();
        NoRN avo = (NoRN) pai.getPai();
        NoRN tio = pai == (NoRN) avo.getEsquerdo() ? (NoRN) avo.getDireito() : (NoRN) avo.getEsquerdo();
        
        if (pai.getCor() == true && tio.getCor() == true){ //se pai e tio forem rubro
            pai.setCor(false); //pai vira negro
            tio.setCor(false); //tio vira negro
            avo.setCor(false); //avô vira negro
        }

        //Vê se precisa de rotação
        if (pai.getCor() == true && tio.getCor() == false) { //se pai for rubro e tio negro precisa de rotação
            rotation(avo, pai);
        }
    }

    public void simpleRightRotation(NoRN p, NoRN a){
        NoRN v = (NoRN)p.getPai(); //pega o avô
        
        p.setCor(false); //pai vira negro
        v.setCor(true); //avô vira rubro

        if (v != null){ //se não é null
            a.setPai(v); //seta o avô como pai do filho
            if (v.getEsquerdo() == p) { //se o pai for filho esquerdo do avô seta o filho como filho esquerdo
                v.setEsquerdo(a);
            } else {
                v.setDireito(a); //senão seta como filho direito
            }
        }else {//se o avô for null é pq o pai é raiz
            a.setPai(null);
            raiz = a;
        }

        if (a.getDireito() != null){ //se o filho tiver filho direito guarda e seta o filho esquerdo do pai como esse filho do a
            NoRN direito = (NoRN)a.getDireito();
            p.setEsquerdo(direito);
            direito.setPai(p);
        } else { //senão seta o filho do pai como null
            p.setEsquerdo(null);
        }
        a.setDireito(p); //filho do filho vai ser o pai
        p.setPai(a); //pai do pai vai ser o filho

        
    }


    public void addChild(Object chave){
        NoRN noPai = (NoRN) treeSearch(raiz, chave);
        NoRN novoNo = new NoRN(noPai, chave, true); // quando insere ele é rubro

        if((int) noPai.getChave() > (int) chave){
            noPai.setEsquerdo(novoNo);
            novoNo.setPai(noPai);
            newIntersection(noPai); //verificar se precisa repintar e fazer rotação
        } else { //inserção do lado direito
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
            newIntersection(noPai); //verificar se precisa repintar e fazer rotação
        }
        tamanho++;
    }
}