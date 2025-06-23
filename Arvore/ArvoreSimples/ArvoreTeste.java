package Arvore.ArvoreSimples;

public class ArvoreTeste {
    public static void main(String[] args) {
        // Cria uma árvore com raiz "A"
        Arvore arvore = new Arvore("A");

        // Adiciona filhos da raiz
        No raiz = arvore.root();
        arvore.addChild(raiz, "B");
        arvore.addChild(raiz, "C");
        arvore.addChild(raiz, "D");

        // Adiciona filhos de B
        No noB = raiz.getFilhos().get(0);
        arvore.addChild(noB, "E");
        arvore.addChild(noB, "F");

        // Adiciona filhos de D
        No noD = raiz.getFilhos().get(2);
        arvore.addChild(noD, "G");

        // ==============================
        System.out.println("===== Percurso PRÉ-ORDEM =====");
        arvore.preOrder(raiz);

        System.out.println("\n===== Percurso PÓS-ORDEM =====");
        arvore.postOrder(raiz);

        // ==============================
        System.out.println("\n===== Teste de altura =====");
        System.out.println("Altura da árvore: " + arvore.height(raiz));

        // ==============================
        System.out.println("\n===== Teste de profundidade =====");
        No noF = noB.getFilhos().get(1);
        System.out.println("Profundidade do nó F: " + arvore.depth(noF));

        // ==============================
        System.out.println("\n===== Verificação de nós =====");
        System.out.println("É raiz (A)? " + arvore.isRoot(raiz));
        System.out.println("É interno (B)? " + arvore.isInternal(noB));
        System.out.println("É externo (G)? " + arvore.isExternal(noD.getFilhos().get(0)));

        // ==============================
        System.out.println("\n===== Teste de swapElements =====");
        System.out.println("Antes da troca A <-> D:");
        arvore.preOrder(raiz);

        No noD_2 = raiz.getFilhos().get(2); // Nó D
        arvore.swapElements(raiz, noD_2);

        System.out.println("Depois da troca A <-> D:");
        arvore.preOrder(raiz);

        // ==============================
        System.out.println("\n===== Teste de replace =====");
        System.out.println("Substituindo valor de C por 'X':");
        No noC = raiz.getFilhos().get(1);
        arvore.replace(noC, "X");
        arvore.preOrder(raiz);

        // ==============================
        System.out.println("\n===== Teste de remoção =====");
        System.out.println("Removendo o nó G:");
        arvore.remove(noD.getFilhos().get(0));
        arvore.preOrder(raiz);

        // ==============================
        System.out.println("\n===== Tamanho da árvore =====");
        System.out.println("Tamanho atual: " + arvore.size());
    }
}
