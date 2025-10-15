import AVL.NoAVL;
import Arvore.ArvoreBinariaPesquisa.ArvoreBP;

public class RNClasse extends ArvoreBP implements RNInterface {
    NoRN raiz;
    int tamanho;

    public RNClasse (Object o) {
        super(o);
        raiz = new NoRN(null, o, false); // raiz é negro
    }

    public void newIntersection(NoRN noPai, NoRN noFilho){
        NoRN avo = (NoRN) noPai.getPai();
        NoRN tio = noPai == (NoRN) avo.getEsquerdo() ? (NoRN) avo.getDireito() : (NoRN) avo.getEsquerdo();

        if (noPai.getCor() == true){ 
            if (tio.getCor() == true) { //se pai e tio forem rubro
                noPai.setCor(false); //pai vira negro
                tio.setCor(false); //tio vira negro
                avo.setCor(false); //avô vira negro
            } else if (tio.getCor() == false || tio.getChave() == null) {
                rotation(noPai, noFilho);
            }
        }
    }

    public void rotation(NoRN pai, NoRN filho){
        NoRN avo = (NoRN) pai.getPai();

        //se o pai for filho esquerdo do avô e o filho for filho esquerdo do pai
        if ((NoRN) avo.getEsquerdo() == pai && (NoRN) pai.getEsquerdo() == filho) { 
            simpleRightRotation(pai, filho); 

        //se o pai for filho direito do avô e o filho for filho direito do pai 
        } else if ((NoRN) avo.getDireito() == pai && (NoRN) pai.getDireito() == filho){ 
            simpleLeftRotation(pai, filho);

        //se o pai for filho esquerdo e o filho for filho direito do pai dupla a direita
        } else if ((NoRN) avo.getEsquerdo() == pai && (NoRN) pai.getDireito() == filho) { 
            if (pai.getDireito() != null) {
                simpleLeftRotation(pai, (NoRN)pai.getDireito()); //faz primeiro a rotação interna pra esquerda passando o pai e o filho direito dele
            }
            if (avo.getEsquerdo() != null) {
                simpleRightRotation(avo,(NoRN) avo.getEsquerdo()); //faz a rotação externa pra direita com o avô e o filho esquerdo dele
            }
        
        //se o pai for filho direito e o filho for filho esquerdo do pai dupla a esquerda
        } else { 
            if (pai.getEsquerdo() != null) {
                simpleRightRotation(pai,(NoRN)pai.getEsquerdo()); //faz primeiro a rotação interna pra direita passando o pai e o filho esquerdo dele
            }
            if (avo.getDireito() != null) {
                simpleLeftRotation(avo,(NoRN) avo.getDireito()); //faz a rotação externa pra esquerda com o avô e o filho direito dele
            }
        }
    }

    public void simpleRightRotation(NoRN p, NoRN a){
        NoRN v = (NoRN)p.getPai(); //pega o avô

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

        p.setCor(false); //pai vira negro
        ((NoRN) p.getDireito()).setCor(true); //filho direito vira rubro
    }

    public void simpleLeftRotation(NoRN p, NoRN a){
        NoRN v = (NoRN)p.getPai(); //pega o avô

        if (v != null){ //se não é null
            a.setPai(v); //seta o avô como pai do filho
            if (v.getEsquerdo() == p) { //se o pai for filho esquerdo do avô seta o filho como filho esquerdo
                v.setEsquerdo(a);
            } else {
                v.setDireito(a); //senão seta como filho direito
            }
        } else { //se o avô for null é pq o pai é raiz, então agora o raiz é o filho
            a.setPai(null);
            raiz = a;
        }

        if (a.getEsquerdo() != null){ //se o filho tiver filho esquerdo guarda e seta o filho direito do pai como esse filho do a
            NoRN esquerdo = (NoRN)a.getEsquerdo();
            p.setDireito(esquerdo);
            esquerdo.setPai(p);
        } else { //senão seta o filho do pai como null
            p.setDireito(null);
        }

        a.setEsquerdo(p); //filho do filho vai ser o pai
        p.setPai(a); //pai do pai vai ser o filho

        p.setCor(false); //pai vira negro
        ((NoRN) p.getEsquerdo()).setCor(true); //filho esquerdo vira rubro
    }

    public void addChild(Object chave){
        NoRN noPai = (NoRN) treeSearch(raiz, chave);
        NoRN novoNo = new NoRN(noPai, chave, true); // quando insere ele é rubro

        if((int) noPai.getChave() > (int) chave){
            noPai.setEsquerdo(novoNo);
            novoNo.setPai(noPai);
            newIntersection(noPai, novoNo); //verificar se precisa repintar e fazer rotação
        } else { //inserção do lado direito
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
            newIntersection(noPai, novoNo); //verificar se precisa repintar e fazer rotação
        }
        tamanho++;
    }
}