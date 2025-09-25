package AVL;

public class AVLTeste{
    public static void main(String[] args){
        AVLClasse avl = new AVLClasse(10);

        avl.addChild(5);
        // avl.printArvore();
        System.out.println();
        avl.addChild(15);
        // avl.printArvore();
        System.out.println();
        avl.addChild(2);
        // avl.printArvore();
        System.out.println();
        avl.addChild(8);
        // avl.printArvore();
        System.out.println();
        avl.addChild(22);

        // avl.printArvore();
        System.out.println();

        avl.addChild(25);

        // avl.printArvore();
        System.out.println();
        avl.remove(5);

        // avl.printArvore();
        System.out.println();
        

    }
}