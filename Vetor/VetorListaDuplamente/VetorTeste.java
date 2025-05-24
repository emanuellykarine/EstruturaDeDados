package Vetor.VetorListaDuplamente;

public class VetorTeste {
    public static void main(String[] args) {
        VetorEncadeado v1 = new VetorEncadeado();
        
        System.out.println("O vetor está vazio? " + v1.isEmpty());
        
        v1.insertAtRank(0, "eu");
        v1.insertAtRank(1, "amo");
        v1.insertAtRank(2, "edl");
        v1.print();

        v1.insertAtRank(1, "não");
        v1.print();

        System.out.println(v1.elemAtRank(2));
        System.out.println(v1.replaceAtRank(3, "figma"));
        v1.print();
        System.out.println(v1.removeAtRank(1));
        v1.print();
    
        System.out.println("O vetor está vazio? " + v1.isEmpty());
        System.out.println(v1.removeAtRank(0));
        v1.print();
        System.out.println(v1.removeAtRank(1));
        v1.print();
      
        System.out.println("O vetor está vazio? " + v1.isEmpty());
    }
}
