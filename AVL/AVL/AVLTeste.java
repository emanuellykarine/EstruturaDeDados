package AVL;

public class AVLTeste{
    public static void main(String[] args){

        System.out.println("AVL 1 Rotação simples a esquerda");
        AVLClasse avl = new AVLClasse(10);
        avl.addChild(5);
        avl.addChild(15);
        avl.addChild(2);
        avl.addChild(8);
        avl.addChild(22);

        avl.printArvore();
        System.out.println();

        avl.addChild(25);
        avl.printArvore();
        System.out.println();

        avl.remove(5);
        avl.printArvore();
        System.out.println();

        System.out.println("AVL 2 Rotação dupla a direita");
        AVLClasse avl2 = new AVLClasse(50);
        avl2.addChild(20);
        avl2.addChild(90);
        avl2.addChild(10);
        avl2.addChild(40);

        avl2.printArvore();
        System.out.println();
        avl2.addChild(30);
    
        avl2.printArvore();
        System.out.println();

        System.out.println("AVL 3 Rotação dupla a esquerda");
        AVLClasse avl3 = new AVLClasse(50);
        avl3.addChild(20);
        avl3.addChild(80);
        avl3.addChild(70);
        avl3.addChild(90);

        avl3.printArvore();
        System.out.println();
        avl3.addChild(60);
    
        avl3.printArvore();
    }
}