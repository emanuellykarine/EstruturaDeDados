package FilaEncadeada;

public class FilaTeste {
    public static void main(String[] args) {
        FilaEncadeada f1 = new FilaEncadeada();

        System.out.println("A lista está vazia: " + f1.isEmpty());
        System.out.println("Inserindo 3 valores");
        f1.enqueue(6);
        f1.enqueue(10);
        f1.enqueue(4);

        f1.printFila();

        System.out.println("Tamanho: " + f1.size());
        System.out.println("Primeiro da fila: " + f1.first());
        System.out.println("A lista está vazia: " + f1.isEmpty());

        System.out.println("Removendo um valor");
        System.out.println("Removido " + f1.dequeue());
        System.out.println("Tamanho: " + f1.size());
        System.out.println("Removendo um valor");
        System.out.println("Removido " + f1.dequeue());

        f1.printFila();

        System.out.println("Inserindo 2 valores");
        f1.enqueue(3);
        f1.enqueue(2);
        System.out.println("Tamanho: " + f1.size());
        System.out.println("Primeiro da fila: " + f1.first());

        f1.printFila();

        System.out.println("Inserindo 1 valor");
        f1.enqueue(23);
        System.out.println("Tamanho: " + f1.size());
        System.out.println("Primeiro da fila: " + f1.first());

        f1.printFila();
        
        System.out.println("Removido " + f1.dequeue());
        System.out.println("Removido " + f1.dequeue());
        System.out.println("Removido " + f1.dequeue());
        System.out.println("Primeiro da fila: " + f1.first());

        f1.printFila();
    }   
}
