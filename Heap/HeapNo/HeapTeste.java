package HeapNo;

public class HeapTeste {
    public static void main(String[] args) {
        HeapNo heap = new HeapNo(4);

        System.out.println("== Teste de inserção ==");
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);
        heap.insert(1);
        heap.insert(20);
        heap.insert(2); // testando redimensionamento

        System.out.println("Tamanho: " + heap.size());
        System.out.println("Menor valor: " + heap.min());

        System.out.println("\n== Teste de remoção ==");
        System.out.println("Removido: " + heap.removeMin()); // deve ser 1
        System.out.println("Novo mínimo : " + heap.min());
        System.out.println("Tamanho após remoção : " + heap.size());

        System.out.println("\n== Remoções consecutivas ==");
        while (!heap.isEmpty()) {
            System.out.println("Removido: " + heap.removeMin());
        }

        System.out.println("Heap está vazio? : " + heap.isEmpty());
    }
}
