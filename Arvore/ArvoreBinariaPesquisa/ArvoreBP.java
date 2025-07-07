package Arvore.ArvoreBinariaPesquisa;
import java.util.Iterator;
import java.util.ArrayList;

public class ArvoreBP implements ArvoreBinariaPesquisa{
    No raiz;
    int tamanho;

    public ArvoreBP (Object o) {
        raiz = new No(null, o);
        tamanho = 1;
    }

    public int size(){
        return tamanho;
    }

    public int height(No n) {
        if (n == null || isExternal(n)){
            return 0;
        } else {
            int hEsquerda = this.height(n.getEsquerdo());
            int hDireito = this.height(n.getDireito());
            
            return 1 + Math.max(hEsquerda, hDireito);
        }
    }

    public int depth(No n){
        if (isRoot(n)){
            return 0;
        } else {
            return 1 + this.depth(n.getPai());
        }
    }

    public boolean isEmpty(){
        return this.tamanho == 0;
    }

    public Iterator children(No n){
        ArrayList<No> a = new ArrayList<>();

        if (n.getDireito() != null) {
            a.add(n.getDireito());
        }

        if (n.getEsquerdo() != null) {
            a.add(n.getEsquerdo());
        }

        return a.iterator();
    }

    public Iterator<Object> elements() {
        ArrayList<Object> elemns = new ArrayList<>();
        element(root(), elemns);
        return elemns.iterator();
    }

    public void element(No n, ArrayList<Object> e){
        e.add(n.getChave());
        Iterator<No> eIterator = children(n);
        while (eIterator.hasNext()) {
            element(eIterator.next(), e);
        }
    }

    public Iterator<No> nos(){
        ArrayList<No> nos = new ArrayList<>();
        no(root(), nos);
        return nos.iterator();
    }

    public void no(No n, ArrayList<No> noArray){
        noArray.add(n);

        Iterator<No> noIterator = children(n);
        while (noIterator.hasNext()){
            no(noIterator.next(), noArray);
        }
    }

    public No root(){
        return raiz;
    }

    public No parent(No n){
        return n.getPai();
    }

    public No leftChild(No n) {
        return n.getEsquerdo();
    }

    public No rightChild(No n){
        return n.getDireito();
    }

    public boolean hasLeft(No n){
        return leftChild(n) != null;
    }

    public boolean hasRight(No n){
        return rightChild(n) != null;
    }

    public boolean isInternal(No n){
        return hasLeft(n) || hasRight(n);
    }

    public boolean isExternal(No n){
        return !hasLeft(n) && !hasRight(n);
    }

    public boolean isRoot(No n){
        return n == raiz;
    }

    public No treeSearch(No n, Object chave){
        if (n == null) {
            return null;
        }
        //Procura no lado esquerdo
        if ((int) chave < (int) n.getChave()) {
            if (n.getEsquerdo() == null) {
                return n;
            }
            return treeSearch(n.getEsquerdo(), chave);
        //Caso seja o mesmo valor encontrou
        } else if ((int) chave == (int) n.getChave()){
            return n;
        } else {
        //Procura no lado direito
            if (n.getDireito() == null) {
                return n;
            }
            return treeSearch(n.getDireito(), chave);
        }
    }

    public void addChild(Object chave){
        No noPai = treeSearch(raiz, chave);
        No novoNo = new No(noPai, chave);

        if ((int) noPai.getChave() > (int) chave){
            noPai.setEsquerdo(novoNo);
            novoNo.setPai(noPai);
        } else {
            noPai.setDireito(novoNo);
            novoNo.setPai(noPai);
        }

        tamanho++;
    }

    public Object remove(Object chave){
        No temp = treeSearch(raiz, chave);
        Object remover = temp.getChave();

        if (temp == null){
            throw new NoExcecao("Chave nao existe");
        } else if (temp.getDireito() == null && temp.getEsquerdo() == null) { //não tem filhos
            No pai = temp.getPai();
            if (pai == null){
                raiz = null;
            } else if (pai.getEsquerdo() == temp) {
                pai.setEsquerdo(null);
            } else {
                pai.setDireito(null);
            }
            
        } else if (temp.getDireito() == null && temp.getEsquerdo() != null || temp.getDireito() != null && temp.getEsquerdo() == null) { //tem um filho
            No filho = (hasLeft(temp)) ? temp.getEsquerdo() : temp.getDireito();

            No pai = temp.getPai();

            if (pai == null) { // temp é raiz
                raiz = filho;
                filho.setPai(null);
            } else {
                if (pai.getEsquerdo() == temp) {
                    pai.setEsquerdo(filho);
                } else {
                    pai.setDireito(filho);
                }
                filho.setPai(pai);
            }
        } else { //tem dois filhos
            No substituto = sucessor(temp.getDireito());
            Object tempChave = substituto.getChave();
            remove(substituto.getChave());
            temp.setChave(tempChave);
        }

        tamanho--;
        return remover;
    }

    public No sucessor(No n){
        while (n.getEsquerdo() != null){
            n = n.getEsquerdo();
        }

        return n;
    }

    public void preOrder(No n){
        System.out.println(n.getChave());

        if (n.getEsquerdo() != null){
            preOrder(n.getEsquerdo());
        }

        if (n.getDireito() != null){
            preOrder(n.getDireito());
        }
    }

    public void postOrder(No n) {
        if (n.getEsquerdo() != null){
            postOrder(n.getEsquerdo());
        }

        if (n.getDireito() != null){
          postOrder(n.getDireito());
        }

        System.out.println(n.getChave());
    }

    public void inOrder(No n) {
        if (n.getEsquerdo() != null){
            inOrder(n.getEsquerdo());
        }

        System.out.println(n.getChave());

        if (n.getDireito() != null){
          inOrder(n.getDireito());
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

    protected void montar(Object[][] matriz, No n, int linha, int coluna){
        if (n == null){ //não tem nada mais 
            return;
        }

        matriz[linha][coluna] = n.getChave();

        //serve para mostrar onde cada nó vai ser posicionado na arvore, quanto mais desce mais distante fica
        int d = (int) Math.pow(2, matriz.length - linha - 2);

        if (n.getEsquerdo() != null) {
            montar(matriz, n.getEsquerdo(), linha + 1, coluna - d);
        }

        if (n.getDireito() != null) {
            montar(matriz, n.getDireito(), linha + 1, coluna + d);
        }

    }
}
