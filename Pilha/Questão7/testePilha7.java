package Questão7;

public class testePilha7 {

    public static void main (String args[]) {
        PilhaArray p1 = new PilhaArray(4, 0);
        PilhaArray p2 = new PilhaArray(5, 0);

        p1.push(5);
        p1.push(3);
        p1.push(7);  

        p2.push(1);
        p2.push(6);
        p2.push("algo");
        
        System.out.println("Topo p1: " + p1.top());
        System.out.println("Topo p2: " + p2.top());

        System.out.println("Tamanho p1:" + p1.size());
        System.out.println("Tamanho p2:" + p2.size());

        p1.adicionaPilha(p2);
        
        System.out.println("Topo p1: " + p1.top());
        System.out.println("Topo p2: " + p2.top());

        System.out.println("Tamanho p1:" + p1.size());
        System.out.println("Tamanho p2:" + p2.size());
    }
    
}
