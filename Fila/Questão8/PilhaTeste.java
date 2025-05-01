package Fila.Questão8;

public class PilhaTeste {
    public static void main(String[] args){
        PilhaComDuasFilas p1 = new PilhaComDuasFilas(5, 0);

        System.out.println("A lista está vazia: " + p1.isEmpty());
        System.out.println("Inserindo 3 valores");
        p1.push(6);
        p1.push(10);
        p1.push(4);

        System.out.println("Tamanho: " + p1.size());
        System.out.println("Primeiro da pilha: " + p1.top());
        System.out.println("A lista está vazia: " + p1.isEmpty());

        System.out.println("Removendo um valor");
        System.out.println("Removido " + p1.pop());
        System.out.println("Tamanho: " + p1.size());
        System.out.println("Removendo um valor");
        System.out.println("Removido " + p1.pop());

        System.out.println("Inserindo 2 valores");
        p1.push(3);
        p1.push(2);
        System.out.println("Tamanho: " + p1.size());
        System.out.println("Primeiro da pilha: " + p1.top());

        System.out.println("Inserindo 1 valor");
        p1.push(23);
        System.out.println("Tamanho: " + p1.size());
        System.out.println("Primeiro da pilha: " + p1.top());
        
        System.out.println("Removido " + p1.pop());
        System.out.println("Removido " + p1.pop());
        System.out.println("Removido " + p1.pop());
        System.out.println("Primeiro da pilha: " + p1.top());
    }
}
