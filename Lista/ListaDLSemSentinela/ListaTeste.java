package Lista.ListaDLSemSentinela;

public class ListaTeste {
    public static void main (String[] args) {
        ListaDuplamenteSemSentinela l1 = new ListaDuplamenteSemSentinela();

        System.out.println("Lista vazia?" + l1.isEmpty());
        System.out.println("Tamanho:" + l1.size());

        System.out.println("Inserindo 2 valores");
        l1.insertFirst("a");
        l1.insertLast("b");
        l1.print();

        System.out.println("Lista vazia?" + l1.isEmpty());
        System.out.println("Tamanho:" + l1.size());
        No zero = l1.first();
        l1.insertAfter(zero, "e");
        l1.print();

        System.out.println("Primeiro da lista: " + l1.first());
        System.out.println("Ultimo da lista: " + l1.last());

        System.out.println("Depois: " + l1.after(zero));
        No ultimo = l1.last();
        System.out.println("Antes: " + l1.before(ultimo));

        System.out.println("Substituir elementos: " + l1.replaceElement(zero, "substituido"));
        l1.print();
        System.out.println("Trocando: ");
        l1.swapElements(zero, ultimo);
        l1.print();

        System.out.println("ultimo é o primeiro? " + l1.isFirst(ultimo));
        System.out.println("primeiro é o ultimo? " + l1.isLast(zero));

        System.out.println("Removendo o primeiro elemento:" + l1.remove(zero));
        l1.print();


    }





}
