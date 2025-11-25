import Arvore.ArvoreBinariaPesquisa.ArvoreBP;
import Arvore.ArvoreBinariaPesquisa.NoExcecao;

public class RNClasse extends ArvoreBP implements RNInterface {
    NoRN raiz;
    int tamanho;
    public static final String RESET = "\u001B[0m";
    public static final String PRETO = "\u001B[30m";
    public static final String VERMELHO = "\u001B[31m";

    public RNClasse (Object o) {
        super(o);
        raiz = new NoRN(null, o, false); // raiz é negro
    }

    public void newIntersection(NoRN noAtual, NoRN noInserido){ //atual é o pai do que foi inserido
        while (!isRoot(noAtual) && noAtual.getCor() != false){ //enquanto não chegar na raiz ou o pai não for negro
            NoRN pai = (NoRN) noAtual.getPai();
            NoRN irmao = (pai == null) ? null : (noAtual == (NoRN) pai.getEsquerdo() ? (NoRN) pai.getDireito() : (NoRN) pai.getEsquerdo());

            if (noAtual.getCor() == true){ 
                if (irmao != null && irmao.getCor() == true) { //se pai e irmao forem rubro
                    noAtual.setCor(false); //pai vira negro
                    irmao.setCor(false); //irmao vira negro
                    if (!isRoot(pai)) {
                        pai.setCor(true); //avô vira rubro
                    }
                    
                } else if (irmao == null || (irmao != null && irmao.getCor() == false)) { //nesse caso o pai pode ser qualquer cor ou ele precisa ser rubro ou negro
                    int tipoRotacao = rotation(noAtual, noInserido); 
                    if (tipoRotacao == -1){ //se for rotação simples a direita
                        noAtual.setCor(false); //atual vira negro
                        if ((NoRN) noAtual.getDireito() != null) {
                            ((NoRN) noAtual.getDireito()).setCor(true); //filho direito vira rubro
                        }
                    } else if (tipoRotacao == 1){ //se for rotação simples a esquerda
                        noAtual.setCor(false); //atual vira negro
                        if ((NoRN) noAtual.getEsquerdo() != null) {
                            ((NoRN) noAtual.getEsquerdo()).setCor(true); //filho esquerdo vira rubro
                        }
                    } else if (tipoRotacao == -2 || tipoRotacao == 2){ //dupla a direita ou dupla a esquerda
                        noInserido.setCor(false);
                        if (pai != null){
                            pai.setCor(true);
                        }
                    }
                    break;
                }
            }
            if (pai == null || (NoRN) pai.getPai() == null) {
                raiz.setCor(false); //garante que a raiz é negra
                break;
            }
            noAtual = (NoRN) pai.getPai(); //sobe na árvore pro avô
            noInserido = pai;
        }
    }

    public void newRemoval(NoRN noRemovido, NoRN noSubstituto){ //elemento que eu removi e o que vai ficar no lugar dele
        NoRN pai = (noRemovido == null) ? null :(NoRN) noRemovido.getPai();
        NoRN irmao = (pai == null) ? null :
            (noRemovido == (NoRN) pai.getDireito()
                ? (NoRN) pai.getEsquerdo()
                : (NoRN) pai.getDireito());
        NoRN sobrinhoPerto = null;
        NoRN sobrinhoLonge = null;

        if (pai != null){
            System.out.println("Pai: " + pai.getChave());
        } 
        if (irmao != null){
            System.out.println("Irmão: " + irmao.getChave());
        }

        if (irmao != null){
            if ((NoRN) pai.getEsquerdo() == irmao){
                sobrinhoPerto = (NoRN) irmao.getDireito();
                sobrinhoLonge = (NoRN) irmao.getEsquerdo();
            } else {
                sobrinhoPerto = (NoRN) irmao.getEsquerdo();
                sobrinhoLonge = (NoRN) irmao.getDireito();
            }
        }

        if(sobrinhoPerto != null){
            System.out.println("Sobrinho Perto: " + sobrinhoPerto.getChave());
        }
        if(sobrinhoLonge != null){
            System.out.println("Sobrinho Longe: " + sobrinhoLonge.getChave());
        }
    
        //Situação 1 = rubro -> rubro
        if (noRemovido.getCor() == true && (noSubstituto != null && noSubstituto.getCor() == true)){ // não faz nada só troca de lugar
            System.out.println("situação 1");
            return;
    
        //Situação 2 = negro -> rubro
        } else if (noRemovido.getCor() == false && (noSubstituto != null && noSubstituto.getCor() == true)) { //troca cor do substituto de rubro pra negro
            System.out.println("situação 2");
            noSubstituto.setCor(false);
            return;

        // Situação 3 = negro -> negro
        } else if (noRemovido.getCor() == false && (noSubstituto == null || noSubstituto.getCor() == false)) { //ve os 4 casos
            System.out.println("situação 3");
            situacao3(noRemovido, pai, irmao, sobrinhoPerto, sobrinhoLonge);

        //Situacao 4 = rubro -> negro
        } else {
            System.out.println("situação 4");
            if (noSubstituto != null) {
                noSubstituto.setCor(true); //substituto vira rubro
                situacao3(noRemovido, pai, irmao, sobrinhoPerto, sobrinhoLonge);
            }
        }
    }  
    

