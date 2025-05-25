package Lista.ListaComArray;

public class ListaTeste {
    public static void main (String[] args) {
        ListaArray l1 = new ListaArray(3);

        System.out.println("Lista vazia?" + l1.isEmpty());
        System.out.println("Tamanho:" + l1.size());

        System.out.println("Inserindo 2 valores");
        l1.insertFirst("a");
        l1.insertLast("b");
        l1.print();

        System.out.println("Lista vazia?" + l1.isEmpty());
        System.out.println("Tamanho:" + l1.size());
        l1.insertAfter(0, "e");
        l1.print();

        System.out.println("Primeiro da lista: " + l1.first());
        System.out.println("Ultimo da lista: " + l1.last());

        System.out.println("Depois: " + l1.after(1));
        System.out.println("Antes: " + l1.before(2));

        System.out.println("Substituir elementos: " + l1.replaceElement(1, "substituido"));
        l1.print();
        System.out.println("Trocando: ");
        l1.swapElements(1, 2);
        l1.print();

        System.out.println("2 é o primeiro? " + l1.isFirst(1));
        System.out.println("1 é o ultimo? " + l1.isLast(0));

        System.out.println("Removendo o 2:" + l1.remove(1));
        l1.print();


    }





}
