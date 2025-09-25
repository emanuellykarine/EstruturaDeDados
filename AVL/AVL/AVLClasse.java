package AVL;
import Arvore.ArvoreBinariaPesquisa.ArvoreBP;
import Arvore.ArvoreBinariaPesquisa.NoExcecao;
import java.lang.Math;

public class AVLClasse extends ArvoreBP implements AVLInterface{
    NoAVL raiz;
    int tamanho;

    public AVLClasse (Object o) {
        super(o); 
        raiz = new NoAVL(null, o, 0);
    }

    public void newFBIntersection(NoAVL noPai){
        while (noPai.getFB() != 0 && noPai.getPai() != null){ // se o fb do antecessor for 0 pare
            NoAVL atual = noPai; // atual é o antecessor que foi atualizado
            noPai = (NoAVL) noPai.getPai(); // novo pai é o pai do antecessor
            if ((atual == noPai.getEsquerdo())){ // se o atual for filho esquerdo
                noPai.setFB(noPai.getFB() + 1); // soma no fb
            } else {
                noPai.setFB(noPai.getFB() - 1); // se for filho direito subtrai
            }
            if (noPai.getFB() >= 2 || noPai.getFB() <= -2){ // depois que atualiza vê se o fb é 2 ou -2 e chama a rotação passando o fb do pai e do atual
                rotation(noPai, atual); //vai servir pra ver qual tipo de rotação fazer
            }
        }
    }

    public void newFBRemoval(NoAVL noPai){
        while (noPai.getFB() == 0 && noPai.getPai() != null){ // se o fb do antecessor for diferente de 0 pare
            NoAVL atual = noPai; // atual é o antecessor que foi atualizado
            noPai = (NoAVL) noPai.getPai(); // novo pai é o pai do antecessor
            if ((atual == noPai.getEsquerdo())){ // se o atual for filho esquerdo
                noPai.setFB(noPai.getFB() - 1); // subtrai no fb
            } else {
                noPai.setFB(noPai.getFB() + 1); // se for filho direito soma
            }
            if (noPai.getFB() >= 2 || noPai.getFB() <= -2){ // depois que atualiza vê se o fb é 2 ou -2 e chama a rotação passando o fb do pai e do atual
                rotation(noPai, atual); //vai servir pra ver qual tipo de rotação fazer
            }
        }
    }

    public void addChild(Object chave){
        NoAVL noPai = (NoAVL) treeSearch(raiz, chave);
        NoAVL novoNo = new NoAVL(noPai, chave, 0);

        if((int) noPai.getChave() > (int) chave){
            noPai.setEsquerdo(novoNo);
            novoNo.setPai(noPai);
            noPai.setFB(noPai.getFB() + 1); // atualiza fb antecessor e começa a regra
            newFBIntersection(noPai);
        } else { //inserção do lado direito
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
            noPai.setFB(noPai.getFB() - 1); // atualiza fb antecessor e começa a regra 
            newFBIntersection(noPai);
        }
       
        tamanho++;
    }

    public Object remove(Object chave){
        NoAVL temp = (NoAVL) treeSearch(raiz, chave);
        Object remover = temp.getChave();

        if (temp == null){
            throw new NoExcecao("Chave nao existe");
        } else if (temp.getDireito() == null && temp.getEsquerdo() == null) { //não tem filhos
            NoAVL pai = (NoAVL) temp.getPai();
            if (pai == null){
                raiz = null;
            } else if (pai.getEsquerdo() == temp) {
                pai.setEsquerdo(null);
                pai.setFB(pai.getFB() - 1); // atualiza fb antecessor e começa a regra
                newFBRemoval(pai);
            } else {
                pai.setDireito(null);
                pai.setFB(pai.getFB() + 1); // atualiza fb antecessor e começa a regra 
                newFBRemoval(pai);
            }

        } else if (temp.getDireito() == null && temp.getEsquerdo() != null || temp.getDireito() != null && temp.getEsquerdo() == null) { //tem um filho
            NoAVL filho = (NoAVL) ((hasLeft(temp)) ? temp.getEsquerdo() : temp.getDireito());

            NoAVL pai = (NoAVL) temp.getPai();

            if (pai == null) { // temp é raiz
                raiz = filho;
                filho.setPai(null);
            } else {
                if (pai.getEsquerdo() == temp) {
                    pai.setEsquerdo(filho);
                    pai.setFB(pai.getFB() - 1); // atualiza fb antecessor e começa a regra
                    newFBRemoval(pai);
                } else {
                    pai.setDireito(filho);
                    pai.setFB(pai.getFB() + 1); // atualiza fb antecessor e começa a regra 
                    newFBRemoval(pai);
                }
                filho.setPai(pai);
            }
        
        } else { //tem dois filhos
            NoAVL substituto = (NoAVL) sucessor((NoAVL) temp.getDireito());
            Object tempChave = substituto.getChave();
            remove(substituto.getChave());
            temp.setChave(tempChave);
        }

        tamanho--;
        return remover;
    }