    public void situacao3(NoRN noRemovido, NoRN pai, NoRN irmao, NoRN sobrinhoPerto, NoRN sobrinhoLonge){
        //Caso 1 - irmão rubro e pai negro
        if ((pai != null && pai.getCor() == false) && (irmao != null && irmao.getCor() == true)) { 
            irmao.setCor(false); //irmão vira negro
            pai.setCor(true); //pai vira rubro 

            System.out.println("Caso 1");
            if ((NoRN) pai.getDireito() == irmao){
                simpleLeftRotation(pai, irmao); //rotação simples a esquerda 
            } else {
                simpleRightRotation(pai, irmao);
            }

            irmao = (pai == null) ? null :
                (noRemovido == (NoRN) pai.getDireito()
                    ? (NoRN) pai.getEsquerdo()
                    : (NoRN) pai.getDireito());
            sobrinhoPerto = null;
            sobrinhoLonge = null;

            if (irmao != null){
                if ((NoRN) pai.getEsquerdo() == irmao){
                    sobrinhoPerto = (NoRN) irmao.getDireito();
                    sobrinhoLonge = (NoRN) irmao.getEsquerdo();
                } else {
                    sobrinhoPerto = (NoRN) irmao.getEsquerdo();
                    sobrinhoLonge = (NoRN) irmao.getDireito();
                }
            }
        }

        //Caso 2 - irmão e sobrinhos negros
        if ((sobrinhoPerto == null || sobrinhoPerto.getCor() == false) && 
        (sobrinhoLonge == null || sobrinhoLonge.getCor() == false) && 
        (irmao == null || irmao.getCor() == false)) {
            if (irmao != null){
                irmao.setCor(true); //irmão vira rubro
            }

            //Caso 2a - pai negro
            if (pai != null){
                System.out.println("Caso 2a");
                if (pai.getCor() == false) {
                    newRemoval(pai, null);
            
                //Caso 2b - pai rubro (Caso terminal) 
                } else {
                    System.out.println("Caso 2b");
                    pai.setCor(false); //pai vira negro 
                }
            }
            return;
        } 
        
        //Caso 3 - sobrinho perto rubro, sobrinho longe negro e irmão negro
        if ((sobrinhoPerto != null && sobrinhoPerto.getCor() == true) && 
        (irmao == null || irmao.getCor() == false) && 
        (sobrinhoLonge == null || sobrinhoLonge.getCor() == false)) {
            System.out.println("Caso 3");

            sobrinhoPerto.setCor(false); //sobrinho perto vira negro
            if (irmao != null) {
                irmao.setCor(true); //irmão vira rubro
                if ((NoRN) pai.getEsquerdo() == irmao){
                    simpleLeftRotation(irmao, sobrinhoPerto);
                } else {
                    simpleRightRotation(irmao, sobrinhoPerto);
                }

                irmao = (pai == null) ? null :
                    (noRemovido == (NoRN) pai.getDireito()
                        ? (NoRN) pai.getEsquerdo()
                        : (NoRN) pai.getDireito());

                if (irmao != null){
                    if (pai.getDireito() == irmao) {
                        sobrinhoLonge = (NoRN) irmao.getDireito();
                    } else {
                        sobrinhoLonge = (NoRN) irmao.getEsquerdo();
                    }
                }

            }

            // Após isso, cair no caso 4 (abaixo)
        } 

        //Caso 4 - irmão negro e sobrinho longe rubro (Caso terminal)
        if ((irmao == null || irmao.getCor() == false) && 
        (sobrinhoLonge != null && sobrinhoLonge.getCor() == true)) {
            System.out.println("Caso 4");

            if (irmao != null) {
                irmao.setCor(pai.getCor()); //irmão pega a cor do pai
                if (pai.getDireito() == irmao){
                    simpleLeftRotation(pai, irmao);
                } else {
                    simpleRightRotation(pai, irmao);
                }
            }

            pai.setCor(false); //pai vira negro
            if (sobrinhoLonge != null) sobrinhoLonge.setCor(false);
        }
    }
             
