package Sequencia;

public class SequenciaListaDLTeste {
    public static void main(String[] args) {
        try {
            SequenciaListaDL s1 = new SequenciaListaDL();

            System.out.println("Inserindo elementos...");
            s1.insertFirst("C");
            s1.insertFirst("B");
            s1.insertFirst("A");

            System.out.println("Tamanho: " + s1.size()); 
            System.out.println("Elementos:");
            s1.print(); 

            System.out.println("\nElemento na posição 1: " + s1.elemAtRank(1)); // Esperado: B

            System.out.println("\nSubstituindo elemento na posição 1 por X...");
            s1.replaceAtRank(1, "X");
            s1.print(); 

            System.out.println("\nRemovendo elemento na posição 1...");
            s1.removeAtRank(1);
            s1.print(); 

            System.out.println("\nInserindo D no final e E na posição 1...");
            s1.insertLast("D");
            s1.insertAtRank(1, "E"); 
            s1.print();

            System.out.println("\nPrimeiro elemento: " + s1.first().getElemento());
            System.out.println("Último elemento: " + s1.last().getElemento());

            No noE = s1.atRank(1);
            s1.insertBefore(noE, "X");
            s1.insertAfter(noE, "Y");  
            s1.print(); 

            System.out.println("\nTrocando A com D...");
            No noA = s1.atRank(0);
            No noD = s1.atRank(s1.size() - 1);
            s1.swapElements(noA, noD);
            s1.print(); 

            System.out.println("\nRemovendo nó Y diretamente...");
            No noY = s1.atRank(3);
            s1.remove(noY);
            s1.print(); 

            System.out.println("\nRank de C: " + s1.rankOf(s1.atRank(3))); // Esperado: 3

        } catch (SequenciaExcecao e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