    public void rotation(NoAVL p, NoAVL a){
        if (p.getFB() == 2 && a.getFB() > 0){ //fb igual a 2 e sinais iguais simples a direita
            simpleRightRotation(p,a);
        } else if (p.getFB() == -2 && a.getFB() < 0){ //fb igual -2 e sinais iguais simples a esquerda
            simpleLeftRotation(p,a);
        } else if (p.getFB() == 2 && a.getFB() < 0) { //fb igual a 2 e sinais diferentes dupla a direita
            simpleLeftRotation(a,(NoAVL)a.getDireito());
            simpleRightRotation(p,a);
        } else {
            simpleRightRotation(a,(NoAVL)a.getEsquerdo()); // fb igual a -2 e sinais diferentes dupla a esquerda
            simpleLeftRotation(p,a);
        }

    }

    public void simpleRightRotation(NoAVL p, NoAVL a){
        NoAVL v = (NoAVL)p.getPai();
        if (v != null){
            a.setPai(v);
            if (v.getEsquerdo() == p) {
                v.setEsquerdo(a);
            } else {
                v.setDireito(a);
            }
        }else {
            a.setPai(null);
            raiz = a;
        }

        if (a.getDireito() != null){
            NoAVL direito = (NoAVL)a.getDireito();
            p.setEsquerdo(direito);
            direito.setPai(p);
        }  
        a.setDireito(p);
        p.setPai(a);

        int fb_p = p.getFB() - 1 - Math.min(a.getFB(), 0);
        int fb_a = a.getFB() - 1 + Math.max(fb_p, 0);
        p.setFB(fb_p);
        a.setFB(fb_a);
    }

    public void simpleLeftRotation(NoAVL p, NoAVL a){
        NoAVL v = (NoAVL)p.getPai();
        if (v != null){
            a.setPai(v);
            if (v.getEsquerdo() == p) {
                v.setEsquerdo(a);
            } else {
                v.setDireito(a);
            }
        } else {
            a.setPai(null);
            raiz = a;
        }

        if (a.getEsquerdo() != null){
            NoAVL esquerdo = (NoAVL)a.getEsquerdo();
            p.setDireito(esquerdo);
            esquerdo.setPai(p);
        }
        a.setEsquerdo(p);
        p.setPai(a);

        int fb_p = p.getFB() + 1 - Math.min(a.getFB(), 0);
        int fb_a = a.getFB() + 1 + Math.max(fb_p, 0);
        p.setFB(fb_p);
        a.setFB(fb_a);
    }

    //Sobrescrevendo métodos pra trabalhar com a classe NoAVL 
    public NoAVL leftChild(NoAVL n) {
        return ((NoAVL)n.getEsquerdo());
    }

    public NoAVL rightChild(NoAVL n){
        return ((NoAVL)n.getDireito());
    }

    public boolean hasLeft(NoAVL n){
        return leftChild(n) != null;
    }

    public boolean hasRight(NoAVL n){
        return rightChild(n) != null;
    }

    public boolean isExternal(NoAVL n){
        return !hasLeft(n) && !hasRight(n);
    }

    public int height(NoAVL n) {
        if (n == null || isExternal(n)){
            return 0;
        } else {
            int hDireito = 0;
            int hEsquerda = 0;
            try{
                hEsquerda = this.height((NoAVL)n.getEsquerdo());
                hDireito = this.height((NoAVL)n.getDireito()); 
            } catch (Exception erro){
                System.out.println(erro);
            }
            
            
            return 1 + Math.max(hEsquerda, hDireito);
        }
    }

    public void printArvore(){
        int altura = height(raiz); 
        int linhas = altura + 1;
        int colunas = (int) Math.pow(2, linhas) - 1;  //numero total de nós (2^linhas) - 1

        Object[][] matriz = new Object[linhas][colunas];
        
        montar(matriz, raiz, 0, (int) colunas /2); //raiz começa no meio da matriz (colunas/2)
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                if (matriz[i][j] == null){
                    System.out.print("  ");
                } else {
                    System.out.printf("%3s", matriz[i][j]);
                }
            }
            System.out.println();
        }
    }

    protected void montar(Object[][] matriz, NoAVL n, int linha, int coluna){
        if (n == null){ //não tem nada mais 
            return;
        }

        matriz[linha][coluna] = n.getChave() + "[" + n.getFB() + "]"; //coloca o nó na matriz com o fb

        //serve para mostrar onde cada nó vai ser posicionado na arvore, quanto mais desce mais distante fica
        int d = (int) Math.pow(2, matriz.length - linha - 2);

        if (n.getEsquerdo() != null) {
            montar(matriz, (NoAVL) n.getEsquerdo(), linha + 1, coluna - d);
        }

        if (n.getDireito() != null) {
            montar(matriz, (NoAVL) n.getDireito(), linha + 1, coluna + d);
        }

    }
}
