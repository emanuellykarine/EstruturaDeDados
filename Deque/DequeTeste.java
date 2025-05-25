package Deque;

public class DequeTeste {
    public static void main(String args[]){
        DequeArray d1 = new DequeArray(3);

        System.out.println("Deque vazio? " + d1.isEmpty());
        System.out.println("Tamanho: " + d1.size());

        System.out.println("Inserindo");
        d1.insertFirst("a");
        d1.print();
        d1.insertFirst("b");
        d1.print();
        d1.insertLast("l");
        d1.print();
        d1.insertFirst("f");
        d1.print();

        System.out.println("Primeiro: " + d1.first());
        System.out.println("Ultimo: " + d1.last());

        System.out.println("Deque vazio? " + d1.isEmpty());
        System.out.println("Tamanho: " + d1.size());

        System.out.println("Removendo: " + d1.removeFirst());
        d1.print();
        System.out.println("Removendo: " + d1.removeLast());
        d1.print();

        System.out.println("Primeiro: " + d1.first());
        System.out.println("Ultimo: " + d1.last());
    }
}
