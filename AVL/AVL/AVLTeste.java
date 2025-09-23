package AVL;

public class AVLTeste{
    public static void main(String[] args){
        AVLClasse avl = new AVLClasse(10);

        avl.addChild(5);
        avl.addChild(15);
        avl.addChild(2);
        avl.addChild(8);
        avl.addChild(22);

        avl.printArvore();

        avl.addChild(25);

        avl.printArvore();

        avl.remove(5);
        
    }
}