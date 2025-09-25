package Arvore.ArvoreBinariaPesquisa;

public class ArvoreBPTeste {
    public static void main(String[] args) {
        // Cria uma árvore com raiz 50
        ArvoreBP arvore = new ArvoreBP(10);

        // Adicionando nós
        arvore.addChild(5);
        arvore.addChild(15);
        arvore.addChild(2);
        arvore.addChild(8);
        arvore.addChild(22);
        arvore.addChild(25);

        System.out.println("\n===== ÁRVORE IMPRESSA =====");
        arvore.printArvore();

        // Testando os percursos
        System.out.println("\n\n===== Percurso PRE-ORDER =====");
        arvore.preOrder(arvore.root());

        System.out.println("\n===== Percurso IN-ORDER =====");
        arvore.inOrder(arvore.root());

        System.out.println("\n===== Percurso POST-ORDER =====");
        arvore.postOrder(arvore.root());

        // Testando altura da árvore
        System.out.println("\n===== Altura da árvore =====");
        System.out.println("Altura: " + arvore.height(arvore.root()));

        // Testando profundidade de um nó
        No no40 = arvore.treeSearch(arvore.root(), 40);
        System.out.println("\n===== Profundidade =====");
        System.out.println("Profundidade do nó com chave 40: " + arvore.depth(no40));

        // Verificar se é raiz, interno ou externo
        System.out.println("\n===== Verificação de tipo de nó =====");
        System.out.println("É raiz? (50): " + arvore.isRoot(arvore.root()));
        System.out.println("É interno? (30): " + arvore.isInternal(arvore.treeSearch(arvore.root(), 30)));
        System.out.println("É externo? (20): " + arvore.isExternal(arvore.treeSearch(arvore.root(), 20)));

        // Testando remoção de folha
        System.out.println("\n===== Remoção de nó folha (20) =====");
        arvore.remove(20);
        arvore.printArvore();

        // Testando remoção de nó com um filho
        System.out.println("\n===== Remoção de nó com 1 filho (40) =====");
        arvore.remove(30);
        arvore.printArvore();

        // Testando remoção de nó com dois filhos
        System.out.println("\n===== Remoção de nó com 2 filhos (70) =====");
        arvore.remove(70);
        arvore.printArvore();

        // Verificando tamanho
        System.out.println("\n===== Tamanho da árvore =====");
        System.out.println("Tamanho atual: " + arvore.size());
    }
}
