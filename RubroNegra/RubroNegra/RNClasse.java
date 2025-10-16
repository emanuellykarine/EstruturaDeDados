import AVL.NoAVL;
import Arvore.ArvoreBinariaPesquisa.ArvoreBP;
import Arvore.ArvoreBinariaPesquisa.NoExcecao;

public class RNClasse extends ArvoreBP implements RNInterface {
    NoRN raiz;
    int tamanho;

    public RNClasse (Object o) {
        super(o);
        raiz = new NoRN(null, o, false); // raiz é negro
    }

    public void newIntersection(NoRN noAtual){ //atual é o pai do que foi inserido
        while (noAtual != raiz || noAtual.getCor() != false){ //enquanto não chegar na raiz ou o pai não for negro
            NoRN pai = (NoRN) noAtual.getPai();
            NoRN irmao = noAtual == (NoRN) pai.getEsquerdo() ? (NoRN) pai.getDireito() : (NoRN) pai.getEsquerdo();

            if (noAtual.getCor() == true){ 
                if (irmao.getCor() == true) { //se pai e irmao forem rubro
                    noAtual.setCor(false); //pai vira negro
                    irmao.setCor(false); //irmao vira negro
                    if (pai != raiz) {
                        pai.setCor(true); //avô vira rubro
                    }
                    
                } else if (irmao.getCor() == false || irmao.getChave() == null) { //nesse caso o pai pode ser qualquer cor ou ele precisa ser rubro ou negro
                    if (rotation(pai, noAtual) == -1){ //se for rotação simples a direita
                        noAtual.setCor(false); //atual vira negro
                        ((NoRN) noAtual.getDireito()).setCor(true); //filho direito vira rubro
                    } else { //se for rotação simples a esquerda
                        noAtual.setCor(false); //atual vira negro
                        ((NoRN) noAtual.getEsquerdo()).setCor(true); //filho esquerdo vira rubro
                    }
                    break;
                }
            }
            noAtual = (NoRN) pai.getPai(); //sobe na árvore pro avô
        }
    }

    public void newRemoval(NoRN noRemovido, NoRN noSubstituto){ //elemento que eu removi e o que vai ficar no lugar dele
        while (true) { 
            NoRN pai = (NoRN) noRemovido.getPai();
            NoRN irmao = noRemovido == (NoRN) pai.getDireito() ? (NoRN) pai.getEsquerdo() : (NoRN) pai.getDireito(); 

            if (noRemovido.getCor() == true && noSubstituto.getCor() == true){ // se for rubro -> rubro Situação 1 só troca de lugar
                break;
            } else if (noRemovido.getCor() == false && noSubstituto.getCor() == true) { //se for negro -> rubro Situação 2 troca cor do substituto de rubro pra negro
                noSubstituto.setCor(false);
                break;
            } else if (noRemovido.getCor() == false && noSubstituto.getCor() == false) { //se for negro -> negro Situação 3 ve os 4 casos
                if (pai.getCor() == false && irmao.getCor() == true) {
                    simpleLeftRotation(pai, irmao); //rotação simples a esquerda 
                    irmao.setCor(false); //irmão vira negro
                    pai.setCor(true); //pai vira rubro
                }
            }

            
        }  
    }

