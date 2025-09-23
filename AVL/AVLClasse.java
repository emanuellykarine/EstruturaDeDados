import Arvore.ArvoreBinariaPesquisa.ArvoreBP;

public class AVLClasse implements AVLInterface{
    NoAVL raiz;
    int tamanho;

    public AVLClasse (Object o) {
        raiz = new No(null, o, 0);
        tamanho = 1;
    }

    public void newFBIntersection(No noPai){
        while (noPai.getFB() != 0 && noPai.getPai() != null){ // se o fb do antecessor for 0 pare
            No atual = noPai; // atual é o antecessor que foi atualizado
            noPai = noPai.getPai(); // novo pai é o pai do antecessor
            if ((atual == noPai.getEsquerdo())){ // se o atual for filho esquerdo
                noPai.setFB(noPai.getFB() + 1); // soma no fb
            } else {
                noPai.setFB(noPai.getFB() - 1); // se for filho direito subtrai
            }
            if (noPai.getFB() >= 1 || noPai.getFB() <= -1){ // depois que atualiza vê se o fb é 2 ou -2 e chama a rotação passando o fb do pai e do atual
                rotation(noPai, atual); //vai servir pra ver qual tipo de rotação fazer
            }
        }
    }

    public void newFBRemoval(No noPai){
        while (noPai.getFB() == 0 && noPai.getPai() != null){ // se o fb do antecessor for diferente de 0 pare
            No atual = noPai; // atual é o antecessor que foi atualizado
            noPai = noPai.getPai(); // novo pai é o pai do antecessor
            if ((atual == noPai.getEsquerdo())){ // se o atual for filho esquerdo
                noPai.setFB(noPai.getFB() - 1); // subtrai no fb
            } else {
                noPai.setFB(noPai.getFB() + 1); // se for filho direito soma
            }
            if (noPai.getFB() >= 1 || noPai.getFB() <= -1){ // depois que atualiza vê se o fb é 2 ou -2 e chama a rotação passando o fb do pai e do atual
                rotation(noPai, atual); //vai servir pra ver qual tipo de rotação fazer
            }
        }
    }

    public void addChild(Object chave){
        No noPai = treeSearch(raiz, chave);
        No novoNo = new No(noPai, chave);

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
                noPai.setFB(noPai.getFB() - 1); // atualiza fb antecessor e começa a regra
                newFBRemoval(noPai);
            } else {
                pai.setDireito(null);
                noPai.setFB(noPai.getFB() + 1); // atualiza fb antecessor e começa a regra 
                newFBRemoval(noPai);
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
                    noPai.setFB(noPai.getFB() - 1); // atualiza fb antecessor e começa a regra
                    newFBRemoval(noPai);
                } else {
                    pai.setDireito(filho);
                    noPai.setFB(noPai.getFB() + 1); // atualiza fb antecessor e começa a regra 
                    newFBRemoval(noPai);
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

    public void rotation(No p, No a){
        //rotação simples a direita
        //rotação simples a esquerda
        // rotação dupla
    }
}