    public int rotation(NoRN pai, NoRN filho){
        NoRN avo = (NoRN) pai.getPai();
        int tipoRotacao = 0;

        //se o pai for filho esquerdo do avô e o filho for filho esquerdo do pai
        if ((avo == null || (avo != null && (NoRN) avo.getEsquerdo() == pai)) && (NoRN) pai.getEsquerdo() == filho) {
            System.out.println("rotação simples a direita"); 
            simpleRightRotation(avo, pai); 
            tipoRotacao =  -1; //sinalizar que foi uma rotação simples a direita

        //se o pai for filho direito do avô e o filho for filho direito do pai 
        } else if ((avo == null || (avo != null && (NoRN) avo.getDireito() == pai)) && (NoRN) pai.getDireito() == filho){ 
            System.out.println("rotaçao simples a esquerda");
            simpleLeftRotation(avo, pai);
            tipoRotacao = 1;
        //se o pai for filho esquerdo e o filho for filho direito do pai dupla a direita
        } else if (avo != null && (NoRN) avo.getEsquerdo() == pai && (NoRN) pai.getDireito() == filho) { 
            System.out.println("rotação dupla a direita"); 
            if (pai.getDireito() != null) {
                simpleLeftRotation(pai, (NoRN) pai.getDireito()); //faz primeiro a rotação interna pra esquerda passando o pai e o filho direito dele
            }
            if (avo != null) {
                simpleRightRotation(avo, (NoRN) avo.getEsquerdo()); //faz a rotação externa pra direita com o avô e o filho esquerdo dele
            }
            tipoRotacao = -2;
        
        //se o pai for filho direito e o filho for filho esquerdo do pai dupla a esquerda
        } else if (avo != null && (NoRN) avo.getDireito() == pai && (NoRN) pai.getEsquerdo() == filho){ 
            System.out.println("rotação dupla a esquerda"); 
            if (pai.getEsquerdo() != null) {
                simpleRightRotation(pai, (NoRN) pai.getEsquerdo()); //faz primeiro a rotação interna pra direita passando o pai e o filho esquerdo dele 
            }
            if (avo != null) {
                simpleLeftRotation(avo, (NoRN) avo.getDireito()); //faz a rotação externa pra esquerda com o avô e o filho direito dele
            }
            tipoRotacao = 2;
        }

        System.out.println("rotation: " + tipoRotacao);
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
            newIntersection(noPai, novoNo); //verificar se precisa repintar e fazer rotação
        } else { //inserção do lado direito
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
            newIntersection(noPai, novoNo); //verificar se precisa repintar e fazer rotação
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
                newRemoval(temp, null);
                pai.setEsquerdo(null);
            } else {
                newRemoval(temp, null);
                pai.setDireito(null);
            }
            
        } else if (temp.getDireito() == null && temp.getEsquerdo() != null || temp.getDireito() != null && temp.getEsquerdo() == null) { //tem um filho
            NoRN filho = (NoRN) ((hasLeft(temp)) ? temp.getEsquerdo() : temp.getDireito());
            pai = (NoRN) temp.getPai();

            if (pai == null) { // temp é raiz
                raiz = filho;
                filho.setPai(null);
            } else {
                if (pai.getEsquerdo() == temp) {
                    newRemoval(temp, filho);
                    pai.setEsquerdo(filho);
                } else {
                    newRemoval(temp, filho);
                    pai.setDireito(filho);
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
                    System.out.print("  ");
                } else {
                    System.out.printf("%3s", matriz[i][j]);
                }
            }
            System.out.println();
        }
    }

    protected void montar(Object[][] matriz, NoRN n, int linha, int coluna){
        if (n == null){ //não tem nada mais 
            return;
        }

        String corTexto = n.getCor() ? VERMELHO : PRETO;


        matriz[linha][coluna] = corTexto + n.getChave() + RESET; //coloca o nó na matriz com o fb

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