    public int rotation(NoRN pai, NoRN filho){
        NoRN avo = (NoRN) pai.getPai();
        int tipoRotacao = 0;

        //se o pai for filho esquerdo do avô e o filho for filho esquerdo do pai
        if ((NoRN) avo.getEsquerdo() == pai && (NoRN) pai.getEsquerdo() == filho) { 
            simpleRightRotation(pai, filho); 
            tipoRotacao =  -1; //sinalizar que foi uma rotação simples a direita

        //se o pai for filho direito do avô e o filho for filho direito do pai 
        } else if ((NoRN) avo.getDireito() == pai && (NoRN) pai.getDireito() == filho){ 
            simpleLeftRotation(pai, filho);
            tipoRotacao = 1;
        //se o pai for filho esquerdo e o filho for filho direito do pai dupla a direita
        } else if ((NoRN) avo.getEsquerdo() == pai && (NoRN) pai.getDireito() == filho) { 
            if (pai.getDireito() != null) {
                simpleLeftRotation(pai, (NoRN)pai.getDireito()); //faz primeiro a rotação interna pra esquerda passando o pai e o filho direito dele
            }
            if (avo.getEsquerdo() != null) {
                simpleRightRotation(avo,(NoRN) avo.getEsquerdo()); //faz a rotação externa pra direita com o avô e o filho esquerdo dele
                tipoRotacao = -1;
            }
        
        //se o pai for filho direito e o filho for filho esquerdo do pai dupla a esquerda
        } else { 
            if (pai.getEsquerdo() != null) {
                simpleRightRotation(pai,(NoRN)pai.getEsquerdo()); //faz primeiro a rotação interna pra direita passando o pai e o filho esquerdo dele 
            }
            if (avo.getDireito() != null) {
                simpleLeftRotation(avo,(NoRN) avo.getDireito()); //faz a rotação externa pra esquerda com o avô e o filho direito dele
                tipoRotacao = 1;
            }
        }

        return tipoRotacao;
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

    public Object remove(Object chave){
        NoRN temp = (NoRN) treeSearch(raiz, chave);
        Object remover = temp.getChave();
        NoRN pai = null;

        if (temp == null){
            throw new NoExcecao("Chave nao existe");
        } else if (temp.getDireito() == null && temp.getEsquerdo() == null) { //não tem filhos
            pai = (NoRN) temp.getPai();
            if (pai == null){
                raiz = null;
            } else if (pai.getEsquerdo() == temp) {
                pai.setEsquerdo(null);
                newRemoval(temp, null);
            } else {
                pai.setDireito(null);
                newRemoval(temp, null);
            }
            
        } else if (temp.getDireito() == null && temp.getEsquerdo() != null || temp.getDireito() != null && temp.getEsquerdo() == null) { //tem um filho
            NoRN filho = (NoRN) ((hasLeft(temp)) ? temp.getEsquerdo() : temp.getDireito());
            pai = (NoRN) temp.getPai();

            if (pai == null) { // temp é raiz
                raiz = filho;
                filho.setPai(null);
            } else {
                if (pai.getEsquerdo() == temp) {
                    pai.setEsquerdo(filho);
                    newRemoval(temp, filho);
                } else {
                    pai.setDireito(filho);
                    newRemoval(temp, filho);
                }
                filho.setPai(pai);
            }
        
        } else { //tem dois filhos
            NoRN substituto = (NoRN) sucessor((NoRN) temp.getDireito());
            Object tempChave = substituto.getChave();
            remove(substituto.getChave());
            temp.setChave(tempChave);
        }

        tamanho--;
        return remover;
    }

    //Sobrescrevendo métodos pra trabalhar com a classe NoRN 
    public NoRN leftChild(NoRN n) {
        return ((NoRN)n.getEsquerdo());
    }

    public NoRN rightChild(NoRN n){
        return ((NoRN)n.getDireito());
    }

    public boolean hasLeft(NoRN n){
        return leftChild(n) != null;
    }

    public boolean hasRight(NoRN n){
        return rightChild(n) != null;
    }

    public boolean isExternal(NoRN n){
        return !hasLeft(n) && !hasRight(n);
    }

    public int height(NoRN n) {
        if (n == null || isExternal(n)){
            return 0;
        } else {            
            int hEsquerda = this.height((NoRN)n.getEsquerdo());
            int hDireito = this.height((NoRN)n.getDireito()); 
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
                    System.out.print("     ");
                } else {
                    System.out.printf("%5s", matriz[i][j]);
                }
            }
            System.out.println();
        }
    }

    protected void montar(Object[][] matriz, NoRN n, int linha, int coluna){
        if (n == null){ //não tem nada mais 
            return;
        }

        matriz[linha][coluna] = n.getChave() + "[" + n.getCor() + "]"; //coloca o nó na matriz com o fb

        //serve para mostrar onde cada nó vai ser posicionado na arvore, quanto mais desce mais distante fica
        int d = (int) Math.pow(2, matriz.length - linha - 2);

        if (n.getEsquerdo() != null) {
            montar(matriz, (NoRN) n.getEsquerdo(), linha + 1, coluna - d);
        }

        if (n.getDireito() != null) {
            montar(matriz, (NoRN) n.getDireito(), linha + 1, coluna + d);
        }

    }

}